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

		$("#size").focus();

		$("#percentile").keydown(function(e){
			changeTab(e, 'TAmin');
		});

		$("#saturation").keydown(function(e){
			changeTab(e,'vaxines1');
		});

		$("#vaxines12").keydown(function(e){
			changeTab(e,'nurseActions1');
		});

        if( ('<c:out value="${editable}"></c:out>') == 'false' || userRole != "nurse" )
        	$('input, select').attr('disabled', 'disabled');

        if (!checkTabErrors('#form-tabs', ["tab-vitalChecks","tab-taControl", "tab-vaccines", "tab-actions"])){
        	$("#size").focus();
         }
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

<h2><c:if test="${NurseForm.new}"><fmt:message key="nurse.form.new" /></c:if> <fmt:message key="nurse.form.form" /> de '<i>${nurseForm.patient.firstName} ${nurseForm.patient.lastName}</i>'</h2>
<c:if test="${editable != null && !editable}">
	<h3>Version Final - No editable - Fecha <fmt:formatDate value="${NurseForm.fillingDate}" pattern="dd/MM/yyyy"/></h3>
</c:if>

<script type="text/javascript">
	if( userRole != "nurse" )
		document.write("<h3>Este formulario no pertenece a su rol</h3>");
</script>

<button type="submit" style="width:200px;" onclick="window.open( contextPath + '/patient/history/<c:out value="${nurseForm.patient.id}"></c:out>' )" class="button-text button-delete fg-button ui-state-default ui-corner-all"><span class="ui-icon ui-icon-arrow-1-n button-icon"></span>Abrir Historia del paciente</button>

<form:form modelAttribute="nurseForm" method="post" id="myform">
	<div id="form-tabs" class="form-tabs">
		<ul>
			<li><a href="#tab-vitalChecks"><fmt:message key="nurse.form.vitalChecks" /></a></li>
			<li><a href="#tab-taControl"><fmt:message key="nurse.form.taControl" /></a></li>
			<li><a href="#tab-vaccines"><fmt:message key="nurse.form.vaccines" /></a></li>
			<li><a href="#tab-actions"><fmt:message key="nurse.form.actions" /></a></li>
		</ul>
		
	<div id="tab-vitalChecks" class="nurseform">
		<table width="100%">
			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
			</tr>

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
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
			</tr>
				
		</table>
	</div>

	<div id="tab-taControl" class="nurseform">
		<table width="100%">

			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
			</tr>

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
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
			</tr>
		
		</table>
	</div>

	<div id="tab-vaccines" class="nurseform">
	<table width="100%">
		<tr>
			<td colspan="${fieldsPerRow}" class="doubleband">
			<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
			<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
			</td>
		</tr>
	
			<c:set var="count" value="0" />
			<c:forEach var="vaxine" items="${allVaxines}">
				<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
					<td><form:checkbox path="vaxines" value="${vaxine}" /> ${vaxine.name}</td>
				<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
			    <c:set var="count" value="${count+1}" />
			</c:forEach>
			<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
			<tr><td><form:errors path="vaxines" /></td></tr>
		<tr>
			<td colspan="${fieldsPerRow}" class="doubleband">
			<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
			<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
			</td>
		</tr>
			
	</table>
	</div>

	<div id="tab-actions" class="nurseform">
		<table width="100%">
			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				</td>
			</tr>
		
			<tr>
				<th colspan="${fieldsPerRow }"><form:label for="nurseActions" path="nurseActions"
					cssErrorClass="error"><fmt:message key="nurse.form.actions" /></form:label></th></tr>
			<c:set var="count" value="0" />
			<c:forEach var="nurseAction" items="${allNurseActions}">
				<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
					<td><form:checkbox path="nurseActions" value="${nurseAction}" /> ${nurseAction.description}</td>
				<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
			    <c:set var="count" value="${count+1}" />
			</c:forEach>
			<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
			<tr><td><form:errors path="nurseActions" /></td></tr>
		
			<tr>
				<th colspan="${fieldsPerRow }"><form:label for="observations" path="observations" cssErrorClass="error"><fmt:message key="nurse.form.observations" /></form:label></th></tr>
			<tr> 
				<th colspan="${fieldsPerRow }"><form:textarea path="observations" /> <form:errors path="observations" /></td></tr>
				
			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				</td>
			</tr>
				
		</table>
	</div>
	<div class="form-save-button">
		<input id="formSubmitBtn" type="submit" value="Guardar Formulario" onClick="_isDirty = false;"/>
	</div>
	<div style="clear: both">
		<hr>
	</div>
  </div>
</form:form>
<hr>