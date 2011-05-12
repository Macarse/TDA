<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<p>Lugares a visitar</p>
 
<ul>
	<c:forEach var="place" items="${savedClass.places}">
		<li>${place.city}</li>
	</c:forEach>
</ul>