<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="form-container">
	<form:form modelAttribute="patient"
		action="add" method="post">
		
		<fieldset>
		
			<form:hidden path="id" />
			
			<legend><fmt:message key="patient.form.title" /></legend>
			
			<div class="form-value">
				<p><form:label for="firstName" path="firstName" cssErrorClass="error">
				<fmt:message key="patient.form.firstName" /></form:label><br/>
				<form:input path="firstName" /> <form:errors path="firstName" /></p>
			</div>
		
			<div class="form-value">
				<p>
					<form:label for="lastName" path="lastName" cssErrorClass="error">
					<fmt:message key="patient.form.lastName" /></form:label><br/>
					<form:input path="lastName" /> <form:errors path="lastName" />
				</p>
			</div>
			
			<div class="form-value">
				<p>
					<form:label for="sex" path="sex"
						cssErrorClass="error"><fmt:message key="patient.form.sex" /></form:label><br/>
					<form:select path="sex">
						<c:forEach var="sex" items="${sex}">
						<form:option value="${sex}"> ${sex.description} </form:option>
					</c:forEach>
					</form:select><form:errors path="sex" />
				</p>
			</div>
			
			<div class="form-value">
				<p>
					<form:label for="birthdate" path="birthdate" cssErrorClass="error">
					<fmt:message key="patient.form.birthdate" /></form:label><br/>
					<form:input path="birthdate" /> <form:errors path="birthdate" />
				</p>
			</div>
		
			<div class="form-value">
				<p>
					<form:label for="dni" path="dni" cssErrorClass="error">
					<fmt:message key="patient.form.dni" /></form:label><br/>
					<form:input path="dni" /> <form:errors path="dni" />
				</p>
			</div>
	
			<div class="form-submit">	
				<input type="submit" value="<fmt:message key="form.submit" />" />
			</div>
	
		</fieldset>		
	
	</form:form>
</div>

<script>
	$(function() {
		$( "#birthdate" ).datepicker(
			{ dateFormat: 'dd/mm/yy',
			  monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
			  monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
			  dayNamesMin: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
			  changeYear: true,
			  changeMonth: true,
			  yearRange: 'c-100,c+00',
			  defaultDate: -3650
		    }
		);
	});
</script>


