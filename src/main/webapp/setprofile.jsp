<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Account/set profile</title>
<link rel="stylesheet" href="css/setprofile.css">
</head>
<body>
	<% 
		int userId = (Integer)request.getAttribute("userid");
	%>
	<h1>Add Account</h1>
	<div class="account-container">
	
		
		<form action="deposit" method="post">
			<input type="hidden" name="userid" value="<%= userId %>">
			
			<label for="name">Name:</label>
			<input id="name" name="name" required="required">
			
			<label for="deposit">Deposit</label>
			<input id="deposit" name="deposit" required="required">
			
			<button type="submit">Finish</button>
		</form>
	</div>
</body>
</html>