<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<META  http-equiv="Content-Type"  content="text/html;charset=UTF-8">
<title>Crear Usuario</title>

</head>
<body>
<div class="container">
<h1>Crear Usuario</h1>

<div class="span-12 last"><form:form modelAttribute="applicationuser"
	action="add" method="post">
	<fieldset>
	
	<form:hidden path="id" />
	
	<legend>Usuario</legend>
	<p><form:label for="username" path="username" cssErrorClass="error">Nombre de usuario</form:label><br />
	<form:input path="username" /> <form:errors path="username" /></p>

	<p><form:label for="password" path="password"
		cssErrorClass="error">Descripción</form:label><br />
	<form:input path="password" /> <form:errors path="password" /></p>

	<p><form:label for="isaccountnonexpired" path="isaccountnonexpired" cssErrorClass="error">No expirado?</form:label><br />
	<form:checkbox path="isaccountnonexpired" /> <form:errors path="isaccountnonexpired" /></p>
	
	<p><form:label for="isaccountnonlocked" path="isaccountnonlocked" cssErrorClass="error">No lockeado?</form:label><br />
	<form:checkbox path="isaccountnonlocked" /> <form:errors path="isaccountnonlocked" /></p>
	
	<p><form:label for="iscredentialsnonexpired" path="iscredentialsnonexpired" cssErrorClass="error">Credenciales no expiradas?</form:label><br />
	<form:checkbox path="iscredentialsnonexpired" /> <form:errors path="iscredentialsnonexpired" /></p>
	
	<p><form:label for="isenabled" path="isenabled" cssErrorClass="error">Habilitado?</form:label><br />
	<form:checkbox path="isenabled" /> <form:errors path="isenabled" /></p>

	<p><input type="submit" /></p>
	</fieldset>
</form:form></div>
<hr>
</div>
</body>
</html>