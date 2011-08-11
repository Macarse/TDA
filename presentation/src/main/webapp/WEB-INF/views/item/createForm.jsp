<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
$(document).ready(function(){
	$("#category-input").autocomplete({
		source: function(request, response) {
			$.get(contextPath + "/getCategory?q="+request.term, function(data) {
				var data = eval(data);
				
				var availableTags = $.map( data, function( item ) {
                    return item;
                        //label: item.name,
                        //value: item.name
                    });
				
                // delegate back to autocomplete, but extract the last term
                response( availableTags );
			})
		},
		minLength: 2
    });
});
</script>

<form:form modelAttribute="item"
	action="add" method="post">
	<fieldset>
	
	<form:hidden path="id" />
	
	<legend><fmt:message key="item.form.legend" /></legend>
	<p><form:label for="name" path="name" cssErrorClass="error"><fmt:message key="item.form.name" /></form:label><br />
	<form:input path="name" /> <form:errors path="name" /></p>

	<p><form:label for="description" path="description"
		cssErrorClass="error"><fmt:message key="item.form.description" /></form:label><br />
	<form:input path="description" /> <form:errors path="description" /></p>

	<p><form:label for="quantity" path="quantity" cssErrorClass="error"><fmt:message key="item.form.quantity" /></form:label><br />
	<form:input path="quantity" /> <form:errors path="quantity" /></p>

	<p><form:label for="category" path="category" cssErrorClass="error"><fmt:message key="item.form.category" /></form:label><br />
	<form:input id="category-input" path="category" /> <form:errors path="category" /></p>

	<p><form:label for="measureUnit" path="measureUnit"
		cssErrorClass="error"><fmt:message key="item.form.measureUnit" /></form:label><br />
	<form:select path="measureUnit">
		<c:forEach var="measureUnit" items="${measureUnits}">
			<form:option value="${measureUnit}"> ${measureUnit.description} </form:option>
		</c:forEach>
	</form:select><form:errors path="measureUnit" /></p>

	<p><input type="submit" value="Guardar"/></p>
	</fieldset>
</form:form>