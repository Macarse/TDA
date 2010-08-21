<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:choose>
	<c:when test="${socialWorkerForm.new}"><c:set var="method" value="post"/></c:when>
	<c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

<h2><c:if test="${socialWorkerForm.new}"><fmt:message key="socialworker.form.new" /></c:if><fmt:message key="socialworker.form.form" /></h2>

<b><fmt:message key="socialworker.form.patient" /></b> ${socialWorkerForm.patient.firstName} ${socialWorkerForm.patient.lastName}
<br/>

<div class="span-12 last"><form:form modelAttribute="socialWorkerForm" method="post">
	<fieldset>
	
	<legend><fmt:message key="socialworker.form.socialwork" /></legend>
	
	<!-- Aca empieza la seccion de Grupo Familiar -->
	
	<p><form:label for="fatherFirstName" path="fatherFirstName" cssErrorClass="error"><fmt:message key="socialworker.form.fatherFirstName" /></form:label><br />
	<form:input path="fatherFirstName" /> <form:errors path="fatherFirstName" /></p>
	
	<p><form:label for="fatherLastName" path="fatherLastName" cssErrorClass="error"><fmt:message key="socialworker.form.fatherLastName" /></form:label><br />
	<form:input path="fatherLastName" /> <form:errors path="fatherLastName" /></p>
	
	<p><form:label for="fatherAge" path="fatherAge" cssErrorClass="error"><fmt:message key="socialworker.form.fatherAge" /></form:label><br />
	<form:input path="fatherAge" /> <form:errors path="fatherAge" /></p>
	
	<p><form:label for="motherFirstName" path="motherFirstName" cssErrorClass="error"><fmt:message key="socialworker.form.motherFirstName" /></form:label><br />
	<form:input path="motherFirstName" /> <form:errors path="motherFirstName" /></p>
	
	<p><form:label for="motherLastName" path="motherLastName" cssErrorClass="error"><fmt:message key="socialworker.form.motherLastName" /></form:label><br />
	<form:input path="motherLastName" /> <form:errors path="motherLastName" /></p>
	
	<p><form:label for="motherAge" path="motherAge" cssErrorClass="error"><fmt:message key="socialworker.form.motherAge" /></form:label><br />
	<form:input path="motherAge" /> <form:errors path="motherAge" /></p>
	
	<p><form:label for="tutorFirstName" path="tutorFirstName" cssErrorClass="error"><fmt:message key="socialworker.form.tutorFirstName" /></form:label><br />
	<form:input path="tutorFirstName" /> <form:errors path="tutorFirstName" /></p>
	
	<p><form:label for="tutorLastName" path="tutorLastName" cssErrorClass="error"><fmt:message key="socialworker.form.tutorLastName" /></form:label><br />
	<form:input path="tutorLastName" /> <form:errors path="tutorLastName" /></p>
	
	<p><form:label for="tutorAge" path="tutorAge" cssErrorClass="error"><fmt:message key="socialworker.form.tutorAge" /></form:label><br />
	<form:input path="tutorAge" /> <form:errors path="tutorAge" /></p>

	<!--  No se puede usar form:radiobuttons para popular todo directamente porque usa
	el key de los enum y necesitamos la description para que tenga sentido... -->
	<p><form:label for="livesWith" path="livesWith" cssErrorClass="error"><fmt:message key="socialworker.form.livesWith" /></form:label><br />
		<c:forEach var="livesWith" items="${livesWith}">
			<form:radiobutton path="livesWith" value="${livesWith}" /> ${livesWith.description}
			<br />
		</c:forEach>
	<form:errors path="livesWith" /></p>
	
	<p><form:label for="peopleAtHome" path="peopleAtHome" cssErrorClass="error"><fmt:message key="socialworker.form.peopleAtHome" /></form:label><br />
	<form:input path="peopleAtHome" /> <form:errors path="peopleAtHome" /></p>
	
	<p><form:label for="peopleAtHomeOverTen" path="peopleAtHomeOverTen" cssErrorClass="error"><fmt:message key="socialworker.form.peopleAtHomeOverTen" /></form:label><br />
	<form:input path="peopleAtHomeOverTen" /> <form:errors path="peopleAtHomeOverTen" /></p>
	
	<p><form:label for="peopleAtHomeUnderTen" path="peopleAtHomeUnderTen" cssErrorClass="error"><fmt:message key="socialworker.form.peopleAtHomeUnderTen" /></form:label><br />
	<form:input path="peopleAtHomeUnderTen" /> <form:errors path="peopleAtHomeUnderTen" /></p>
	
	<!-- Aca empieza la seccion de Vivienda -->
	
	<p><form:label for="roomsExcludingKitchenAndBathroom" path="roomsExcludingKitchenAndBathroom" cssErrorClass="error"><fmt:message key="socialworker.form.roomsExcludingKitchenAndBathroom" /></form:label><br />
	<form:input path="roomsExcludingKitchenAndBathroom" /> <form:errors path="roomsExcludingKitchenAndBathroom" /></p>

	<p><form:label for="interiorFloor" path="interiorFloor" cssErrorClass="error"><fmt:message key="socialworker.form.interiorFloor" /></form:label><br />
		<c:forEach var="interiorFloor" items="${interiorFloor}">
			<form:radiobutton path="interiorFloor" value="${interiorFloor}" /> ${interiorFloor.description}
			<br />
		</c:forEach>
	<form:errors path="interiorFloor" /></p>

	<p><form:label for="roofType" path="roofType" cssErrorClass="error"><fmt:message key="socialworker.form.roofType" /></form:label><br />
		<c:forEach var="roofType" items="${roofType}">
			<form:radiobutton path="roofType" value="${roofType}" /> ${roofType.description}
			<br />
		</c:forEach>
	<form:errors path="roofType" /></p>
	
	<p><form:label for="hasCeiling" path="hasCeiling" cssErrorClass="error"><fmt:message key="socialworker.form.hasCeiling" /></form:label><br />
	<form:checkbox path="hasCeiling" /> <form:errors path="hasCeiling" /></p>
	
	<p><form:label for="waterSource" path="waterSource" cssErrorClass="error"><fmt:message key="socialworker.form.waterSource" /></form:label><br />
		<c:forEach var="waterSource" items="${waterSource}">
			<form:radiobutton path="waterSource" value="${waterSource}" /> ${waterSource.description}
			<br />
		</c:forEach>
	<form:errors path="waterSource" /></p>
	
	<p><form:label for="waterSourceType" path="waterSourceType" cssErrorClass="error"><fmt:message key="socialworker.form.waterSourceType" /></form:label><br />
		<c:forEach var="waterSourceType" items="${waterSourceType}">
			<form:radiobutton path="waterSourceType" value="${waterSourceType}" /> ${waterSourceType.description}
			<br />
		</c:forEach>
	<form:errors path="waterSourceType" /></p>
	
	<p><form:label for="hasBathroom" path="hasBathroom" cssErrorClass="error"><fmt:message key="socialworker.form.hasBathroom" /></form:label><br />
	<form:checkbox path="hasBathroom" /> <form:errors path="hasBathroom" /></p>
	
	<p><form:label for="bathroomInside" path="bathroomInside" cssErrorClass="error"><fmt:message key="socialworker.form.bathroomInside" /></form:label><br />
	<form:checkbox path="bathroomInside" /> <form:errors path="bathroomInside" /></p>
	
	<p><form:label for="bathroomSewerType" path="bathroomSewerType" cssErrorClass="error"><fmt:message key="socialworker.form.bathroomSewerType" /></form:label><br />
		<c:forEach var="bathroomSewerType" items="${bathroomSewerType}">
			<form:radiobutton path="bathroomSewerType" value="${bathroomSewerType}" /> ${bathroomSewerType.description}
			<br />
		</c:forEach>
	<form:errors path="bathroomSewerType" /></p>
	
	<p><form:label for="inSinkingZone" path="inSinkingZone" cssErrorClass="error"><fmt:message key="socialworker.form.isSinkingZone" /></form:label><br />
	<form:checkbox path="inSinkingZone" /> <form:errors path="inSinkingZone" /></p>
	
	<p><form:label for="kitchenFuel" path="kitchenFuel" cssErrorClass="error"><fmt:message key="socialworker.form.kitchenFuel" /></form:label><br />
		<c:forEach var="kitchenFuel" items="${kitchenFuel}">
			<form:radiobutton path="kitchenFuel" value="${kitchenFuel}" /> ${kitchenFuel.description}
			<br />
		</c:forEach>
	<form:errors path="kitchenFuel" /></p>
	
	<p><form:label for="electricity" path="electricity" cssErrorClass="error"><fmt:message key="socialworker.form.electricity" /></form:label><br />
		<c:forEach var="electricity" items="${electricity}">
			<form:radiobutton path="electricity" value="${electricity}" /> ${electricity.description}
			<br />
		</c:forEach>
	<form:errors path="electricity" /></p>
	
	<!-- Aca empieza la seccion de Escolaridad -->
	
	<p><form:label for="knowsHowToReadAndWrite" path="knowsHowToReadAndWrite" cssErrorClass="error"><fmt:message key="socialworker.form.knowsHowToReadAndWrite" /></form:label><br />
	<form:checkbox path="knowsHowToReadAndWrite" /> <form:errors path="knowsHowToReadAndWrite" /></p>
	
	<p><form:label for="goingToSchool" path="goingToSchool" cssErrorClass="error"><fmt:message key="socialworker.form.goingToSchool" /></form:label><br />
	<form:checkbox path="goingToSchool" /> <form:errors path="goingToSchool" /></p>
	
	<p><form:label for="scholarity" path="scholarity" cssErrorClass="error"><fmt:message key="socialworker.form.scholarity" /></form:label><br />
		<c:forEach var="scholarity" items="${scholarity}">
			<form:radiobutton path="scholarity" value="${scholarity}" /> ${scholarity.description}
			<br />
		</c:forEach>
	<form:errors path="scholarity" /></p>
	
	<p><form:label for="schoolHours" path="schoolHours" cssErrorClass="error"><fmt:message key="socialworker.form.schoolHours" /></form:label><br />
		<c:forEach var="schoolHours" items="${schoolHours}">
			<form:radiobutton path="schoolHours" value="${schoolHours}" /> ${schoolHours.description}
			<br />
		</c:forEach>
	<form:errors path="schoolHours" /></p>
	
	<p><form:label for="schoolService" path="schoolService" cssErrorClass="error"><fmt:message key="socialworker.form.schoolService" /></form:label><br />
		<c:forEach var="schoolService" items="${schoolService}">
			<form:radiobutton path="schoolService" value="${schoolService}" /> ${schoolService.description}
			<br />
		</c:forEach>
	<form:errors path="schoolService" /></p>
	
	<!-- Aca empieza la seccion de Datos socioeconomicos del grupo familiar -->
	
	<p><form:label for="workingPeople" path="workingPeople" cssErrorClass="error"><fmt:message key="socialworker.form.workingPeople" /></form:label><br />
	<form:input path="workingPeople" /> <form:errors path="workingPeople" /></p>
	
		<c:forEach var="adultEducationalLevel" items="${adultEducationalLevel}">
	<p><form:label for="adultEducationalLevel" path="adultEducationalLevel" cssErrorClass="error"><fmt:message key="socialworker.form.adultEducationalLevel" /></form:label><br />
			<form:radiobutton path="adultEducationalLevel" value="${adultEducationalLevel}" /> ${adultEducationalLevel.description}
			<br />
		</c:forEach>
	<form:errors path="adultEducationalLevel" /></p>
	
	<p><form:label for="mainIncome" path="mainIncome" cssErrorClass="error"><fmt:message key="socialworker.form.mainIncome" /></form:label><br />
		<c:forEach var="mainIncome" items="${mainIncome}">
			<form:radiobutton path="mainIncome" value="${mainIncome}" /> ${mainIncome.description}
			<br />
		</c:forEach>
	<form:errors path="mainIncome" /></p>
	
	<p><form:label for="hasHealthCare" path="hasHealthCare" cssErrorClass="error"><fmt:message key="socialworker.form.hasHealthCare" /></form:label><br />
	<form:checkbox path="hasHealthCare" /> <form:errors path="hasHealthCare" /></p>
	
	<!-- TODO: esto deberia ser multiple.. hay que modificar el modelo -->
	<p><form:label for="nbi" path="nbi" cssErrorClass="error"><fmt:message key="socialworker.form.nbi" /></form:label><br />
		<c:forEach var="nbi" items="${nbi}">
			<form:radiobutton path="nbi" value="${nbi}" /> ${nbi.description}
			<br />
		</c:forEach>
	<form:errors path="nbi" /></p>
	
	<p><form:label for="addiction" path="addiction" cssErrorClass="error"><fmt:message key="socialworker.form.addiction" /></form:label><br />
		<c:forEach var="addiction" items="${addiction}">
			<form:radiobutton path="addiction" value="${addiction}" /> ${addiction.description}
			<br />
		</c:forEach>
	<form:errors path="addiction" /></p>
	
	<p><form:label for="hasProfessionalAssistanceForAddiction" path="hasProfessionalAssistanceForAddiction" cssErrorClass="error"><fmt:message key="socialworker.form.hasProfessionalAssistanceForAddiction" /></form:label><br />
	<form:checkbox path="hasProfessionalAssistanceForAddiction" /> <form:errors path="hasProfessionalAssistanceForAddiction" /></p>
	
	<p><form:label for="mistreatment" path="mistreatment" cssErrorClass="error"><fmt:message key="socialworker.form.mistreatment" /></form:label><br />
		<c:forEach var="mistreatment" items="${mistreatment}">
			<form:radiobutton path="mistreatment" value="${mistreatment}" /> ${mistreatment.description}
			<br />
		</c:forEach>
	<form:errors path="mistreatment" /></p>
	
	<p><form:label for="hasProfessionalAssistanceForMistreatment" path="hasProfessionalAssistanceForMistreatment" cssErrorClass="error"><fmt:message key="socialworker.form.hasProfessionalAssistanceForMistreatment" /></form:label><br />
	<form:checkbox path="hasProfessionalAssistanceForMistreatment" /> <form:errors path="hasProfessionalAssistanceForMistreatment" /></p>
	
	<p><form:label for="hasBeenDerivedToOtherSocialServices" path="hasBeenDerivedToOtherSocialServices" cssErrorClass="error"><fmt:message key="socialworker.form.hasBeenDerivedToOtherSocialServices" /></form:label><br />
	<form:checkbox path="hasBeenDerivedToOtherSocialServices" /> <form:errors path="hasBeenDerivedToOtherSocialServices" /></p>
	
	<p><form:label for="diagnose" path="diagnose" cssErrorClass="error"><fmt:message key="socialworker.form.diagnose" /></form:label><br />
	<form:textarea path="diagnose" /><form:errors path="diagnose" /></p>

	<p><form:label for="observations" path="observations" cssErrorClass="error"><fmt:message key="socialworker.form.observations" /></form:label><br />
	<form:textarea path="observations" /><form:errors path="observations" /></p>	
	
	<p><input type="submit" /></p>
	</fieldset>
</form:form></div>
<hr>
