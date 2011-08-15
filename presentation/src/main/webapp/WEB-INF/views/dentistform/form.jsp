<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">

	$(document).ready(function() {

		$("#form-tabs").tabs();

		$("#myform").change(function() {
			_isDirty = true;
		});

	$("#myform").submit(function() {
		var tooths = getData();
		$("#tooths").val(tooths);
		return true;
	});

	$("#comment").keydown(function(e){
		changeTab(e,'tab-odontogram');
	});

     if( ('<c:out value="${editable}"></c:out>') == 'false' || userRole != "dentist")
     	$('input, select').attr('disabled', 'disabled');

     if (!checkTabErrors('#form-tabs', ["tab-history","tab-odontogram", "tab-inspection"])){
    	 $("#receivedAttentionInTrain1").focus();
     }
	});

	window.onbeforeunload = nextTabUnload;
	var _isDirty = false;

	function submitForm() {
	 _isDirty = false;
	}

</script>

<!-- aux variables -->
<c:set var="count" value="0" />
<c:set var="fieldsPerRow" value="3" />

<c:choose>
	<c:when test="${dentistForm.new}"><c:set var="method" value="post"/></c:when>
	<c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

<h2><c:if test="${dentistForm.new}"><fmt:message key="dentist.form.new" /></c:if><fmt:message key="dentist.form.form" /> de '<i>${dentistForm.patient.firstName} ${dentistForm.patient.lastName}</i>'</h2>
<c:if test="${editable != null && !editable}">
	<h3>Version Final - No editable - Fecha <fmt:formatDate value="${dentistForm.fillingDate}" pattern="dd/MM/yyyy"/></h3>
</c:if>

<script type="text/javascript">
	if( userRole != "dentist" )
		document.write("<h3>Este formulario no pertenece a su rol</h3>");
</script>

<button type="submit" style="width:200px;" onclick="window.open( contextPath + '/patient/history/<c:out value="${dentistForm.patient.id}"></c:out>' )" class="button-text button-delete fg-button ui-state-default ui-corner-all"><span class="ui-icon ui-icon-arrow-1-n button-icon"></span>Abrir Historia del paciente</button>

