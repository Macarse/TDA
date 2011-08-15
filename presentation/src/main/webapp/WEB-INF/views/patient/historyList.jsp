<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:if test="${!empty param.message}">
	<div class="message">
		<fmt:message key="${param.message }" />
	</div>
</c:if>

<h2>Historial de paciente</h2>


<jsp:include flush="true" page="../layout/patientheader.jsp">
 				<jsp:param name="paramNotUsed" value="" />
</jsp:include>

<c:choose>
	<c:when test="${paginator.totalResultsCount<=0}">
		<div style="font-size: 15px;">No se encontraron registros</div>
	</c:when>
<c:otherwise>

<table class="list-table">
	<thead>
		<tr>
			<th>
				<a href="?orderField=fillingDate&orderAscending=<c:out value="${!paginator.orderAscending}&${params}"/>">
					Fecha de llenado
				</a>
				<c:if test="${paginator.orderField=='fillingDate'}">
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
				<a href="?orderField=formType&orderAscending=<c:out value="${!paginator.orderAscending}&${params}"/>">
					Tipo de formulario
				</a>
				<c:if test="${paginator.orderField=='formType'}">
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
			</th>
			<th>
			</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${formlist}" var="oneForm" varStatus="indexStatus">
			<tr>
				<td align="center"><fmt:formatDate value="${oneForm.fillingDate}" pattern="dd/MM/yyyy"/></td>
				<td align="center">${oneForm.formType.description}</td>
				<td><form:form method="GET"
						action="${pageContext.request.contextPath}/patient/${patient.id}/${oneForm.formType}/${oneForm.id}/edit">
						<button type="submit" class="button-text button-edit fg-button ui-state-default ui-corner-all"><span class="ui-icon ui-icon-transferthick-e-w button-icon"></span> Ver</button>
					</form:form>
				</td>
				<td>
				<c:if test="${user.admin}">
					<form:form method="POST"
					action="${pageContext.request.contextPath}/patient/delete/${patient.id}/${oneForm.formType}/${oneForm.id}">
					<button type="submit" class="button-text button-delete fg-button ui-state-default ui-corner-all  confirmLink"><span class="ui-icon ui-icon-closethick button-icon"></span> Eliminar</button>
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