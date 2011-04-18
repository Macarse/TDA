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

<script language='javascript' type='text/javascript'>
	$(document).ready(function(){
		$("#search-button").click(function(){
			$("#filter-containter").slideToggle('slow');
		});
	});
</script>

<div id="search-button">
	<a href="#" style="font-size: 14px;"><span class="ui-icon ui-icon-search button-icon"> </span> Buscar Item</a>
</div>

<div class="filter-container" style="display:none;" id="filter-containter">
	<jsp:include page="/WEB-INF/views/item/filter.jsp" flush="true"/>
</div>

<table class="list-table">
	<thead>
		<tr>
			<th>
				<a href="?orderField=name&orderAscending=<c:out value="${!paginator.orderAscending}"/>&${params}">
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
				<a href="?orderField=description&orderAscending=<c:out value="${!paginator.orderAscending}"/>&${params}">
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
				<a href="?orderField=quantity&orderAscending=<c:out value="${!paginator.orderAscending}&${params}"/>">
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
				<a href="?orderField=category&orderAscending=<c:out value="${!paginator.orderAscending}&${params}"/>">
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
				<a href="?orderField=measureUnit&orderAscending=<c:out value="${!paginator.orderAscending}&${params}"/>">
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
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${itemList}" var="item">
			<tr>
				<td>${item.name}</td>
				<td>${item.description}</td>
				<td>${item.quantity}</td>
				<td>${item.category.description}</td>
				<td>${item.measureUnit.description}</td>
				<td><form:form method="GET"
					action="${editUrl}/${item.id}">
					<button type="submit" class="button-text button-edit fg-button ui-state-default ui-corner-all"><span class="ui-icon ui-icon-transferthick-e-w button-icon"></span> Editar</button>
				</form:form></td>
				<td align="center"><form:form method="POST"
					action="${deleteUrl}/${item.id}">
						<button type="submit" class="button-text button-delete fg-button ui-state-default ui-corner-all confirmLink"><span class="ui-icon ui-icon-closethick button-icon"></span> Eliminar</button>
				</form:form></td>
			</tr>
		</c:forEach>
	</tbody>

	<tfoot>
		<tr>
			<td><a class="button-text fg-button  button-add ui-state-default ui-corner-all" href="${addUrl}"><span class="ui-icon ui-icon-circle-plus button-icon"></span> Agregar</a></td>
		  	<td colspan="2">
				<jsp:include page="/WEB-INF/views/paginator/paginator.jsp" flush="true"/>
		  	</td>
		</tr>
	</tfoot>
</table>
