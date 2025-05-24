package bams;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/transfer")
public class Transfer extends HttpServlet {

	String balancesql = "SELECT balance FROM accounts WHERE account_id = ?";
	String debitsql = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
	String creditsql = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
	String insertsql = "INSERT INTO transcations(from_acc, to_acc, amount) VALUES(?, ?, ?)";

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		int fromaccountid = Integer.parseInt(req.getParameter("from"));
		int toaccountid = Integer.parseInt(req.getParameter("to"));
		double amount = Double.parseDouble(req.getParameter("amount"));

		if (amount <= 0) {
			req.setAttribute("msg", "Transfer amount must be greater than zero.");
			req.getRequestDispatcher("transfer.jsp").forward(req, res);
			return;
		}

		if (fromaccountid == toaccountid) {
			req.setAttribute("msg", "Sender and receiver accounts cannot be the same.");
			req.getRequestDispatcher("transfer.jsp").forward(req, res);
			return;
		}

		try (Connection con = DBConnection.getConnection()) {
			con.setAutoCommit(false);

			try (PreparedStatement balancestmt = con.prepareStatement(balancesql)) {
				balancestmt.setInt(1, fromaccountid);
				ResultSet balancers = balancestmt.executeQuery();

				if (!balancers.next()) {
					req.setAttribute("msg", "Sender account not found.");
					req.getRequestDispatcher("transfer.jsp").forward(req, res);
					return;
				}

				double balance = balancers.getDouble("balance");
				if (balance < amount) {
					req.setAttribute("msg", "Insufficient balance.");
					req.getRequestDispatcher("transfer.jsp").forward(req, res);
					return;
				}

				try (PreparedStatement debitstmt = con.prepareStatement(debitsql)) {
					debitstmt.setDouble(1, amount);
					debitstmt.setInt(2, fromaccountid);
					debitstmt.executeUpdate();
				}

				try (PreparedStatement creditstmt = con.prepareStatement(creditsql)) {
					creditstmt.setDouble(1, amount);
					creditstmt.setInt(2, toaccountid);
					int rows1 = creditstmt.executeUpdate();

					if (rows1 <= 0) {
						con.rollback();
						req.setAttribute("msg", "Receiver account not found.");
						req.getRequestDispatcher("transfer.jsp").forward(req, res);
						return;
					}
				}

				try (PreparedStatement insertstmt = con.prepareStatement(insertsql)) {
					insertstmt.setInt(1, fromaccountid);
					insertstmt.setInt(2, toaccountid);
					insertstmt.setDouble(3, amount);
					insertstmt.executeUpdate();
				}

				con.commit();
				req.setAttribute("msg", "Transaction successful.");
				req.getRequestDispatcher("transfer.jsp").forward(req, res);
			} catch (SQLException e) {
				con.rollback(); 
				req.setAttribute("msg", "Transaction failed due to system error.");
				req.getRequestDispatcher("transfer.jsp").forward(req, res);
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("msg", "Database connection error.");
			req.getRequestDispatcher("transfer.jsp").forward(req, res);
		}
	}
}
