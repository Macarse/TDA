<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<META  http-equiv="Content-Type"  content="text/html;charset=UTF-8">
<title><fmt:message key="user.form.title" /></title>

</head>
<body>
<div class="container">
<h1><fmt:message key="user.form.title" /></h1>

<div class="span-12 last"><form:form modelAttribute="applicationUser"
	action="add" method="post">
	<fieldset>
	
	<form:hidden path="id" />
	
	<legend>Usuario</legend>
	<p><form:label for="username" path="username" cssErrorClass="error">Nombre de usuario</form:label><br />
	<form:input path="username" /> <form:errors path="username" /></p>

	<p><form:label for="password" path="password"
		cssErrorClass="error">Contraseña</form:label><br />
	<form:input path="password" /> <form:errors path="password" /></p>
	
	<p><form:label for="myAuthorities" path="myAuthorities"
		cssErrorClass="error">Autoridades</form:label><br />
	<form:checkboxes items="${allAuthorities}" path="myAuthorities" itemLabel="name"/><form:errors path="myAuthorities" /></p>

	<p><input type="submit" /></p>
	</fieldset>
</form:form></div>
<hr>
</div>
</body>
</html>