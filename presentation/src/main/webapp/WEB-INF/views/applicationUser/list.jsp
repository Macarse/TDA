<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- Edit/Delete buttons variables -->
<spring:url value="applicationuser/" var="startUrl"/>
<spring:url value="delete" var="deleteUrl"/>
<spring:message text="Eliminar" var="deleteLabel"/>
<spring:url value="edit" var="editUrl"/>
<spring:url value="add" var="addUrl"/>
<spring:message text="Editar" var="editLabel"/>

<a href="${startUrl}${addUrl}">Agregar</a>
<table class="list-table">
	<thead>
		<tr>
			<th>Nombre de usuario</th>
			<th>Contraseña</th>
			<th>Autoridades </th>
			<th></th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach items="${applicationUserList}" var="user">
		<tr>
			<td>${user.username}</td>
			<td>${user.password}</td>
			<td>${user.myAuthorities}</td>
			<td>
				<form:form method="POST" action="${startUrl}${deleteUrl}/${user.id}">
					<input type="submit" value="${deleteLabel}"/>
				</form:form>
			</td>
			<td>
				<form:form method="GET" action="${startUrl}${editUrl}/${user.id}" >
					<input type="submit" value="${editLabel}"/>
				</form:form>
			</td>
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
