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
	<c:when test="${dentistForm.new}"><c:set var="method" value="post"/></c:when>
	<c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

<h2><c:if test="${dentistForm.new}"><fmt:message key="dentist.form.new" /></c:if><fmt:message key="dentist.form.form" /></h2>

<b><fmt:message key="dentist.form.patient" /></b> ${dentistForm.patient.firstName} ${dentistForm.patient.lastName}
<br/>

<form:form modelAttribute="dentistForm" method="post">
	<div id="form-tabs">
		<ul>
			<li><a href="#tab-vitalChecks"><fmt:message key="dentist.form.vitalChecks" /></a></li>
		</ul>
		
	<div id="tab-vitalChecks" class="dentistform">
		<table>

			<tr>
				<th><form:label for="comment" path="comment" cssErrorClass="error"><fmt:message key="dentist.form.comment" /></form:label></th> 
				<td><form:input path="comment" /> <form:errors path="comment" /></td></tr>
		</table>
	</div>
	
	<input type="submit" />
  </div>
</form:form>
<hr>