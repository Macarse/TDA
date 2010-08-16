<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:if test="${!paginator.firstPage}">
  <a href="?page=<c:out value="${paginator.previousPage}"/>"> <B>&lt;&lt; Prev</B></a>
</c:if>

<c:forEach items="${paginator.pages}" var="page">
	<c:choose>
		<c:when test="${page == paginator.pageIndex}">
  			<c:out value="${page}"/>
		</c:when>
		<c:otherwise>
  				<a href="?page=<c:out value="${page}&${paginator.params}"/>"><c:out value="${page}"/></a>
		</c:otherwise>
	</c:choose>
</c:forEach>

<c:if test="${!paginator.lastPage}">
	<a href="?page=<c:out value="${paginator.nextPage}"/>"> <B>Next &gt;&gt;</B></a>
</c:if>