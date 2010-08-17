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

<a href="${addUrl}">Agregar</a>

<table class="list-table">
	<thead>
		<tr>
			<th>
				<a href="?orderField=name&orderAscending=<c:out value="${!paginator.orderAscending}"/>">
					<fmt:message key="item.form.name" />
				</a>
				<c:if test="${paginator.orderField=='name'}">
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
				<a href="?orderField=description&orderAscending=<c:out value="${!paginator.orderAscending}"/>">
					<fmt:message key="item.form.description" />
				</a>
				<c:if test="${paginator.orderField=='description'}">
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
				<a href="?orderField=quantity&orderAscending=<c:out value="${!paginator.orderAscending}"/>">
					<fmt:message key="item.form.quantity" />
				</a>
				<c:if test="${paginator.orderField=='quantity'}">
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
				<a href="?orderField=category&orderAscending=<c:out value="${!paginator.orderAscending}"/>">
					<fmt:message key="item.form.category" />
				</a>
				<c:if test="${paginator.orderField=='category'}">
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
				<a href="?orderField=measureUnit&orderAscending=<c:out value="${!paginator.orderAscending}"/>">
					<fmt:message key="item.form.measureUnit" />
				</a>
				<c:if test="${paginator.orderField=='measureUnit'}">
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
				<td>${item.measureUnit}</td>
				<td><form:form method="POST"
					action="${deleteUrl}/${item.id}">
					<input type="submit" value="${deleteLabel}" />
				</form:form></td>
				<td><form:form method="GET"
					action="${editUrl}/${item.id}">
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