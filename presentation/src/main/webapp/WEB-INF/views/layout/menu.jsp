<div class="menu-items-container">
	<div class="menu-item">
		<table cellpadding="0" cellspacing="0">
			<tr><td rowspan="2"><div class="menu-item-border menu-border-black" /></td>
				<td><div class="menu-item-title"><a href="${pageContext.request.contextPath}/item/">Items</a></div></td></tr>
			<tr><td><div class="menu-item-desc">Listado de Items</div></td></tr>
		</table>
	</div>
 	
	<div class="menu-item">
		<table cellpadding="0" cellspacing="0">
			<tr><td rowspan="2"><div class="menu-item-border menu-border-red" /></td>
				<td><div class="menu-item-title"><a href="${pageContext.request.contextPath}/applicationUser/">Usuarios</a></div></td></tr>
			<tr><td><div class="menu-item-desc">Listado de Usuarios</div></td></tr>
		</table>
	</div>
	
	<div class="menu-item">
		<table cellpadding="0" cellspacing="0">
			<tr><td rowspan="2"><div class="menu-item-border menu-border-blue" /></td>
				<td><div class="menu-item-title"><a href="${pageContext.request.contextPath}/patient/">Pacientes</a></div></td></tr>
			<tr><td><div class="menu-item-desc">Listado de Pacientes</div></td></tr>
		</table>
	</div>
	
	<div class="menu-item">
		<table cellpadding="0" cellspacing="0">
			<tr><td rowspan="2"><div class="menu-item-border menu-border-green" /></td>
				<td><div class="menu-item-title"><a href="#">Test</a></div></td></tr>
			<tr><td><div class="menu-item-desc">Listado de teststete</div></td></tr>
		</table>
	</div>
	
	<div class="menu-item">
		<table cellpadding="0" cellspacing="0">
			<tr><td rowspan="2"><div class="menu-item-border menu-border-yellow" /></td>
				<td><div class="menu-item-title"><a href="#">Test</a></div></td></tr>
			<tr><td><div class="menu-item-desc">Listado de teststete</div></td></tr>
		</table>
	</div>
	
	<div class="menu-item">
		<table cellpadding="0" cellspacing="0">
			<tr><td rowspan="2"><div class="menu-item-border menu-border-orange" /></td>
				<td><div class="menu-item-title"><a href="#">Test</a></div></td></tr>
			<tr><td><div class="menu-item-desc">Listado de teststete</div></td></tr>
		</table>
	</div>
</div>

<script type="text/javascript">
<!--
	$(document).ready(function(){
		$(".menu-item").mouseenter(function(){
			$(this).animate({
				left: "+=10px",
				"opacity": "1"}, "slow");
		});

		$(".menu-item").mouseout(function(){
			$(this).animate({"left": "0px"}, "slow");
		});
	});
//-->
</script>