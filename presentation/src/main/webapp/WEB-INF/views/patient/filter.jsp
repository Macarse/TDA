<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="filter-container">
	<form:form modelAttribute="patient"
		action="search" method="get">
		<table>
			<thead>
				<tr><td colspan="2"><fmt:message key="filter.title" /></td></tr>
			</thead>
			<tbody>
				<tr>
					<td><form:label for="firstName" path="firstName" cssErrorClass="error">
						<fmt:message key="patient.form.firstName" /></form:label></td>
					<td><form:input path="firstName" /> <form:errors path="firstName" /></td></tr>
				<tr>
					<td><form:label for="lastName" path="lastName" cssErrorClass="error">
						<fmt:message key="patient.form.lastName" /></form:label></td>
					<td><form:input path="lastName" /> <form:errors path="lastName" /></td></tr>
				<tr>
					<td><form:label for="sex" path="sex"cssErrorClass="error"><fmt:message key="patient.form.sex" /></form:label></td>
					<td><form:select path="sex">
							<form:option value=""></form:option>
							<c:forEach var="sex" items="${sex}">
							<form:option value="${sex}"> ${sex.description} </form:option>
						</c:forEach>
						</form:select><form:errors path="sex" /></td></tr>
				<tr>
					<td><form:label for="birthdate" path="birthdate" cssErrorClass="error">
						<fmt:message key="patient.form.birthdate" /></form:label></td>
					<td><form:input path="birthdate" /> <form:errors path="birthdate" /></td></tr>
				<tr>
					<td><form:label for="dni" path="dni" cssErrorClass="error">
						<fmt:message key="patient.form.dni" /></form:label></td>
					<td><form:input path="dni" /> <form:errors path="dni" /></td></tr>
			</tbody>
			<tfoot>
				<tr><td colspan="2">
					<div class="filter-submit">	
						<button type="submit" class="button-text button-search fg-button ui-state-default ui-corner-all"><span class="ui-icon ui-icon-search button-icon"></span> <fmt:message key="filter.submit" /></button>
					</div></td></tr>
			</tfoot>
		</table>
	</form:form>
</div>