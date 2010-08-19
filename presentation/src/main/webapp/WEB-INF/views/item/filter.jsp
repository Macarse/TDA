<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="filter-container">
	<form:form modelAttribute="item"
		action="search" method="get">
		<legend><fmt:message key="filter.title" /></legend>
		
		<div class="filter-value">
			<form:label for="name" path="name" cssErrorClass="error">
			<fmt:message key="item.form.name" /></form:label>
			<form:input path="name" /> <form:errors path="name" />
		</div>
	
		<div class="filter-value">
			<form:label for="description" path="description" cssErrorClass="error">
			<fmt:message key="item.form.description" /></form:label>
			<form:input path="description" /> <form:errors path="description" />
		</div>
		
		<div class="filter-value">
			<form:label for="quantity" path="quantity" cssErrorClass="error">
			<fmt:message key="item.form.quantity" /></form:label>
			<form:input path="quantity" /> <form:errors path="quantity" />
		</div>
	
		<div class="filter-value">
			<form:label for="category" path="category" cssErrorClass="error">
			<fmt:message key="item.form.category" /></form:label>
			<form:select path="category">
				<form:option value=""></form:option>
				<c:forEach var="category" items="${categories}">
					<form:option value="${category}"> ${category.description} </form:option>
				</c:forEach>
			</form:select><form:errors path="category" />
		</div>
		
		<div class="filter-value">
			<form:label for="measureUnit" path="measureUnit"
				cssErrorClass="error"><fmt:message key="item.form.measureUnit" /></form:label>
			<form:select path="measureUnit">
				<form:option value=""></form:option>
				<c:forEach var="measureUnit" items="${measureUnits}">
					<form:option value="${measureUnit}"> ${measureUnit.description} </form:option>
				</c:forEach>
			</form:select><form:errors path="measureUnit" />
		</div>
		
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderAscending" value="${orderAscending}" />

		<div class="filter-submit">	
			<input type="submit" value="<fmt:message key="filter.submit" />" />
		</div>
		
	</form:form>
</div>