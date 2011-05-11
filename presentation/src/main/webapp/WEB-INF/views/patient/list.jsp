<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Edit/Delete buttons variables -->
<spring:url value="patient" var="startUrl" />
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

<c:choose>
<c:when test="${paginator.totalResultsCount<=0}">
	<div>No se encontraron registros</div>
</c:when>
<c:otherwise>

<div id="search-button">
	<a href="#" style="font-size: 14px;"><span class="ui-icon ui-icon-search button-icon"> </span> Buscar paciente</a>
</div>

<div class="filter-container" style="display:none;" id="filter-containter">
	<jsp:include page="/WEB-INF/views/patient/filter.jsp" flush="true"/>
</div>

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
			<th>
				<a href="?orderField=birthdate&orderAscending=<c:out value="${!paginator.orderAscending}&${params}"/>">
					<fmt:message key="patient.form.birthdate" />
				</a>
				<c:if test="${paginator.orderField=='birthdate'}">
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
				<a href="?orderField=dni&orderAscending=<c:out value="${!paginator.orderAscending}&${params}"/>">
					<fmt:message key="patient.form.dni" />
				</a>
				<c:if test="${paginator.orderField=='dni'}">
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

		<c:forEach items="${patientList}" var="patient" varStatus="indexStatus">
			<tr>
				<td>${patient.firstName}</td>
				<td>${patient.lastName}</td>
				<td>${patient.sex.description}</td>
				<td><fmt:formatDate value="${patient.birthdate}" pattern="dd/MM/yyyy"/></td>
				<td>${patient.dni}</td>
				<td><form:form method="GET"
					action="${editUrl}/${patient.id}">
					<button type="submit" class="button-text button-edit fg-button ui-state-default ui-corner-all"><span class="ui-icon ui-icon-transferthick-e-w button-icon"></span> ${editLabel}</button>
				</form:form></td>
				<td><form:form method="POST"
					action="${deleteUrl}/${patient.id}">
					<button type="submit" class="button-text button-delete fg-button ui-state-default ui-corner-all  confirmLink"><span class="ui-icon ui-icon-closethick button-icon"></span> ${deleteLabel}</button>
				</form:form></td>
				<td>
						<c:choose>
							<c:when test="${patientInTrainArray[indexStatus.index]}">
								<a id="switchbutton${patient.id}" onclick="confirmDownInTrain(${patient.id})" class="button-text fg-button button-add ui-state-default ui-corner-all" href="#">
								<span class="ui-icon ui-icon-arrowreturnthick-1-s button-icon"></span>
								<span>Bajar</span>
								</a>
							</c:when>
							<c:otherwise>
								<a id="switchbutton${patient.id}" onclick="switchInTrain(${patient.id})" class="button-text fg-button button-add ui-state-default ui-corner-all" href="#">
								<span class="ui-icon ui-icon-arrowreturnthick-1-n button-icon"></span>
								<span>Subir</span>
								</a>
							</c:otherwise>
						</c:choose>
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
</c:otherwise>
</c:choose>
<div class="newBtn"><a class="button-text fg-button  button-add ui-state-default ui-corner-all" href="${addUrl}"><span class="ui-icon ui-icon-circle-plus button-icon"></span> Agregar</a></div>


<script language='javascript' type='text/javascript'>
	$(document).ready(function(){
		$("#search-button").click(function(){
			$("#filter-containter").slideToggle('slow');
		});
	});
</script>