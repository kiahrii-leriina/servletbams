package bams;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/create")
public class CreateAccount extends HttpServlet{
	
	String sql = "INSERT INTO users (name, email, phone, password) VALUES(?,?,?,?)";
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		long phone = 0;
		
		try {
			
			phone = Long.parseLong(req.getParameter("phone"));
		}catch(NumberFormatException e) {
			PrintWriter pw = res.getWriter();
			pw.write("invalid phone number");
			return;
		}
		String password = req.getParameter("password");
		
		try(Connection con = DBConnection.getConnection()){
			
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setLong(3, phone);
				ps.setNString(4, password);
				
				int rows = ps.executeUpdate();
				if(rows>0) {
					System.out.println("registration success");
//					((javax.servlet.http.HttpServletResponse)res).sendRedirect("login.jsp");
					req.setAttribute("msg", "registration sucess");
					RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
					
					rd.forward(req, res);
					
				}
				ps.close();
			}
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
