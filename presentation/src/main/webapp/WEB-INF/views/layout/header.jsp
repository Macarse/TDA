<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="float:left">
<img src="${pageContext.request.contextPath}/themes/default/image/header.jpg" width="1000" />
</div>

<div style="text-align:right; font-size:25px; float: right">

<c:out value="${user.username}" />
<c:out value="${user.id}" />

	<c:url value="${pageContext.request.contextPath}/j_spring_security_logout" var="logoutUrl"/>
	<a href="${logoutUrl}">Log Out</a>
</div>