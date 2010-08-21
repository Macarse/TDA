<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

	<head>
		<title>Login: Spring Security Web Application</title>
	</head>
	
	<body onload='document.loginForm.j_username.focus();'>
	
	<h1>Please Log In to Your Account</h1>
	
	<p>Please use the form below to log in to your account.</p>
	
	<form action="/presentation/j_spring_security_check" method="post">
		<label for="j_username">Login</label>:
		<input id="j_username" name="j_username" size="20" maxlength="50" type="text"/> <br />
		
		<label for="j_password">Password</label>:
		<input id="j_password" name="j_password" size="20" maxlength="50" type="password"/>
	
		<br />
		
		<input type="submit" value="Login"/> </form>
	
	</body>

</html>