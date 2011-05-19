<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/sync/do" var="syncdo" />

<div class="filter-container">

	<span style="font-size:15px;">Reportes disponibles:
	</span>
	
	<table>
		<tr>
			<td>
				<a href="${pageContext.request.contextPath}/report/patientReport"><span>de Pacientes</span></a>
			</td>
		</tr>
	</table>

	<c:if test="${!empty resultMessage}">
	<div id="respMsg" style="height:30px; font-size:15px;">
		<c:out value="${resultMessage}"></c:out>
	</div>
</c:if>
</div>