<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
$(document).ready(function() {
	var placePosition = 0;
	$("#addPlaceButton").click(function() {
		placePosition++;
 
		$.get("<%=request.getContextPath()%>/itinerary/appendPlace", { fieldId: placePosition},
			function(data){
				$("#submitRow").before(data);
		});
	});
});
</script>

<form:form action="add" method="post" modelAttribute="itineraryForm">
	<fieldset>
	
	<legend>Crear itinerario</legend>
	
	<!-- FECHA DE INICIO -->
	<p>
		<form:label for="beginningDate" path="beginningDate" cssErrorClass="error">Fecha de salida</form:label>
		<form:input path="beginningDate"/>
		<form:errors path="beginningDate" />
	</p>

	<!-- FECHA DE FIN -->
	<p>
		<form:label for="endDate" path="endDate" cssErrorClass="error">Fecha de fin</form:label>
		<form:input path="endDate"/>
		<form:errors path="endDate" />
	</p>	
	
	<!-- DESCRIPCION -->
	<p>
		<form:label for="description" path="description" cssErrorClass="error">Descripción</form:label>
		<form:input path="description"/>
		<form:errors path="description" />
	</p>
	
	<!-- LUGARES -->
	<p>
		<form:label for="places" path="places[0].city" cssErrorClass="error">Ciudad</form:label>
		<spring:bind path="places[0].city">
			<form:input path="${status.expression}" size="30"/>
		</spring:bind>
		<input type="button" id="addPlaceButton" value="Agregar otro destino"  />
	</p>
	
	<table>
		<tr id="submitRow">
			<td>&nbsp;</td>
			<td>		
				<input type="submit" value="Crear itinerario"/>
			</td>
		</tr>
	</table>
	</fieldset>
</form:form>