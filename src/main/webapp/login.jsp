<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${msg }</h1>
	<div>
		<form action="login">
			<label for="email">Email:</label>
			<input id="name" name="email" required="required">
			<label for="password">Password:</label>
			<input id="password" name="password" required="required">
			<button type="submit">Login</button>
		</form>
	</div>
</body>
</html>