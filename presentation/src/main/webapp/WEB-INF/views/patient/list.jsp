<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Edit/Delete buttons variables -->
<spring:url value="patient" var="startUrl" />
<spring:url value="delete" var="deleteUrl" />
<spring:message text="Eliminar" var="deleteLabel" />
<spring:url value="edit" var="editUrl" />
<spring:url value="add" var="addUrl" />
<spring:url value="search" var="searchUrl" />
<spring:message text="Editar" var="editLabel" />

<c:if test="${!empty param.message}">
	<div class="message">
		<fmt:message key="${param.message }" />
	</div>
</c:if>

<div class="filter-container">
	<jsp:include page="/WEB-INF/views/patient/filter.jsp" flush="true"/>
</div>

<a href="${addUrl}">Agregar</a>

<table class="list-table">
	<thead>
		<tr>
			<th><fmt:message key="patient.form.firstName" /></th>
			<th><fmt:message key="patient.form.lastName" /></th>
			<th><fmt:message key="patient.form.sex" /></th>
			<th><fmt:message key="patient.form.birthdate" /></th>
			<th><fmt:message key="patient.form.dni" /></th>
			<th></th>
		</tr>
	</thead>
	<tbody>


		<c:forEach items="${patientList}" var="patient">
			<tr>
				<td>${patient.firstName}</td>
				<td>${patient.lastName}</td>
				<td>${patient.sex}</td>
				<td>${patient.birthdate}</td>
				<td>${patient.dni}</td>
				<td><form:form method="POST"
					action="${deleteUrl}/${patient.id}">
					<input type="submit" value="${deleteLabel}" />
				</form:form></td>
				<td><form:form method="GET"
					action="${editUrl}/${patient.id}">
					<input type="submit" value="${editLabel}" />
				</form:form></td>
			</tr>
		</c:forEach>
	</tbody>

	<tfoot>
		<tr>
		  <td colspan="2">
			<jsp:include page="/WEB-INF/views/paginator/paginator.jsp" flush="true"/>
		  </td>
		</tr>
	</tfoot>
</table>