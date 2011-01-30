<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	$(document).ready(function(){
		$("#form-tabs").tabs();
	});

	window.onbeforeunload = nextTabUnload;
	var _isDirty = true;
</script>

<!-- aux variables -->
<c:set var="count" value="0" />
<c:set var="fieldsPerRow" value="3" />

<c:choose>
	<c:when test="${socialWorkerForm.new}"><c:set var="method" value="post"/></c:when>
	<c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

<h2><c:if test="${socialWorkerForm.new}"><fmt:message key="socialworker.form.new" /></c:if><fmt:message key="socialworker.form.form" /></h2>

<b><fmt:message key="socialworker.form.patient" /></b> ${socialWorkerForm.patient.firstName} ${socialWorkerForm.patient.lastName}
<br/>

<form:form modelAttribute="socialWorkerForm" method="post">
	<div id="form-tabs">
		<ul>
			<li><a href="#tab-gf"><fmt:message key="socialworker.form.familyGroup" /></a></li>
			<li><a href="#tab-viv"><fmt:message key="socialworker.form.livingPlace" /></a></li>
			<li><a href="#tab-esc"><fmt:message key="socialworker.form.scholarity" /></a></li>
			<li><a href="#tab-eco"><fmt:message key="socialworker.form.socialandeconomy" /></a></li>
		</ul>
		
	<!-- Aca empieza la seccion de Grupo Familiar -->
	<div id="tab-gf" class="socialworkerform">
		<table width="100%">
			<tr>
				<th colspan="${fieldsPerRow}"><fmt:message key="socialworker.form.fatherTitle" /></th></tr>
				<tr>
					<td><form:label for="fatherFirstName" path="fatherFirstName" cssErrorClass="error"><fmt:message key="socialworker.form.fatherFirstName" /></form:label>
						<form:input path="fatherFirstName" /> <form:errors path="fatherFirstName" /></td>
					<td><form:label for="fatherLastName" path="fatherLastName" cssErrorClass="error"><fmt:message key="socialworker.form.fatherLastName" /></form:label>
						<form:input path="fatherLastName" /> <form:errors path="fatherLastName" /></td>
					<td><form:label for="fatherAge" path="fatherAge" cssErrorClass="error"><fmt:message key="socialworker.form.fatherAge" /></form:label>
						<form:input path="fatherAge" /> <form:errors path="fatherAge" /></td>
					</tr>
			<tr>
				<th colspan="${fieldsPerRow}"><fmt:message key="socialworker.form.motherTitle" /></th></tr>
				<tr>
					<td><form:label for="motherFirstName" path="motherFirstName" cssErrorClass="error"><fmt:message key="socialworker.form.motherFirstName" /></form:label>
						<form:input path="motherFirstName" /> <form:errors path="motherFirstName" /></td>
					<td><form:label for="motherLastName" path="motherLastName" cssErrorClass="error"><fmt:message key="socialworker.form.motherLastName" /></form:label>
						<form:input path="motherLastName" /> <form:errors path="motherLastName" /></td>
					<td><form:label for="motherAge" path="motherAge" cssErrorClass="error"><fmt:message key="socialworker.form.motherAge" /></form:label>
						<form:input path="motherAge" /> <form:errors path="motherAge" /></td>
					</tr>
			<tr>
				<th colspan="${fieldsPerRow}"><fmt:message key="socialworker.form.tutorTitle" /></th></tr>
				<tr>
					<td><form:label for="tutorFirstName" path="tutorFirstName" cssErrorClass="error"><fmt:message key="socialworker.form.tutorFirstName" /></form:label>
						<form:input path="tutorFirstName" /> <form:errors path="tutorFirstName" /></td>
					<td><form:label for="tutorLastName" path="tutorLastName" cssErrorClass="error"><fmt:message key="socialworker.form.tutorLastName" /></form:label>
						<form:input path="tutorLastName" /> <form:errors path="tutorLastName" /></td>
					<td><form:label for="tutorAge" path="tutorAge" cssErrorClass="error"><fmt:message key="socialworker.form.tutorAge" /></form:label>
						<form:input path="tutorAge" /> <form:errors path="tutorAge" /></td>
					</tr>
			<tr>
				<th colspan="${fieldsPerRow}"><form:label for="livesWith" path="livesWith" cssErrorClass="error"><fmt:message key="socialworker.form.livesWith" /></form:label></th></tr>

				<!--  No se puede usar form:radiobuttons para popular todo directamente porque usa
				el key de los enum y necesitamos la description para que tenga sentido... -->
				<c:forEach var="livesWith" items="${livesWith}">
					<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
						<td><form:radiobutton path="livesWith" value="${livesWith}" />
						<form:label path="livesWith" for="livesWith${count+1}">${livesWith.description}</form:label></td>
					<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
				<tr><td><form:errors path="livesWith" /></td></tr>
			<tr>
				<th colspan="${fieldsPerRow}"><fmt:message key="socialworker.form.homeTitle" /></th></tr>
			<tr>
				<td colspan="${fieldsPerRow}">
					<form:label for="peopleAtHome" path="peopleAtHome" cssErrorClass="error"><fmt:message key="socialworker.form.peopleAtHome" /></form:label>
					<form:input path="peopleAtHome" /> <form:errors path="peopleAtHome" /></td></tr>
			<tr>
				<td colspan="${fieldsPerRow}">
					<form:label for="peopleAtHomeOverTen" path="peopleAtHomeOverTen" cssErrorClass="error"><fmt:message key="socialworker.form.peopleAtHomeOverTen" /></form:label>
					<form:input path="peopleAtHomeOverTen" /> <form:errors path="peopleAtHomeOverTen" /></td></tr>
			<tr>
				<td colspan="${fieldsPerRow}">
					<form:label for="peopleAtHomeUnderTen" path="peopleAtHomeUnderTen" cssErrorClass="error"><fmt:message key="socialworker.form.peopleAtHomeUnderTen" /></form:label>
					<form:input path="peopleAtHomeUnderTen" /> <form:errors path="peopleAtHomeUnderTen" /></td></tr>
		<tr>
		<td>
		<br/><br/>
		<a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a>
		</td>
		</tr>
		
		</table>
	</div>	
		
	<!-- Aca empieza la seccion de Vivienda -->
	<div id="tab-viv" class="socialworkerform">
		<table>
			<tr>
				<td colspan="${fieldsPerRow}"><form:label for="roomsExcludingKitchenAndBathroom" path="roomsExcludingKitchenAndBathroom" cssErrorClass="error"><span class="titleform"><fmt:message key="socialworker.form.roomsExcludingKitchenAndBathroom" /></span></form:label>
				<form:input path="roomsExcludingKitchenAndBathroom" /> <form:errors path="roomsExcludingKitchenAndBathroom" /></td></tr>
			<tr>
				<th colspan="${fieldsPerRow}"><form:label for="interiorFloor" path="interiorFloor" cssErrorClass="error"><fmt:message key="socialworker.form.interiorFloor" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="interiorFloor" items="${interiorFloor}">
					<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
						<td><form:radiobutton path="interiorFloor" value="${interiorFloor}" /> 
						<form:label path="interiorFloor" for="interiorFloor${count+1}">${interiorFloor.description}</form:label></td>
					<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
				<tr><td><form:errors path="interiorFloor" /></td></tr>
			<tr>
				<th colspan="${fieldsPerRow}"><form:label for="roofType" path="roofType" cssErrorClass="error"><fmt:message key="socialworker.form.roofType" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="roofType" items="${roofType}">
					<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
						<td><form:radiobutton path="roofType" value="${roofType}" /> 
						<form:label path="roofType" for="roofType${count+1 }"> ${roofType.description}</form:label></td>
					<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%fieldsPerRow  != 0 }"></tr></c:if>
				<tr><td><form:errors path="roofType" /></td></tr>
			<tr>
				<td colspan="${fieldsPerRow }"><form:label for="hasCeiling1" path="hasCeiling" cssErrorClass="error"> <span class="titleform"><fmt:message key="socialworker.form.hasCeiling" /></span></form:label>
				<form:checkbox path="hasCeiling" /> <form:errors path="hasCeiling" /></td></tr>
			<tr>
				<th colspan="${fieldsPerRow}"><form:label for="waterSource" path="waterSource" cssErrorClass="error"><fmt:message key="socialworker.form.waterSource" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="waterSource" items="${waterSource}">
					<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
						<td><form:radiobutton path="waterSource" value="${waterSource}" /> 
						<form:label path="waterSource" for="waterSource${count+1 }"> ${waterSource.description}</form:label></td>
					<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
				<tr><td><form:errors path="waterSource" /></td></tr>
			<tr>
				<th colspan="${fieldsPerRow}"><form:label for="waterSourceType" path="waterSourceType" cssErrorClass="error"><fmt:message key="socialworker.form.waterSourceType" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="waterSourceType" items="${waterSourceType}">
					<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
						<td><form:radiobutton path="waterSourceType" value="${waterSourceType}" /> 
						<form:label path="waterSource" for="waterSourceType${count+1 }"> ${waterSourceType.description} </form:label></td>
					<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
				<tr><td><form:errors path="waterSourceType" /></td></tr>
			<tr>
				<td><form:label for="hasBathroom1" path="hasBathroom" cssErrorClass="error"> <span class="titleform"><fmt:message key="socialworker.form.hasBathroom" /></span></form:label>
					<form:checkbox path="hasBathroom" /> <form:errors path="hasBathroom" /></td></tr>
			<tr>
				<td><form:label for="bathroomInside1" path="bathroomInside" cssErrorClass="error"><span class="titleform"><fmt:message key="socialworker.form.bathroomInside" /> </span></form:label>
					<form:checkbox path="bathroomInside" /> <form:errors path="bathroomInside" /></td></tr>
			<tr>
				<th colspan="${fieldsPerRow}"><form:label for="bathroomSewerType" path="bathroomSewerType" cssErrorClass="error"><fmt:message key="socialworker.form.bathroomSewerType" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="bathroomSewerType" items="${bathroomSewerType}">
					<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
						<td><form:radiobutton path="bathroomSewerType" value="${bathroomSewerType}" /> 
						<form:label path="bathroomSewerType" for="bathroomSewerType${count+1 }">  ${bathroomSewerType.description} </form:label></td>
					<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
				<tr><td><form:errors path="bathroomSewerType" /></td></tr>
			<tr>
				<td><form:label for="inSinkingZone1" path="inSinkingZone" cssErrorClass="error"><span class="titleform"><fmt:message key="socialworker.form.isSinkingZone" /></span></form:label>
				<form:checkbox path="inSinkingZone" /> <form:errors path="inSinkingZone" /></td></tr>
			<tr>
				<th colspan="${fieldsPerRow}"><form:label for="kitchenFuel" path="kitchenFuel" cssErrorClass="error"><fmt:message key="socialworker.form.kitchenFuel" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="kitchenFuel" items="${kitchenFuel}">
					<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
						<td><form:radiobutton path="kitchenFuel" value="${kitchenFuel}" /> 
						<form:label path="kitchenFuel" for="kitchenFuel${count+1 }">${kitchenFuel.description}</form:label></td>
					<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
				<tr><td><form:errors path="kitchenFuel" /></td></tr>
			<tr>
				<th colspan="${fieldsPerRow}"><form:label for="electricity" path="electricity" cssErrorClass="error"><fmt:message key="socialworker.form.electricity" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="electricity" items="${electricity}">
					<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
						<td><form:radiobutton path="electricity" value="${electricity}" /> 
						<form:label path="electricity" for="electricity${count+1 }">${electricity.description}</form:label></td>
					<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
				<tr><td><form:errors path="electricity" /></td></tr>
				
		<tr>
		<td>
		<br/><br/>
		<a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a>
		</td>
		</tr>
				
		</table>
		
	</div>
		
	<!-- Aca empieza la seccion de Escolaridad -->
	<div id="tab-esc" class="socialworkerform">
		<table>
			<tr>
				<td colspan="${fieldsPerRow}"><form:label for="knowsHowToReadAndWrite1" path="knowsHowToReadAndWrite" cssErrorClass="error"><span class="titleform"><fmt:message key="socialworker.form.knowsHowToReadAndWrite" /></span></form:label>
					<form:checkbox path="knowsHowToReadAndWrite" /> <form:errors path="knowsHowToReadAndWrite" /></td></tr>
			<tr>
				<td colspan="${fieldsPerRow}"><form:label for="goingToSchool1" path="goingToSchool" cssErrorClass="error"><span class="titleform"><fmt:message key="socialworker.form.goingToSchool" /></span></form:label>
					<form:checkbox path="goingToSchool" /> <form:errors path="goingToSchool" /></td></tr>
			<tr>
				<th colspan="${fieldsPerRow}"><form:label for="scholarity" path="scholarity" cssErrorClass="error"><fmt:message key="socialworker.form.scholarity" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="scholarity" items="${scholarity}">
					<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
						<td><form:radiobutton path="scholarity" value="${scholarity}" />
						<form:label path="scholarity" for="scholarity${count+1 }">${scholarity.description}</form:label></td> 
					<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
				<tr><td><form:errors path="scholarity" /></td></tr>
			<tr>
				<th colspan="${fieldsPerRow}"><form:label for="schoolHours" path="schoolHours" cssErrorClass="error"><fmt:message key="socialworker.form.schoolHours" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="schoolHours" items="${schoolHours}">
					<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
						<td><form:radiobutton path="schoolHours" value="${schoolHours}" />
						<form:label path="schoolHours" for="schoolHours${count+1 }">${schoolHours.description}</form:label></td> 
					<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
				<tr><td><form:errors path="schoolHours" /></td></tr>
			<tr>
				<th colspan="${fieldsPerRow}"><form:label for="schoolService" path="schoolService" cssErrorClass="error"><fmt:message key="socialworker.form.schoolService" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="schoolService" items="${schoolService}">
					<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
						<td><form:radiobutton path="schoolService" value="${schoolService}" />
						<form:label path="schoolService" for="schoolService${count+1 }">${schoolService.description}</form:label></td> 
					<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
				<tr><td><form:errors path="schoolService" /></td></tr>
				
		<tr>
		<td>
		<br/><br/>
		<a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a>
		</td>
		</tr>
				
		</table>
		
	</div>
	
	<!-- Aca empieza la seccion de Datos socioeconomicos del grupo familiar -->
	<div id="tab-eco" class="socialworkerform">
		<table>
			<tr>
				<td colspan="${fieldsPerRow}"><form:label for="workingPeople1" path="workingPeople" cssErrorClass="error"><span class="titleform"><fmt:message key="socialworker.form.workingPeople" /></span></form:label>
					<form:input path="workingPeople" /> <form:errors path="workingPeople" /></td></tr>
			<tr>
				<th colspan="${fieldsPerRow}"><form:label for="adultEducationalLevel" path="adultEducationalLevel" cssErrorClass="error"><fmt:message key="socialworker.form.adultEducationalLevel" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="adultEducationalLevel" items="${adultEducationalLevel}">
					<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
						<td><form:radiobutton path="adultEducationalLevel" value="${adultEducationalLevel}" />
						 <form:label path="adultEducationalLevel" for="adultEducationalLevel${count+1 }">${adultEducationalLevel.description}</form:label></td>
					<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
				<tr><td><form:errors path="adultEducationalLevel" /></td></tr>
			<tr>
				<th colspan="${fieldsPerRow}"><form:label for="mainIncome" path="mainIncome" cssErrorClass="error"><fmt:message key="socialworker.form.mainIncome" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="mainIncome" items="${mainIncome}">
					<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
						<td><form:radiobutton path="mainIncome" value="${mainIncome}" />
						 <form:label path="mainIncome" for="mainIncome${count+1 }">${mainIncome.description}</form:label></td>
					<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
				<tr><td><form:errors path="mainIncome" /></td></tr>
			<tr>
				<th colspan="${fieldsPerRow}">
					<form:label for="nbi" path="nbi" cssErrorClass="error"><fmt:message key="socialworker.form.nbi" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="nbi" items="${nbi}">
					<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
						<td><form:checkbox path="nbi" value="${nbi}" />
						<form:label path="nbi" for="nbi${count+1 }">${nbi.description}</form:label></td>
					<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
				<tr><td><form:errors path="nbi" /></td></tr>
			<tr>
				<th colspan="${fieldsPerRow}"><form:label for="addiction" path="addiction" cssErrorClass="error"><fmt:message key="socialworker.form.addiction" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="addiction" items="${addiction}">
					<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
						<td><form:radiobutton path="addiction" value="${addiction}" />
						<form:label path="addiction" for="addiction${count+1 }">${addiction.description}</form:label></td>
					<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
				<tr><td><form:errors path="addiction" /></td></tr>
				<tr>
				<td colspan="${fieldsPerRow } }"><form:label for="hasProfessionalAssistanceForAddiction1" path="hasProfessionalAssistanceForAddiction" cssErrorClass="error"><span class="titleform"><fmt:message key="socialworker.form.hasProfessionalAssistanceForAddiction" /></span></form:label>
					<form:checkbox path="hasProfessionalAssistanceForAddiction" /> <form:errors path="hasProfessionalAssistanceForAddiction" /></td></tr>
			
			<tr>
				<th colspan="${fieldsPerRow}"><form:label for="mistreatment" path="mistreatment" cssErrorClass="error"><fmt:message key="socialworker.form.mistreatment" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="mistreatment" items="${mistreatment}">
					<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
						<td><form:radiobutton path="mistreatment" value="${mistreatment}" />
						<form:label path="mistreatment" for="mistreatment${count+1 }">${mistreatment.description}</form:label></td>
					<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
				<tr><td><form:errors path="mistreatment" /></td></tr>
			<tr>
				<td colspan="${fieldsPerRow}"><form:label for="hasProfessionalAssistanceForMistreatment1" path="hasProfessionalAssistanceForMistreatment" cssErrorClass="error"><span class="titleform"><fmt:message key="socialworker.form.hasProfessionalAssistanceForMistreatment" /></span></form:label>
					<form:checkbox path="hasProfessionalAssistanceForMistreatment" /> <form:errors path="hasProfessionalAssistanceForMistreatment" /></td></tr>
			<tr>
				<td colspan="${fieldsPerRow}"><form:label for="hasHealthCare1" path="hasHealthCare" cssErrorClass="error"><span class="titleform"><fmt:message key="socialworker.form.hasHealthCare" /></span></form:label>
					<form:checkbox path="hasHealthCare" /> <form:errors path="hasHealthCare" /></td></tr>
			
			<tr>
			
				<td colspan="${fieldsPerRow}"><form:label for="hasBeenDerivedToOtherSocialServices1" path="hasBeenDerivedToOtherSocialServices" cssErrorClass="error"><span class="titleform"><fmt:message key="socialworker.form.hasBeenDerivedToOtherSocialServices" /></span></form:label>
					<form:checkbox path="hasBeenDerivedToOtherSocialServices" /> <form:errors path="hasBeenDerivedToOtherSocialServices" /></td></tr>
			<tr>
				<th colspan="${fieldsPerRow}"><form:label for="diagnose" path="diagnose" cssErrorClass="error"><fmt:message key="socialworker.form.diagnose" /></form:label></th></tr>
			<tr>
				<td colspan="${fieldsPerRow}"><form:textarea path="diagnose" /><form:errors path="diagnose" /></td></tr>
			<tr>
				<th colspan="${fieldsPerRow}"><form:label for="observations" path="observations" cssErrorClass="error"><fmt:message key="socialworker.form.observations" /></form:label></th></tr>
			<tr>	
				<td colspan="${fieldsPerRow}"><form:textarea path="observations" /><form:errors path="observations" /></td></tr>
		
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