<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	$(document).ready(function(){
		$("#form-tabs").tabs();
		$("#myform").change(function() {
			_isDirty = true;
		});

		//if( document.getElementById('distosicRadioButton').checked )
			//$('.distosicHidden').show();

		//setInterval(autoSubmitFormAjax,30*1000);

        if( ('<c:out value="${editable}"></c:out>') == 'false' || userRole != "pediatrician" )
        	$('input, select').attr('disabled', 'disabled');

        $( "#diagnosisId" )
        // don't navigate away from the field on tab when selecting an item
        .bind( "keydown", function( event ) {
            if ( event.keyCode === $.ui.keyCode.TAB &&
                    $( this ).data( "autocomplete" ).menu.active ) {
                event.preventDefault();
            }
        })
        
        $("#diagnosisId").autocomplete({
        	focus: function() {
            // prevent value inserted on focus
            return false;
        	},
            source: function( request, response ) {
	            request.term = extractLast( request.term );
	            $.get(contextPath + "/getDiagnosis?q="+request.term, function(data) {
	                var data = eval(data);
	                
	                var availableTags = $.map( data, function( item ) {
	                    return item.name;
	                        //label: item.name,
	                        //value: item.name
	                    });
	                
	                // delegate back to autocomplete, but extract the last term
	                response( availableTags );
    	 	});
        },
        cacheLength: 1,
        minLength: 2,
        multiple: true,
        select: function( event, ui ) {
        	var terms = split( this.value );
			// remove the current input
			terms.pop();
			// add the selected item
			terms.push( ui.item.value );
			// add placeholder to get the comma-and-space at the end
			terms.push( "" );
			this.value = terms.join( ", " );
			return false;
        },
        /*
        open: function() {
            $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
        },
        close: function() {
            $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
        }
        */
    	});
	});

	window.onbeforeunload = nextTabUnload;
	var _isDirty = false;

    function split( val ) {
        return val.split( /,\s*/ );
    }
    function extractLast( term ) {
        return split( term ).pop();
    }
	
</script>

<!-- aux variables -->
<c:set var="count" value="0" />
<c:set var="fieldsPerRow" value="3" />

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
	</c:if>	<fmt:message key="pediatrician.form.form" /> de '<i>${pediatricianForm.patient.firstName} ${pediatricianForm.patient.lastName}</i>'
</h2>
<c:if test="${editable != null && !editable}">
	<h3>Version Final - No editable - Fecha <fmt:formatDate value="${pediatricianForm.fillingDate}" pattern="dd/MM/yyyy"/></h3>
</c:if>

<script type="text/javascript">
	if( userRole != "pediatrician" )
		document.write("<h3>Este formulario no pertenece a su rol</h3>");
</script>

