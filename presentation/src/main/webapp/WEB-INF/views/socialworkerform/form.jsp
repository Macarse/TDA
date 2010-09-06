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
	<div id="tab-gf">
		<table>
			<tr>
				<td><form:label for="fatherFirstName" path="fatherFirstName" cssErrorClass="error"><fmt:message key="socialworker.form.fatherFirstName" /></form:label></td> 
				<td><form:input path="fatherFirstName" /> <form:errors path="fatherFirstName" /></td></tr>
			<tr>
				<td><form:label for="fatherLastName" path="fatherLastName" cssErrorClass="error"><fmt:message key="socialworker.form.fatherLastName" /></form:label></td>
				<td><form:input path="fatherLastName" /> <form:errors path="fatherLastName" /></td></tr>
			<tr>
				<td><form:label for="fatherAge" path="fatherAge" cssErrorClass="error"><fmt:message key="socialworker.form.fatherAge" /></form:label></td>
				<td><form:input path="fatherAge" /> <form:errors path="fatherAge" /></td></tr>
			<tr>
				<td><form:label for="motherFirstName" path="motherFirstName" cssErrorClass="error"><fmt:message key="socialworker.form.motherFirstName" /></form:label></td>
				<td><form:input path="motherFirstName" /> <form:errors path="motherFirstName" /></td></tr>
			<tr>
				<td><form:label for="motherLastName" path="motherLastName" cssErrorClass="error"><fmt:message key="socialworker.form.motherLastName" /></form:label> </td>
				<td><form:input path="motherLastName" /> <form:errors path="motherLastName" /></td></tr>
			<tr>
				<td><form:label for="motherAge" path="motherAge" cssErrorClass="error"><fmt:message key="socialworker.form.motherAge" /></form:label></td>
				<td><form:input path="motherAge" /> <form:errors path="motherAge" /></td></tr>
			<tr>
				<td><form:label for="tutorFirstName" path="tutorFirstName" cssErrorClass="error"><fmt:message key="socialworker.form.tutorFirstName" /></form:label></td>
				<td><form:input path="tutorFirstName" /> <form:errors path="tutorFirstName" /></td></tr>
			<tr>
				<td><form:label for="tutorLastName" path="tutorLastName" cssErrorClass="error"><fmt:message key="socialworker.form.tutorLastName" /></form:label> </td>
				<td><form:input path="tutorLastName" /> <form:errors path="tutorLastName" /></td></tr>
			<tr>
				<td><form:label for="tutorAge" path="tutorAge" cssErrorClass="error"><fmt:message key="socialworker.form.tutorAge" /></form:label></td>
				<td><form:input path="tutorAge" /> <form:errors path="tutorAge" /></td></tr>
			<tr>
				<td><form:label for="livesWith" path="livesWith" cssErrorClass="error"><fmt:message key="socialworker.form.livesWith" /></form:label></td>
				<td>
					<!--  No se puede usar form:radiobuttons para popular todo directamente porque usa
					el key de los enum y necesitamos la description para que tenga sentido... -->
					<c:forEach var="livesWith" items="${livesWith}">
						<form:radiobutton path="livesWith" value="${livesWith}" /> ${livesWith.description}<br/>
					</c:forEach>
					<form:errors path="livesWith" /></td></tr>
			<tr>
				<td><form:label for="peopleAtHome" path="peopleAtHome" cssErrorClass="error"><fmt:message key="socialworker.form.peopleAtHome" /></form:label></td>
				<td><form:input path="peopleAtHome" /> <form:errors path="peopleAtHome" /></td></tr>
			<tr>
				<td><form:label for="peopleAtHomeOverTen" path="peopleAtHomeOverTen" cssErrorClass="error"><fmt:message key="socialworker.form.peopleAtHomeOverTen" /></form:label></td>
				<td><form:input path="peopleAtHomeOverTen" /> <form:errors path="peopleAtHomeOverTen" /></td></tr>
			<tr>
				<td><form:label for="peopleAtHomeUnderTen" path="peopleAtHomeUnderTen" cssErrorClass="error"><fmt:message key="socialworker.form.peopleAtHomeUnderTen" /></form:label></td>
				<td><form:input path="peopleAtHomeUnderTen" /> <form:errors path="peopleAtHomeUnderTen" /></td></tr>
		</table>
	</div>	
		
	<!-- Aca empieza la seccion de Vivienda -->
	<div id="tab-viv">
		<table>
			<tr>
				<td><form:label for="roomsExcludingKitchenAndBathroom" path="roomsExcludingKitchenAndBathroom" cssErrorClass="error"><fmt:message key="socialworker.form.roomsExcludingKitchenAndBathroom" /></form:label></td>
				<td><form:input path="roomsExcludingKitchenAndBathroom" /> <form:errors path="roomsExcludingKitchenAndBathroom" /></td></tr>
			<tr>
				<td><form:label for="interiorFloor" path="interiorFloor" cssErrorClass="error"><fmt:message key="socialworker.form.interiorFloor" /></form:label></td>
				<td><c:forEach var="interiorFloor" items="${interiorFloor}">
						<form:radiobutton path="interiorFloor" value="${interiorFloor}" /> ${interiorFloor.description} <br />
					</c:forEach>
					<form:errors path="interiorFloor" /></td></tr>
			<tr>
				<td><form:label for="roofType" path="roofType" cssErrorClass="error"><fmt:message key="socialworker.form.roofType" /></form:label></td>
				<td><c:forEach var="roofType" items="${roofType}">
						<form:radiobutton path="roofType" value="${roofType}" /> ${roofType.description}<br/>
					</c:forEach>
					<form:errors path="roofType" /></td></tr>
			<tr>
				<td><form:label for="hasCeiling" path="hasCeiling" cssErrorClass="error"><fmt:message key="socialworker.form.hasCeiling" /></form:label></td>
				<td><form:checkbox path="hasCeiling" /> <form:errors path="hasCeiling" /></td></tr>
			<tr>
				<td><form:label for="waterSource" path="waterSource" cssErrorClass="error"><fmt:message key="socialworker.form.waterSource" /></form:label></td>
				<td><c:forEach var="waterSource" items="${waterSource}">
						<form:radiobutton path="waterSource" value="${waterSource}" /> ${waterSource.description}<br/>
					</c:forEach>
					<form:errors path="waterSource" /></td></tr>
			<tr>
				<td><form:label for="waterSourceType" path="waterSourceType" cssErrorClass="error"><fmt:message key="socialworker.form.waterSourceType" /></form:label></td>
				<td><c:forEach var="waterSourceType" items="${waterSourceType}">
						<form:radiobutton path="waterSourceType" value="${waterSourceType}" /> ${waterSourceType.description}<br/>
					</c:forEach>
					<form:errors path="waterSourceType" /></td></tr>
			<tr>
				<td><form:label for="hasBathroom" path="hasBathroom" cssErrorClass="error"><fmt:message key="socialworker.form.hasBathroom" /></form:label></td>
				<td><form:checkbox path="hasBathroom" /> <form:errors path="hasBathroom" /></td></tr>
			<tr>
				<td><form:label for="bathroomInside" path="bathroomInside" cssErrorClass="error"><fmt:message key="socialworker.form.bathroomInside" /></form:label></td>
				<td><form:checkbox path="bathroomInside" /> <form:errors path="bathroomInside" /></td></tr>
			<tr>
				<td><form:label for="bathroomSewerType" path="bathroomSewerType" cssErrorClass="error"><fmt:message key="socialworker.form.bathroomSewerType" /></form:label></td>
				<td><c:forEach var="bathroomSewerType" items="${bathroomSewerType}">
						<form:radiobutton path="bathroomSewerType" value="${bathroomSewerType}" /> ${bathroomSewerType.description}<br/>
					</c:forEach>
					<form:errors path="bathroomSewerType" /></td></tr>
			<tr>
				<td><form:label for="inSinkingZone" path="inSinkingZone" cssErrorClass="error"><fmt:message key="socialworker.form.isSinkingZone" /></form:label></td>
				<td><form:checkbox path="inSinkingZone" /> <form:errors path="inSinkingZone" /></td></tr>
			<tr>
				<td><form:label for="kitchenFuel" path="kitchenFuel" cssErrorClass="error"><fmt:message key="socialworker.form.kitchenFuel" /></form:label></td>
				<td><c:forEach var="kitchenFuel" items="${kitchenFuel}">
						<form:radiobutton path="kitchenFuel" value="${kitchenFuel}" /> ${kitchenFuel.description}<br/>
					</c:forEach>
					<form:errors path="kitchenFuel" /></td></tr>
			<tr>
				<td><form:label for="electricity" path="electricity" cssErrorClass="error"><fmt:message key="socialworker.form.electricity" /></form:label></td>
				<td><c:forEach var="electricity" items="${electricity}">
						<form:radiobutton path="electricity" value="${electricity}" /> ${electricity.description}<br/>
					</c:forEach>
					<form:errors path="electricity" /></td></tr>
			<tr>
				<td></td>
				<td></td></tr>
		</table>
	</div>
		
	<!-- Aca empieza la seccion de Escolaridad -->
	<div id="tab-esc">
		<table>
			<tr>
				<td><form:label for="knowsHowToReadAndWrite" path="knowsHowToReadAndWrite" cssErrorClass="error"><fmt:message key="socialworker.form.knowsHowToReadAndWrite" /></form:label></td>
				<td><form:checkbox path="knowsHowToReadAndWrite" /> <form:errors path="knowsHowToReadAndWrite" /></td></tr>
			<tr>
				<td><form:label for="goingToSchool" path="goingToSchool" cssErrorClass="error"><fmt:message key="socialworker.form.goingToSchool" /></form:label></td>
				<td><form:checkbox path="goingToSchool" /> <form:errors path="goingToSchool" /></td></tr>
			<tr>
				<td><form:label for="scholarity" path="scholarity" cssErrorClass="error"><fmt:message key="socialworker.form.scholarity" /></form:label></td>
				<td><c:forEach var="scholarity" items="${scholarity}">
						<form:radiobutton path="scholarity" value="${scholarity}" /> ${scholarity.description}<br/>
					</c:forEach>
					<form:errors path="scholarity" /></td></tr>
			<tr>
				<td><form:label for="schoolHours" path="schoolHours" cssErrorClass="error"><fmt:message key="socialworker.form.schoolHours" /></form:label></td>
				<td><c:forEach var="schoolHours" items="${schoolHours}">
						<form:radiobutton path="schoolHours" value="${schoolHours}" /> ${schoolHours.description}<br/>
					</c:forEach>
					<form:errors path="schoolHours" /></td></tr>
			<tr>
				<td><form:label for="schoolService" path="schoolService" cssErrorClass="error"><fmt:message key="socialworker.form.schoolService" /></form:label></td>
				<td><c:forEach var="schoolService" items="${schoolService}">
						<form:radiobutton path="schoolService" value="${schoolService}" /> ${schoolService.description}<br/>
					</c:forEach>
					<form:errors path="schoolService" /></td></tr>
		</table>
	</div>
	
	<!-- Aca empieza la seccion de Datos socioeconomicos del grupo familiar -->
	<div id="tab-eco">
		<table>
			<tr>
				<td><form:label for="workingPeople" path="workingPeople" cssErrorClass="error"><fmt:message key="socialworker.form.workingPeople" /></form:label></td>
				<td><form:input path="workingPeople" /> <form:errors path="workingPeople" /></td></tr>
			<tr>
				<td><form:label for="adultEducationalLevel" path="adultEducationalLevel" cssErrorClass="error"><fmt:message key="socialworker.form.adultEducationalLevel" /></form:label></td>
				<td><c:forEach var="adultEducationalLevel" items="${adultEducationalLevel}">
						<form:radiobutton path="adultEducationalLevel" value="${adultEducationalLevel}" /> ${adultEducationalLevel.description}<br/>
					</c:forEach>
					<form:errors path="adultEducationalLevel" /></td></tr>
			<tr>
				<td><form:label for="mainIncome" path="mainIncome" cssErrorClass="error"><fmt:message key="socialworker.form.mainIncome" /></form:label></td>
				<td><c:forEach var="mainIncome" items="${mainIncome}">
						<form:radiobutton path="mainIncome" value="${mainIncome}" /> ${mainIncome.description}<br/>
					</c:forEach>
					<form:errors path="mainIncome" /></td></tr>
			<tr>
				<td><form:label for="hasHealthCare" path="hasHealthCare" cssErrorClass="error"><fmt:message key="socialworker.form.hasHealthCare" /></form:label></td>
				<td><form:checkbox path="hasHealthCare" /> <form:errors path="hasHealthCare" /></td></tr>
			<tr>
				<td><!-- TODO: esto deberia ser multiple.. hay que modificar el modelo -->
					<form:label for="nbi" path="nbi" cssErrorClass="error"><fmt:message key="socialworker.form.nbi" /></form:label></td>
				<td><c:forEach var="nbi" items="${nbi}">
						<form:radiobutton path="nbi" value="${nbi}" /> ${nbi.description}<br/>
					</c:forEach>
					<form:errors path="nbi" /></td></tr>
			<tr>
				<td><form:label for="addiction" path="addiction" cssErrorClass="error"><fmt:message key="socialworker.form.addiction" /></form:label></td>
				<td><c:forEach var="addiction" items="${addiction}">
						<form:radiobutton path="addiction" value="${addiction}" /> ${addiction.description}<br/>
					</c:forEach>
					<form:errors path="addiction" /></td></tr>
			<tr>
				<td><form:label for="hasProfessionalAssistanceForAddiction" path="hasProfessionalAssistanceForAddiction" cssErrorClass="error"><fmt:message key="socialworker.form.hasProfessionalAssistanceForAddiction" /></form:label></td>
				<td><form:checkbox path="hasProfessionalAssistanceForAddiction" /> <form:errors path="hasProfessionalAssistanceForAddiction" /></td></tr>
			<tr>
				<td><form:label for="mistreatment" path="mistreatment" cssErrorClass="error"><fmt:message key="socialworker.form.mistreatment" /></form:label></td>
				<td><c:forEach var="mistreatment" items="${mistreatment}">
						<form:radiobutton path="mistreatment" value="${mistreatment}" /> ${mistreatment.description}<br/>
					</c:forEach>
					<form:errors path="mistreatment" /></td></tr>
			<tr>
				<td><form:label for="hasProfessionalAssistanceForMistreatment" path="hasProfessionalAssistanceForMistreatment" cssErrorClass="error"><fmt:message key="socialworker.form.hasProfessionalAssistanceForMistreatment" /></form:label></td>
				<td><form:checkbox path="hasProfessionalAssistanceForMistreatment" /> <form:errors path="hasProfessionalAssistanceForMistreatment" /></td></tr>
			<tr>
				<td><form:label for="hasBeenDerivedToOtherSocialServices" path="hasBeenDerivedToOtherSocialServices" cssErrorClass="error"><fmt:message key="socialworker.form.hasBeenDerivedToOtherSocialServices" /></form:label></td>
				<td><form:checkbox path="hasBeenDerivedToOtherSocialServices" /> <form:errors path="hasBeenDerivedToOtherSocialServices" /></td></tr>
			<tr>
				<td><form:label for="diagnose" path="diagnose" cssErrorClass="error"><fmt:message key="socialworker.form.diagnose" /></form:label></td>
				<td><form:textarea path="diagnose" /><form:errors path="diagnose" /></td></tr>
			<tr>
				<td><form:label for="observations" path="observations" cssErrorClass="error"><fmt:message key="socialworker.form.observations" /></form:label></td>
				<td><form:textarea path="observations" /><form:errors path="observations" /></td></tr>
			<tr>
				<td><input type="submit" /></td>
				<td> </td></tr>
		</table>
	</div>
	</div>
</form:form>
<hr>