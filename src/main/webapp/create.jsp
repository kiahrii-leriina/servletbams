<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create user Account</title>
<link rel="stylesheet" href="css/create.css">
</head>
<body>
	<div class="create-container">
		<form action="create" method="post">
			<label for="name">Name:</label>
			<input id="name" type="text" name="name" required="required">
			<label for="email">Email:</label>
			<input id="email" type="text" name="email" required="required">
			<label for="phone">Phone:</label>
			<input id="phone" name="phone" required="required">
			<label for="password">Password:</label>
			<input id="password" name="password" required="required">
			<button type="submit">create</button>
		</form>
	</div>
</body>
</html>