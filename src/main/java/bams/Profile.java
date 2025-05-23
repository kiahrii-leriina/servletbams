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

@WebServlet(urlPatterns = "/profile")
public class Profile extends HttpServlet{

	String sql = "SELECT * FROM accounts WHERE account_id = ?";
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		int id = (int) req.getAttribute("accountid");
		
		try(Connection con = DBConnection.getConnection()){
				
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					req.setAttribute("rs", rs);
					RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
					rd.forward(req, res);
				}
				else {
					System.out.println("not able to find account ");
					res.getWriter().write("Not able to find account");		
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
