<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Registration Form</title>
</head>
<body>
	<h1>User Registration Form</h1>
	<form action="updateUserDetails?userId=${user.userId }" method="post">
		<table style="with: 20%">
			<tr>
				<td>First Name</td>
				<td><input type="text" name="firstName" value="${user.firstName}" /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="lastName"  value="${user.lastName}"  /></td>
			</tr>
			<tr>
			  <td>Mobile Number</td>
			  <td><input type="text" name="mobileNo"  value="${user.mobileNo}"  /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email"  value="${user.email}"  /></td>
			</tr>			
		</table>
		<input type="submit" value="Submit" />
	</form>
</body>
</html>