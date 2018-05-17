<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to our Social Network</title>
</head>
<body>	
	<h1>Welcome to our Social Network</h1>
	<c:form action="login">
		<c:textfield key="user.userName" label="User Name"/>
		<c:password key="user.password" label="Password"/>
		<c:submit value="login"/>
	</c:form>
</body>
</html>