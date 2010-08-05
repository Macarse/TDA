<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<META  http-equiv="Content-Type"  content="text/html;charset=UTF-8">
<title><fmt:message key="item.form.title" /></title>

</head>
<body>
<div class="container">
<h1>Crear Item</h1>

<div class="span-12 last"><form:form modelAttribute="item"
	action="add" method="post">
	<fieldset>
	
	<form:hidden path="id" />
	
	<legend>Item</legend>
	<p><form:label for="name" path="name" cssErrorClass="error">Nombre</form:label><br />
	<form:input path="name" /> <form:errors path="name" /></p>

	<p><form:label for="description" path="description"
		cssErrorClass="error">Descripción</form:label><br />
	<form:input path="description" /> <form:errors path="description" /></p>

	<p><form:label for="quantity" path="quantity" cssErrorClass="error">Cantidad</form:label><br />
	<form:input path="quantity" /> <form:errors path="quantity" /></p>

	<p><form:label for="category" path="category" cssErrorClass="error">Categoría</form:label><br />
	<form:select path="category">
		<c:forEach var="category" items="${categories}">
			<form:option value="${category}"> ${category.description} </form:option>
		</c:forEach>
	</form:select><form:errors path="category" /></p>

	<p><form:label for="measureUnit" path="measureUnit"
		cssErrorClass="error">Unidad de medida</form:label><br />
	<form:select path="measureUnit">
		<c:forEach var="measureUnit" items="${measureUnits}">
			<form:option value="${measureUnit}"> ${measureUnit.description} </form:option>
		</c:forEach>
	</form:select><form:errors path="measureUnit" /></p>


	<p><input type="submit" /></p>
	</fieldset>
</form:form></div>
<hr>
</div>
</body>
</html>