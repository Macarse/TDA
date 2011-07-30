<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="filter-container">
	<form:form modelAttribute="auditLog"
		action="search" method="get">
		<table>
			<thead>
				<tr><td colspan="2"><fmt:message key="filter.title" /></td></tr>
			</thead>
			<tbody>
				<tr>
					<td><form:label for="formId" path="formId" cssErrorClass="error">
						<fmt:message key="auditLog.form.formId" /></form:label></td>
					<td><form:input path="formId" /> <form:errors path="formId" /></td></tr>
				<tr>
					<td><form:label for="user" path="user" cssErrorClass="error">
			<fmt:message key="auditLog.form.user" /></form:label></td>
					<td><form:input path="user" /> <form:errors path="user" /></td></tr>
				<tr>
					<td><form:label for="controllerUsed" path="controllerUsed" cssErrorClass="error">
			<fmt:message key="auditLog.form.controllerUsed" /></form:label></td>
					<td><form:input path="controllerUsed" /> <form:errors path="controllerUsed" /></td></tr>
				<tr>
					<td><form:label for="timestamp" path="timestamp" cssErrorClass="error">
			<fmt:message key="auditLog.form.timestamp" /></form:label></td>
					<td><form:input path="timestamp" /><form:errors path="timestamp" /></td></tr>
			</tbody>
			<tfoot>
				<tr><td colspan="2">
					<div class="filter-submit">	
						<button type="submit" class="button-text button-search fg-button ui-state-default ui-corner-all"><span class="ui-icon ui-icon-search button-icon"></span> <fmt:message key="filter.submit" /></button>
					</div></td></tr>
				<tr><td><input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderAscending" value="${orderAscending}" /></td></tr>
			</tfoot>
		</table>
	</form:form>
</div>