<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	$(document).ready(function(){
		$("#form-tabs").tabs();

		if( document.getElementById('distosicRadioButton').checked )
			$('.distosicHidden').show();
		
	});
</script>

<!-- aux variables -->
<c:set var="count" value="0" />

<c:choose>
	<c:when test="${pediatricianForm.new}">
		<c:set var="method" value="post"/>
	</c:when>
	<c:otherwise>
		<c:set var="method" value="put"/>
	</c:otherwise>
</c:choose>

<h2>
	<c:if test="${pediatricianForm.new}">
		<fmt:message key="pediatrician.form.new" />
	</c:if>
	<fmt:message key="pediatrician.form.form" />
</h2>

<b>
	<fmt:message key="pediatrician.form.patient" />
</b> 
${pediatricianForm.patient.firstName} ${pediatricianForm.patient.lastName}
<br/>

<form:form modelAttribute="pediatricianForm" method="post">
	<div id="form-tabs">
		<ul>
			<li><a href="#tab-peb"><fmt:message key="pediatrician.form.perinatalBackground" /></a></li>
			<li><a href="#tab-pab"><fmt:message key="pediatrician.form.patientBackground" /></a></li>
			<li><a href="#tab-fab"><fmt:message key="pediatrician.form.familyBackground" /></a></li>
			<li><a href="#tab-mat"><fmt:message key="pediatrician.form.maturation" /></a></li>
			<li><a href="#tab-phy"><fmt:message key="pediatrician.form.physical" /></a></li>
			<li><a href="#tab-lab"><fmt:message key="pediatrician.form.laboratory" /></a></li>
			<li><a href="#tab-rad"><fmt:message key="pediatrician.form.radiology" /></a></li>
			<li><a href="#tab-dia"><fmt:message key="pediatrician.form.diagnosis" /></a></li>
			<li><a href="#tab-int"><fmt:message key="pediatrician.form.internment" /></a></li>		 
		</ul>
		
		<!-- PERINATAL BACKGROUND -->
		<div id="tab-peb" class="pediatricianform">
			<table width="100%">
				<tr><th colspan="2">
					<form:label for="birthPlace" path="birthPlace" cssErrorClass="error">
						<fmt:message key="pediatrician.form.birthPlace" />
					</form:label></th></tr>
					
					<c:forEach var="birthPlace" items="${birthPlace}">
						<c:if test="${count%2 == 0 }"><tr></c:if>
							<td><form:radiobutton path="birthPlace" value="${birthPlace}" /> ${birthPlace.description}</td>
						<c:if test="${count%2 == 1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%2 == 1 }"></tr></c:if>
					<tr><td><form:errors path="birthPlace" /></td></tr>
				
				<tr><th>
					<form:label for="patologyDuringBirth" path="patologyDuringBirth" cssErrorClass="error">
						<fmt:message key="pediatrician.form.patologyDuringBirth" />
					</form:label></th>
					<td><form:checkbox path="patologyDuringBirth" /> <form:errors path="patologyDuringBirth" />
				</td></tr>
					
				<tr><th colspan="2">
					<form:label for="birthType" path="birthType" cssErrorClass="error">
						<fmt:message key="pediatrician.form.birthType" />
					</form:label></th></tr>

					<c:set var="count" value="0" />
					<c:forEach var="birthType" items="${birthType}">
						<c:if test="${count%2 == 0 }"><tr></c:if>
							<td>
							<c:choose>
							<c:when test="${birthType.description == 'Dist√≥cico'}">
								<form:radiobutton id="distosicRadioButton" path="birthType" value="${birthType}" onclick="$('.distosicHidden').show()"/> ${birthType.description}
							</c:when>
							<c:otherwise>
								<form:radiobutton path="birthType" value="${birthType}" onclick="$('.distosicHidden').hide()"/> ${birthType.description}
							</c:otherwise>
							</c:choose>
							</td>
						<c:if test="${count%2 == 1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%2 == 1 }"></tr></c:if>
					<tr><td><form:errors path="birthType" /></td></tr>
				
				<tr class="distosicHidden"><th colspan="2">
					<form:label for="birthTypePresentation" path="birthTypePresentation" cssErrorClass="error">
						<fmt:message key="pediatrician.form.birthTypePresentation" />
					</form:label></th></tr>
					
					<c:set var="count" value="0" />
					<c:forEach var="birthTypePresentation" items="${birthTypePresentation}">
						<c:if test="${count%2 == 0 }"><tr class="distosicHidden"></c:if>
							<td><form:radiobutton path="birthTypePresentation" value="${birthTypePresentation}" /> ${birthTypePresentation.description}</td>
						<c:if test="${count%2 == 1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%2 == 1 }"></tr></c:if>
					<tr class="distosicHidden"><td><form:errors path="birthTypePresentation" /></td></tr>
					
				<tr class="distosicHidden"><th>
					<form:label for="birthTypeTermination" path="birthTypeTermination" cssErrorClass="error">
						<fmt:message key="pediatrician.form.birthTypeTermination" />
					</form:label>
					</th></tr>
					
					<c:set var="count" value="0" />
					<c:forEach var="birthTypeTermination" items="${birthTypeTermination}">
						<c:if test="${count%2 == 0 }"><tr class="distosicHidden"></c:if>
							<td><form:radiobutton path="birthTypeTermination" value="${birthTypeTermination}" /> ${birthTypeTermination.description}</td>
						<c:if test="${count%2 == 1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%2 == 1 }"></tr></c:if>
					<tr class="distosicHidden"><td><form:errors path="birthTypeTermination" /></td></tr>

				<tr><th>
					<form:label for="gestationalAge" path="gestationalAge" cssErrorClass="error">
						<fmt:message key="pediatrician.form.gestationalAge" />
					</form:label></th>
					<td><form:input path="gestationalAge" /></td></tr>
					<tr><td><form:errors path="gestationalAge" /></td></tr>
				
				
				<tr><th>
					<form:label for="birthWeight" path="birthWeight" cssErrorClass="error">
						<fmt:message key="pediatrician.form.birthWeight" />
					</form:label></th>
					<td><form:input path="birthWeight" /></td></tr>
					<tr><td><form:errors path="birthWeight" /></td></tr>
				
				<tr><th>
					<form:label for="size" path="size" cssErrorClass="error">
						<fmt:message key="pediatrician.form.size" />
					</form:label></th>
					<td><form:input path="size" /></td></tr>
					<tr><td><form:errors path="size" /></td></tr>
				
				<tr><th>
					<form:label for="headCircumference" path="headCircumference" cssErrorClass="error">
						<fmt:message key="pediatrician.form.headCircumference" />
					</form:label></th>
					<td><form:input path="headCircumference" /></td></tr>
					<tr><td><form:errors path="headCircumference" /></td></tr>
			
				<tr><th>
					<form:label for="apgarDepressed" path="apgarDepressed" cssErrorClass="error">
						<fmt:message key="pediatrician.form.apgarDepressed" />
					</form:label></th>
					<td><form:checkbox path="apgarDepressed" /></td></tr> 
					<tr><td><form:errors path="apgarDepressed" /></td></tr>
				
				<tr><th>
					<form:label for="apgarReanimated" path="apgarReanimated" cssErrorClass="error">
						<fmt:message key="pediatrician.form.apgarReanimated" />
					</form:label></th>
					<td><form:checkbox path="apgarReanimated" /></td></tr> 
					<tr><td><form:errors path="apgarReanimated" /></td></tr>
				
				<tr><th>
					<form:label for="malformation" path="malformation" cssErrorClass="error">
						<fmt:message key="pediatrician.form.malformation" />
					</form:label></th>
					<td><form:checkbox path="malformation" /></td></tr>
					<tr><td><form:errors path="malformation" /></td></tr>
				
				<tr><th>
					<form:label for="jaundice" path="jaundice" cssErrorClass="error">
						<fmt:message key="pediatrician.form.jaundice" />
					</form:label></th>
					<td><form:checkbox path="jaundice" /></td></tr> 
					<tr><td><form:errors path="jaundice" /></td></tr>
				
				<tr><th>
					<form:label for="respiratoryDisease" path="respiratoryDisease" cssErrorClass="error">
						<fmt:message key="pediatrician.form.respiratoryDisease" />
					</form:label></th>
					<td><form:checkbox path="respiratoryDisease" /></td></tr> 
					<tr><td><form:errors path="respiratoryDisease" /></td></tr>
				
				<tr><th>
					<form:label for="congenitalInfection" path="congenitalInfection" cssErrorClass="error">
						<fmt:message key="pediatrician.form.congenitalInfection" />
					</form:label></th>
					<td><form:checkbox path="congenitalInfection" /> </td></tr>
					<tr><td><form:errors path="congenitalInfection" /></td></tr>
				
				<tr><th>
					<form:label for="neurologicalProblems" path="neurologicalProblems" cssErrorClass="error">
						<fmt:message key="pediatrician.form.neurologicalProblems" />
					</form:label></th>
					<td><form:checkbox path="neurologicalProblems" /> </td></tr>
					<tr><td><form:errors path="neurologicalProblems" /></td></tr>
				
				<tr><th>
					<form:label for="aids" path="aids" cssErrorClass="error">
						<fmt:message key="pediatrician.form.aids" />
					</form:label></th>
					<td><form:checkbox path="aids" /></td></tr> 
				<tr>
					<td><form:errors path="aids" /></td></tr>
				
				<tr><th>
					<form:label for="acquiredInfection" path="acquiredInfection" cssErrorClass="error">
						<fmt:message key="pediatrician.form.acquiredInfection" />
					</form:label></th>
					<td><form:checkbox path="acquiredInfection" /> </td></tr>
				<tr>
					<td><form:errors path="acquiredInfection" /> </td></tr>
				
				<tr><th colspan="2">
					<form:label for="otherPerinatalDiseases" path="otherPerinatalDiseases" cssErrorClass="error">
						<fmt:message key="pediatrician.form.otherPerinatalDiseases" />
					</form:label></th></tr>
				<tr><td colspan="2">
					<form:textarea path="otherPerinatalDiseases" /></td></tr>
				<tr><td>
					<form:errors path="otherPerinatalDiseases" /></td></tr>
				
				<tr><th colspan="2">
					<form:label for="exitStatus" path="exitStatus" cssErrorClass="error">
						<fmt:message key="pediatrician.form.exitStatus" />
					</form:label></th></tr>
					
					<c:forEach var="exitStatus" items="${exitStatus}">
						<c:if test="${count%2 == 0 }"><tr></c:if>
							<td><form:radiobutton path="exitStatus" value="${exitStatus}" /> ${exitStatus.description}</td>
						<c:if test="${count%2 == 1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%2 == 1 }"></tr></c:if>
					<tr><td><form:errors path="exitStatus" /></td></tr>
			</table>
		</div>
		
		<!-- PATIENT BACKGROUND -->
		<div id="tab-pab" class="pediatricianform">
		    <table>
				<tr><th>
					<form:label for="consumedTobacco" path="consumedTobacco" cssErrorClass="error">
						<fmt:message key="pediatrician.form.consumedTobacco" />
					</form:label></th>
					<td><form:checkbox path="consumedTobacco" /></td></tr> 
				<tr><td><form:errors path="consumedTobacco" /></td></tr>
				
				<tr><th>
					<form:label for="consumedAlcohol" path="consumedAlcohol" cssErrorClass="error">
						<fmt:message key="pediatrician.form.consumedAlcohol" />
					</form:label></th>
					<td><form:checkbox path="consumedAlcohol" /></td></tr> 
				<tr><td><form:errors path="consumedAlcohol" /></td></tr>
				
				<tr><th>
					<form:label for="tattooed" path="tattooed" cssErrorClass="error">
						<fmt:message key="pediatrician.form.tattooed" />
					</form:label></th>
					<td><form:checkbox path="tattooed" /></td></tr>
				<tr><td><form:errors path="tattooed" /></td></tr>
				
				<tr><th>
					<form:label for="infectiousDiseases" path="infectiousDiseases" cssErrorClass="error">
						<fmt:message key="pediatrician.form.infectiousDiseases" />
					</form:label></th>
					<td><form:checkbox path="infectiousDiseases" /></td></tr>
				<tr><td><form:errors path="infectiousDiseases" /></td></tr>
				
				<tr><th>
					<form:label for="hemorrhagic" path="hemorrhagic" cssErrorClass="error">
						<fmt:message key="pediatrician.form.hemorrhagic" />
					</form:label></th>
					<td><form:checkbox path="hemorrhagic" /></td></tr> 
				<tr><td><form:errors path="hemorrhagic" /></td></tr>
				
				<tr><th>
					<form:label for="trauma" path="trauma" cssErrorClass="error">
						<fmt:message key="pediatrician.form.trauma" />
					</form:label></th>
					<td><form:checkbox path="trauma" /></td></tr> 
				<tr><td><form:errors path="trauma" /></td></tr>
				
				<tr><th>
					<form:label for="allergies" path="allergies" cssErrorClass="error">
						<fmt:message key="pediatrician.form.allergies" />
					</form:label></th>
					<td><form:checkbox path="allergies" /></td></tr> 
					<tr><td><form:errors path="allergies" /></td></tr>
				
				<tr><th>
					<form:label for="previousAdmissions" path="previousAdmissions" cssErrorClass="error">
						<fmt:message key="pediatrician.form.previousAdmissions" />
					</form:label></th>
					<td><form:checkbox path="previousAdmissions" /></td></tr> 
				<tr><td><form:errors path="previousAdmissions" /></td></tr>
				
				<tr><th colspan="2">
					<form:label for="otherPatientDiseases" path="otherPatientDiseases" cssErrorClass="error">
						<fmt:message key="pediatrician.form.otherPatientDiseases" />
					</form:label></th></tr>
				<tr><td colspan="2">
					<form:textarea path="otherPatientDiseases" /></td></tr>
				<tr><td><form:errors path="otherPatientDiseases" /></td></tr>
				
			</table>
		</div>
		
		<!-- FAMILY BACKGROUND -->
		<div id="tab-fab" class="pediatricianform">
			<table>
				<tr><th>
					<form:label for="cardiovascular" path="cardiovascular" cssErrorClass="error">
						<fmt:message key="pediatrician.form.cardiovascular" />
					</form:label></th>
					<td><form:checkbox path="cardiovascular" /></td></tr> 
				<tr><td><form:errors path="cardiovascular" /></td></tr>
				
				<tr><th>
					<form:label for="dbt" path="dbt" cssErrorClass="error">
						<fmt:message key="pediatrician.form.dbt" />
					</form:label></th>
					<td><form:checkbox path="dbt" /></td></tr> 
				<tr><td><form:errors path="dbt" /></td></tr>
				
				<tr><th>
					<form:label for="hta" path="hta" cssErrorClass="error">
						<fmt:message key="pediatrician.form.hta" />
					</form:label></th>
					<td><form:checkbox path="hta" /></td></tr>
				<tr><td><form:errors path="hta" /></td></tr>
				
				<tr><th>
					<form:label for="asthma" path="asthma" cssErrorClass="error">
						<fmt:message key="pediatrician.form.asthma" />
					</form:label></th>
					<td><form:checkbox path="asthma" /></td></tr> 
				<tr><td><form:errors path="asthma" /></td></tr>
				
				<tr><th>
					<form:label for="mentalDisorder" path="mentalDisorder" cssErrorClass="error">
						<fmt:message key="pediatrician.form.mentalDisorder" />
					</form:label></th>
					<td><form:checkbox path="mentalDisorder" /> </td></tr>
				<tr><td><form:errors path="mentalDisorder" /></td></tr>
				
				<tr><th>
					<form:label for="familyAllergies" path="familyAllergies" cssErrorClass="error">
						<fmt:message key="pediatrician.form.familyAllergies" />
					</form:label></th>
					<td><form:checkbox path="familyAllergies" /></td></tr> 
				<tr><td><form:errors path="familyAllergies" /></td></tr>
				
				<tr><th>
					<form:label for="addictions" path="addictions" cssErrorClass="error">
						<fmt:message key="pediatrician.form.addictions" />
					</form:label></th>
					<td><form:checkbox path="addictions" /></td></tr> 
				<tr><td><form:errors path="addictions" /></td></tr>
				
				<tr><th>
					<form:label for="familyInfectiousDiseases" path="familyInfectiousDiseases" cssErrorClass="error">
						<fmt:message key="pediatrician.form.familyInfectiousDiseases" />
					</form:label></th>
					<td><form:checkbox path="familyInfectiousDiseases" /></td></tr> 
				<tr><td><form:errors path="familyInfectiousDiseases" /></td></tr>
							
				<tr><th>
					<form:label for="familyHemorrhagic" path="familyHemorrhagic" cssErrorClass="error">
						<fmt:message key="pediatrician.form.familyHemorrhagic" />
					</form:label></th>
					<td><form:checkbox path="familyHemorrhagic" /></td></tr> 
				<tr><td><form:errors path="familyHemorrhagic" /></td></tr>
				
				<tr><th colspan="2">
					<form:label for="otherFamilyDiaseases" path="otherFamilyDiaseases" cssErrorClass="error">
						<fmt:message key="pediatrician.form.otherFamilyDiaseases" />
					</form:label></th></tr>
				<tr><td colspan="2">
					<form:textarea path="otherFamilyDiaseases" /><form:errors path="otherFamilyDiaseases" />
				</td></tr>	
			</table>
		</div>
		
		<!-- MATURATION AND DEVELOPMENT -->
		<div id="tab-mat" class="pediatricianform">
			<table>
				<tr><th colspan="2">
					<form:label for="maturationAndDevelopment" path="maturationAndDevelopment" cssErrorClass="error">
						<fmt:message key="pediatrician.form.maturationAndDevelopment" />
					</form:label></th></tr>
				<tr><td colspan="2"><form:textarea path="maturationAndDevelopment" /></td></tr>
				<tr><td><form:errors path="maturationAndDevelopment" /></td></tr>	
			</table>
		</div>
		
		<!-- PHYSICAL EXAM -->
		<div id="tab-phy" class="pediatricianform">
			<table>
				
				<c:choose>
				<c:when test="${nurseForm != null}">
				
				<tr><th colspan="2">
						<fmt:message key="socialworker.form.fatherAge" />
				</th></tr>
				<tr><td colspan="2">

				<fmt:formatNumber maxFractionDigits="2"><c:out value="${pediatricianForm.patient.age}"></c:out></fmt:formatNumber>
				</td></tr>
				
				<tr><th colspan="2">
						<fmt:message key="nurse.form.weight" />
				</th></tr>
				<tr><td colspan="2">

				<fmt:formatNumber maxFractionDigits="2"><c:out value="${nurseForm.weight}"></c:out></fmt:formatNumber>
				</td></tr>
				
				<tr><th colspan="2">
						<fmt:message key="nurse.form.size" />
				</th></tr>
				<tr><td colspan="2">

				<fmt:formatNumber maxFractionDigits="2"><c:out value="${nurseForm.size}"></c:out></fmt:formatNumber>
				</td></tr>

				<tr><th colspan="2">
						<fmt:message key="nurse.form.headCircumference" />
				</th></tr>
				<tr><td colspan="2">

				<fmt:formatNumber maxFractionDigits="2"><c:out value="${nurseForm.headCircumference}"></c:out></fmt:formatNumber>
				</td></tr>		
				
				
				<tr><th colspan="2">
						<fmt:message key="pediatrician.form.PPE" />
				</th></tr>
				<tr><td colspan="2">

				<fmt:formatNumber maxFractionDigits="2"><c:out value="${nurseForm.weight/nurseForm.size/nurseForm.size*10000}"></c:out></fmt:formatNumber>
				</td></tr>
				
				<tr><th colspan="2">
						<fmt:message key="pediatrician.form.PTE" />
				</th></tr>
				<tr><td colspan="2">
			
				
				<fmt:formatNumber maxFractionDigits="2"><c:out value="${nurseForm.weight/nurseForm.size/nurseForm.size*10000}"></c:out></fmt:formatNumber>
				
				</td></tr>
				
				<tr><th colspan="2">
						<fmt:message key="pediatrician.form.PIMC" />
				</th></tr>
				<tr><td colspan="2">
						
				<fmt:formatNumber maxFractionDigits="2"><c:out value="${nurseForm.weight/nurseForm.size/nurseForm.size*10000}"></c:out></fmt:formatNumber>
				</td></tr>
				
				</c:when>
				<c:otherwise>
				<tr><th colspan="2">
						<fmt:message key="pediatrician.form.missingNurseData" />
						<a href="${pageContext.request.contextPath}/patient/${pediatricianForm.patient.id}/nurse/new">
							<fmt:message key="pediatrician.form.missingNurseDataLink" />
						</a>
				</th></tr>
				<tr><td colspan="2">
				</c:otherwise>
				</c:choose>
				
				<tr><th colspan="2">
					<form:label for="symptoms" path="symptoms" cssErrorClass="error">
						<fmt:message key="pediatrician.form.symptoms" />
					</form:label></th></tr>
				<tr><td colspan="2"><form:textarea path="symptoms" /></td></tr>
				<tr><td><form:errors path="symptoms" /></td></tr>	
				
				<tr><th>
					<form:label for="pathologyFound" path="pathologyFound" cssErrorClass="error">
						<fmt:message key="pediatrician.form.pathologyFound" />
					</form:label></th></tr>
				<tr><td colspan="2"><form:textarea path="pathologyFound" /></td></tr>
				<tr><td><form:errors path="pathologyFound" /></td></tr>	
				
			</table>
		</div>
		
		<!-- LABORATORY -->
		<div id="tab-lab" class="pediatricianform">
			<table>
				<tr><th>
					<form:label for="hematrocito" path="hematrocito" cssErrorClass="error">
						<fmt:message key="pediatrician.form.hematocrito" />
					</form:label></th>
					<td><form:input path="hematrocito" /></td></tr>
					<tr><td><form:errors path="hematrocito" /></td></tr>
					
				<tr><th>
					<form:label for="globulosBlancos" path="globulosBlancos" cssErrorClass="error">
						<fmt:message key="pediatrician.form.globulosBlancos" />
					</form:label></th>
					<td><form:input path="globulosBlancos" /></td></tr>
					<tr><td><form:errors path="globulosBlancos" /></td></tr>
					
				<tr><th>
					<form:label for="leucocitaria" path="leucocitaria" cssErrorClass="error">
						<fmt:message key="pediatrician.form.leucocitaria" />
					</form:label></th>
					<td><form:input path="leucocitaria" /></td></tr>
					<tr><td><form:errors path="leucocitaria" /></td></tr>
					
				<tr><th>
					<form:label for="plaquetas" path="plaquetas" cssErrorClass="error">
						<fmt:message key="pediatrician.form.plaquetas" />
					</form:label></th>
					<td><form:input path="plaquetas" /></td></tr>
					<tr><td><form:errors path="plaquetas" /></td></tr>
					
				<tr><th>
					<form:label for="eritrosedimentacion" path="eritrosedimentacion" cssErrorClass="error">
						<fmt:message key="pediatrician.form.eritrosedimentacion" />
					</form:label></th>
					<td><form:input path="eritrosedimentacion" /></td></tr>
					<tr><td><form:errors path="eritrosedimentacion" /></td></tr>
					
				<tr><th>
					<form:label for="tiempoSangria" path="tiempoSangria" cssErrorClass="error">
						<fmt:message key="pediatrician.form.sangria" />
					</form:label></th>
					<td><form:input path="tiempoSangria" /></td></tr>
					<tr><td><form:errors path="tiempoSangria" /></td></tr>
					
					
				<tr><th colspan="2">
					<form:label for="suero" path="suero" cssErrorClass="error">
						<fmt:message key="pediatrician.form.suero" />
					</form:label></th></tr>
					
					<c:forEach var="suero" items="${suero}">
						<c:if test="${count%2 == 0 }"><tr></c:if>
							<td><form:radiobutton path="suero" value="${suero}" /> ${suero.description}</td>
						<c:if test="${count%2 == 1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%2 == 1 }"></tr></c:if>
					<tr><td><form:errors path="suero" /></td></tr>
					
				<tr><th>
					<form:label for="glucosa" path="glucosa" cssErrorClass="error">
						<fmt:message key="pediatrician.form.glucosa" />
					</form:label></th>
					<td><form:input path="glucosa" /></td></tr>
					<tr><td><form:errors path="glucosa" /></td></tr>
					
				<tr><th>
					<form:label for="urea" path="urea" cssErrorClass="error">
						<fmt:message key="pediatrician.form.urea" />
					</form:label></th>
					<td><form:input path="urea" /></td></tr>
					<tr><td><form:errors path="urea" /></td></tr>
					
				<tr><th>
					<form:label for="got" path="got" cssErrorClass="error">
						<fmt:message key="pediatrician.form.got" />
					</form:label></th>
					<td><form:input path="got" /></td></tr>
					<tr><td><form:errors path="got" /></td></tr>
					
				<tr><th>
					<form:label for="gpt" path="gpt" cssErrorClass="error">
						<fmt:message key="pediatrician.form.gpt" />
					</form:label></th>
					<td><form:input path="gpt" /></td></tr>
					<tr><td><form:errors path="gpt" /></td></tr>
					
				<tr><th>
					<form:label for="colesterol" path="colesterol" cssErrorClass="error">
						<fmt:message key="pediatrician.form.colesterol" />
					</form:label></th>
					<td><form:input path="colesterol" /></td></tr>
					<tr><td><form:errors path="colesterol" /></td></tr>
					
										
				<tr><th>
					<form:label for="proteinas" path="proteinas" cssErrorClass="error">
						<fmt:message key="pediatrician.form.proteinas" />
					</form:label></th>
					<td><form:input path="proteinas" /></td></tr>
					<tr><td><form:errors path="proteinas" /></td></tr>
					
				<tr><th>
					<form:label for="albumina" path="albumina" cssErrorClass="error">
						<fmt:message key="pediatrician.form.albumina" />
					</form:label></th>
					<td><form:input path="albumina" /></td></tr>
					<tr><td><form:errors path="albumina" /></td></tr>
					
				<tr><th>
					<form:label for="orina" path="orina" cssErrorClass="error">
						<fmt:message key="pediatrician.form.orina" />
					</form:label></th>
					<td><form:input path="orina" /></td></tr>
					<tr><td><form:errors path="orina" /></td></tr>
					
										
				<tr><th>
					<form:label for="anticuerpos" path="anticuerpos" cssErrorClass="error">
						<fmt:message key="pediatrician.form.anticuerpos" />
					</form:label></th>
					<td><form:input path="anticuerpos" /></td></tr>
					<tr><td><form:errors path="anticuerpos" /></td></tr>
					
				<tr><th>
					<form:label for="reumatoideo" path="reumatoideo" cssErrorClass="error">
						<fmt:message key="pediatrician.form.reumatoideo" />
					</form:label></th>
					<td><form:input path="reumatoideo" /></td></tr>
					<tr><td><form:errors path="reumatoideo" /></td></tr>
					
				<tr><th colspan="2">
					<form:label for="testEmbarazo" path="testEmbarazo" cssErrorClass="error">
						<fmt:message key="pediatrician.form.embarazo" />
					</form:label></th></tr>
					
					<c:forEach var="testEmbarazo" items="${testEmbarazo}">
						<c:if test="${count%2 == 0 }"><tr></c:if>
							<td><form:radiobutton path="testEmbarazo" value="${testEmbarazo}" /> ${testEmbarazo.description}</td>
						<c:if test="${count%2 == 1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%2 == 1 }"></tr></c:if>
					<tr><td><form:errors path="testEmbarazo" /></td></tr>
					
				<tr><th colspan="2">
					<form:label for="streptococos" path="streptococos" cssErrorClass="error">
						<fmt:message key="pediatrician.form.streptococos" />
					</form:label></th></tr>
					
					<c:forEach var="streptococos" items="${streptococos}">
						<c:if test="${count%2 == 0 }"><tr></c:if>
							<td><form:radiobutton path="streptococos" value="${streptococos}" /> ${streptococos.description}</td>
						<c:if test="${count%2 == 1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%2 == 1 }"></tr></c:if>
					<tr><td><form:errors path="streptococos" /></td></tr>
					
				<tr><th>
					<form:label for="vdrl" path="vdrl" cssErrorClass="error">
						<fmt:message key="pediatrician.form.vdrl" />
					</form:label></th>
					<td><form:input path="vdrl" /></td></tr>
					<tr><td><form:errors path="vdrl" /></td></tr>
										
				<tr><th>
					<form:label for="parasitologico" path="parasitologico" cssErrorClass="error">
						<fmt:message key="pediatrician.form.parasitologico" />
					</form:label></th>
					<td><form:input path="parasitologico" /></td></tr>
					<tr><td><form:errors path="parasitologico" /></td></tr>

				<tr><th colspan="2">
					<form:label for="testGraham" path="testGraham" cssErrorClass="error">
						<fmt:message key="pediatrician.form.graham" />
					</form:label></th></tr>
					
					<c:forEach var="testGraham" items="${testGraham}">
						<c:if test="${count%2 == 0 }"><tr></c:if>
							<td><form:radiobutton path="testGraham" value="${testGraham}" /> ${testGraham.description}</td>
						<c:if test="${count%2 == 1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%2 == 1 }"></tr></c:if>
					<tr><td><form:errors path="testGraham" /></td></tr>
					
				<tr><th colspan="2">
					<form:label for="chagas" path="chagas" cssErrorClass="error">
						<fmt:message key="pediatrician.form.chagas" />
					</form:label></th></tr>
					
					<c:forEach var="chagas" items="${chagas}">
						<c:if test="${count%2 == 0 }"><tr></c:if>
							<td><form:radiobutton path="chagas" value="${chagas}" /> ${chagas.description}</td>
						<c:if test="${count%2 == 1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%2 == 1 }"></tr></c:if>
					<tr><td><form:errors path="chagas" /></td></tr>
			</table>
		</div>
		
		<!-- RADIOLOGY -->
		<div id="tab-rad" class="pediatricianform">
			<table>
				<tr><th colspan="2">
					<form:label for="chest" path="chest" cssErrorClass="error">
						<fmt:message key="pediatrician.form.chest" />
					</form:label></th></tr>
				<tr><td colspan="2"><form:textarea path="chest" /></td></tr>
				<tr><td><form:errors path="chest" /></td></tr>
				
				<tr><th colspan="2">
					<form:label for="bones" path="bones" cssErrorClass="error">
						<fmt:message key="pediatrician.form.bones" />
					</form:label></th></tr>
				<tr><td colspan="2"><form:textarea path="bones" /></td></tr>
				<tr><td><form:errors path="bones" /></td></tr>
							
				<tr><th colspan="2">
					<form:label for="radiologyOther" path="radiologyOther" cssErrorClass="error">
						<fmt:message key="pediatrician.form.radiologyOther" />
					</form:label></th></tr>
				<tr><td colspan="2"><form:textarea path="radiologyOther" /></td></tr>
				<tr><td><form:errors path="radiologyOther" /></td></tr>
										
				<tr><th colspan="2">
					<form:label for="radiologyComments" path="radiologyComments" cssErrorClass="error">
						<fmt:message key="pediatrician.form.radiologyComments" />
					</form:label></th></tr>
				<tr><td colspan="2"><form:textarea path="radiologyComments" /></td></tr>
				<tr><td><form:errors path="radiologyComments" /></td></tr>
			</table>
		</div>
		
		<!-- DIAGNOSIS -->
		<div id="tab-dia" class="pediatricianform">
		</div>
		
		<!-- INTERNMENT -->
		<div id="tab-int" class="pediatricianform">
			<table>
				<tr><th>
					<form:label for="interconsultation" path="interconsultation" cssErrorClass="error">
						<fmt:message key="pediatrician.form.interconsultation" />
					</form:label></th>
					<td><form:checkbox path="interconsultation" /></td></tr> 
				<tr><td><form:errors path="interconsultation" /></td></tr>
				
				<tr><th>
					<form:label for="internment" path="internment" cssErrorClass="error">
						<fmt:message key="pediatrician.form.internment" />
					</form:label></th>
					<td><form:checkbox path="internment" /></td></tr> 
				<tr><td><form:errors path="internment" /></td></tr>
								
				<tr><th colspan="2">
					<form:label for="treatment" path="treatment" cssErrorClass="error">
						<fmt:message key="pediatrician.form.treatment" />
					</form:label></th></tr>
				<tr><td colspan="2">
					<form:textarea path="treatment" /></td></tr>
				<tr><td>
					<form:errors path="treatment" /></td></tr>
			</table>
		</div>
		<input type="submit" />
	</div>
</form:form>