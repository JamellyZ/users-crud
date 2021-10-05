<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hello</title>
</head>
<body>
<h3>Thank u</h3>

<label>First Name</label>
${userDB.getFname()}<br>

<label>Last Name</label>
${userDB.getLname()}<br>

<label>Phone</label>
${userDB.getPhone()}<br>
</body>
</html>