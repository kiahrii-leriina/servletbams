<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="form-container">
	<form action="addbank" method="post">
		<label for="name">Name:</label>
		<input id="name" type="text" required="required">
		<label for="deposit">Deposit:</label>
		<input id="deposit" type="text" required="required">
		<button type="submit">Submit</button>
	</form>
	</div>
</body>
</html>