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

@WebServlet(urlPatterns = "/history")
public class History extends HttpServlet {
	
	String sql = "SELECT * FROM transcations WHERE from_acc = ? OR to_acc = ? ORDER BY timestamp DESC";

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(req.getParameter("accountid"));

			try (Connection con = DBConnection.getConnection();
				 PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setInt(1, id);
				ps.setInt(2, id);

				ResultSet rs = ps.executeQuery();

				if (rs.isBeforeFirst()) {
					req.setAttribute("rs", rs);
					req.getRequestDispatcher("history.jsp").forward(req, resp);
				} else {
					req.setAttribute("msg", "No transactions found for this account.");
					req.getRequestDispatcher("getstarted.jsp").forward(req, resp);
				}
			}
		} catch (NumberFormatException e) {
			req.setAttribute("msg", "Invalid account ID.");
			req.getRequestDispatcher("getstarted.jsp").forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("msg", "Database error occurred.");
			req.getRequestDispatcher("getstarted.jsp").forward(req, resp);
		}
	}
}