<form:form modelAttribute="dentistForm" method="post" id="myform">
	<div id="form-tabs" class="form-tabs">
		<ul>
			<li><a href="#tab-history"><fmt:message key="dentist.form.history" /></a></li>
			<li><a href="#tab-odontogram"><fmt:message key="dentist.form.odontogram" /></a></li>
			<li><a href="#tab-inspection"><fmt:message key="dentist.form.inspection" /></a></li>
		</ul>
	<div id="tab-history" class="dentistform">
		<table width="100%">
		
			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
			</tr>
		
			<tr><td>
				<form:label for="receivedAttentionInTrain" path="receivedAttentionInTrain" cssErrorClass="error">
					<fmt:message key="dentist.form.receivedAttentionInTrain" />
				</form:label></td>
				<td><form:checkbox tabindex="1" path="receivedAttentionInTrain" /> <form:errors path="receivedAttentionInTrain" />
			</td></tr>
			
			<tr><td>
				<form:label for="receivedAttentionOutsideTrain" path="receivedAttentionInTrain" cssErrorClass="error">
					<fmt:message key="dentist.form.receivedAttentionOutsideTrain" />
				</form:label></td>
				<td><form:checkbox tabindex="2" path="receivedAttentionOutsideTrain" /> <form:errors path="receivedAttentionOutsideTrain" />
			</td></tr>
			
			<tr><td>
				<form:label for="receivedAnesthesia" path="receivedAnesthesia" cssErrorClass="error">
					<fmt:message key="dentist.form.receivedAnesthesia" />
				</form:label></td>
				<td><form:checkbox tabindex="3" path="receivedAnesthesia" /> <form:errors path="receivedAnesthesia" />
			</td></tr>

			<tr><td>
				<form:label for="haemorrhage" path="haemorrhage" cssErrorClass="error">
					<fmt:message key="dentist.form.haemorrhage" />
				</form:label></td>
				<td><form:checkbox tabindex="4" path="haemorrhage" /> <form:errors path="haemorrhage" />
			</td></tr>

			<tr><td>
				<form:label for="medicineAllergic" path="medicineAllergic" cssErrorClass="error">
					<fmt:message key="dentist.form.medicineAllergic" />
				</form:label></td>
				<td><form:checkbox tabindex="5" path="medicineAllergic" /> <form:errors path="medicineAllergic" />
			</td></tr>
			
			<tr><td>
				<form:label for="yodoAllergic" path="yodoAllergic" cssErrorClass="error">
					<fmt:message key="dentist.form.yodoAllergic" />
				</form:label></td>
				<td><form:checkbox tabindex="6" path="yodoAllergic" /> <form:errors path="yodoAllergic" />
			</td></tr>
			
			<tr>
				<th colspan="2"><form:label for="comment" path="comment" cssErrorClass="error"><fmt:message key="dentist.form.comment" /></form:label></th></tr>
			<tr>
				<td colspan="2"><form:textarea tabindex="7" path="comment" /> <form:errors path="comment" /></td></tr>
		
			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
			</tr>
		
		</table>
	</div>

	<div id="tab-odontogram" class="dentistform">
		<table width="100%">

			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
			</tr>

			<tr>
				<td>
					<form:label for="cpod" path="cpod" cssErrorClass="error">
						<fmt:message key="dentist.form.cpod" />
					</form:label>
				</td>
				<td>
					<form:input tabindex="8" path="cpod" />
				</td>
			</tr>
			<tr>
				<td>
					<form:errors path="cpod" />
				</td>
			</tr>

			<tr>
				<td>
					<form:label for="cpos" path="cpos" cssErrorClass="error">
						<fmt:message key="dentist.form.cpos" />
					</form:label>
				</td>
				<td>
					<form:input tabindex="9" path="cpos" />
				</td>
			</tr>
			<tr>
				<td>
					<form:errors path="cpos" />
				</td>
			</tr>
			
			<tr>
				<td>
					<form:label for="ceod" path="ceod" cssErrorClass="error">
						<fmt:message key="dentist.form.ceod" />
					</form:label>
				</td>
				<td>
					<form:input tabindex="10" path="ceod" />
				</td>
			</tr>
			<tr>
				<td>
					<form:errors path="ceod" />
				</td>
			</tr>
			
			<tr>
				<td>
					<form:label for="ceos" path="ceos" cssErrorClass="error">
						<fmt:message key="dentist.form.ceos" />
					</form:label>
				</td>
				<td>
					<form:input tabindex="11" path="ceos" />
				</td>
			</tr>
			<tr>
				<td>
					<form:errors path="ceos" />
				</td>
			</tr>

			<tr>
				<td>
					<form:label for="tooths" path="tooths" cssErrorClass="error">
						<fmt:message key="dentist.form.tooths" />
					</form:label>
				</td>
				<td>
					<form:hidden path="tooths"/>
				</td>
			</tr>
			<tr>
				<td>
					<form:errors path="tooths" />
				</td>
			</tr>

		<tr>
		<td colspan="2">
		<br/><br/>

		<div id="odontogram">
			<jsp:include flush="true" page="odontogram.jsp">
 				<jsp:param name="paramNotUsed" value="" />
			</jsp:include>
		</div>
		</td>
		</tr>
		
		<tr>
			<td colspan="${fieldsPerRow}" class="doubleband">
			<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
			<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
			</td>
		</tr>
			
		</table>
	</div>

	<div id="tab-inspection" class="dentistform">
		<table width="100%">
		
			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				</td>
			</tr>
		
			<tr>
				<th colspan="2">
					<form:label for="stains" path="stains" cssErrorClass="error">
						<fmt:message key="dentist.form.stains" />
					</form:label>
				</th></tr>
			<tr>
				<td colspan="2">
					<form:textarea tabindex="12" path="stains" />
				</td>
			</tr>
			<tr>
				<td>
					<form:errors path="stains" />
				</td>
			</tr>
			
			<tr><td>
				<form:label for="cleftPalate" path="cleftPalate" cssErrorClass="error">
					<fmt:message key="dentist.form.cleftPalate" />
				</form:label></td>
				<td><form:checkbox tabindex="13" path="cleftPalate" /> <form:errors path="cleftPalate" />
			</td></tr>
			
			<tr><td>
				<form:label for="cleftLip" path="cleftLip" cssErrorClass="error">
					<fmt:message key="dentist.form.cleftLip" />
				</form:label></td>
				<td><form:checkbox tabindex="14" path="cleftLip" /> <form:errors path="cleftLip" />
			</td></tr>
			
			<tr>
				<th colspan="2"><form:label for="cleftComments" path="cleftComments" cssErrorClass="error"><fmt:message key="dentist.form.cleftComments" /></form:label></th></tr>
			<tr> 
				<td colspan="2"><form:textarea tabindex="15" path="cleftComments" /> <form:errors path="cleftComments" /></td></tr>
				
			<tr><th colspan="2">
					<form:label for="gumDisease" path="gumDisease" cssErrorClass="error">
						<fmt:message key="dentist.form.gumDisease" />
					</form:label></th></tr>
					
					<c:set var="count" value="0" />
					<c:forEach var="gumDisease" items="${gumDisease}">
						<c:if test="${count%2 == 0 }"><tr></c:if>
							<td><form:radiobutton tabindex="${count+16}" path="gumDisease" value="${gumDisease}" /> ${gumDisease.description}</td>
						<c:if test="${count%2 == 1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%2 == 1 }"></tr></c:if>
					<tr><td><form:errors path="gumDisease" /></td>
			</tr>
			
			<tr><th colspan="2">
					<form:label for="periodontalDisease" path="periodontalDisease" cssErrorClass="error">
						<fmt:message key="dentist.form.periodontalDisease" />
					</form:label></th></tr>
					
					<c:set var="count" value="0" />
					<c:forEach var="periodontalDisease" items="${periodontalDisease}">
						<c:if test="${count%2 == 0 }"><tr></c:if>
							<td><form:radiobutton tabindex="${count+19}" path="periodontalDisease" value="${periodontalDisease}" /> ${periodontalDisease.description}</td>
						<c:if test="${count%2 == 1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%2 == 1 }"></tr></c:if>
					<tr><td><form:errors path="periodontalDisease" /></td>
			</tr>
			
			<tr><th colspan="2">
					<form:label for="toothMobility" path="toothMobility" cssErrorClass="error">
						<fmt:message key="dentist.form.toothMobility" />
					</form:label></th></tr>
					
					<c:set var="count" value="0" />
					<c:forEach var="toothMobility" items="${toothMobility}">
						<c:if test="${count%2 == 0 }"><tr></c:if>
							<td><form:radiobutton tabindex="${count+22}" path="toothMobility" value="${toothMobility}" /> ${toothMobility.description}</td>
						<c:if test="${count%2 == 1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%2 == 1 }"></tr></c:if>
					<tr><td><form:errors path="toothMobility" /></td>
			</tr>
			
			<tr>
				<th colspan="2"><form:label for="severityLevelComments" path="severityLevelComments" cssErrorClass="error"><fmt:message key="dentist.form.severityLevelComments" /></form:label></th></tr>
			<tr>
				<td><form:textarea tabindex="25" path="severityLevelComments" /> <form:errors path="severityLevelComments" /></td></tr>
				
		<tr>
			<td colspan="${fieldsPerRow}" class="doubleband">
			<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
			</td>
		</tr>
			
			
		</table>
	</div>
	<div class="form-save-button">
		<input id="formSubmitBtn" tabindex="26" type="submit" value="Guardar Formulario" onclick="_isDirty = false;"/>
	</div>
	<div style="clear: both">
		<hr>
	</div>
  </div>
</form:form>
<hr>