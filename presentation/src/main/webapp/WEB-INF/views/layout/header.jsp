<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="float:left; background-image:url('${pageContext.request.contextPath}/themes/default/image/header.jpg'); width:1000px; height:94px">
	<div style="position: relative; font-size: 14px; top: 73px; left: 5px;">
		<a href="${pageContext.request.contextPath}">Principal</a> | <a href="${pageContext.request.contextPath}/patient/"><span>Pacientes</span></a> | <a href="${pageContext.request.contextPath}/item/"><span>Inventario</span></a> 
		
		<c:if test="${user.admin}">
		| <a href="${pageContext.request.contextPath}/applicationUser/"><span>Usuarios</span></a> | <a href="${pageContext.request.contextPath}/sync/main"><span>Sincronización</span></a> | <a href="${pageContext.request.contextPath}/report/list"><span>Reportes</span></a> | <a href="${pageContext.request.contextPath}/auditLog/"><span>Logs</span></a> 
		</c:if>
	</div>
</div>
<c:url value="/logout" var="logoutUrl"/>

<div class="fg-buttonset fg-buttonset-multi" style="position:absolute; top:1px; right:20%;">
	<a class="fg-button button-text ui-state-default ui-corner-left" href="#">Bienvenido <i><c:out value="${user.username}" /></i></a>
	<a class="fg-button button-text ui-state-default ui-corner-right" href="${logoutUrl}">salir</a>
</div>

<script language='javascript' type='text/javascript'>
/*
	$(document).ready(function(){
	    setTimeout("slide()",5000);
	});

	function slide(){
	  if($('.msg-info')){
	      $('.msg-info').slideUp('slow', function() {});
	  }
	}
 */
</script>