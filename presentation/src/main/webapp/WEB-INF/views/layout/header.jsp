<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="float:left; background-image:url('${pageContext.request.contextPath}/themes/default/image/header.jpg'); width:1000px; height:94px">
	<div style="position: relative; font-size: 14px; top: 73px; left: 5px;">
		<a href="${pageContext.request.contextPath}">Principal</a> | <a href="${pageContext.request.contextPath}/applicationUser/"><span>Usuarios</span></a> | <a href="${pageContext.request.contextPath}/patient/"><span>Pacientes</span></a> | <a href="${pageContext.request.contextPath}/item/"><span>Inventario</span></a> | <a href="${pageContext.request.contextPath}/sync/main"><span>Sincronización</span></a> | <a href="${pageContext.request.contextPath}/report/list"><span>Reportes</span></a>
	</div>
</div>
<c:url value="/logout" var="logoutUrl"/>

<div class="fg-buttonset fg-buttonset-multi" style="position:absolute; top:1px; right:20%;">
	<a class="fg-button button-text ui-state-default ui-corner-left" href="#">Bienvenido <i><c:out value="${user.username}" /></i></a>
	<a class="fg-button button-text ui-state-default ui-corner-right" href="${logoutUrl}">salir</a>
</div>

<div style="margin: 10px 10px 10px 10px;" class="msg-info">
	<div class="ui-widget" style="float: left; font-size: 12px; width: 100%;">
		<div class="ui-state-highlight ui-corner-all" style="margin-top: 10px; padding: 0 .7em;"> 
			<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
			<strong>Itinerario:</strong>
			<c:choose>
				<c:when test="${empty currentItinerary.beginningDate }">
					No se ha definido el itinerario. Click <a href="${pageContext.request.contextPath }/itinerary/add">aquí</a> para hacerlo.
				</c:when>
				<c:otherwise>
					El próximo viaje es desde el <b><fmt:formatDate value="${currentItinerary.beginningDate }" pattern="dd/MM/yyyy"/></b>  hasta el <b><fmt:formatDate value="${currentItinerary.endDate}" pattern="dd/MM/yyyy"/></b>.
					 Haga click <a href="${pageContext.request.contextPath }/itinerary/edit/${currentItinerary.id}">aquí</a> para cambiarlo.
				</c:otherwise>
			</c:choose>
			</p>
		</div>
	</div>
</div>

<script language='javascript' type='text/javascript'> 
	$(document).ready(function(){
	    setTimeout("slide()",5000);
	});

	function slide(){
	  if($('.msg-info')){
	      $('.msg-info').slideUp('slow', function() {});
	  }
	}
 
</script>