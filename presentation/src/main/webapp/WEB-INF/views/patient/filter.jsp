<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="filter-container">
	<form:form modelAttribute="patient"
		action="" method="get">
		<legend><fmt:message key="filter.title" /></legend>
		
		<div class="filter-value">
			<form:label for="firstName" path="firstName" cssErrorClass="error">
			<fmt:message key="patient.form.firstName" /></form:label>
			<form:input path="firstName" /> <form:errors path="firstName" />
		</div>
	
		<div class="filter-value">
			<form:label for="lastName" path="lastName" cssErrorClass="error">
			<fmt:message key="patient.form.lastName" /></form:label>
			<form:input path="lastName" /> <form:errors path="lastName" />
		</div>
		
		<div class="filter-value">
			<form:label for="sex" path="sex" cssErrorClass="error">
			<fmt:message key="patient.form.sex" /></form:label>
			<form:input path="sex" /> <form:errors path="sex" />
		</div>
		
		<div class="filter-value">
			<form:label for="birthdate" path="birthdate" cssErrorClass="error">
			<fmt:message key="patient.form.birthdate" /></form:label>
			<form:input path="birthdate" /> <form:errors path="birthdate" />
		</div>
	
		<div class="filter-value">
			<form:label for="dni" path="dni" cssErrorClass="error">
			<fmt:message key="patient.form.dni" /></form:label>
			<form:input path="dni" /> <form:errors path="dni" />
		</div>

		<div class="filter-submit">	
			<input type="submit" value="<fmt:message key="filter.submit" />" />
		</div>
		
	</form:form>
</div>