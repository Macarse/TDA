<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="float:left; background-image:url('${pageContext.request.contextPath}/themes/default/image/header.jpg'); width:1000px; height:94px">
	<div style="position: relative; font-size: 14px; top: 73px; left: 5px;">
		<a href="${pageContext.request.contextPath}">Principal</a> | <a href="${pageContext.request.contextPath}/applicationUser/"><span>Usuarios</span></a> | <a href="${pageContext.request.contextPath}/patient/"><span>Pacientes</span></a> | <a href="${pageContext.request.contextPath}/item/"><span>Items</span></a>
	</div>
</div>
<c:url value="/logout" var="logoutUrl"/>
<c:url value="/sync/do" var="syncUrl"/>

<div class="fg-buttonset fg-buttonset-multi" style="position:absolute; top:1px; right:20%;">
	<a class="fg-button button-text ui-state-default ui-corner-left" href="#">Bienvenido <i><c:out value="${user.username}" /></i></a>
	<a class="fg-button button-text ui-state-default ui-corner-right" href="${logoutUrl}">salir</a>
	<a class="fg-button button-text ui-state-default ui-corner-right" href="${syncUrl}">SyncIt</a>
</div>