<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="paginator">
	<ul id="pagination-digg">
	<c:if test="${paginator.pageCount > 1}"> 
	
		<c:if test="${!paginator.firstPage}">
			<li class="previous"><a href="?page=<c:out value="${paginator.previousPage}&orderField=${orderField}&orderAscending=${orderAscending}&${params}"/>"> <B>&lt;&lt; Anterior</B></a></li>
		</c:if>
		<c:if test="${paginator.firstPage}">
			<li class="previous-off"><B>&lt;&lt; Anterior</B></li>
		</c:if>
		
		<c:forEach items="${paginator.pages}" var="page">
			<c:choose>
				<c:when test="${page == paginator.pageIndex}">
		  			<li class="active"><c:out value="${page}"/></li>
				</c:when>
				<c:otherwise>
	  				<li><a href="?page=<c:out value="${page}&orderField=${orderField}&orderAscending=${orderAscending}&${params}"/>"><c:out value="${page}"/></a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:if test="${!paginator.lastPage}">
			<li class="next"><a href="?page=<c:out value="${paginator.nextPage}&orderField=${orderField}&orderAscending=${orderAscending}&${params}"/>"> <B>Siguiente &gt;&gt;</B></a></li>
		</c:if>
		<c:if test="${paginator.lastPage}">
			<li class="next-off"><B>Siguiente &gt;&gt;</B></li>
		</c:if>
		
	</c:if>
	</ul>
</div>
<br/>