<form:form modelAttribute="pediatricianForm" method="post" id="myform">
	<div id="form-tabs" class="form-tabs">
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
			
				<tr>
					<td colspan="${fieldsPerRow}" class="doubleband">
					<div style="float:right;">
						<a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a>
					</div>
					</td>
				</tr>
			
				<tr><th colspan="${fieldsPerRow}">
					<form:label for="birthPlace" path="birthPlace" cssErrorClass="error">
						<fmt:message key="pediatrician.form.birthPlace" />
					</form:label></th></tr>
					
					<c:set var="count" value="0" />
					<c:forEach var="birthPlace" items="${birthPlace}">
						<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
							<td><form:radiobutton path="birthPlace" value="${birthPlace}" /> ${birthPlace.description}</td>
						<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
					<tr><td><form:errors path="birthPlace" /></td></tr>
				
					
				<tr><th colspan="${fieldsPerRow}">
					<form:label for="birthType" path="birthType" cssErrorClass="error">
						<fmt:message key="pediatrician.form.birthType" />
					</form:label></th></tr>

					<c:set var="count" value="0" />
					<c:forEach var="birthType" items="${birthType}">
						<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
							<td>
							<c:choose>
							<c:when test="${birthType.description == 'Distócico'}">
								<form:radiobutton id="distosicRadioButton" path="birthType" value="${birthType}"/> ${birthType.description}
							</c:when>
							<c:otherwise>
								<form:radiobutton path="birthType" value="${birthType}"/> ${birthType.description}
							</c:otherwise>
							</c:choose>
							</td>
						<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
					<tr><td><form:errors path="birthType" /></td></tr>
				
				<tr class="distosicHidden">
					<th colspan="${fieldsPerRow}">
					<form:label for="birthTypePresentation" path="birthTypePresentation" cssErrorClass="error">
						<fmt:message key="pediatrician.form.birthTypePresentation" />
					</form:label></th></tr>
					
					<c:set var="count" value="0" />
					<c:forEach var="birthTypePresentation" items="${birthTypePresentation}">
						<c:if test="${count%fieldsPerRow == 0 }"><tr class="distosicHidden"></c:if>
							<td><form:radiobutton path="birthTypePresentation" value="${birthTypePresentation}" /> ${birthTypePresentation.description}</td>
						<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
					<tr class="distosicHidden"><td><form:errors path="birthTypePresentation" /></td></tr>
					
				<tr class="distosicHidden"><th colspan="${fieldsPerRow}">
					<form:label for="birthTypeTermination" path="birthTypeTermination" cssErrorClass="error">
						<fmt:message key="pediatrician.form.birthTypeTermination" />
					</form:label>
					</th></tr>
					
					<c:set var="count" value="0" />
					<c:forEach var="birthTypeTermination" items="${birthTypeTermination}">
						<c:if test="${count%fieldsPerRow == 0 }"><tr class="distosicHidden"></c:if>
							<td><form:radiobutton path="birthTypeTermination" value="${birthTypeTermination}" /> ${birthTypeTermination.description}</td>
						<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
					<tr class="distosicHidden"><td><form:errors path="birthTypeTermination" /></td></tr>

				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="patologyDuringBirth1" path="patologyDuringBirth" cssErrorClass="error">
						<fmt:message key="pediatrician.form.patologyDuringBirth" />
					</form:label></div>
					<div class="form-input"><form:textarea path="patologyDuringBirth" /> <form:errors path="patologyDuringBirth" /></div>
					</td></tr>
					
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="gestationalAge" path="gestationalAge" cssErrorClass="error">
						<fmt:message key="pediatrician.form.gestationalAge" />
					</form:label></div>
					<div class="form-input"><form:input path="gestationalAge" /> <form:errors path="gestationalAge" /></div>
					</td></tr>
				
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="birthWeight" path="birthWeight" cssErrorClass="error">
						<fmt:message key="pediatrician.form.birthWeight" />
					</form:label></div>
					<div class="form-input"><form:input path="birthWeight" /> <form:errors path="birthWeight" /></div>
					</td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="size" path="size" cssErrorClass="error">
						<fmt:message key="pediatrician.form.size" />
					</form:label> </div>
					<div class="form-input"><form:input path="size" />
					<form:errors path="size" /></div></td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="headCircumference" path="headCircumference" cssErrorClass="error">
						<fmt:message key="pediatrician.form.headCircumference" />
					</form:label></div>
					<div class="form-input"><form:input path="headCircumference" />
					<form:errors path="headCircumference" /></div>
					</td></tr>
			
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="apgarDepressed1" path="apgarDepressed" cssErrorClass="error">
						<fmt:message key="pediatrician.form.apgarDepressed" />
					</form:label></div>
					<div class="form-input"><form:checkbox path="apgarDepressed" />
					<form:errors path="apgarDepressed" /></div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="apgarReanimated1" path="apgarReanimated" cssErrorClass="error">
						<fmt:message key="pediatrician.form.apgarReanimated" />
					</form:label></div>
					<div class="form-input"><form:checkbox path="apgarReanimated" />
					<form:errors path="apgarReanimated" /></div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="malformation1" path="malformation" cssErrorClass="error">
						<fmt:message key="pediatrician.form.malformation" />
					</form:label></div>
					<div class="form-input"><form:checkbox path="malformation" />
					<form:errors path="malformation" /></div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="jaundice1" path="jaundice" cssErrorClass="error">
						<fmt:message key="pediatrician.form.jaundice" /></form:label></div>
					<div class="form-input"><form:checkbox path="jaundice" /><form:errors path="jaundice" /></div></td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="respiratoryDisease1" path="respiratoryDisease" cssErrorClass="error">
						<fmt:message key="pediatrician.form.respiratoryDisease" />
					</form:label></div>
					<div class="form-input"><form:checkbox path="respiratoryDisease" />
					<form:errors path="respiratoryDisease" /></div></td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="congenitalInfection1" path="congenitalInfection" cssErrorClass="error">
						<fmt:message key="pediatrician.form.congenitalInfection" />
					</form:label></div>
					<div class="form-input"><form:checkbox path="congenitalInfection" /> 
					<form:errors path="congenitalInfection" /></div></td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="neurologicalProblems1" path="neurologicalProblems" cssErrorClass="error">
						<fmt:message key="pediatrician.form.neurologicalProblems" />
					</form:label></div>
					<div class="form-input"><form:checkbox path="neurologicalProblems" /> <form:errors path="neurologicalProblems" /></div></td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="aids1" path="aids" cssErrorClass="error">
						<fmt:message key="pediatrician.form.aids" />
					</form:label></div>
					<div class="form-input"><form:checkbox path="aids" /> <form:errors path="aids" /></div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow }">
					<div class="form-input"><form:label for="acquiredInfection1" path="acquiredInfection" cssErrorClass="error">
						<fmt:message key="pediatrician.form.acquiredInfection" />
					</form:label></div>
					<div class="form-input"><form:checkbox path="acquiredInfection" /> <form:errors path="acquiredInfection" /> </div> </td></tr>
				
				<tr><th colspan="${fieldsPerRow}">
					<form:label for="otherPerinatalDiseases" path="otherPerinatalDiseases" cssErrorClass="error">
						<fmt:message key="pediatrician.form.otherPerinatalDiseases" />
					</form:label></th></tr>
				<tr><td colspan="${fieldsPerRow}">
					<form:textarea path="otherPerinatalDiseases" /></td></tr>
				<tr><td>
					<form:errors path="otherPerinatalDiseases" /></td></tr>
				
				<tr><th colspan="${fieldsPerRow}">
					<form:label for="exitStatus" path="exitStatus" cssErrorClass="error">
						<fmt:message key="pediatrician.form.exitStatus" />
					</form:label></th></tr>
					
					<c:forEach var="exitStatus" items="${exitStatus}">
						<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
							<td><form:radiobutton path="exitStatus" value="${exitStatus}" /> ${exitStatus.description}</td>
						<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
					<tr><td><form:errors path="exitStatus" /></td></tr>
					
				<tr>
					<td colspan="${fieldsPerRow}" class="doubleband">
					<div style="float:right;">
						<a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a>
					</div>
					</td>
				</tr>
					
		</table>
		</div>
		
		<!-- PATIENT BACKGROUND -->
		<div id="tab-pab" class="pediatricianform">
		    <table width="100%">
		    
		    	<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
				</tr>                
                <tr><td colspan="${fieldsPerRow}">
                    <div class="form-input"><form:label for="takesMedicine1" path="takesMedicine" cssErrorClass="error">
                        <fmt:message key="pediatrician.form.takesMedicine" />
                    </form:label></div>
                    <div class="form-input"><form:input size="55" path="takesMedicine" /> <form:errors path="takesMedicine" /> </div> </td></tr>
                
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="consumedTobacco1" path="consumedTobacco" cssErrorClass="error">
						<fmt:message key="pediatrician.form.consumedTobacco" />
					</form:label></div>
					<div class="form-input"><form:checkbox path="consumedTobacco" /> <form:errors path="consumedTobacco" /></div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="consumedAlcohol1" path="consumedAlcohol" cssErrorClass="error">
						<fmt:message key="pediatrician.form.consumedAlcohol" />
					</form:label></div>
					<div class="form-input"><form:checkbox path="consumedAlcohol" /> <form:errors path="consumedAlcohol" /> </div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="tattooed1" path="tattooed" cssErrorClass="error">
						<fmt:message key="pediatrician.form.tattooed" />
					</form:label></div>
					<div class="form-input"><form:checkbox path="tattooed" /> <form:errors path="tattooed" /> </div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="infectiousDiseases1" path="infectiousDiseases" cssErrorClass="error">
						<fmt:message key="pediatrician.form.infectiousDiseases" />
					</form:label></div> 
					<div class="form-input"><form:input size="55" path="infectiousDiseases" /> <form:errors path="infectiousDiseases" /> </div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="hemorrhagic1" path="hemorrhagic" cssErrorClass="error">
						<fmt:message key="pediatrician.form.hemorrhagic" />
					</form:label></div>
					<div class="form-input"><form:input size="55" path="hemorrhagic" /> <form:errors path="hemorrhagic" /> </div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="trauma1" path="trauma" cssErrorClass="error">
						<fmt:message key="pediatrician.form.trauma" />
					</form:label></div>
					<div class="form-input"><form:input size="55" path="trauma" /> <form:errors path="trauma" /> </div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="allergies1" path="allergies" cssErrorClass="error">
						<fmt:message key="pediatrician.form.allergies" />
					</form:label></div>
					<div class="form-input"><form:input size="55" path="allergies" /> <form:errors path="allergies" /> </div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="previousAdmissions1" path="previousAdmissions" cssErrorClass="error">
						<fmt:message key="pediatrician.form.previousAdmissions" />
					</form:label></div>
					<div class="form-input"><form:input size="55" path="previousAdmissions" /> <form:errors path="previousAdmissions" /> </div> </td></tr>
				
				<tr><th colspan="${fieldsPerRow}">
					<form:label for="otherPatientDiseases1" path="otherPatientDiseases" cssErrorClass="error">
						<fmt:message key="pediatrician.form.otherPatientDiseases" />
					</form:label></th></tr>
				<tr><td colspan="${fieldsPerRow}">
					<form:textarea path="otherPatientDiseases" /></td></tr>
				<tr><td><form:errors path="otherPatientDiseases" /></td></tr>
				
			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
			</tr>
				
			</table>
		</div>
		
		<!-- FAMILY BACKGROUND -->
		<div id="tab-fab" class="pediatricianform">
			<table width="100%">
			
				<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
			</tr>
			
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="cardiovascular1" path="cardiovascular" cssErrorClass="error">
						<fmt:message key="pediatrician.form.cardiovascular" />
					</form:label></div>
					<div class="form-input"><form:checkbox path="cardiovascular" /> <form:errors path="cardiovascular" /> </div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="dbt1" path="dbt" cssErrorClass="error">
						<fmt:message key="pediatrician.form.dbt" />
					</form:label> </div> 
					<div class="form-input"><form:checkbox path="dbt" /> <form:errors path="dbt" /> </div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="hta1" path="hta" cssErrorClass="error">
						<fmt:message key="pediatrician.form.hta" />
					</form:label> </div> 
					<div class="form-input"><form:checkbox path="hta" /> <form:errors path="hta" /> </div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="asthma1" path="asthma" cssErrorClass="error">
						<fmt:message key="pediatrician.form.asthma" />
					</form:label> </div>
					<div class="form-input"> <form:checkbox path="asthma" /> <form:errors path="asthma" /> </div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"> <form:label for="mentalDisorder1" path="mentalDisorder" cssErrorClass="error">
						<fmt:message key="pediatrician.form.mentalDisorder" />
					</form:label> </div> 
					<div class="form-input"><form:checkbox path="mentalDisorder" /> <form:errors path="mentalDisorder" /> </div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="familyAllergies1" path="familyAllergies" cssErrorClass="error">
						<fmt:message key="pediatrician.form.familyAllergies" />
					</form:label> </div> 
					<div class="form-input"><form:checkbox path="familyAllergies" /> <form:errors path="familyAllergies" /> </div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="addictions1" path="addictions" cssErrorClass="error">
						<fmt:message key="pediatrician.form.addictions" />
					</form:label> </div> 
					<div class="form-input"><form:checkbox path="addictions" /> <form:errors path="addictions" /> </div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="familyInfectiousDiseases1" path="familyInfectiousDiseases" cssErrorClass="error">
						<fmt:message key="pediatrician.form.familyInfectiousDiseases" />
					</form:label> </div> 
					<div class="form-input"><form:checkbox path="familyInfectiousDiseases" /> <form:errors path="familyInfectiousDiseases" /> </div> </td></tr>
							
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="familyHemorrhagic1" path="familyHemorrhagic" cssErrorClass="error">
						<fmt:message key="pediatrician.form.familyHemorrhagic" />
					</form:label></div>
					<div class="form-input"><form:checkbox path="familyHemorrhagic" /> <form:errors path="familyHemorrhagic" /> </div> </td></tr>
				
				<tr><th colspan="${fieldsPerRow}">
					<form:label for="otherFamilyDiaseases" path="otherFamilyDiaseases" cssErrorClass="error">
						<fmt:message key="pediatrician.form.otherFamilyDiaseases" />
					</form:label></th></tr>
				<tr><td colspan="${fieldsPerRow}">
					<form:textarea path="otherFamilyDiaseases" /><form:errors path="otherFamilyDiaseases" />
				</td></tr>	
				
			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
			</tr>
				
			</table>
		</div>
		
		<!-- MATURATION AND DEVELOPMENT -->
		<div id="tab-mat" class="pediatricianform">
			<table width="100%">
			
			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
			</tr>
			
			<tr><th colspan="${fieldsPerRow}">
				<form:label for="maturationAndDevelopment" path="maturationAndDevelopment" cssErrorClass="error">
					<fmt:message key="pediatrician.form.maturationAndDevelopment" />
				</form:label></th></tr>
			<tr><td colspan="${fieldsPerRow}"><form:textarea path="maturationAndDevelopment" /></td></tr>
			<tr><td><form:errors path="maturationAndDevelopment" /></td></tr>
				
			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
			</tr>
					
			</table>
		</div>
		
		<!-- PHYSICAL EXAM -->
		<div id="tab-phy" class="pediatricianform">
			<table width="100%">
				
			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
			</tr>
				
				<c:choose>
				<c:when test="${nurseForm != null}">
				
				<tr><th colspan="${fieldsPerRow}">
						<fmt:message key="socialworker.form.fatherAge" />
				</th></tr>
				<tr><td colspan="${fieldsPerRow}">

				<fmt:formatNumber maxFractionDigits="2"><c:out value="${pediatricianForm.patient.age}"></c:out></fmt:formatNumber>
				</td></tr>
				
				<tr><th colspan="${fieldsPerRow}">
						<fmt:message key="nurse.form.weight" />
				</th></tr>
				<tr><td colspan="${fieldsPerRow}">

				<fmt:formatNumber maxFractionDigits="2"><c:out value="${nurseForm.weight}"></c:out></fmt:formatNumber>
				</td></tr>
				
				<tr><th colspan="${fieldsPerRow}">
						<fmt:message key="nurse.form.size" />
				</th></tr>
				<tr><td colspan="${fieldsPerRow}">

				<fmt:formatNumber maxFractionDigits="2"><c:out value="${nurseForm.size}"></c:out></fmt:formatNumber>
				</td></tr>

				<tr><th colspan="${fieldsPerRow}">
						<fmt:message key="nurse.form.headCircumference" />
				</th></tr>
				<tr><td colspan="${fieldsPerRow}">

				<fmt:formatNumber maxFractionDigits="2"><c:out value="${nurseForm.headCircumference}"></c:out></fmt:formatNumber>
				</td></tr>		
				
				
				<tr><th colspan="${fieldsPerRow}">
						<fmt:message key="pediatrician.form.PPE" />
				</th></tr>
				<tr><td colspan="${fieldsPerRow}">

				<fmt:formatNumber maxFractionDigits="2"><c:out value="${nurseForm.weight/nurseForm.size/nurseForm.size*10000}"></c:out></fmt:formatNumber>
				</td></tr>
				
				<tr><th colspan="${fieldsPerRow}">
						<fmt:message key="pediatrician.form.PTE" />
				</th></tr>
				<tr><td colspan="${fieldsPerRow}">
			
				
				<fmt:formatNumber maxFractionDigits="2"><c:out value="${nurseForm.weight/nurseForm.size/nurseForm.size*10000}"></c:out></fmt:formatNumber>
				
				</td></tr>
				
				<tr><th colspan="${fieldsPerRow}">
						<fmt:message key="pediatrician.form.PIMC" />
				</th></tr>
				<tr><td colspan="${fieldsPerRow}">
						
				<fmt:formatNumber maxFractionDigits="2"><c:out value="${nurseForm.weight/nurseForm.size/nurseForm.size*10000}"></c:out></fmt:formatNumber>
				</td></tr>
				
				</c:when>
				<c:otherwise>
				<tr><th colspan="${fieldsPerRow}">
						<fmt:message key="pediatrician.form.missingNurseData" />
						<a href="${pageContext.request.contextPath}/patient/${pediatricianForm.patient.id}/nurse/new">
							<fmt:message key="pediatrician.form.missingNurseDataLink" />
						</a>
				</th></tr>
				</c:otherwise>
				</c:choose>
				
				<tr><th colspan="${fieldsPerRow}">
					<form:label for="symptoms" path="symptoms" cssErrorClass="error">
						<fmt:message key="pediatrician.form.symptoms" />
					</form:label></th></tr>
				<tr><td colspan="${fieldsPerRow}"><form:textarea path="symptoms" /></td></tr>
				<tr><td><form:errors path="symptoms" /></td></tr>	
				
				<tr><th>
					<form:label for="pathologyFound" path="pathologyFound" cssErrorClass="error">
						<fmt:message key="pediatrician.form.pathologyFound" />
					</form:label></th></tr>
				<tr><td colspan="${fieldsPerRow}"><form:textarea path="pathologyFound" /></td></tr>
				<tr><td><form:errors path="pathologyFound" /></td></tr>	
				
			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
			</tr>
				
			</table>
		</div>
		
		<!-- LABORATORY -->
		<div id="tab-lab" class="pediatricianform">
			<table width="100%">
			
			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
			</tr>
			
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="hematrocito" path="hematrocito" cssErrorClass="error">
						<fmt:message key="pediatrician.form.hematocrito" />
					</form:label> </div> 
					<div class="form-input"><form:input path="hematrocito" /> <form:errors path="hematrocito" /> </div> </td></tr>
					
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="globulosBlancos" path="globulosBlancos" cssErrorClass="error">
						<fmt:message key="pediatrician.form.globulosBlancos" />
					</form:label> </div> 
					<div class="form-input"><form:input path="globulosBlancos" /> <form:errors path="globulosBlancos" /> </div> </td></tr>
					
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="leucocitaria" path="leucocitaria" cssErrorClass="error">
						<fmt:message key="pediatrician.form.leucocitaria" />
					</form:label> </div>
					<div class="form-input"><form:input path="leucocitaria" /> <form:errors path="leucocitaria" /> </div> </td></tr>
					
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="plaquetas" path="plaquetas" cssErrorClass="error">
						<fmt:message key="pediatrician.form.plaquetas" />
					</form:label> </div> 
					<div class="form-input"><form:input path="plaquetas" /> <form:errors path="plaquetas" /> </div> </td></tr>
					
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="eritrosedimentacion" path="eritrosedimentacion" cssErrorClass="error">
						<fmt:message key="pediatrician.form.eritrosedimentacion" />
					</form:label></div>
					<div class="form-input"><form:input path="eritrosedimentacion" /> <form:errors path="eritrosedimentacion" /> </div> </td></tr>
					
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="tiempoSangria" path="tiempoSangria" cssErrorClass="error">
						<fmt:message key="pediatrician.form.sangria" />
					</form:label></div>
					<div class="form-input"><form:input path="tiempoSangria" /> <form:errors path="tiempoSangria" /> </div> </td></tr>
					
					
				<tr><th colspan="${fieldsPerRow}">
					<form:label for="suero" path="suero" cssErrorClass="error">
						<fmt:message key="pediatrician.form.suero" />
					</form:label></th></tr>
					
					<c:forEach var="suero" items="${suero}">
						<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
							<td><form:radiobutton path="suero" value="${suero}" /> ${suero.description}</td>
					    <c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
						<c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
					<tr><td><form:errors path="suero" /></td></tr>
					
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="glucosa" path="glucosa" cssErrorClass="error">
						<fmt:message key="pediatrician.form.glucosa" />
					</form:label> </div> 
					<div class="form-input"><form:input path="glucosa" /> <form:errors path="glucosa" /> </div> </td></tr>
					
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="urea" path="urea" cssErrorClass="error">
						<fmt:message key="pediatrician.form.urea" />
					</form:label> </div> 
					<div class="form-input"><form:input path="urea" /> <form:errors path="urea" /> </div> </td></tr>
					
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="got" path="got" cssErrorClass="error">
						<fmt:message key="pediatrician.form.got" />
					</form:label> </div>
					<div class="form-input"><form:input path="got" /> <form:errors path="got" /> </div> </td></tr>
					
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="gpt" path="gpt" cssErrorClass="error">
						<fmt:message key="pediatrician.form.gpt" />
					</form:label> </div> 
					<div class="form-input"><form:input path="gpt" /> <form:errors path="gpt" /> </div> </td></tr>
					
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="colesterol" path="colesterol" cssErrorClass="error">
						<fmt:message key="pediatrician.form.colesterol" />
					</form:label> </div> 
					<div class="form-input"><form:input path="colesterol" /> <form:errors path="colesterol" /> </div> </td></tr>
					
										
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="proteinas" path="proteinas" cssErrorClass="error">
						<fmt:message key="pediatrician.form.proteinas" />
					</form:label> </div>
					<div class="form-input"><form:input path="proteinas" /> <form:errors path="proteinas" /> </div> </td></tr>
					
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="albumina" path="albumina" cssErrorClass="error">
						<fmt:message key="pediatrician.form.albumina" />
					</form:label> </div> 
					<div class="form-input"><form:input path="albumina" /> <form:errors path="albumina" /> </div> </td></tr>
					
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="orina" path="orina" cssErrorClass="error">
						<fmt:message key="pediatrician.form.orina" />
					</form:label> </div> 
					<div class="form-input"><form:input path="orina" /> <form:errors path="orina" /> </div> </td></tr>
										
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="anticuerpos" path="anticuerpos" cssErrorClass="error">
						<fmt:message key="pediatrician.form.anticuerpos" />
					</form:label> </div> 
					<div class="form-input"><form:input path="anticuerpos" /> <form:errors path="anticuerpos" /> </div> </td></tr>
					
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="reumatoideo" path="reumatoideo" cssErrorClass="error">
						<fmt:message key="pediatrician.form.reumatoideo" />
					</form:label> </div> 
					<div class="form-input"><form:input path="reumatoideo" /> <form:errors path="reumatoideo" /> </div> </td></tr>
					
				<tr><th colspan="${fieldsPerRow}">
					<form:label for="testEmbarazo" path="testEmbarazo" cssErrorClass="error">
						<fmt:message key="pediatrician.form.embarazo" />
					</form:label></th></tr>
					
					<c:set var="count" value="0" />
					<c:forEach var="testEmbarazo" items="${testEmbarazo}">
						<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
							<td><form:radiobutton path="testEmbarazo" value="${testEmbarazo}" /> ${testEmbarazo.description}</td>
						<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
					<tr><td><form:errors path="testEmbarazo" /></td></tr>
					
				<tr><th colspan="${fieldsPerRow}">
					<form:label for="streptococos" path="streptococos" cssErrorClass="error">
						<fmt:message key="pediatrician.form.streptococos" />
					</form:label></th></tr>
					
					<c:set var="count" value="0" />
					<c:forEach var="streptococos" items="${streptococos}">
						<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
							<td><form:radiobutton path="streptococos" value="${streptococos}" /> ${streptococos.description}</td>
						<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
					<tr><td><form:errors path="streptococos" /></td></tr>
					
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="vdrl" path="vdrl" cssErrorClass="error">
						<fmt:message key="pediatrician.form.vdrl" />
					</form:label></div>
					<div class="form-input"><form:input path="vdrl" /> <form:errors path="vdrl" /> </div> </td></tr>
										
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="parasitologico" path="parasitologico" cssErrorClass="error">
						<fmt:message key="pediatrician.form.parasitologico" />
					</form:label> </div> 
					<div class="form-input"><form:input path="parasitologico" /> <form:errors path="parasitologico" /> </div> </td></tr>

				<tr><th colspan="${fieldsPerRow}">
					<form:label for="testGraham" path="testGraham" cssErrorClass="error">
						<fmt:message key="pediatrician.form.graham" />
					</form:label></th></tr>
					
					<c:set var="count" value="0" />
					<c:forEach var="testGraham" items="${testGraham}">
						<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
							<td><form:radiobutton path="testGraham" value="${testGraham}" /> ${testGraham.description}</td>
						<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
					<tr><td><form:errors path="testGraham" /></td></tr>
					
				<tr><th colspan="${fieldsPerRow}">
					<form:label for="chagas" path="chagas" cssErrorClass="error">
						<fmt:message key="pediatrician.form.chagas" />
					</form:label></th></tr>
					
					<c:set var="count" value="0" />
					<c:forEach var="chagas" items="${chagas}">
						<c:if test="${count%fieldsPerRow == 0 }"><tr></c:if>
							<td><form:radiobutton path="chagas" value="${chagas}" /> ${chagas.description}</td>
						<c:if test="${count%fieldsPerRow == fieldsPerRow-1 }"></tr></c:if>
					    <c:set var="count" value="${count+1}" />
					</c:forEach>
					<c:if test="${count%fieldsPerRow != 0 }"></tr></c:if>
					<tr><td><form:errors path="chagas" /></td></tr>
					
			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
			</tr>
					
			</table>
		</div>
		
		<!-- RADIOLOGY -->
		<div id="tab-rad" class="pediatricianform">
			<table width="100%">
			
			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
			</tr>
			
				<tr><th colspan="${fieldsPerRow}">
					<form:label for="chest" path="chest" cssErrorClass="error">
						<fmt:message key="pediatrician.form.chest" />
					</form:label></th></tr>
				<tr><td colspan="${fieldsPerRow}"><form:textarea path="chest" /></td></tr>
				<tr><td><form:errors path="chest" /></td></tr>
				
				<tr><th colspan="${fieldsPerRow}">
					<form:label for="bones" path="bones" cssErrorClass="error">
						<fmt:message key="pediatrician.form.bones" />
					</form:label></th></tr>
				<tr><td colspan="${fieldsPerRow}"><form:textarea path="bones" /></td></tr>
				<tr><td><form:errors path="bones" /></td></tr>
							
				<tr><th colspan="${fieldsPerRow}">
					<form:label for="radiologyOther" path="radiologyOther" cssErrorClass="error">
						<fmt:message key="pediatrician.form.radiologyOther" />
					</form:label></th></tr>
				<tr><td colspan="${fieldsPerRow}"><form:textarea path="radiologyOther" /></td></tr>
				<tr><td><form:errors path="radiologyOther" /></td></tr>
										
				<tr><th colspan="${fieldsPerRow}">
					<form:label for="radiologyComments" path="radiologyComments" cssErrorClass="error">
						<fmt:message key="pediatrician.form.radiologyComments" />
					</form:label></th></tr>
				<tr><td colspan="${fieldsPerRow}"><form:textarea path="radiologyComments" /></td></tr>
				<tr><td><form:errors path="radiologyComments" /></td></tr>
				
			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
				</td>
			</tr>
				
			</table>
		</div>
		
		<!-- DIAGNOSIS -->
		<div id="tab-dia" class="pediatricianform">
		<table width="100%">
		
		<tr>
			<td colspan="${fieldsPerRow}" class="doubleband">
			<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
			<div style="float:right;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="nextTab('#form-tabs')">Siguiente</a></div>
			</td>
		</tr>
		
		<tr>
			<td colspan="${fieldsPerRow}">
				<div class="form-input">
					<form:label for="diagnosis" path="diagnosis" cssErrorClass="error">
						<fmt:message key="pediatrician.form.diagnosis" />
					</form:label>
				</div>
				<div class="form-input"><form:textarea path="diagnosis" id="diagnosisId"/>
					<form:errors path="diagnosis" />
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
		
		<!-- INTERNMENT -->
		<div id="tab-int" class="pediatricianform">
			<table width="100%">
			
			<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				</td>
			</tr>
			
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="interconsultation" path="interconsultation" cssErrorClass="error">
						<fmt:message key="pediatrician.form.interconsultation" />
					</form:label> </div>
					<div class="form-input"><form:checkbox path="interconsultation" /> <form:errors path="interconsultation" /> </div> </td></tr>
				
				<tr><td colspan="${fieldsPerRow}">
					<div class="form-input"><form:label for="internment" path="internment" cssErrorClass="error">
						<fmt:message key="pediatrician.form.internment" />
					</form:label></div>
					<div class="form-input"><form:checkbox path="internment" /> <form:errors path="internment" /> </div> </td></tr>
								
				<tr><th colspan="${fieldsPerRow}">
					<form:label for="treatment" path="treatment" cssErrorClass="error">
						<fmt:message key="pediatrician.form.treatment" />
					</form:label></th></tr>
				<tr><td colspan="${fieldsPerRow}">
					<form:textarea path="treatment" /></td></tr>
				<tr><td>
					<form:errors path="treatment" /></td></tr>
					
		<tr>
				<td colspan="${fieldsPerRow}" class="doubleband">
				<div style="float:left;"><a href="#" class="button-text button-search fg-button ui-state-default ui-corner-all" onClick="previousTab('#form-tabs')">Anterior</a></div>
				</td>
			</tr>
					
			</table>
		</div>
		<div class="form-save-button">
		<input id="formSubmitBtn" type="submit" value="Guardar Formulario" onClick="_isDirty = false;"/>
	</div>
	<div style="clear: both">
		<hr>
	</div>
	</div>
</form:form>