<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transaction</title>
<link rel="stylesheet" href="css/transfer.css">
</head>
<body>
	<div class="container">
		<h1>${msg}</h1>
		<form action="transfer" method="post">
			<label for="from">Enter account id to transfer from:</label> <input
				id="from" name="from" required="required"> <label for="to">Enter
				account id to transfer to:</label> <input id="to" name="to"
				required="required"> <label for="amount">Enter
				amount:</label> <input id="amount" name="amount" required="required">

			<div class="button-group">
				<button type="submit">Transfer</button>
				<a href="getstarted.jsp"><button type="button">Back</button></a>
			</div>

		</form>
	</div>
</body>
</html>