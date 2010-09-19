<div class="menu-items-container">
	<div>
		<ul class="menu">
		  <li><a href="${pageContext.request.contextPath}/applicationUser/"><span>Usuarios</span></a></li>
		  <li><a href="${pageContext.request.contextPath}/patient/"><span>Pacientes</span></a></li>
		  <li><a href="${pageContext.request.contextPath}/item/"><span>Items</span></a></li>
		</ul>
	</div>
</div>

<div id="queue">
	<a href="#" onclick="loadQueue();">loadQueue</a>
</div>

<script language='javascript' type='text/javascript'> 

var refreshId = setInterval(function()
		{
			$.get("/presentation/getUserQueue", function(data){
		   		alert("Data Loaded: " + data);
		 	});
		}, 10000);


function loadQueue() {
	$.get("/presentation/getUserQueue", function(data){
		   alert("Data Loaded: " + data);
		 });
}
</script>

