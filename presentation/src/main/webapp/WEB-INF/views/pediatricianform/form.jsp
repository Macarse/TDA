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
		<div id="tab-peb">
			<p>
				<form:label for="birthPlace" path="birthPlace" cssErrorClass="error">
					<fmt:message key="pediatrician.form.birthPlace" />
				</form:label>
				<br />
				<c:forEach var="birthPlace" items="${birthPlace}">
					<form:radiobutton path="birthPlace" value="${birthPlace}" /> ${birthPlace.description}
					<br />
				</c:forEach>
				<form:errors path="birthPlace" />
			</p>
			
			<p>
				<form:label for="patologyDuringBirth" path="patologyDuringBirth" cssErrorClass="error">
					<fmt:message key="pediatrician.form.patologyDuringBirth" />
				</form:label>
				<form:checkbox path="patologyDuringBirth" /> <form:errors path="patologyDuringBirth" />
			</p>
				
			<p>
				<form:label for="birthType" path="birthType" cssErrorClass="error">
					<fmt:message key="pediatrician.form.birthType" />
				</form:label>
				<br />
				<c:forEach var="birthType" items="${birthType}">
					<form:radiobutton path="birthType" value="${birthType}" /> ${birthType.description}
					<br />
				</c:forEach>
				<form:errors path="birthType" />
			</p>
			
			<p>
				<form:label for="birthTypePresentation" path="birthTypePresentation" cssErrorClass="error">
					<fmt:message key="pediatrician.form.birthTypePresentation" />
				</form:label>
				<br />
				<c:forEach var="birthTypePresentation" items="${birthTypePresentation}">
					<form:radiobutton path="birthTypePresentation" value="${birthTypePresentation}" /> ${birthTypePresentation.description}
					<br />
				</c:forEach>
				<form:errors path="birthTypePresentation" />
			</p>
			
			<p>
				<form:label for="birthTypeTermination" path="birthTypeTermination" cssErrorClass="error">
					<fmt:message key="pediatrician.form.birthTypeTermination" />
				</form:label>
				<br />
				<c:forEach var="birthTypeTermination" items="${birthTypeTermination}">
					<form:radiobutton path="birthTypeTermination" value="${birthTypeTermination}" /> ${birthTypeTermination.description}
					<br />
				</c:forEach>
				<form:errors path="birthTypeTermination" />
			</p>
			
			<p>
				<form:label for="gestationalAge" path="gestationalAge" cssErrorClass="error">
					<fmt:message key="pediatrician.form.gestationalAge" />
				</form:label>
				<br />
				<form:input path="gestationalAge" />
				<form:errors path="gestationalAge" />
			</p>
			
			<p>
				<form:label for="birthWeight" path="birthWeight" cssErrorClass="error">
					<fmt:message key="pediatrician.form.birthWeight" />
				</form:label>
				<br />
				<form:input path="birthWeight" />
				<form:errors path="birthWeight" />
			</p>
			
			<p>
				<form:label for="size" path="size" cssErrorClass="error">
					<fmt:message key="pediatrician.form.size" />
				</form:label>
				<br />
				<form:input path="size" />
				<form:errors path="size" />
			</p>
			
			<p>
				<form:label for="headCircumference" path="headCircumference" cssErrorClass="error">
					<fmt:message key="pediatrician.form.headCircumference" />
				</form:label>
				<br />
				<form:input path="headCircumference" />
				<form:errors path="headCircumference" />
			</p>
		
			<p>
				<form:label for="apgarDepressed" path="apgarDepressed" cssErrorClass="error">
					<fmt:message key="pediatrician.form.apgarDepressed" />
				</form:label>
				<form:checkbox path="apgarDepressed" /> <form:errors path="apgarDepressed" />
			</p>
			
			<p>
				<form:label for="apgarReanimated" path="apgarReanimated" cssErrorClass="error">
					<fmt:message key="pediatrician.form.apgarReanimated" />
				</form:label>
				<form:checkbox path="apgarReanimated" /> <form:errors path="apgarReanimated" />
			</p>
			
			<p>
				<form:label for="malformation" path="malformation" cssErrorClass="error">
					<fmt:message key="pediatrician.form.malformation" />
				</form:label>
				<form:checkbox path="malformation" /> <form:errors path="malformation" />
			</p>
			
			<p>
				<form:label for="jaundice" path="jaundice" cssErrorClass="error">
					<fmt:message key="pediatrician.form.jaundice" />
				</form:label>
				<form:checkbox path="jaundice" /> <form:errors path="jaundice" />
			</p>
			
			<p>
				<form:label for="respiratoryDisease" path="respiratoryDisease" cssErrorClass="error">
					<fmt:message key="pediatrician.form.respiratoryDisease" />
				</form:label>
				<form:checkbox path="respiratoryDisease" /> <form:errors path="respiratoryDisease" />
			</p>
			
			<p>
				<form:label for="congenitalInfection" path="congenitalInfection" cssErrorClass="error">
					<fmt:message key="pediatrician.form.congenitalInfection" />
				</form:label>
				<form:checkbox path="congenitalInfection" /> <form:errors path="congenitalInfection" />
			</p>
			
			<p>
				<form:label for="neurologicalProblems" path="neurologicalProblems" cssErrorClass="error">
					<fmt:message key="pediatrician.form.neurologicalProblems" />
				</form:label>
				<form:checkbox path="neurologicalProblems" /> <form:errors path="neurologicalProblems" />
			</p>
			
			<p>
				<form:label for="aids" path="aids" cssErrorClass="error">
					<fmt:message key="pediatrician.form.aids" />
				</form:label>
				<form:checkbox path="aids" /> <form:errors path="aids" />
			</p>
			
			<p>
				<form:label for="acquiredInfection" path="acquiredInfection" cssErrorClass="error">
					<fmt:message key="pediatrician.form.acquiredInfection" />
				</form:label>
				<form:checkbox path="acquiredInfection" /> <form:errors path="acquiredInfection" />
			</p>
			
			<p>
				<form:label for="otherPerinatalDiseases" path="otherPerinatalDiseases" cssErrorClass="error">
					<fmt:message key="pediatrician.form.otherPerinatalDiseases" />
				</form:label>
				<br />
				<form:textarea path="otherPerinatalDiseases" />
				<form:errors path="otherPerinatalDiseases" />
			</p>
			
			<p>
				<form:label for="exitStatus" path="exitStatus" cssErrorClass="error">
					<fmt:message key="pediatrician.form.exitStatus" />
				</form:label>
				<br />
				<c:forEach var="exitStatus" items="${exitStatus}">
					<form:radiobutton path="exitStatus" value="${exitStatus}" /> ${exitStatus.description}
					<br />
				</c:forEach>
				<form:errors path="exitStatus" />
			</p>

		</div>
		
		<!-- PATIENT BACKGROUND -->
		<div id="tab-pab">
			<p>
				<form:label for="consumedTobacco" path="consumedTobacco" cssErrorClass="error">
					<fmt:message key="pediatrician.form.consumedTobacco" />
				</form:label>
				<form:checkbox path="consumedTobacco" /> <form:errors path="consumedTobacco" />
			</p>
			
			<p>
				<form:label for="consumedAlcohol" path="consumedAlcohol" cssErrorClass="error">
					<fmt:message key="pediatrician.form.consumedAlcohol" />
				</form:label>
				<form:checkbox path="consumedAlcohol" /> <form:errors path="consumedAlcohol" />
			</p>
			
			<p>
				<form:label for="tattooed" path="tattooed" cssErrorClass="error">
					<fmt:message key="pediatrician.form.tattooed" />
				</form:label>
				<form:checkbox path="tattooed" /> <form:errors path="tattooed" />
			</p>
			
			<p>
				<form:label for="infectiousDiseases" path="infectiousDiseases" cssErrorClass="error">
					<fmt:message key="pediatrician.form.infectiousDiseases" />
				</form:label>
				<form:checkbox path="infectiousDiseases" /> <form:errors path="infectiousDiseases" />
			</p>
			
			<p>
				<form:label for="hemorrhagic" path="hemorrhagic" cssErrorClass="error">
					<fmt:message key="pediatrician.form.hemorrhagic" />
				</form:label>
				<form:checkbox path="hemorrhagic" /> <form:errors path="hemorrhagic" />
			</p>
			
			<p>
				<form:label for="trauma" path="trauma" cssErrorClass="error">
					<fmt:message key="pediatrician.form.trauma" />
				</form:label>
				<form:checkbox path="trauma" /> <form:errors path="trauma" />
			</p>
			
			<p>
				<form:label for="allergies" path="allergies" cssErrorClass="error">
					<fmt:message key="pediatrician.form.allergies" />
				</form:label>
				<form:checkbox path="allergies" /> <form:errors path="allergies" />
			</p>
			
			<p>
				<form:label for="previousAdmissions" path="previousAdmissions" cssErrorClass="error">
					<fmt:message key="pediatrician.form.previousAdmissions" />
				</form:label>
				<form:checkbox path="previousAdmissions" /> <form:errors path="previousAdmissions" />
			</p>
			
			<p>
				<form:label for="otherPatientDiseases" path="otherPatientDiseases" cssErrorClass="error">
					<fmt:message key="pediatrician.form.otherPatientDiseases" />
				</form:label>
				<br />
				<form:textarea path="otherPatientDiseases" /><form:errors path="otherPatientDiseases" />
			</p>	
		</div>
		
		<!-- FAMILY BACKGROUND -->
		<div id="tab-fab">
			<p>
				<form:label for="cardiovascular" path="cardiovascular" cssErrorClass="error">
					<fmt:message key="pediatrician.form.cardiovascular" />
				</form:label>
				<form:checkbox path="cardiovascular" /> 
				<form:errors path="cardiovascular" />
			</p>
			
			<p>
				<form:label for="dbt" path="dbt" cssErrorClass="error">
					<fmt:message key="pediatrician.form.dbt" />
				</form:label>
				<form:checkbox path="dbt" /> 
				<form:errors path="dbt" />
			</p>
			
			<p>
				<form:label for="hta" path="hta" cssErrorClass="error">
					<fmt:message key="pediatrician.form.hta" />
				</form:label>
				<form:checkbox path="hta" /> 
				<form:errors path="hta" />
			</p>
			
			<p>
				<form:label for="asthma" path="asthma" cssErrorClass="error">
					<fmt:message key="pediatrician.form.asthma" />
				</form:label>
				<form:checkbox path="asthma" /> 
				<form:errors path="asthma" />
			</p>
			
			<p>
				<form:label for="mentalDisorder" path="mentalDisorder" cssErrorClass="error">
					<fmt:message key="pediatrician.form.mentalDisorder" />
				</form:label>
				<form:checkbox path="mentalDisorder" /> 
				<form:errors path="mentalDisorder" />
			</p>
			
			<p>
				<form:label for="familyAllergies" path="familyAllergies" cssErrorClass="error">
					<fmt:message key="pediatrician.form.familyAllergies" />
				</form:label>
				<form:checkbox path="familyAllergies" /> 
				<form:errors path="familyAllergies" />
			</p>
			
			<p>
				<form:label for="addictions" path="addictions" cssErrorClass="error">
					<fmt:message key="pediatrician.form.addictions" />
				</form:label>
				<form:checkbox path="addictions" /> 
				<form:errors path="addictions" />
			</p>
			
			<p>
				<form:label for="familyInfectiousDiseases" path="familyInfectiousDiseases" cssErrorClass="error">
					<fmt:message key="pediatrician.form.familyInfectiousDiseases" />
				</form:label>
				<form:checkbox path="familyInfectiousDiseases" /> 
				<form:errors path="familyInfectiousDiseases" />
			</p>
						
			<p>
				<form:label for="familyHemorrhagic" path="familyHemorrhagic" cssErrorClass="error">
					<fmt:message key="pediatrician.form.familyHemorrhagic" />
				</form:label>
				<form:checkbox path="familyHemorrhagic" /> 
				<form:errors path="familyHemorrhagic" />
			</p>
			
			<p>
				<form:label for="otherFamilyDiaseases" path="otherFamilyDiaseases" cssErrorClass="error">
					<fmt:message key="pediatrician.form.otherFamilyDiaseases" />
				</form:label>
				<br />
				<form:textarea path="otherFamilyDiaseases" /><form:errors path="otherFamilyDiaseases" />
			</p>	
		</div>
		
		<!-- MATURATION AND DEVELOPMENT -->
		<div id="tab-mat">
			<p>
				<form:label for="maturationAndDevelopment" path="maturationAndDevelopment" cssErrorClass="error">
					<fmt:message key="pediatrician.form.maturationAndDevelopment" />
				</form:label>
				<br />
				<form:textarea path="maturationAndDevelopment" />
				<form:errors path="maturationAndDevelopment" />
			</p>	
		</div>
		
		<!-- PHYSICAL EXAM -->
		<div id="tab-phy">
			<p>
				<form:label for="symptoms" path="symptoms" cssErrorClass="error">
					<fmt:message key="pediatrician.form.symptoms" />
				</form:label>
				<br />
				<form:textarea path="symptoms" />
				<form:errors path="symptoms" />
			</p>	
			
			<p>
				<form:label for="pathologyFound" path="pathologyFound" cssErrorClass="error">
					<fmt:message key="pediatrician.form.pathologyFound" />
				</form:label>
				<br />
				<form:textarea path="pathologyFound" />
				<form:errors path="pathologyFound" />
			</p>	
		</div>
		
		<!-- LABORATORY -->
		<div id="tab-lab">
		</div>
		
		<!-- RADIOLOGY -->
		<div id="tab-rad">
			<p>
				<form:label for="chest" path="chest" cssErrorClass="error">
					<fmt:message key="pediatrician.form.chest" />
				</form:label>
				<br />
				<form:textarea path="chest" />
				<form:errors path="chest" />
			</p>
			
			<p>
				<form:label for="bones" path="bones" cssErrorClass="error">
					<fmt:message key="pediatrician.form.bones" />
				</form:label>
				<br />
				<form:textarea path="bones" />
				<form:errors path="bones" />
			</p>
						
			<p>
				<form:label for="radiologyOther" path="radiologyOther" cssErrorClass="error">
					<fmt:message key="pediatrician.form.radiologyOther" />
				</form:label>
				<br />
				<form:textarea path="radiologyOther" />
				<form:errors path="radiologyOther" />
			</p>
									
			<p>
				<form:label for="radiologyComments" path="radiologyComments" cssErrorClass="error">
					<fmt:message key="pediatrician.form.radiologyComments" />
				</form:label>
				<br />
				<form:textarea path="radiologyComments" />
				<form:errors path="radiologyComments" />
			</p>
		</div>
		
		<!-- DIAGNOSIS -->
		<div id="tab-dia">
		</div>
		
		<!-- INTERNMENT -->
		<div id="tab-int">
			<p>
				<form:label for="interconsultation" path="interconsultation" cssErrorClass="error">
					<fmt:message key="pediatrician.form.interconsultation" />
				</form:label>
				<form:checkbox path="interconsultation" /> 
				<form:errors path="interconsultation" />
			</p>
			
			<p>
				<form:label for="internment" path="internment" cssErrorClass="error">
					<fmt:message key="pediatrician.form.internment" />
				</form:label>
				<form:checkbox path="internment" /> 
				<form:errors path="internment" />
			</p>
							
			<p>
				<form:label for="treatment" path="treatment" cssErrorClass="error">
					<fmt:message key="pediatrician.form.treatment" />
				</form:label>
				<br />
				<form:textarea path="treatment" />
				<form:errors path="treatment" />
			</p>
		</div>
		
		<p>
			<input type="submit" />
		</p>
	</div>
</form:form>