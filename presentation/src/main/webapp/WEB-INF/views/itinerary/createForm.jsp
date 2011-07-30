<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
function initialDatePickers(placePosition){
	$( ".datePicker" ).datepicker(
			{ dateFormat: 'dd/mm/yy',
			  monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
			  monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
			  dayNamesMin: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
			  changeYear: true,
			  changeMonth: true,
			  yearRange: 'c-100,c+00'
		    }
		);
	for (i=0; i <= placePosition; i++) {
		addPickerToNewContent(i);
	}
}

function addPickerToNewContent(position) {
    $( "#itinerary-place-date" + position).datepicker(
            { dateFormat: 'dd/mm/yy',
              monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
              monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
              dayNamesMin: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
              changeYear: true,
              changeMonth: true,
              yearRange: 'c-100,c+00'
            }
        );
}

function setupRemoveHandlers(placePosition) {
	for (i = 0; i <= placePosition; i++) {
		removeHandlerForNewContent(i);
	}
}

function removeHandlerForNewContent(position) {
	$("#itinerary-place-remove" + position).click(function() {
        $.get("<%=request.getContextPath()%>/itinerary/removePlace", { fieldId: position},
       	    function (data) {
        	   $("#itinerary-place-container" + position).hide(); 
        });
    });
}

$(document).ready(function() {
	var placePosition = ${placeSize - 1};
	$("#addPlaceButton").click(function() {
		placePosition++;
 
		$.get("<%=request.getContextPath()%>/itinerary/appendPlace", { fieldId: placePosition},
			function(data){
				$("#submitRow").before(data);
				addPickerToNewContent(placePosition);
				removeHandlerForNewContent(placePosition);

				var last = $("[id$='province']:last");
				last.focus();
		});
	});
	
	setupRemoveHandlers(placePosition);
	initialDatePickers(placePosition);

	$("#beginningDate").change(function(){
		var date = $(this).datepicker("getDate", '+1d');
		date.setDate(date.getDate() + 1)
		var endDate = $("#endDate");
		if(endDate.val() == "")
			endDate.datepicker("setDate", date);
	});
	
	if (placePosition < 0) {
		document.getElementById('addPlaceButton').click();
	}
});
</script>

<div class="itinerary-form">
<form:form action="${pageContext.request.contextPath }/itinerary/add" method="post" modelAttribute="itineraryForm">
	<fieldset>
	<legend>Itinerario</legend>
	<form:hidden path="id" />
	
	<div class="itinerary-journey">
		<fieldset>
			<legend>Datos Principales del Viaje</legend>
		<ol>
			<!-- FECHA DE INICIO -->
			<li>
				<form:label for="beginningDate" path="beginningDate" cssErrorClass="error">Fecha en que salimos</form:label>
				<form:input class="datePicker" path="beginningDate"/>
				<form:errors path="beginningDate" />
			</li>
		
			<!-- FECHA DE FIN -->
			<li>
				<form:label for="endDate" path="endDate" cssErrorClass="error">Fecha en que volvemos</form:label>
				<form:input class="datePicker" path="endDate"/>
				<form:errors path="endDate" />
			</li>	
			
			<!-- DESCRIPCION -->
			<li>
				<form:label for="description" path="description" cssErrorClass="error">Descripción</form:label>
				<form:input path="description"/>
				<form:errors path="description" />
			</li>
		</ol>
		</fieldset>
	</div>
	<br />
	
	<c:forEach var="i" begin="1" end="${placeSize }">
	
	<div id="itinerary-place-container${i-1 }" class="itinerary-place">
	<fieldset>
		<legend> Destino #<c:out value="${i}" /></legend>
	
		<ol>
		<li>
			<form:label for="places" path="places[${i - 1}].province" cssErrorClass="error">Provincia</form:label>
			<spring:bind path="places[${i - 1}].province">
				<form:select path="${status.expression}">
				    <form:options items="${provinces}" />
				</form:select>
	        <form:errors path="${status.expression }" />
			</spring:bind>
		</li>
		
		<li>
			<form:label for="places" path="places[${i - 1}].city" cssErrorClass="error">Ciudad</form:label>
			<spring:bind path="places[${i - 1}].city">
				<form:input path="${status.expression}" size="30"/>
			</spring:bind>
			<form:errors path="places[${i - 1}].city" />
		</li>
		
		<li>
			<form:label for="places" path="places[${i - 1}].neighbourhood" cssErrorClass="error">Localidad</form:label>
			<spring:bind path="places[${i - 1}].neighbourhood">
				<form:input path="${status.expression}" size="30"/>
			</spring:bind>
			<form:errors path="places[${i - 1}].neighbourhood" />
		</li>
		
		<li>
			<form:label for="places" path="places[${i - 1}].arrivalDate" cssErrorClass="error">Fecha de llegada</form:label>
			<spring:bind path="places[${i - 1}].arrivalDate">
				<form:input id="itinerary-place-date${i - 1 }" path="${status.expression}" size="30"/>
			</spring:bind>
			<form:errors path="places[${i - 1}].arrivalDate" />
		</li>

        <li>
            <input type="button" id="itinerary-place-remove${i - 1 }" value="Eliminar"  />
        </li>
		</ol>
	</fieldset>
	</div>
	</c:forEach>

	<div style="clear: both;">	
		<div id="submitRow" style="float: right;"><input type="submit" value="Guardar"/></div>
		<div style="float: left;"><input type="button" id="addPlaceButton" value="Agregar otro destino"  /></div>
	</div>
	
	
	</fieldset>
</form:form>
</div>
