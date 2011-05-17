<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="float:left; background-image:url('${pageContext.request.contextPath}/themes/default/image/header.jpg'); width:1000px; height:94px">
	<div style="position: relative; font-size: 14px; top: 73px; left: 5px;">
		<a href="${pageContext.request.contextPath}">Principal</a> | <a href="${pageContext.request.contextPath}/applicationUser/"><span>Usuarios</span></a> | <a href="${pageContext.request.contextPath}/patient/"><span>Pacientes</span></a> | <a href="${pageContext.request.contextPath}/item/"><span>Inventario</span></a> | <a href="${pageContext.request.contextPath}/sync/main"><span>Sincronizaci�n</span></a>
		
		<c:choose>
			<c:when test="${empty currentItinerary.beginningDate }">
				No se ha definido el itinerario. Click <a href="${pageContext.request.contextPath }/itinerary/add">aqu�</a> para hacerlo
			</c:when>
			<c:otherwise>
				El pr�ximo viaje es desde el <c:out value="${currentItinerary.beginningDate }" />  hasta el <c:out value="${currentItinerary.endDate}" />.
				 <a href="${pageContext.request.contextPath }/itinerary/edit/${currentItinerary.id}">Cambiarlo</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<c:url value="/logout" var="logoutUrl"/>

<div class="fg-buttonset fg-buttonset-multi" style="position:absolute; top:1px; right:20%;">
	<a class="fg-button button-text ui-state-default ui-corner-left" href="#">Bienvenido <i><c:out value="${user.username}" /></i></a>
	<a class="fg-button button-text ui-state-default ui-corner-right" href="${logoutUrl}">salir</a>
</div>