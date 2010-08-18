<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="filter-container">
	<form:form modelAttribute="applicationUser"
		action="" method="get">
		<legend><fmt:message key="filter.title" /></legend>
		
		<div class="filter-value">
			<form:label for="username" path="username" cssErrorClass="error">
			<fmt:message key="user.form.username" /></form:label>
			<form:input path="username" /> <form:errors path="username" />
		</div>
	
		<div class="filter-value">
			<form:label for="password" path="password" cssErrorClass="error">
			<fmt:message key="user.form.password" /></form:label>
			<form:input path="password" /> <form:errors path="password" />
		</div>
		
		<div class="filter-value">
			<form:label for="myAuthorities" path="myAuthorities" cssErrorClass="error">
			<fmt:message key="user.form.autorities" /></form:label>
			<form:checkboxes items="${allAuthorities}" path="myAuthorities" itemLabel="name"/><form:errors path="myAuthorities" />
		</div>
		
		<input type="hidden" name="orderField" value="${orderField}" />
		<input type="hidden" name="orderAscending" value="${orderAscending}" />

		<div class="filter-submit">	
			<input type="submit" value="<fmt:message key="filter.submit" />" />
		</div>
		
	</form:form>
</div>