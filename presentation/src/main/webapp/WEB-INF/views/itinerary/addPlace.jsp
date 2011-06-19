<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="itinerary-place-container${placeNumber }" class="itinerary-place">
<fieldset>
	<legend>Destino #${placeNumber + 1 }</legend>
	
	<ol>
		<li>
			<form:label for="places" path="itineraryPlace.places[${placeNumber }].province" cssErrorClass="error">
			Provincia
			</form:label>
			<form:select path="itineraryPlace.places[${placeNumber }].province">
			     <c:forEach var="province" items="${provinces}">
                    <form:option value="${province}"> ${province} </form:option>
                 </c:forEach>
			</form:select>
			<form:errors path="itineraryPlace.places[${placeNumber }].province" />
		</li>
		
		<li>
			<form:label for="places" path="itineraryPlace.places[${placeNumber }].city" cssErrorClass="error">
				Ciudad
			</form:label>
			<form:input path="itineraryPlace.places[${placeNumber }].city" size="30" />
			<form:errors path="itineraryPlace.places[${placeNumber }].city" />
		</li>
		
		<li>
			<form:label for="places" path="itineraryPlace.places[${placeNumber }].neighbourhood" cssErrorClass="error">
				Localidad
			</form:label>
			<form:input path="itineraryPlace.places[${placeNumber }].neighbourhood" size="30" />
			<form:errors path="itineraryPlace.places[${placeNumber }].neighbourhood" />
		</li>
		
		<li>
			<form:label for="places" path="itineraryPlace.places[${placeNumber }].arrivalDate" cssErrorClass="error">
				Fecha de llegada
			</form:label>
			<form:input id="itinerary-place-date${placeNumber }" path="itineraryPlace.places[${placeNumber }].arrivalDate" size="30" />
			<form:errors path="itineraryPlace.places[${placeNumber }].arrivalDate" />
		</li>
		
        <li><input type="button" id="itinerary-place-remove${placeNumber }" value="Eliminar"  /></li>
	</ol>
</fieldset>
</div>