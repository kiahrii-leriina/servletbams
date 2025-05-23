package bams;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/deposit")
public class Deposit extends HttpServlet {

	String insertSql = "INSERT INTO accounts(name, balance, user_id) VALUES(?, ?, ?)";
	String accountSql = "SELECT account_id FROM accounts WHERE name = ?";

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String name = req.getParameter("name");
		double deposit = 0;
		int userId = Integer.parseInt(req.getParameter("userid"));

		try {
			deposit = Double.parseDouble(req.getParameter("deposit"));
		} catch (NumberFormatException e) {
			PrintWriter pw = res.getWriter();
			pw.write("Invalid deposit amount");
			return;
		}

		try (Connection con = DBConnection.getConnection();
				PreparedStatement insertStmt = con.prepareStatement(insertSql)) {

			insertStmt.setString(1, name);
			insertStmt.setDouble(2, deposit);
			insertStmt.setInt(3, userId);

			int rows = insertStmt.executeUpdate();

			if (rows > 0) {
				try (PreparedStatement accountStmt = con.prepareStatement(accountSql)) {
					accountStmt.setString(1, name);
					ResultSet accountrs = accountStmt.executeQuery();
					if (accountrs.next()) {
						req.setAttribute("accountid", accountrs.getInt("account_id"));
						RequestDispatcher rd = req.getRequestDispatcher("profile");
						rd.forward(req, res);
					} else {
						res.getWriter().write("Account created but account ID retrieval failed.");
					}
				}
			} else {
				req.setAttribute("msg", "Invalid entry");
				RequestDispatcher rd = req.getRequestDispatcher("setprofile.jsp");
				rd.forward(req, res);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
