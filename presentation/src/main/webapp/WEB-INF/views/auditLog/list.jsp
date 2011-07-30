<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Edit/Delete buttons variables -->
<spring:url value="auditLog" var="startUrl" />
<spring:url value="search" var="searchUrl" />

<c:if test="${!empty param.message}">
	<div class="message">
		<fmt:message key="${param.message }" />
	</div>
</c:if>

<c:choose>
<c:when test="${paginator.totalResultsCount<=0}">
	<div style="font-size: 15px;">No se encontraron registros</div>
</c:when>
<c:otherwise>
<div id="search-button">
	<a href="#" style="font-size: 14px;"><span class="ui-icon ui-icon-search button-icon"> </span> Buscar Log</a>
</div>

<div class="filter-container" style="display:none;" id="filter-containter">
	<jsp:include page="/WEB-INF/views/auditLog/filter.jsp" flush="true"/>
</div>

<table class="list-table">
	<thead>
		<tr>
			<th>
				<a href="?orderField=formId&orderAscending=<c:out value="${!paginator.orderAscending}"/>&${params}">
					<fmt:message key="auditLog.form.formId" />
				</a>
				<c:if test="${paginator.orderField=='formId'}">
					<c:choose>
						<c:when test="${paginator.orderAscending}">
			  				<img src='${pageContext.request.contextPath}/<spring:theme code="uparrow.img"/>'>
						</c:when>
						<c:otherwise>
			  				<img src='${pageContext.request.contextPath}/<spring:theme code="downarrow.img"/>'>
						</c:otherwise>
					</c:choose>
				</c:if>
			</th>
			<th>
				<a href="?orderField=user&orderAscending=<c:out value="${!paginator.orderAscending}"/>&${params}">
					<fmt:message key="auditLog.form.user" />
				</a>
				<c:if test="${paginator.orderField=='user'}">
					<c:choose>
						<c:when test="${paginator.orderAscending}">
			  				<img src='${pageContext.request.contextPath}/<spring:theme code="uparrow.img"/>'>
						</c:when>
						<c:otherwise>
			  				<img src='${pageContext.request.contextPath}/<spring:theme code="downarrow.img"/>'>
						</c:otherwise>
					</c:choose>
				</c:if>
			</th>
			<th>
				<a href="?orderField=controllerUsed&orderAscending=<c:out value="${!paginator.orderAscending}&${params}"/>">
					<fmt:message key="auditLog.form.controllerUsed" />
				</a>
				<c:if test="${paginator.orderField=='quantity'}">
					<c:choose>
						<c:when test="${paginator.orderAscending}">
			  				<img src='${pageContext.request.contextPath}/<spring:theme code="uparrow.img"/>'>
						</c:when>
						<c:otherwise>
			  				<img src='${pageContext.request.contextPath}/<spring:theme code="downarrow.img"/>'>
						</c:otherwise>
					</c:choose>
				</c:if>
			</th>
			<th>
				<a href="?orderField=timestamp&orderAscending=<c:out value="${!paginator.orderAscending}&${params}"/>">
					<fmt:message key="auditLog.form.timestamp" />
				</a>
				<c:if test="${paginator.orderField=='timestamp'}">
					<c:choose>
						<c:when test="${paginator.orderAscending}">
			  				<img src='${pageContext.request.contextPath}/<spring:theme code="uparrow.img"/>'>
						</c:when>
						<c:otherwise>
			  				<img src='${pageContext.request.contextPath}/<spring:theme code="downarrow.img"/>'>
						</c:otherwise>
					</c:choose>
				</c:if>
			</th>
			<th colspan="2"></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${auditLogList}" var="auditLog">
			<tr>
				<td>${auditLog.formId}</td>
				<td>${auditLog.user}</td>
				<td>${auditLog.controllerUsed}</td>
				<td>${auditLog.timestamp}</td>
			</tr>
		</c:forEach>
	</tbody>

	<tfoot>
		<tr>
		  	<td colspan="2">
				<jsp:include page="/WEB-INF/views/paginator/paginator.jsp" flush="true"/>
		  	</td>
		</tr>
	</tfoot>
</table>
</c:otherwise>
</c:choose>


<script language='javascript' type='text/javascript'>
	$(document).ready(function(){
		$.editable.addInputType('updown',{
			element: function(settings, original){
				var input = $("<input id='inline-value' type='text' style='width: 30px;' />");
				var hidden = $("<input id='inline-hidden' type='hidden' />");
				var up = $("<a href='#'><img id='inline-up' src='" + contextPath + "/themes/default/image/arrow_order_up.gif' alt='sumar' class='inline-up' /></a>");
				var down = $("<a href='#'><img id='inline-down' src='" + contextPath + "/themes/default/image/arrow_order_down.gif' alt='restar' class='inline-down' /></a>");
				var submit = $("<input type='image' id='inline-submit' src='" + contextPath + "/themes/default/image/ok.png' alt='enviar' class='inline-submit' /></a>");

				$(this).append(hidden);
				$(this).append(input);
				$(this).append(up);
				$(this).append(down);
				$(this).append(submit);
				
				$("#inline-up", this).click(function(){
					var inp = $("#inline-value");
					inp.val(parseInt(inp.val()) + 1);
				});

				$("#inline-down", this).click(function(){
					var inp = $("#inline-value");
					inp.val(parseInt(inp.val()) - 1);
				});
				
				return hidden;
			},
			submit: function (settings, original){
				var value = $("#inline-value", this).val();
				$("#inline-hidden", this).val(value);	
			},
			content: function(string, settings, original){
				$("#inline-value", this).val(string);
			}
		});
	});
</script>
