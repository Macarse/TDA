<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script language='javascript' type='text/javascript'> 
    $(document).ready(function(){
        $("#close-itinerary-banner").click(
        		function () {
        			$("#welcome-banner").hide();
        		});
    }); 
</script>

<div id="welcome-banner" style="margin: 10px 10px 10px 10px;">
    <div class="ui-widget" style="float: left; font-size: 12px; width: 100%;">
        <div class="ui-state-highlight ui-corner-all" style="margin-top: 10px; padding: 0 .7em;"> 
            <p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
            <strong>Itinerario:</strong>
            <c:choose>
                <c:when test="${empty currentItinerary.beginningDate }">
                    No se ha definido el itinerario. Click <a href="${pageContext.request.contextPath }/itinerary/add">aquí</a> para hacerlo.
                </c:when>
                <c:otherwise>
                    El próximo viaje es desde el <b><fmt:formatDate value="${currentItinerary.beginningDate }" pattern="dd/MM/yyyy"/></b>  hasta el <b><fmt:formatDate value="${currentItinerary.endDate}" pattern="dd/MM/yyyy"/></b>.
                     Haga click <a href="${pageContext.request.contextPath }/itinerary/edit/${currentItinerary.id}">aquí</a> para cambiarlo.
                </c:otherwise>
            </c:choose>
            <span id="close-itinerary-banner"><strong>[x]</strong></span>
            </p>
        </div>
    </div>
</div>

<c:if test="${!empty param.message}">
	<div class="message">
		<fmt:message key="${param.message }" />
	</div>
</c:if>

<c:choose>
<c:when test="${paginator.totalResultsCount<=0}">
	<div style="font-size: 15px;">No hay pacientes en el tren</div>
</c:when>
<c:otherwise>

<div id="search-button">
	<a href="#" style="font-size: 14px;"><span class="ui-icon ui-icon-search button-icon"> </span> Buscar paciente</a>
</div>

<div class="filter-container" style="display:none;" id="filter-containter">
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
							<img src="${pageContext.request.contextPath}/themes/default/image/ok.png" /> <a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/socialworker/${patientintrain.socialworkerform.id}/edit" class="trigger">Formulario Trabajador Social</a> <br/>
						</c:when>
						<c:otherwise>
			  				<img src="${pageContext.request.contextPath}/themes/default/image/edit.gif" /><a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/socialworker/new" class="trigger">Formulario Trabajador Social</a> <br/>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${patientintrain.padiatricianform != null}">
							<img src="${pageContext.request.contextPath}/themes/default/image/ok.png" /> <a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/pediatrician/${patientintrain.padiatricianform.id}/edit" class="trigger">Formulario Pediatra</a> <br/>
						</c:when>
						<c:otherwise>
							<img src="${pageContext.request.contextPath}/themes/default/image/edit.gif" /> <a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/pediatrician/new" class="trigger">Formulario Pediatra</a> <br/>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${patientintrain.nurseform != null}">
							<img src="${pageContext.request.contextPath}/themes/default/image/ok.png" /> <a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/nurse/${patientintrain.nurseform.id}/edit">Formulario Enfermero</a> <br/>
						</c:when>
						<c:otherwise>
							<img src="${pageContext.request.contextPath}/themes/default/image/edit.gif" /> <a href="${pageContext.request.contextPath}/patient/${patientintrain.patient.id}/nurse/new">Formulario Enfermería</a> <br/>
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
				<td align="center"><a class="button-text fg-button button-add ui-state-default ui-corner-all" href="#" onClick="confirmDownInTrain(${patientintrain.patient.id});"><span class="ui-icon ui-icon-arrowreturnthick-1-s button-icon"></span> Bajar </a></td>
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
</c:otherwise>
</c:choose>