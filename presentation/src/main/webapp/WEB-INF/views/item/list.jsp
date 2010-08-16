<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Edit/Delete buttons variables -->
<spring:url value="item" var="startUrl" />
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
	<jsp:include page="/WEB-INF/views/item/filter.jsp" flush="true"/>
</div>

<a href="${startUrl}/${addUrl}">Agregar</a>

<table class="list-table">
	<thead>
		<tr>
			<th><a href="${startUrl}?orderField=name&orderAscending=<%
			    if (request.getParameter("orderAscending") == null || ((String)request.getParameter("orderAscending")).equals("true")) {
			        out.println("false");
			    } else {
			        out.println("true");
			    }
			%>">
			<fmt:message key="item.form.name" /></a></th>
			<th><a href="${startUrl}?orderField=description&orderAscending=<%
			    if (request.getParameter("orderAscending") == null || ((String)request.getParameter("orderAscending")).equals("true")) {
			        out.println("false");
			    } else {
			        out.println("true");
			    }
			%>"><fmt:message key="item.form.description" /></a></th>
			<th><a href="${startUrl}?orderField=quantity&orderAscending=<%
			    if (request.getParameter("orderAscending") == null || ((String)request.getParameter("orderAscending")).equals("true")) {
			        out.println("false");
			    } else {
			        out.println("true");
			    }
			%>"><fmt:message key="item.form.quantity" /></a></th>
			<th><a href="${startUrl}?orderField=category&orderAscending=<%
			    if (request.getParameter("orderAscending") == null || ((String)request.getParameter("orderAscending")).equals("true")) {
			        out.println("false");
			    } else {
			        out.println("true");
			    }
			%>"><fmt:message key="item.form.category" /></a></th>
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
					action="${startUrl}/${deleteUrl}/${item.id}">
					<input type="submit" value="${deleteLabel}" />
				</form:form></td>
				<td><form:form method="GET"
					action="${startUrl}/${editUrl}/${item.id}">
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