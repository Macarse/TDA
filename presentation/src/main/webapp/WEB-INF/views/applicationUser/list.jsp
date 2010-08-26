<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- Edit/Delete buttons variables -->
<spring:url value="applicationUser" var="startUrl"/>
<spring:url value="delete" var="deleteUrl"/>
<spring:message text="Eliminar" var="deleteLabel"/>
<spring:url value="edit" var="editUrl"/>
<spring:url value="passwordEdit" var="passwordEditUrl"/>
<spring:url value="add" var="addUrl"/>
<spring:url value="search" var="searchUrl" />
<spring:message text="Cambiar Contraseña" var="passwordEditLabel"/>
<spring:message text="Editar" var="editLabel"/>

<c:if test="${!empty param.message}">
	<div class="message">
		<fmt:message key="${param.message }" />
	</div>
</c:if>

<div class="filter-container">
	<jsp:include page="/WEB-INF/views/applicationUser/filter.jsp" flush="true"/>
</div>

<a href="${addUrl}">Agregar</a>

<table class="list-table">
	<thead>
		<tr>
			<th>
				<a href="?orderField=username&orderAscending=<c:out value="${!paginator.orderAscending}&${params}"/>">
					<fmt:message key="user.form.username" />
				</a>
				<c:if test="${paginator.orderField=='username'}">
					<c:choose>
						<c:when test="${paginator.orderAscending}">
			  				<img src='${pageContext.request.contextPath}/<spring:theme code="uparrow.img"/>'>
						</c:when>
						<c:otherwise>
			  				<img src='${pageContext.request.contextPath}/<spring:theme code="downarrow.img"/>'>
						</c:otherwise>
					</c:choose>
				</c:if>
			</th>
			<th>
				<a href="?orderField=myAuthorities&orderAscending=<c:out value="${!paginator.orderAscending}&${params}"/>">
					<fmt:message key="user.form.autorities" />
				</a>
				<c:if test="${paginator.orderField=='myAuthorities'}">
					<c:choose>
						<c:when test="${paginator.orderAscending}">
			  				<img src='${pageContext.request.contextPath}/<spring:theme code="uparrow.img"/>'>
						</c:when>
						<c:otherwise>
			  				<img src='${pageContext.request.contextPath}/<spring:theme code="downarrow.img"/>'>
						</c:otherwise>
					</c:choose>
				</c:if>
			</th>
			<th></th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach items="${applicationUserList}" var="user">
		<tr>
			<td>${user.username}</td>
			<td>${user.myAuthorities}</td>
			<td>
				<form:form method="POST" action="${deleteUrl}/${user.id}">
					<input type="submit" value="${deleteLabel}"/>
				</form:form>
			</td>
			<td>
				<form:form method="GET" action="${editUrl}/${user.id}" >
					<input type="submit" value="${editLabel}"/>
				</form:form>
			</td>
			<td>
				<form:form method="GET" action="${passwordEditUrl}/${user.id}" >
					<input type="submit" value="${passwordEditLabel}"/>
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
