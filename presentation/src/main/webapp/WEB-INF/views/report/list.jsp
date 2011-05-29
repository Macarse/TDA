<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<span style="font-size:15px;"><fmt:message key="report.availables" /></span>

	<div class="reportRow">
		<form:form modelAttribute="configReport" action="${pageContext.request.contextPath}/report/patientReport" method="get">
			<table>
				<thead>
					<tr>
						<th colspan="2">
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
						<td>
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
			<table>
			<thead>
					<tr>
						<th colspan="2">
							<fmt:message key="report.patientReportDate" />
						</th>
					</tr>
				</thead>
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
						<td>
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
				<form:form modelAttribute="configReport" action="${pageContext.request.contextPath}/report/sexGraphReport" method="get">
			<table>
			<thead>
					<tr>
						<th colspan="2">
							<fmt:message key="report.sexGraphReport" />
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
						<td>
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
				<form:form modelAttribute="configReport" action="${pageContext.request.contextPath}/report/itineraryReport" method="get">
			<table>
			<thead>
					<tr>
						<th colspan="2">
							<fmt:message key="report.itineraryReport" />
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
						<td>
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
				<form:form modelAttribute="configReport" action="${pageContext.request.contextPath}/report/patientReportAge" method="get">
			<table>
			<thead>
					<tr>
						<th colspan="2">
							<fmt:message key="report.patientReportAge" />
						</th>
					</tr>
				</thead>
				<tbody>
	
					<tr>
						<td><form:label for="ageFrom" path="ageFrom" cssErrorClass="error">
							<fmt:message key="report.ageFrom" /></form:label></td>
						<td><form:input path="ageFrom" /> <form:errors path="ageFrom" /></td></tr>
					<tr>
						<td><form:label for="ageTo" path="ageTo" cssErrorClass="error">
							<fmt:message key="report.ageTo" /></form:label></td>
						<td><form:input path="ageTo" /> <form:errors path="ageTo" /></td></tr>
						
	
					<tr>
						<td><form:label for="format" path="format" cssErrorClass="error">
							<fmt:message key="report.format" /></form:label></td>
						<td><form:select path="format" items="${allFormat}"/> <form:errors path="format" /></td></tr>
						
				</tbody>
				<tfoot>
					<tr>
						<td>
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
				<form:form modelAttribute="configReport" action="${pageContext.request.contextPath}/report/ageGraphReport" method="get">
			<table>
			<thead>
					<tr>
						<th colspan="2">
							<fmt:message key="report.ageGraphReport" />
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
						<td>
						<div class="filter-submit">	
							<button type="submit" class="button-text fg-button ui-state-default ui-corner-all"><span class="ui-icon button-icon"></span> <fmt:message key="report.export" /></button>
						</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</form:form>
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