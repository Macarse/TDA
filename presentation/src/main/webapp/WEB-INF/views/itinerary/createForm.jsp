<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
$(document).ready(function() {
	var placePosition = ${placeSize - 1};
	$("#addPlaceButton").click(function() {
		placePosition++;
 
		$.get("<%=request.getContextPath()%>/itinerary/appendPlace", { fieldId: placePosition},
			function(data){
				$("#submitRow").before(data);
		});
	});
});
</script>

<form:form action="${pageContext.request.contextPath }/itinerary/add" method="post" modelAttribute="itineraryForm">
	<fieldset>
	
	<legend>Crear itinerario</legend>
	
	<form:hidden path="id" />
	
	<!-- FECHA DE INICIO -->
	<p>
		<form:label for="beginningDate" path="beginningDate" cssErrorClass="error">Fecha en que salimos</form:label>
		<form:input path="beginningDate"/>
		<form:errors path="beginningDate" />
	</p>

	<!-- FECHA DE FIN -->
	<p>
		<form:label for="endDate" path="endDate" cssErrorClass="error">Fecha en que volvemos</form:label>
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


	<input type="button" id="addPlaceButton" value="Agregar otro destino"  />
	<br />
	
	<c:forEach var="i" begin="1" end="${placeSize }">
	
	Destino <c:out value="${i}"></c:out>

	<p>
		<form:label for="places" path="places[${i - 1}].province" cssErrorClass="error">Provincia</form:label>
		<spring:bind path="places[${i - 1}].province">
			<form:input path="${status.expression}" size="30"/>
        <form:errors path="${status.expression }" />
		</spring:bind>
	</p>
	
	<p>
		<form:label for="places" path="places[${i - 1}].city" cssErrorClass="error">Ciudad</form:label>
		<spring:bind path="places[${i - 1}].city">
			<form:input path="${status.expression}" size="30"/>
		</spring:bind>
		<form:errors path="places[${i - 1}].city" />
	</p>
	
	<p>
		<form:label for="places" path="places[${i - 1}].neighbourhood" cssErrorClass="error">Localidad</form:label>
		<spring:bind path="places[${i - 1}].neighbourhood">
			<form:input path="${status.expression}" size="30"/>
		</spring:bind>
		<form:errors path="places[${i - 1}].neighbourhood" />
	</p>
	
	<p>
		<form:label for="places" path="places[${i - 1}].arrivalDate" cssErrorClass="error">Fecha de llegada</form:label>
		<spring:bind path="places[${i - 1}].arrivalDate">
			<form:input id="itinerary-arrival-date" path="${status.expression}" size="30"/>
		</spring:bind>
		<form:errors path="places[${i - 1}].arrivalDate" />
	</p>
	
	</c:forEach>
	
	<table>
		<tr id="submitRow">
			<td>&nbsp;</td>
			<td>		
				<input type="submit" value="Guardar"/>
			</td>
		</tr>
	</table>
	</fieldset>
</form:form>

<script>
	$(function() {
		$( "#itinerary-arrival-date" ).datepicker(
			{ dateFormat: 'dd/mm/yy',
			  monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
			  monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
			  dayNamesMin: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
			  changeYear: true,
			  changeMonth: true,
			  yearRange: 'c-100,c+00'
		    }
		);
		$( "#beginningDate" ).datepicker(
				{ dateFormat: 'dd/mm/yy',
				  monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
				  monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
				  dayNamesMin: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
				  changeYear: true,
				  changeMonth: true,
				  yearRange: 'c-100,c+00'
			    }
			);
		$( "#endDate" ).datepicker(
				{ dateFormat: 'dd/mm/yy',
				  monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
				  monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
				  dayNamesMin: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
				  changeYear: true,
				  changeMonth: true,
				  yearRange: 'c-100,c+00'
			    }
			);
	});
</script>