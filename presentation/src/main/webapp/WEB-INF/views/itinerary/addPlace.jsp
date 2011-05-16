<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<tr>
	<td>Destino #${placeNumber + 1 }</td>
	<td>&nbsp;</td>
	<td>
		<form:label for="places" path="itineraryForm.places[${placeNumber }].province" cssErrorClass="error">
			Provincia
		</form:label>
	</td>
	<td>
		<form:input path="itineraryForm.places[${placeNumber }].province" size="30" />
	</td>
	<td>&nbsp;</td>
</tr>

<tr>
	<td>
		<form:label for="places" path="itineraryForm.places[${placeNumber }].city" cssErrorClass="error">
			Ciudad
		</form:label>
	</td>
	<td>
		<form:input path="itineraryForm.places[${placeNumber }].city" size="30" />
	</td>
	<td>&nbsp;</td>
</tr>

<tr>
	<td>
		<form:label for="places" path="itineraryForm.places[${placeNumber }].neighbourhood" cssErrorClass="error">
			Localidad
		</form:label>
	</td>
	<td>
		<form:input path="itineraryForm.places[${placeNumber }].neighbourhood" size="30" />
	</td>
	<td>&nbsp;</td>
</tr>

<tr>
	<td>
		<form:label for="places" path="itineraryForm.places[${placeNumber }].arrivalDate" cssErrorClass="error">
			Fecha de llegada
		</form:label>
	</td>
	<td>
		<form:input path="itineraryForm.places[${placeNumber }].arrivalDate" size="30" />
	</td>
	<td>&nbsp;</td>
</tr>