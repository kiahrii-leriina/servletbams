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

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet{

	String loginsql = "SELECT id FROM users WHERE email = ? AND password = ?";
	String accountsql = "SELECT * FROM accounts WHERE user_id = ?";
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try(Connection con = DBConnection.getConnection()){
			try(PreparedStatement loginstmt = con.prepareStatement(loginsql)){
				loginstmt.setString(1, email);
				loginstmt.setString(2, password);
				
				ResultSet loginrs = loginstmt.executeQuery();
				if(loginrs.next()) {
					int user_id = loginrs.getInt("id");
					
					try(PreparedStatement accountstmt = con.prepareStatement(accountsql)){
						accountstmt.setInt(1, user_id);
						ResultSet accountrs = accountstmt.executeQuery();
						
						if(accountrs.next()) {
							req.setAttribute("accountid", accountrs.getInt("account_id"));
							RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
							rd.forward(req, res);
						}
						else {
							req.setAttribute("userid", user_id);
							RequestDispatcher rd = req.getRequestDispatcher("setprofile.jsp");
							rd.forward(req, res);
							
						}
					}
				}
				else {
				    req.setAttribute("msg", "Invalid username or password");
				    RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
				    rd.forward(req, res);
				}

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
