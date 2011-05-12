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

<div id="search-button">
	<a href="#" style="font-size: 14px;"><span class="ui-icon ui-icon-search button-icon"> </span> Buscar Usuario</a>
</div>

<div class="filter-container" style="display:none;" id="filter-containter">
	<jsp:include page="/WEB-INF/views/applicationUser/filter.jsp" flush="true"/>
</div>

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
			<th colspan="3"></th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach items="${applicationUserList}" var="user">
		<tr>
			<td>${user.username}</td>
			<td width="400">${user.myAuthorities}</td>
			<td width="100">
				<form:form method="GET" action="${editUrl}/${user.id}" >
					<button type="submit" class="button-text button-edit fg-button ui-state-default ui-corner-all"><span class="ui-icon ui-icon-transferthick-e-w button-icon"></span> <c:out value="${editLabel}" /> </button>
				</form:form>
			</td>
			<td>
				<form:form method="POST" action="${deleteUrl}/${user.id}">
					<button type="submit" class="button-text button-delete fg-button ui-state-default ui-corner-all confirmLink"><span class="ui-icon ui-icon-closethick button-icon"></span>  <c:out value="${deleteLabel}" /> </button>
				</form:form>
			</td>
			<td>
				<form:form method="GET" action="${passwordEditUrl}/${user.id}" >
					<button type="submit" class="button-text button-delete fg-button ui-state-default ui-corner-all"><span class="ui-icon ui-icon-transferthick-e-w button-icon"></span>  <c:out value="${passwordEditLabel}" /> </button>
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
<div class="newBtn"><a class="button-text fg-button  button-add ui-state-default ui-corner-all" href="${addUrl}"><span class="ui-icon ui-icon-circle-plus button-icon"></span> Agregar</a></div>