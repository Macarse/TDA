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
Buscar paciente
</div>
<div class="filter-container">
	<jsp:include page="/WEB-INF/views/welcome/filter.jsp" flush="true"/>
</div>

<div class="table-container">
	<table class="list-table">
	<thead>
		<tr>
			<th>
				<fmt:message key="welcome.name" />
			</th>
			<th>
				<div class="welcome-forms">
					<fmt:message key="welcome.forms" />
				</div>
			</th>
			<th>
				<div class="welcome-location">
					<fmt:message key="welcome.location" />
				</div>
			</th>
			<th>
				<fmt:message key="welcome.options" />
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
							<img src="${pageContext.request.contextPath}/themes/default/image/ok.png" /> <a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/socialworker/${patientintrain.socialworkerform.id}/edit">Formulario Trabajador Social</a> <br/>
						</c:when>
						<c:otherwise>
			  				<img src="${pageContext.request.contextPath}/themes/default/image/edit.gif" /><a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/socialworker/new">Formulario Trabajador Social</a> <br/>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${patientintrain.padiatricianform != null}">
							<img src="${pageContext.request.contextPath}/themes/default/image/ok.png" /> <a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/pediatrician/${patientintrain.padiatricianform.id}/edit">Formulario Pediatra</a> <br/>
						</c:when>
						<c:otherwise>
							<img src="${pageContext.request.contextPath}/themes/default/image/edit.gif" /> <a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/pediatrician/new">Formulario Pediatra</a> <br/>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${patientintrain.nurseform != null}">
							<img src="${pageContext.request.contextPath}/themes/default/image/ok.png" /> <a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/nurse/${patientintrain.nurseform.id}/edit">Formulario Enfermero</a> <br/>
						</c:when>
						<c:otherwise>
							<img src="${pageContext.request.contextPath}/themes/default/image/edit.gif" /> <a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/nurse/new">Formulario Enfermeno</a> <br/>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${patientintrain.dentistform != null}">
							<img src="${pageContext.request.contextPath}/themes/default/image/ok.png" /> <a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/dentist/${patientintrain.dentistform.id}/edit">Formulario Dentista</a> <br/>
						</c:when>
						<c:otherwise>
							<img src="${pageContext.request.contextPath}/themes/default/image/edit.gif" /> <a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/dentist/new">Formulario Dentista</a> <br/>
						</c:otherwise>
					</c:choose>
					</td>
				<td>
					<c:choose>
						<c:when test="${patientintrain.user != null}">
							${patientintrain.user.username}
						</c:when>
						<c:otherwise>
							<fmt:message key="welcome.state.waiting" />
						</c:otherwise>
					</c:choose>
				</td>
				<td align="center"><a class="button-text fg-button button-add ui-state-default ui-corner-all" href="#" onClick="refreshPatients()"><span class="ui-icon ui-icon-arrowreturnthick-1-s button-icon"></span> Bajar </a></td>
			</tr>
		</c:forEach>
	</tbody>

	<tfoot>
		<tr>
		  <td colspan="3">
			<jsp:include page="/WEB-INF/views/paginator/paginator.jsp" flush="true"/>
		  </td>
		</tr>
	</tfoot>
</table>
</div>
