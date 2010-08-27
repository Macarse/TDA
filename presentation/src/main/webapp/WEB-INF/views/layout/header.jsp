<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="float:left">
<img src="${pageContext.request.contextPath}/themes/default/image/header.jpg" width="1000" />
</div>
<c:url value="${pageContext.request.contextPath}/j_spring_security_logout" var="logoutUrl"/>

<div class="fg-buttonset fg-buttonset-multi" style="position:absolute; top:1px; right:20%;">
	<a class="fg-button button-text ui-state-default ui-corner-left" href="#">Usuario: <i><c:out value="${user.username}" /></i></a>
	<a class="fg-button button-text ui-state-default" href="#">Menu 1</a>
	<a class="fg-button button-text ui-state-default" href="#">Menu 2</a>
	<a class="fg-button button-text ui-state-default ui-corner-right" href="${logoutUrl}">salir</a>
</div>