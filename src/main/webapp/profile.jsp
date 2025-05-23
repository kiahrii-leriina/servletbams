<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile page</title>
<link rel="stylesheet" href="css/profile.css">
</head>
<body>

	<%ResultSet rs = (ResultSet)request.getAttribute("rs"); %>
	<div class="profile-container">
	
		<table>
			<thead>
				<tr>
					<th>Account ID</th>
					<th>Name</th>
					<th>Balance</th>
				</tr>
			</thead>
			<tbody>
					
					<tr>
						<td><%=rs.getInt("account_id") %></td>
						<td><%=rs.getString("name") %></td>
						<td><%=rs.getDouble("balance") %></td>
					</tr>
							
			</tbody>
		</table>
	</div>
</body>
</html>