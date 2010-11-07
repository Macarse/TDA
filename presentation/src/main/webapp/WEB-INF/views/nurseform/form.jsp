<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
<!--
	$(document).ready(function(){
		$("#form-tabs").tabs();
	});
//-->
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

<form:form modelAttribute="nurseForm" method="post">
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
				<th><form:label for="size" path="size" cssErrorClass="error"><fmt:message key="nurse.form.size" /></form:label></th> 
				<td><form:input path="size" /> <form:errors path="size" /></td></tr>
			<tr>
				<th><form:label for="weight" path="weight" cssErrorClass="error"><fmt:message key="nurse.form.weight" /></form:label></th>
				<td><form:input path="weight" /> <form:errors path="weight" /></td></tr>
			<tr>
				<th><form:label for="headCircumference" path="headCircumference" cssErrorClass="error"><fmt:message key="nurse.form.headCircumference" /></form:label></th>
				<td><form:input path="headCircumference" /> <form:errors path="headCircumference" /></td></tr>
			<tr>
				<th><form:label for="percentile" path="percentile" cssErrorClass="error"><fmt:message key="nurse.form.percentile" /></form:label></th>
				<td><form:input path="percentile" /> <form:errors path="percentile" /></td></tr>
		</table>
	</div>

	<div id="tab-taControl" class="nurseform">
		<table>

			<tr>
				<th><form:label for="TAmin" path="TAmin" cssErrorClass="error"><fmt:message key="nurse.form.TAmin" /></form:label></th> 
				<td><form:input path="TAmin" /> <form:errors path="TAmin" /></td></tr>
			<tr>
				<th><form:label for="TAmax" path="TAmax" cssErrorClass="error"><fmt:message key="nurse.form.TAmax" /></form:label></th>
				<td><form:input path="TAmax" /> <form:errors path="TAmax" /></td></tr>
				
			<tr>
				<th><form:label for="TempMin" path="TempMin" cssErrorClass="error"><fmt:message key="nurse.form.TempMin" /></form:label></th>
				<td><form:input path="TempMin" /> <form:errors path="TempMin" /></td></tr>
				
			<tr>
				<th><form:label for="TempMax" path="TempMax" cssErrorClass="error"><fmt:message key="nurse.form.TempMax" /></form:label></th>
				<td><form:input path="TempMax" /> <form:errors path="TempMax" /></td></tr>
				
			<tr>
				<th><form:label for="saturation" path="saturation" cssErrorClass="error"><fmt:message key="nurse.form.saturation" /></form:label></th>
				<td><form:input path="saturation" /> <form:errors path="saturation" /></td></tr>
		</table>
	</div>

	<div id="tab-vaccines" class="nurseform">
	<table>
			<tr>
			<td>
				<form:checkboxes items="${allVaxines}" path="vaxines" itemLabel="name" itemValue="id"/>
			</td>
			</tr>
			
<!--			<c:forEach var="vaxine" items="${allVaxines}">-->
<!--			<c:if test="${vaxine.optative}">-->
<!--			<tr>-->
<!--				<td>-->
<!--				<form:checkbox path="vaxines" value="${vaxine.id}" />-->
<!--				<form:label path="vaxines" for="vaxines">${vaxine.name}</form:label>-->
<!--				</td>-->
<!--			</tr> -->
<!--			</c:if>-->
<!--			</c:forEach>-->
	</table>
	</div>

	<div id="tab-actions" class="nurseform">
	
	<p><form:label for="nurseActions" path="nurseActions"
		cssErrorClass="error"><fmt:message key="nurse.form.actions" /></form:label><br />
	<form:checkboxes items="${allNurseActions}" path="nurseActions" itemLabel="description"/><form:errors path="nurseActions" /></p>
	
		<table>
			<tr>
				<th><form:label for="observations" path="observations" cssErrorClass="error"><fmt:message key="nurse.form.observations" /></form:label></th> 
				<td><form:textarea path="observations" /> <form:errors path="observations" /></td></tr>
		</table>
	
	</div>
	
	<input type="submit" />
  </div>
</form:form>
<hr>