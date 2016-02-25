<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>SUCCESS PAGE!!!</h1>
		<br><br>
		<shiro:hasRole name="admin">
		<a href="admin.jsp">To Admin</a>
		<br><br>
		</shiro:hasRole>
		<a href="testShiroAnnotation">To testShiroAnnotation</a>
		<br><br>
		<shiro:hasRole name="user">
		<a href="user.jsp">To User</a>
		<br><br>
		</shiro:hasRole>
		<a href="shiro-logout">Logout</a>
</body>
</html>