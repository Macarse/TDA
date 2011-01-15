<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="form-container">
	<form:form modelAttribute="patient"
		action="add" method="post">
		
		<form:hidden path="id" />
		
		<legend><fmt:message key="patient.form.title" /></legend>
		
		<div class="form-value">
			<form:label for="firstName" path="firstName" cssErrorClass="error">
			<fmt:message key="patient.form.firstName" /></form:label>
			<form:input path="firstName" /> <form:errors path="firstName" />
		</div>
	
		<div class="form-value">
			<form:label for="lastName" path="lastName" cssErrorClass="error">
			<fmt:message key="patient.form.lastName" /></form:label>
			<form:input path="lastName" /> <form:errors path="lastName" />
		</div>
		
		<div class="form-value">
			<form:label for="sex" path="sex"
				cssErrorClass="error"><fmt:message key="patient.form.sex" /></form:label>
			<form:select path="sex">
				<c:forEach var="sex" items="${sex}">
				<form:option value="${sex}"> ${sex.description} </form:option>
			</c:forEach>
			</form:select><form:errors path="sex" />	
		</div>
		
		<div class="form-value">
			<form:label for="birthdate" path="birthdate" cssErrorClass="error">
			<fmt:message key="patient.form.birthdate" /></form:label>
			<form:input path="birthdate" /> <form:errors path="birthdate" />
		</div>
	
		<div class="form-value">
			<form:label for="dni" path="dni" cssErrorClass="error">
			<fmt:message key="patient.form.dni" /></form:label>
			<form:input path="dni" /> <form:errors path="dni" />
		</div>

		<div class="form-submit">	
			<input type="submit" value="<fmt:message key="form.submit" />" />
		</div>
		
	</form:form>
</div>

<script>
	$(function() {
		$( "#birthdate" ).datepicker(
			{ dateFormat: 'dd/mm/yy',
			  monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
			  dayNamesMin: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa']
			   }
		);
	});
</script>


