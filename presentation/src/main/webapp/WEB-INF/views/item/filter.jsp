<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="filter-container">
	<form:form modelAttribute="item"
		action="search" method="get">
		<table>
			<thead>
				<tr><td colspan="2"><fmt:message key="filter.title" /></td></tr>
			</thead>
			<tbody>
				<tr>
					<td><form:label for="name" path="name" cssErrorClass="error">
						<fmt:message key="item.form.name" /></form:label></td>
					<td><form:input path="name" /> <form:errors path="name" /></td></tr>
				<tr>
					<td><form:label for="description" path="description" cssErrorClass="error">
			<fmt:message key="item.form.description" /></form:label></td>
					<td><form:input path="description" /> <form:errors path="description" /></td></tr>
				<tr>
					<td><form:label for="quantity" path="quantity" cssErrorClass="error">
			<fmt:message key="item.form.quantity" /></form:label></td>
					<td><form:input path="quantity" /> <form:errors path="quantity" /></td></tr>
				<tr>
					<td><form:label for="category" path="category" cssErrorClass="error">
			<fmt:message key="item.form.category" /></form:label></td>
					<td><form:select path="category">
				<form:option value=""></form:option>
				<c:forEach var="category" items="${categories}">
					<form:option value="${category}"> ${category.description} </form:option>
				</c:forEach>
			</form:select><form:errors path="category" /></td></tr>
				<tr>
					<td><form:label for="measureUnit" path="measureUnit"
				cssErrorClass="error"><fmt:message key="item.form.measureUnit" /></form:label></td>
					<td><form:select path="measureUnit">
				<form:option value=""></form:option>
				<c:forEach var="measureUnit" items="${measureUnits}">
					<form:option value="${measureUnit}"> ${measureUnit.description} </form:option>
				</c:forEach>
			</form:select><form:errors path="measureUnit" /></td></tr>
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