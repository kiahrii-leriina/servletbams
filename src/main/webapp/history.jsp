<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transaction History</title>
<link rel="stylesheet" href="css/history.css">
</head>
<body>

    <% ResultSet rs = (ResultSet)request.getAttribute("rs"); %>
    <div class="history-container">
        <h2>Transaction History</h2>
        <table>
            <thead>
                <tr>
                    <th>Transaction ID</th>
                    <th>From Account</th>
                    <th>To Account</th>
                    <th>Amount</th>
                    <th>Timestamp</th>
                </tr>
            </thead>
            <tbody>
                <%
                    boolean found = false;
                    while (rs != null && rs.next()) {
                        found = true;
                %>
                    <tr>
                        <td><%= rs.getInt("txn_id") %></td>
                        <td><%= rs.getInt("from_acc") %></td>
                        <td><%= rs.getInt("to_acc") %></td>
                        <td><%= rs.getDouble("amount") %></td>
                        <td><%= rs.getTimestamp("timestamp") %></td>
                    </tr>
                <%
                    }
                    if (!found) {
                %>
                    <tr>
                        <td colspan="5">No transaction history available.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
