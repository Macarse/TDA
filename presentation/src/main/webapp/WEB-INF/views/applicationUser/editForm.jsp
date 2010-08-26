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
	
	<legend><fmt:message key="user.form.legend" /></legend>
	<p><form:label for="username" path="username" cssErrorClass="error"><fmt:message key="user.form.username" /></form:label><br />
	<form:input path="username" /> <form:errors path="username" /></p>
	
	<p><form:label for="myAuthorities" path="myAuthorities"
		cssErrorClass="error"><fmt:message key="user.form.autorities" /></form:label><br />
	<form:checkboxes items="${allAuthorities}" path="myAuthorities" itemLabel="name" itemValue="authority"/><form:errors path="myAuthorities" /></p>

	<p><input type="submit"/></p>
	</fieldset>
</form:form>
