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
		<table>
			<tr>
				<th><form:label for="fatherFirstName" path="fatherFirstName" cssErrorClass="error"><fmt:message key="socialworker.form.fatherFirstName" /></form:label></th> 
				<td><form:input path="fatherFirstName" /> <form:errors path="fatherFirstName" /></td></tr>
			<tr>
				<th><form:label for="fatherLastName" path="fatherLastName" cssErrorClass="error"><fmt:message key="socialworker.form.fatherLastName" /></form:label></th>
				<td><form:input path="fatherLastName" /> <form:errors path="fatherLastName" /></td></tr>
			<tr>
				<th><form:label for="fatherAge" path="fatherAge" cssErrorClass="error"><fmt:message key="socialworker.form.fatherAge" /></form:label></th>
				<td><form:input path="fatherAge" /> <form:errors path="fatherAge" /></td></tr>
			<tr>
				<th><form:label for="motherFirstName" path="motherFirstName" cssErrorClass="error"><fmt:message key="socialworker.form.motherFirstName" /></form:label></th>
				<td><form:input path="motherFirstName" /> <form:errors path="motherFirstName" /></td></tr>
			<tr>
				<th><form:label for="motherLastName" path="motherLastName" cssErrorClass="error"><fmt:message key="socialworker.form.motherLastName" /></form:label> </td>
				<td><form:input path="motherLastName" /> <form:errors path="motherLastName" /></td></tr>
			<tr>
				<th><form:label for="motherAge" path="motherAge" cssErrorClass="error"><fmt:message key="socialworker.form.motherAge" /></form:label></th>
				<td><form:input path="motherAge" /> <form:errors path="motherAge" /></td></tr>
			<tr>
				<th><form:label for="tutorFirstName" path="tutorFirstName" cssErrorClass="error"><fmt:message key="socialworker.form.tutorFirstName" /></form:label></th>
				<td><form:input path="tutorFirstName" /> <form:errors path="tutorFirstName" /></td></tr>
			<tr>
				<th><form:label for="tutorLastName" path="tutorLastName" cssErrorClass="error"><fmt:message key="socialworker.form.tutorLastName" /></form:label> </td>
				<td><form:input path="tutorLastName" /> <form:errors path="tutorLastName" /></td></tr>
			<tr>
				<th><form:label for="tutorAge" path="tutorAge" cssErrorClass="error"><fmt:message key="socialworker.form.tutorAge" /></form:label></th>
				<td><form:input path="tutorAge" /> <form:errors path="tutorAge" /></td></tr>
			<tr>
				<th colspan="2"><form:label for="livesWith" path="livesWith" cssErrorClass="error"><fmt:message key="socialworker.form.livesWith" /></form:label></th></tr>

				<!--  No se puede usar form:radiobuttons para popular todo directamente porque usa
				el key de los enum y necesitamos la description para que tenga sentido... -->
				<c:forEach var="livesWith" items="${livesWith}">
					<c:if test="${count%2 == 0 }"><tr></c:if>
						<td><form:radiobutton path="livesWith" value="${livesWith}" /> ${livesWith.description}</td>
					<c:if test="${count%2 == 1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%2 == 1 }"></tr></c:if>
				<tr><td><form:errors path="livesWith" /></td></tr>
			<tr>
				<th><form:label for="peopleAtHome" path="peopleAtHome" cssErrorClass="error"><fmt:message key="socialworker.form.peopleAtHome" /></form:label></th>
				<td><form:input path="peopleAtHome" /> <form:errors path="peopleAtHome" /></td></tr>
			<tr>
				<th><form:label for="peopleAtHomeOverTen" path="peopleAtHomeOverTen" cssErrorClass="error"><fmt:message key="socialworker.form.peopleAtHomeOverTen" /></form:label></th>
				<td><form:input path="peopleAtHomeOverTen" /> <form:errors path="peopleAtHomeOverTen" /></td></tr>
			<tr>
				<th><form:label for="peopleAtHomeUnderTen" path="peopleAtHomeUnderTen" cssErrorClass="error"><fmt:message key="socialworker.form.peopleAtHomeUnderTen" /></form:label></th>
				<td><form:input path="peopleAtHomeUnderTen" /> <form:errors path="peopleAtHomeUnderTen" /></td></tr>
		</table>
	</div>	
		
	<!-- Aca empieza la seccion de Vivienda -->
	<div id="tab-viv" class="socialworkerform">
		<table>
			<tr>
				<th><form:label for="roomsExcludingKitchenAndBathroom" path="roomsExcludingKitchenAndBathroom" cssErrorClass="error"><fmt:message key="socialworker.form.roomsExcludingKitchenAndBathroom" /></form:label></th>
				<td><form:input path="roomsExcludingKitchenAndBathroom" /> <form:errors path="roomsExcludingKitchenAndBathroom" /></td></tr>
			<tr>
				<th colspan="2"><form:label for="interiorFloor" path="interiorFloor" cssErrorClass="error"><fmt:message key="socialworker.form.interiorFloor" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="interiorFloor" items="${interiorFloor}">
					<c:if test="${count%2 == 0 }"><tr></c:if>
						<td><form:radiobutton path="interiorFloor" value="${interiorFloor}" /> ${interiorFloor.description}</td>
					<c:if test="${count%2 == 1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%2 == 1 }"></tr></c:if>
				<tr><td><form:errors path="interiorFloor" /></td></tr>
			<tr>
				<th colspan="2"><form:label for="roofType" path="roofType" cssErrorClass="error"><fmt:message key="socialworker.form.roofType" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="roofType" items="${roofType}">
					<c:if test="${count%2 == 0 }"><tr></c:if>
						<td><form:radiobutton path="roofType" value="${roofType}" /> ${roofType.description}</td>
					<c:if test="${count%2 == 1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%2 == 1 }"></tr></c:if>
				<tr><td><form:errors path="roofType" /></td></tr>
			<tr>
				<th><form:label for="hasCeiling" path="hasCeiling" cssErrorClass="error"><fmt:message key="socialworker.form.hasCeiling" /></form:label></th>
				<td><form:checkbox path="hasCeiling" /> <form:errors path="hasCeiling" /></td></tr>
			<tr>
				<th colspan="2"><form:label for="waterSource" path="waterSource" cssErrorClass="error"><fmt:message key="socialworker.form.waterSource" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="waterSource" items="${waterSource}">
					<c:if test="${count%2 == 0 }"><tr></c:if>
						<td><form:radiobutton path="waterSource" value="${waterSource}" /> ${waterSource.description}</td>
					<c:if test="${count%2 == 1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%2 == 1 }"></tr></c:if>
				<tr><td><form:errors path="waterSource" /></td></tr>
			<tr>
				<th colspan="2"><form:label for="waterSourceType" path="waterSourceType" cssErrorClass="error"><fmt:message key="socialworker.form.waterSourceType" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="waterSourceType" items="${waterSourceType}">
					<c:if test="${count%2 == 0 }"><tr></c:if>
						<td><form:radiobutton path="waterSourceType" value="${waterSourceType}" /> ${waterSourceType.description}</td>
					<c:if test="${count%2 == 1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%2 == 1 }"></tr></c:if>
				<tr><td><form:errors path="waterSourceType" /></td></tr>
			<tr>
				<th><form:label for="hasBathroom" path="hasBathroom" cssErrorClass="error"><fmt:message key="socialworker.form.hasBathroom" /></form:label></th>
				<td><form:checkbox path="hasBathroom" /> <form:errors path="hasBathroom" /></td></tr>
			<tr>
				<th><form:label for="bathroomInside" path="bathroomInside" cssErrorClass="error"><fmt:message key="socialworker.form.bathroomInside" /></form:label></th>
				<td><form:checkbox path="bathroomInside" /> <form:errors path="bathroomInside" /></td></tr>
			<tr>
				<th colspan="2"><form:label for="bathroomSewerType" path="bathroomSewerType" cssErrorClass="error"><fmt:message key="socialworker.form.bathroomSewerType" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="bathroomSewerType" items="${bathroomSewerType}">
					<c:if test="${count%2 == 0 }"><tr></c:if>
						<td><form:radiobutton path="bathroomSewerType" value="${bathroomSewerType}" /> ${bathroomSewerType.description}</td>
					<c:if test="${count%2 == 1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%2 == 1 }"></tr></c:if>
				<tr><td><form:errors path="bathroomSewerType" /></td></tr>
			<tr>
				<th><form:label for="inSinkingZone" path="inSinkingZone" cssErrorClass="error"><fmt:message key="socialworker.form.isSinkingZone" /></form:label></th>
				<td><form:checkbox path="inSinkingZone" /> <form:errors path="inSinkingZone" /></td></tr>
			<tr>
				<th colspan="2"><form:label for="kitchenFuel" path="kitchenFuel" cssErrorClass="error"><fmt:message key="socialworker.form.kitchenFuel" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="kitchenFuel" items="${kitchenFuel}">
					<c:if test="${count%2 == 0 }"><tr></c:if>
						<td><form:radiobutton path="kitchenFuel" value="${kitchenFuel}" /> ${kitchenFuel.description}</td>
					<c:if test="${count%2 == 1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%2 == 1 }"></tr></c:if>
				<tr><td><form:errors path="kitchenFuel" /></td></tr>
			<tr>
				<th colspan="2"><form:label for="electricity" path="electricity" cssErrorClass="error"><fmt:message key="socialworker.form.electricity" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="electricity" items="${electricity}">
					<c:if test="${count%2 == 0 }"><tr></c:if>
						<td><form:radiobutton path="electricity" value="${electricity}" /> ${electricity.description}</td>
					<c:if test="${count%2 == 1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%2 == 1 }"></tr></c:if>
				<tr><td><form:errors path="electricity" /></td></tr>
		</table>
	</div>
		
	<!-- Aca empieza la seccion de Escolaridad -->
	<div id="tab-esc" class="socialworkerform">
		<table>
			<tr>
				<th><form:label for="knowsHowToReadAndWrite" path="knowsHowToReadAndWrite" cssErrorClass="error"><fmt:message key="socialworker.form.knowsHowToReadAndWrite" /></form:label></th>
				<td><form:checkbox path="knowsHowToReadAndWrite" /> <form:errors path="knowsHowToReadAndWrite" /></td></tr>
			<tr>
				<th><form:label for="goingToSchool" path="goingToSchool" cssErrorClass="error"><fmt:message key="socialworker.form.goingToSchool" /></form:label></th>
				<td><form:checkbox path="goingToSchool" /> <form:errors path="goingToSchool" /></td></tr>
			<tr>
				<th colspan="2"><form:label for="scholarity" path="scholarity" cssErrorClass="error"><fmt:message key="socialworker.form.scholarity" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="scholarity" items="${scholarity}">
					<c:if test="${count%2 == 0 }"><tr></c:if>
						<td><form:radiobutton path="scholarity" value="${scholarity}" /> ${scholarity.description}</td>
					<c:if test="${count%2 == 1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%2 == 1 }"></tr></c:if>
				<tr><td><form:errors path="scholarity" /></td></tr>
			<tr>
				<th colspan="2"><form:label for="schoolHours" path="schoolHours" cssErrorClass="error"><fmt:message key="socialworker.form.schoolHours" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="schoolHours" items="${schoolHours}">
					<c:if test="${count%2 == 0 }"><tr></c:if>
						<td><form:radiobutton path="schoolHours" value="${schoolHours}" /> ${schoolHours.description}</td>
					<c:if test="${count%2 == 1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%2 == 1 }"></tr></c:if>
				<tr><td><form:errors path="schoolHours" /></td></tr>
			<tr>
				<th colspan="2"><form:label for="schoolService" path="schoolService" cssErrorClass="error"><fmt:message key="socialworker.form.schoolService" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="schoolService" items="${schoolService}">
					<c:if test="${count%2 == 0 }"><tr></c:if>
						<td><form:radiobutton path="schoolService" value="${schoolService}" /> ${schoolService.description}</td>
					<c:if test="${count%2 == 1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%2 == 1 }"></tr></c:if>
				<tr><td><form:errors path="schoolService" /></td></tr>
		</table>
	</div>
	
	<!-- Aca empieza la seccion de Datos socioeconomicos del grupo familiar -->
	<div id="tab-eco" class="socialworkerform">
		<table>
			<tr>
				<th><form:label for="workingPeople" path="workingPeople" cssErrorClass="error"><fmt:message key="socialworker.form.workingPeople" /></form:label></th>
				<td><form:input path="workingPeople" /> <form:errors path="workingPeople" /></td></tr>
			<tr>
				<th colspan="2"><form:label for="adultEducationalLevel" path="adultEducationalLevel" cssErrorClass="error"><fmt:message key="socialworker.form.adultEducationalLevel" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="adultEducationalLevel" items="${adultEducationalLevel}">
					<c:if test="${count%2 == 0 }"><tr></c:if>
						<td><form:radiobutton path="adultEducationalLevel" value="${adultEducationalLevel}" /> ${adultEducationalLevel.description}</td>
					<c:if test="${count%2 == 1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%2 == 1 }"></tr></c:if>
				<tr><td><form:errors path="adultEducationalLevel" /></td></tr>
			<tr>
				<th colspan="2"><form:label for="mainIncome" path="mainIncome" cssErrorClass="error"><fmt:message key="socialworker.form.mainIncome" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="mainIncome" items="${mainIncome}">
					<c:if test="${count%2 == 0 }"><tr></c:if>
						<td><form:radiobutton path="mainIncome" value="${mainIncome}" /> ${mainIncome.description}</td>
					<c:if test="${count%2 == 1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%2 == 1 }"></tr></c:if>
				<tr><td><form:errors path="mainIncome" /></td></tr>
			<tr>
				<th><form:label for="hasHealthCare" path="hasHealthCare" cssErrorClass="error"><fmt:message key="socialworker.form.hasHealthCare" /></form:label></th>
				<td><form:checkbox path="hasHealthCare" /> <form:errors path="hasHealthCare" /></td></tr>
			<tr>
				<th colspan="2"><!-- TODO: esto deberia ser multiple.. hay que modificar el modelo -->
					<form:label for="nbi" path="nbi" cssErrorClass="error"><fmt:message key="socialworker.form.nbi" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="nbi" items="${nbi}">
					<c:if test="${count%2 == 0 }"><tr></c:if>
						<td><form:radiobutton path="nbi" value="${nbi}" /> ${nbi.description}</td>
					<c:if test="${count%2 == 1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%2 == 1 }"></tr></c:if>
				<tr><td><form:errors path="nbi" /></td></tr>
			<tr>
				<th colspan="2"><form:label for="addiction" path="addiction" cssErrorClass="error"><fmt:message key="socialworker.form.addiction" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="addiction" items="${addiction}">
					<c:if test="${count%2 == 0 }"><tr></c:if>
						<td><form:radiobutton path="addiction" value="${addiction}" /> ${addiction.description}</td>
					<c:if test="${count%2 == 1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%2 == 1 }"></tr></c:if>
				<tr><td><form:errors path="addiction" /></td></tr>
			<tr>
				<th><form:label for="hasProfessionalAssistanceForAddiction" path="hasProfessionalAssistanceForAddiction" cssErrorClass="error"><fmt:message key="socialworker.form.hasProfessionalAssistanceForAddiction" /></form:label></th>
				<td><form:checkbox path="hasProfessionalAssistanceForAddiction" /> <form:errors path="hasProfessionalAssistanceForAddiction" /></td></tr>
			<tr>
				<th colspan="2"><form:label for="mistreatment" path="mistreatment" cssErrorClass="error"><fmt:message key="socialworker.form.mistreatment" /></form:label></th></tr>
				<c:set var="count" value="0" />
				<c:forEach var="mistreatment" items="${mistreatment}">
					<c:if test="${count%2 == 0 }"><tr></c:if>
						<td><form:radiobutton path="mistreatment" value="${mistreatment}" /> ${mistreatment.description}</td>
					<c:if test="${count%2 == 1 }"></tr></c:if>
				    <c:set var="count" value="${count+1}" />
				</c:forEach>
				<c:if test="${count%2 == 1 }"></tr></c:if>
				<tr><td><form:errors path="mistreatment" /></td></tr>
			<tr>
				<th><form:label for="hasProfessionalAssistanceForMistreatment" path="hasProfessionalAssistanceForMistreatment" cssErrorClass="error"><fmt:message key="socialworker.form.hasProfessionalAssistanceForMistreatment" /></form:label></th>
				<td><form:checkbox path="hasProfessionalAssistanceForMistreatment" /> <form:errors path="hasProfessionalAssistanceForMistreatment" /></td></tr>
			<tr>
				<th><form:label for="hasBeenDerivedToOtherSocialServices" path="hasBeenDerivedToOtherSocialServices" cssErrorClass="error"><fmt:message key="socialworker.form.hasBeenDerivedToOtherSocialServices" /></form:label></th>
				<td><form:checkbox path="hasBeenDerivedToOtherSocialServices" /> <form:errors path="hasBeenDerivedToOtherSocialServices" /></td></tr>
			<tr>
				<th colspan="2"><form:label for="diagnose" path="diagnose" cssErrorClass="error"><fmt:message key="socialworker.form.diagnose" /></form:label></th></tr>
			<tr>
				<td colspan="2"><form:textarea path="diagnose" /><form:errors path="diagnose" /></td></tr>
			<tr>
				<th colspan="2"><form:label for="observations" path="observations" cssErrorClass="error"><fmt:message key="socialworker.form.observations" /></form:label></th></tr>
			<tr>	
				<td colspan="2"><form:textarea path="observations" /><form:errors path="observations" /></td></tr>
			<tr>
				<td><input type="submit" /></td>
				<td> </td></tr>
		</table>
	</div>
	</div>
</form:form>
<hr>