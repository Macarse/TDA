<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:if test="${!empty param.message}">
	<div class="message">
		<fmt:message key="${param.message }" />
	</div>
</c:if>

<div class="filter-container">
	<jsp:include page="/WEB-INF/views/patient/filter.jsp" flush="true"/>
</div>

<div class="table-container">
	<table class="list-table">
	<thead>
		<tr>
			<th>
				<a href="?orderField=firstName&orderAscending=<c:out value="${!paginator.orderAscending}&${params}"/>">
					<fmt:message key="patient.form.firstName" />
				</a>
				<c:if test="${paginator.orderField=='firstName'}">
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
				<a href="?orderField=lastName&orderAscending=<c:out value="${!paginator.orderAscending}&${params}"/>">
					<fmt:message key="patient.form.lastName" />
				</a>
				<c:if test="${paginator.orderField=='lastName'}">
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
				<a href="?orderField=sex&orderAscending=<c:out value="${!paginator.orderAscending}&${params}"/>">
					<fmt:message key="patient.form.sex" />
				</a>
				<c:if test="${paginator.orderField=='sex'}">
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


		<c:forEach items="${patientList}" var="patient">
			<tr>
				<td>${patient.firstName}</td>
				<td>${patient.lastName}</td>
				<td>${patient.sex}</td>
				<td><a href="${pageContext.request.contextPath}/patient/${patient.id}/socialworker/new">Formulario Trabajador Social</a></td>
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
</div>