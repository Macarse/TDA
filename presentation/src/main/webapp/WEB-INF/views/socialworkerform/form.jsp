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

<h2><c:if test="${socialWorkerForm.new}">Nuevo </c:if>Formulario de Trabajador Social</h2>

<b>Paciente:</b> ${socialWorkerForm.patient.firstName} ${socialWorkerForm.patient.lastName}
<br/>


<form:form modelAttribute="socialWorkerForm" method="post">
	<div id="form-tabs">
		<ul>
			<li><a href="#tab-gf">Grupo Familiar</a></li>
			<li><a href="#tab-viv">Vivienda</a></li>
			<li><a href="#tab-esc">Escolaridad</a></li>
		</ul>
		
		<!-- Aca empieza la seccion de Grupo Familiar -->
		<div id="tab-gf">
			<p><form:label for="fatherFirstName" path="fatherFirstName" cssErrorClass="error">Nombre del padre</form:label><br />
			<form:input path="fatherFirstName" /> <form:errors path="fatherFirstName" /></p>
			
			<p><form:label for="fatherLastName" path="fatherLastName" cssErrorClass="error">Apellido del padre</form:label><br />
			<form:input path="fatherLastName" /> <form:errors path="fatherLastName" /></p>
			
			<p><form:label for="fatherAge" path="fatherAge" cssErrorClass="error">Edad del padre</form:label><br />
			<form:input path="fatherAge" /> <form:errors path="fatherAge" /></p>
			
			<p><form:label for="motherFirstName" path="motherFirstName" cssErrorClass="error">Nombre de la madre</form:label><br />
			<form:input path="motherFirstName" /> <form:errors path="motherFirstName" /></p>
			
			<p><form:label for="motherLastName" path="motherLastName" cssErrorClass="error">Apellido de la madre</form:label><br />
			<form:input path="motherLastName" /> <form:errors path="motherLastName" /></p>
			
			<p><form:label for="motherAge" path="motherAge" cssErrorClass="error">Edad de la madre</form:label><br />
			<form:input path="motherAge" /> <form:errors path="motherAge" /></p>
			
			<p><form:label for="tutorFirstName" path="tutorFirstName" cssErrorClass="error">Nombre del tutor</form:label><br />
			<form:input path="tutorFirstName" /> <form:errors path="tutorFirstName" /></p>
			
			<p><form:label for="tutorLastName" path="tutorLastName" cssErrorClass="error">Apellido del tutor</form:label><br />
			<form:input path="tutorLastName" /> <form:errors path="tutorLastName" /></p>
			
			<p><form:label for="tutorAge" path="tutorAge" cssErrorClass="error">Edad del tutor</form:label><br />
			<form:input path="tutorAge" /> <form:errors path="tutorAge" /></p>
		
			<!--  No se puede usar form:radiobuttons para popular todo directamente porque usa
			el key de los enum y necesitamos la description para que tenga sentido... -->
			<p><form:label for="livesWith" path="livesWith" cssErrorClass="error">Vive con</form:label><br />
				<c:forEach var="livesWith" items="${livesWith}">
					<form:radiobutton path="livesWith" value="${livesWith}" /> ${livesWith.description}
					<br />
				</c:forEach>
			<form:errors path="livesWith" /></p>
			
			<p><form:label for="peopleAtHome" path="peopleAtHome" cssErrorClass="error">Cantidad de miembros del hogar</form:label><br />
			<form:input path="peopleAtHome" /> <form:errors path="peopleAtHome" /></p>
			
			<p><form:label for="peopleAtHomeOverTen" path="peopleAtHomeOverTen" cssErrorClass="error">Cantidad de miembros del hogar mayores de 10 años</form:label><br />
			<form:input path="peopleAtHomeOverTen" /> <form:errors path="peopleAtHomeOverTen" /></p>
			
			<p><form:label for="peopleAtHomeUnderTen" path="peopleAtHomeUnderTen" cssErrorClass="error">Cantidad de miembros del hogar menores de 10 años</form:label><br />
			<form:input path="peopleAtHomeUnderTen" /> <form:errors path="peopleAtHomeUnderTen" /></p>
		</div>	
		
		<!-- Aca empieza la seccion de Vivienda -->
		<div id="tab-viv">
			<p><form:label for="roomsExcludingKitchenAndBathroom" path="roomsExcludingKitchenAndBathroom" cssErrorClass="error">Cantidad de ambientes/habitaciones sin contar la cocina y el baño</form:label><br />
			<form:input path="roomsExcludingKitchenAndBathroom" /> <form:errors path="roomsExcludingKitchenAndBathroom" /></p>
		
			<p><form:label for="interiorFloor" path="interiorFloor" cssErrorClass="error">Los pisos interiores son principalmente de</form:label><br />
				<c:forEach var="interiorFloor" items="${interiorFloor}">
					<form:radiobutton path="interiorFloor" value="${interiorFloor}" /> ${interiorFloor.description}
					<br />
				</c:forEach>
			<form:errors path="interiorFloor" /></p>
		
			<p><form:label for="roofType" path="roofType" cssErrorClass="error">La cubierta exterior del techo es de</form:label><br />
				<c:forEach var="roofType" items="${roofType}">
					<form:radiobutton path="roofType" value="${roofType}" /> ${roofType.description}
					<br />
				</c:forEach>
			<form:errors path="roofType" /></p>
			
			<p><form:label for="hasCeiling" path="hasCeiling" cssErrorClass="error">El techo tiene cielorraso/revestimiento interior</form:label><br />
			<form:checkbox path="hasCeiling" /> <form:errors path="hasCeiling" /></p>
			
			<p><form:label for="waterSource" path="waterSource" cssErrorClass="error">Tiene agua...</form:label><br />
				<c:forEach var="waterSource" items="${waterSource}">
					<form:radiobutton path="waterSource" value="${waterSource}" /> ${waterSource.description}
					<br />
				</c:forEach>
			<form:errors path="waterSource" /></p>
			
			<p><form:label for="waterSourceType" path="waterSourceType" cssErrorClass="error">El agua es de...</form:label><br />
				<c:forEach var="waterSourceType" items="${waterSourceType}">
					<form:radiobutton path="waterSourceType" value="${waterSourceType}" /> ${waterSourceType.description}
					<br />
				</c:forEach>
			<form:errors path="waterSourceType" /></p>
			
			<p><form:label for="hasBathroom" path="hasBathroom" cssErrorClass="error">Tiene baño/letrina</form:label><br />
			<form:checkbox path="hasBathroom" /> <form:errors path="hasBathroom" /></p>
			
			<p><form:label for="bathroomInside" path="bathroomInside" cssErrorClass="error">El baño está dentro de la vivienda</form:label><br />
			<form:checkbox path="bathroomInside" /> <form:errors path="bathroomInside" /></p>
			
			<p><form:label for="bathroomSewerType" path="bathroomSewerType" cssErrorClass="error">El desagüe del baño es</form:label><br />
				<c:forEach var="bathroomSewerType" items="${bathroomSewerType}">
					<form:radiobutton path="bathroomSewerType" value="${bathroomSewerType}" /> ${bathroomSewerType.description}
					<br />
				</c:forEach>
			<form:errors path="bathroomSewerType" /></p>
			
			<p><form:label for="inSinkingZone" path="inSinkingZone" cssErrorClass="error">La vivienda está ubicada en zona inundable</form:label><br />
			<form:checkbox path="inSinkingZone" /> <form:errors path="inSinkingZone" /></p>
			
			<p><form:label for="kitchenFuel" path="kitchenFuel" cssErrorClass="error">Combustible utilizado para cocinar</form:label><br />
				<c:forEach var="kitchenFuel" items="${kitchenFuel}">
					<form:radiobutton path="kitchenFuel" value="${kitchenFuel}" /> ${kitchenFuel.description}
					<br />
				</c:forEach>
			<form:errors path="kitchenFuel" /></p>
			
			<p><form:label for="electricity" path="electricity" cssErrorClass="error">Electricidad</form:label><br />
				<c:forEach var="electricity" items="${electricity}">
					<form:radiobutton path="electricity" value="${electricity}" /> ${electricity.description}
					<br />
				</c:forEach>
			<form:errors path="electricity" /></p>
		</div>
		
		<!-- Aca empieza la seccion de Escolaridad -->
		<div id="tab-esc">
			<p><form:label for="knowsHowToReadAndWrite" path="knowsHowToReadAndWrite" cssErrorClass="error">Sabe leer y escribir</form:label><br />
			<form:checkbox path="knowsHowToReadAndWrite" /> <form:errors path="knowsHowToReadAndWrite" /></p>
			
			<p><form:label for="goingToSchool" path="goingToSchool" cssErrorClass="error">Asiste a la escuela</form:label><br />
			<form:checkbox path="goingToSchool" /> <form:errors path="goingToSchool" /></p>
			
			<p><form:label for="scholarity" path="scholarity" cssErrorClass="error">Escolaridad</form:label><br />
				<c:forEach var="scholarity" items="${scholarity}">
					<form:radiobutton path="scholarity" value="${scholarity}" /> ${scholarity.description}
					<br />
				</c:forEach>
			<form:errors path="scholarity" /></p>
			
			<p><form:label for="schoolHours" path="schoolHours" cssErrorClass="error">El horario en el que asiste a la escuela es</form:label><br />
				<c:forEach var="schoolHours" items="${schoolHours}">
					<form:radiobutton path="schoolHours" value="${schoolHours}" /> ${schoolHours.description}
					<br />
				</c:forEach>
			<form:errors path="schoolHours" /></p>
			
			<p><form:label for="schoolService" path="schoolService" cssErrorClass="error">La escuela tiene servicio de</form:label><br />
				<c:forEach var="schoolService" items="${schoolService}">
					<form:radiobutton path="schoolService" value="${schoolService}" /> ${schoolService.description}
					<br />
				</c:forEach>
			<form:errors path="schoolService" /></p>
			
			<!-- Aca empieza la seccion de Datos socioeconomicos del grupo familiar -->
			
			<p><form:label for="workingPeople" path="workingPeople" cssErrorClass="error">Cantidad de personas que trabajan</form:label><br />
			<form:input path="workingPeople" /> <form:errors path="workingPeople" /></p>
			
			<p><form:label for="adultEducationalLevel" path="adultEducationalLevel" cssErrorClass="error">Nivel de educación formal de los adultos</form:label><br />
				<c:forEach var="adultEducationalLevel" items="${adultEducationalLevel}">
					<form:radiobutton path="adultEducationalLevel" value="${adultEducationalLevel}" /> ${adultEducationalLevel.description}
					<br />
				</c:forEach>
			<form:errors path="adultEducationalLevel" /></p>
			
			<p><form:label for="mainIncome" path="mainIncome" cssErrorClass="error">Ingreso más importante del hogar</form:label><br />
				<c:forEach var="mainIncome" items="${mainIncome}">
					<form:radiobutton path="mainIncome" value="${mainIncome}" /> ${mainIncome.description}
					<br />
				</c:forEach>
			<form:errors path="mainIncome" /></p>
			
			<p><form:label for="hasHealthCare" path="hasHealthCare" cssErrorClass="error">Tiene algún tipo de obra social</form:label><br />
			<form:checkbox path="hasHealthCare" /> <form:errors path="hasHealthCare" /></p>
			
			<!-- TODO: esto deberia ser multiple.. hay que modificar el modelo -->
			<p><form:label for="nbi" path="nbi" cssErrorClass="error">Necesidad básica insatisfecha</form:label><br />
				<c:forEach var="nbi" items="${nbi}">
					<form:radiobutton path="nbi" value="${nbi}" /> ${nbi.description}
					<br />
				</c:forEach>
			<form:errors path="nbi" /></p>
			
			<p><form:label for="addiction" path="addiction" cssErrorClass="error">Refiere casos de adicciones en el grupo familiar</form:label><br />
				<c:forEach var="addiction" items="${addiction}">
					<form:radiobutton path="addiction" value="${addiction}" /> ${addiction.description}
					<br />
				</c:forEach>
			<form:errors path="addiction" /></p>
			
			<p><form:label for="hasProfessionalAssistanceForAddiction" path="hasProfessionalAssistanceForAddiction" cssErrorClass="error">Se ha dado intervención a un profesional</form:label><br />
			<form:checkbox path="hasProfessionalAssistanceForAddiction" /> <form:errors path="hasProfessionalAssistanceForAddiction" /></p>
			
			<p><form:label for="mistreatment" path="mistreatment" cssErrorClass="error">Alguno de los miembros del grupo familiar manifiesta signos de maltrato</form:label><br />
				<c:forEach var="mistreatment" items="${mistreatment}">
					<form:radiobutton path="mistreatment" value="${mistreatment}" /> ${mistreatment.description}
					<br />
				</c:forEach>
			<form:errors path="mistreatment" /></p>
			
			<p><form:label for="hasProfessionalAssistanceForMistreatment" path="hasProfessionalAssistanceForMistreatment" cssErrorClass="error">Se ha dado intervención a un profesional</form:label><br />
			<form:checkbox path="hasProfessionalAssistanceForMistreatment" /> <form:errors path="hasProfessionalAssistanceForMistreatment" /></p>
			
			<p><form:label for="hasBeenDerivedToOtherSocialServices" path="hasBeenDerivedToOtherSocialServices" cssErrorClass="error">Se han producido derivaciones a otros servicios sociales</form:label><br />
			<form:checkbox path="hasBeenDerivedToOtherSocialServices" /> <form:errors path="hasBeenDerivedToOtherSocialServices" /></p>
			
			<p><form:label for="diagnose" path="diagnose" cssErrorClass="error">Diagnóstico</form:label><br />
			<form:textarea path="diagnose" /><form:errors path="diagnose" /></p>
		
			<p><form:label for="observations" path="observations" cssErrorClass="error">Observaciones</form:label><br />
			<form:textarea path="observations" /><form:errors path="observations" /></p>	
			
			<p><input type="submit" /></p>
		</div>
	</div>
</form:form>
