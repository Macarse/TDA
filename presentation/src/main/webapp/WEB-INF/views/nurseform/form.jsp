<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	$(document).ready(function(){
		$("#form-tabs").tabs();
		$("#myform").change(function() {
			_isDirty = true;
		});
	});

	window.onbeforeunload = nextTabUnload;
	var _isDirty = false;
	
</script>

<!-- aux variables -->
<c:set var="count" value="0" />
<c:set var="fieldsPerRow" value="3" />

<c:choose>
	<c:when test="${nurseForm.new}"><c:set var="method" value="post"/></c:when>
	<c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

<h2><c:if test="${NurseForm.new}"><fmt:message key="nurse.form.new" /></c:if><fmt:message key="nurse.form.form" /></h2>

<b><fmt:message key="nurse.form.patient" /></b> ${nurseForm.patient.firstName} ${nurseForm.patient.lastName}
<br/>

<form:form modelAttribute="nurseForm" method="post" id="myform">
	<div id="form-tabs">
		<ul>
			<li><a href="#tab-vitalChecks"><fmt:message key="nurse.form.vitalChecks" /></a></li>
			<li><a href="#tab-taControl"><fmt:message key="nurse.form.taControl" /></a></li>
			<li><a href="#tab-vaccines"><fmt:message key="nurse.form.vaccines" /></a></li>
			<li><a href="#tab-actions"><fmt:message key="nurse.form.actions" /></a></li>
		</ul>
		
	<div id="tab-vitalChecks" class="nurseform">
		<table>

			<tr>
				<td><form:label for="size" path="size" cssErrorClass="error"><fmt:message key="nurse.form.size" /></form:label></td> 
				<td><form:input path="size" /> <form:errors path="size" /></td></tr>
			<tr>
				<td><form:label for="weight" path="weight" cssErrorClass="error"><fmt:message key="nurse.form.weight" /></form:label></td>
				<td><form:input path="weight" /> <form:errors path="weight" /></td></tr>
			<tr>
				<td><form:label for="headCircumference" path="headCircumference" cssErrorClass="error"><fmt:message key="nurse.form.headCircumference" /></form:label></td>
				<td><form:input path="headCircumference" /> <form:errors path="headCircumference" /></td></tr>
			<tr>
				<td><form:label for="percentile" path="percentile" cssErrorClass="error"><fmt:message key="nurse.form.percentile" /></form:label></td>
				<td><form:input path="percentile" /> <form:errors path="percentile" /></td></tr>
				
		<tr>
		<td>
		<br/><br/>
		<a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a>
		</td>
		</tr>
				
		</table>
	</div>

	<div id="tab-taControl" class="nurseform">
		<table>

			<tr>
				<td><form:label for="TAmin" path="TAmin" cssErrorClass="error"><fmt:message key="nurse.form.TAmin" /></form:label></td> 
				<td><form:input path="TAmin" /> <form:errors path="TAmin" /></td></tr>
			<tr>
				<td><form:label for="TAmax" path="TAmax" cssErrorClass="error"><fmt:message key="nurse.form.TAmax" /></form:label></td>
				<td><form:input path="TAmax" /> <form:errors path="TAmax" /></td></tr>
				
			<tr>
				<td><form:label for="TempMin" path="TempMin" cssErrorClass="error"><fmt:message key="nurse.form.TempMin" /></form:label></td>
				<td><form:input path="TempMin" /> <form:errors path="TempMin" /></td></tr>
				
			<tr>
				<td><form:label for="TempMax" path="TempMax" cssErrorClass="error"><fmt:message key="nurse.form.TempMax" /></form:label></td>
				<td><form:input path="TempMax" /> <form:errors path="TempMax" /></td></tr>
				
			<tr>
				<td><form:label for="saturation" path="saturation" cssErrorClass="error"><fmt:message key="nurse.form.saturation" /></form:label></td>
				<td><form:input path="saturation" /> <form:errors path="saturation" /></td></tr>
		
		<tr>
		<td>
		<br/><br/>
		<a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a>
		</td>
		</tr>
		
		</table>
	</div>

	<div id="tab-vaccines" class="nurseform">
	<table>
			<c:forEach var="vaxine" items="${allVaxines}">
				<c:if test="${count%2 == 0 }"><tr></c:if>
					<td><form:checkbox path="vaxines" value="${vaxine.id}" /> ${vaxine.name}</td>
				<c:if test="${count%2 == 1 }"></tr></c:if>
			    <c:set var="count" value="${count+1}" />
			</c:forEach>
			<c:if test="${count%2 == 1 }"></tr></c:if>
			<tr><td><form:errors path="vaxines" /></td></tr>
			
		<tr>
		<td>
		<br/><br/>
		<a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a>
		</td>
		</tr>
			
	</table>
	</div>

	<div id="tab-actions" class="nurseform">
		<table width="100%">
			<tr>
				<th><form:label for="nurseActions" path="nurseActions"
					cssErrorClass="error"><fmt:message key="nurse.form.actions" /></form:label></th></tr>
			<tr><td><form:checkboxes items="${allNurseActions}" path="nurseActions" itemLabel="description"/><form:errors path="nurseActions" /></td></tr>
			<tr>
				<th><form:label for="observations" path="observations" cssErrorClass="error"><fmt:message key="nurse.form.observations" /></form:label></th></tr>
			<tr> 
				<td><form:textarea path="observations" /> <form:errors path="observations" /></td></tr>
				
		<tr>
		<td>
		<br/><br/>
		<input type="submit" value="Guardar" onClick="_isDirty = false;"/>
		</td>
		</tr>
				
		</table>
	
	</div>
  </div>
</form:form>
<hr>