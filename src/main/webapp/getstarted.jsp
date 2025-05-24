<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String accountid = request.getParameter("accountid");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Get Started</title>
<link rel="stylesheet" href="css/getstarted.css">
</head>
<body>

	<div class="container">
	<h1>${msg }</h1>
		<a href="transfer.jsp"><button>Transfer Money</button></a>
		<a href="history?accountid=<%= accountid%>"><button>Transaction History</button></a>
	</div>
</body>
</html>