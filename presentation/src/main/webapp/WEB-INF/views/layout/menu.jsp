<div class="menu-item">
	<div id="accordion" class="ui-accordion ui-widget ui-helper-reset ui-accordion-icons">
	    <h3><a href="#">Items</a></h3>
	    <div><a href="${pageContext.request.contextPath}/item/">Listado</a></div>
	    <h3><a href="#">Usuarios</a></h3>
	    <div><a href="${pageContext.request.contextPath}/applicationUser/">Listado</a></div>
	    <h3><a href="#">Pacientes</a></h3>
	    <div><a href="${pageContext.request.contextPath}/patient/">Listado</a></div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#accordion").accordion();
});
</script>