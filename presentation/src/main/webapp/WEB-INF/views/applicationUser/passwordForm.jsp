<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form modelAttribute="applicationUser"
	action="" method="post">
	<fieldset>

	<form:hidden path="id" />

	<p><form:label for="password" path="password"
		cssErrorClass="error"><fmt:message key="user.form.password" /></form:label><br />
	<form:password path="password" /> <form:errors path="password" /></p>

	<p><form:label for="password" path="confirmPassword"
		cssErrorClass="error"><fmt:message key="user.form.password2" /></form:label><br />
	<form:password path="confirmPassword" /> <form:errors path="confirmPassword" /></p>

	<p><input type="submit"/></p>
	</fieldset>
</form:form>
