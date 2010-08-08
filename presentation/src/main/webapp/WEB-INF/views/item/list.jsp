<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Item list</title>
</head>
<body>

<c:if test="${!empty param.message}">
	<fmt:message key="${param.message }" />
</c:if>

<table>
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

		<!-- Edit/Delete buttons variables -->
		<spring:url value="/item/" var="startUrl" />
		<spring:url value="delete/" var="deleteUrl" />
		<spring:message text="Eliminar" var="deleteLabel" />
		<spring:url value="edit/" var="editUrl" />
		<spring:message text="Editar" var="editLabel" />

		<c:forEach items="${itemList}" var="item">
			<tr>
				<td>${item.name}</td>
				<td>${item.description}</td>
				<td>${item.quantity}</td>
				<td>${item.category}</td>
				<td><form:form method="POST"
					action="${startUrl}${deleteUrl}${item.id}">
					<input type="submit" value="${deleteLabel}" />
				</form:form></td>
				<td><form:form method="GET"
					action="${startUrl}${editUrl}${item.id}">
					<input type="submit" value="${editLabel}" />
				</form:form></td>
			</tr>
		</c:forEach>
	</tbody>

	<tfoot>
		<tr>
		  <td colspan="2"> 
		    <c:if test="${!paginator.firstPage}">
		      <a href="?page=previous"><B>&lt;&lt; Prev</B></a>
		    </c:if>
		    <c:forEach items="${paginator.pages}" var="page">
		      <c:choose>
		        <c:when test="${false}">
		          <b><c:out value="${page}"/></b>
		        </c:when>
		        <c:otherwise>
		          <a href="?page=<c:out value="${page}"/>"><c:out value="${page}"/></a>
		        </c:otherwise>
		      </c:choose>
		    </c:forEach> 
			<c:if test="${!paginator.lastPage}">
			  <a href="?page=next"><B>Next &gt;&gt;</B></a>
			</c:if>
		  </td>
		</tr>
	</tfoot>
</table>
</body>
</html>