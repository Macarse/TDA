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

<div>
Pacientes en el tren
</div>

<div class="table-container">
	<table class="list-table">
	<thead>
		<tr>
			<th>
				<fmt:message key="welcome.name" />
			</th>
			<th>
				<fmt:message key="welcome.forms" />
			</th>
			<th>
				<fmt:message key="welcome.location" />
			</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${patientList}" var="patientintrain">
			<tr>
				<td>${patientintrain.patient.firstName} ${patientintrain.patient.lastName}</td>
				<td>
					<c:choose>
						<c:when test="${patientintrain.socialworkerform != null}">
							<a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/socialworker/${patientintrain.socialworkerform.id}/edit">Formulario Trabajador Social</a> <br/>
						</c:when>
						<c:otherwise>
			  				<a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/socialworker/new">Formulario Trabajador Social</a> <br/>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/socialworker/new">Formulario Pediatra</a> <br/>
					<a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/socialworker/new">Formulario Enfermero</a> <br/>
					<a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/socialworker/new">Formulario Dentista</a> <br/>
					</td>
				<td> </td>
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

<div>
Buscar paciente
</div>