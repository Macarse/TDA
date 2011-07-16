<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Edit/Delete buttons variables -->
<spring:url value="item" var="startUrl" />
<spring:url value="delete" var="deleteUrl" />
<spring:message text="Eliminar" var="deleteLabel" />
<spring:url value="edit" var="editUrl" />
<spring:url value="add" var="addUrl" />
<spring:url value="search" var="searchUrl" />
<spring:message text="Editar" var="editLabel" />

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
	<a href="#" style="font-size: 14px;"><span class="ui-icon ui-icon-search button-icon"> </span> Buscar Item</a>
</div>

<div class="filter-container" style="display:none;" id="filter-containter">
	<jsp:include page="/WEB-INF/views/item/filter.jsp" flush="true"/>
</div>

<table class="list-table">
	<thead>
		<tr>
			<th>
				<a href="?orderField=name&orderAscending=<c:out value="${!paginator.orderAscending}"/>&${params}">
					<fmt:message key="item.form.name" />
				</a>
				<c:if test="${paginator.orderField=='name'}">
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
				<a href="?orderField=description&orderAscending=<c:out value="${!paginator.orderAscending}"/>&${params}">
					<fmt:message key="item.form.description" />
				</a>
				<c:if test="${paginator.orderField=='description'}">
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
				<a href="?orderField=quantity&orderAscending=<c:out value="${!paginator.orderAscending}&${params}"/>">
					<fmt:message key="item.form.quantity" />
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
				<a href="?orderField=category&orderAscending=<c:out value="${!paginator.orderAscending}&${params}"/>">
					<fmt:message key="item.form.category" />
				</a>
				<c:if test="${paginator.orderField=='category'}">
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
				<a href="?orderField=measureUnit&orderAscending=<c:out value="${!paginator.orderAscending}&${params}"/>">
					<fmt:message key="item.form.measureUnit" />
				</a>
				<c:if test="${paginator.orderField=='measureUnit'}">
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
		<c:forEach items="${itemList}" var="item">
			<tr>
				<td>${item.name}</td>
				<td>${item.description}</td>
				<td><div class='editable' id='${item.id }'>${item.quantity}</div></td>
				<td>${item.category.description}</td>
				<td>${item.measureUnit.description}</td>
				<td>
				<c:if test="${user.admin}">
				<form:form method="GET"
					action="${editUrl}/${item.id}">
					<button type="submit" class="button-text button-edit fg-button ui-state-default ui-corner-all"><span class="ui-icon ui-icon-transferthick-e-w button-icon"></span>Editar</button>
				</form:form>
				</c:if>
				</td>
				<td align="center">
				<c:if test="${user.admin}">
					<form:form method="POST"
					action="${deleteUrl}/${item.id}">
						<button type="submit" class="button-text button-delete fg-button ui-state-default ui-corner-all confirmLink"><span class="ui-icon ui-icon-closethick button-icon"></span>Eliminar</button>
					</form:form>
				</c:if>
				</td>
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

<c:if test="${user.admin}">
	<div class="newBtn"><a class="button-text fg-button  button-add ui-state-default ui-corner-all" href="${addUrl}"><span class="ui-icon ui-icon-circle-plus button-icon"></span> Agregar</a></div>
</c:if>

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
		
		$('.editable').editable(contextPath + "/item/edit", {
			indicator: 'Guardando...',
			tooltip: 'Click para editar',
			type: 'updown'
		});
	});
</script>
