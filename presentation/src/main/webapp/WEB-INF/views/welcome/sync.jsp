<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/sync/do" var="syncdo" />

<div class="filter-container">
	<form:form modelAttribute="configSync" action="${syncdo}" method="get" onsubmit="$('#syncLoadImage').show();">
		<table>
			<tbody>
				<tr>
					<td><form:label for="ip" path="ip" cssErrorClass="error">
						<fmt:message key="sync.ip" /></form:label></td>
					<td><form:input path="ip" /> <form:errors path="ip" /></td></tr>
				<tr>
					<td><form:label for="port" path="port" cssErrorClass="error">
						<fmt:message key="sync.port" /></form:label></td>
					<td><form:input path="port" /> <form:errors path="port" /></td></tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
					<div class="filter-submit">	
						<button type="submit" class="button-text fg-button ui-state-default ui-corner-all"><span class="ui-icon button-icon"></span> <fmt:message key="sync.title" /></button><img id="syncLoadImage" src='${pageContext.request.contextPath}/themes/default/image/ajax-loader.gif' style="display:none;"/>
					</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</form:form>
	<c:if test="${!empty resultMessage}">
	<div style="height:30px; font-size:15px;">
		<c:out value="${resultMessage}"></c:out>
	</div>
</c:if>
</div>