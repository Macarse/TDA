<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Edit/Delete buttons variables -->
<spring:url value="item/" var="startUrl" />
<spring:url value="delete" var="deleteUrl" />
<spring:message text="Eliminar" var="deleteLabel" />
<spring:url value="edit" var="editUrl" />
<spring:url value="add" var="addUrl" />
<spring:message text="Editar" var="editLabel" />

<c:if test="${!empty param.message}">
	<fmt:message key="${param.message }" />
</c:if>

<a href="${startUrl}${addUrl}">Agregar</a>

<table class="list-table">
	<thead>
		<tr>
			<th><fmt:message key="item.form.name" /></th>
			<th><fmt:message key="item.form.description" /></th>
			<th><fmt:message key="item.form.quantity" /></th>
			<th><fmt:message key="item.form.category" /></th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>


		<c:forEach items="${itemList}" var="item">
			<tr>
				<td>${item.name}</td>
				<td>${item.description}</td>
				<td>${item.quantity}</td>
				<td>${item.category}</td>
				<td><form:form method="POST"
					action="${startUrl}${deleteUrl}/${item.id}">
					<input type="submit" value="${deleteLabel}" />
				</form:form></td>
				<td><form:form method="GET"
					action="${startUrl}${editUrl}/${item.id}">
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