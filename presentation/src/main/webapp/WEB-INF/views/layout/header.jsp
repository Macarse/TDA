<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p>header</p>

<div style="text-align:right; font-size:25px;">
	<c:url value="/presentation/j_spring_security_logout" var="logoutUrl"/>
	<a href="${logoutUrl}">Log Out</a>
</div>