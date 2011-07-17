<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="historyDiv">
	<table>
		<tr>
			<td align="center" style="padding-right: 10px;"><img class="patientPicture" src='/presentation/patient/getPicture/${patient.id }' width="200px" height="150px" /></td>
			<td>
				<div class="patientName"><c:out value="${patient.firstName}"></c:out> <c:out value="${patient.lastName}"></c:out></div>
				<div> </div>
				<div>Sexo: <c:out value="${patient.sex.description}"></c:out></div>
				<div>Fecha de Nacimiento: <fmt:formatDate value="${patient.birthdate}" pattern="dd/MM/yyyy"/></div>
				<div>DNI: <c:out value="${patient.dni}"></c:out></div>
			</td>
		</tr>
	</table>
</div>