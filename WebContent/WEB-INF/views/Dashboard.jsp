<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
<script type="text/javascript" src="<c:url value="resources/js/userData.js"/>"></script>
<script type="text/javascript" src="<c:url value="resources/js/jquery.dataTables.min.js"/>"></script>

<link rel="stylesheet" href="<c:url value="resources/css/jquery.dataTables.min.css"/>" />

<style type="text/css">
a {
	text-decoration:underline;
}
</style>
</head>
<body>
<h1 align="center">Welcome to SunStarCafe</h1><hr/>
<div align="center">
<a id="userListLink">User List</a>
<a href="showFormForAddUser">Add a New User</a>
<a href="userList">Order List</a>
</div>
<hr>
<div class="container">
		
		<table id="userTable" class="table table-bordered table-striped">
			
		</table>

	</div>
</body>
</html>