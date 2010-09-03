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
				<form:input path="otherPerinatalDiseases" />
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
		
		<div id="tab-pab">
		</div>
		
		<div id="tab-fab">
		</div>
		
		<div id="tab-mat">
		</div>
		
		<div id="tab-phy">
		</div>
		
		<div id="tab-lab">
		</div>
		
		<div id="tab-rad">
		</div>
		
		<div id="tab-dia">
		</div>
		
		<div id="tab-int">
		</div>
		
		<p>
			<input type="submit" />
		</p>
	</div>
</form:form>