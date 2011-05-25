<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/sync/do" var="syncdo" />

<div class="filter-container">

	<fmt:message key="report.availables" />

		<div class="reportRow">
			<form:form modelAttribute="configReport" action="${pageContext.request.contextPath}/report/patientReport" method="get">
				<table>
					<thead>
						<tr>
							<th>
								<fmt:message key="report.patientReport" />
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><form:label for="format" path="format" cssErrorClass="error">
								<fmt:message key="report.format" /></form:label></td>
							<td><form:select path="format" items="${allFormat}"/> <form:errors path="format" /></td></tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
							<div class="filter-submit">	
								<button type="submit" class="button-text fg-button ui-state-default ui-corner-all"><span class="ui-icon button-icon"></span> <fmt:message key="report.export" /></button>
							</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</form:form>
		</div>
		<br></br>
		<div class="reportRow">
			<form:form modelAttribute="configReport" action="${pageContext.request.contextPath}/report/patientReportDate" method="get">
				<legend><fmt:message key="report.patientReportDate" /></legend>
				<table>
					<tbody>
						<tr>
							<td><form:label for="dateFrom" path="dateFrom" cssErrorClass="error">
								<fmt:message key="report.dateFrom" /></form:label></td>
							<td><form:input path="dateFrom" /> <form:errors path="dateFrom" /></td></tr>
						<tr>
							<td><form:label for="dateTo" path="dateTo" cssErrorClass="error">
								<fmt:message key="report.dateTo" /></form:label></td>
							<td><form:input path="dateTo" /> <form:errors path="dateTo" /></td></tr>
							
						<tr>
							<td><form:label for="format" path="format" cssErrorClass="error">
								<fmt:message key="report.format" /></form:label></td>
							<td><form:select path="format" items="${allFormat}"/> <form:errors path="format" /></td></tr>
							
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
							<div class="filter-submit">	
								<button type="submit" class="button-text fg-button ui-state-default ui-corner-all"><span class="ui-icon button-icon"></span> <fmt:message key="report.export" /></button>
							</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</form:form>
		</div>
</div>

<script>
	$(function() {
		$( "#dateFrom" ).datepicker(
			{ dateFormat: 'dd/mm/yy',
			  monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
			  monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
			  dayNamesMin: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
			  changeYear: true,
			  changeMonth: true,
			  yearRange: 'c-100,c+00'
		    }
		);

		$( "#dateTo" ).datepicker(
				{ dateFormat: 'dd/mm/yy',
				  monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
				  monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
				  dayNamesMin: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
				  changeYear: true,
				  changeMonth: true,
				  yearRange: 'c-100,c+00'
			    }
		);
	});
</script>