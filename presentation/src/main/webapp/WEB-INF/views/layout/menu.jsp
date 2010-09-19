<div class="menu-items-container">
	<div>
		<ul class="menu">
		  <li><a href="${pageContext.request.contextPath}/applicationUser/"><span>Usuarios</span></a></li>
		  <li><a href="${pageContext.request.contextPath}/patient/"><span>Pacientes</span></a></li>
		  <li><a href="${pageContext.request.contextPath}/item/"><span>Items</span></a></li>
		</ul>
	</div>
</div>

<div id="queue" style="text-align:center; padding-top:20px;">
	<img id="loadImage" src='${pageContext.request.contextPath}/themes/default/image/ajax-loader.gif' style="visibility:hidden;">
	<table>
		<tbody id="queueTable"></tbody>
	</table>
</div>

<script language='javascript' type='text/javascript'> 

var refreshId = setInterval(function()
		{
			$.get(contextPath + "/getUserQueue", function(data){
				//Me llega la lista separada por &:
		   		var parsedData = data.split('&');
		   		var innerHtml = "";
		   		var i;
		   		for(i in parsedData ) {
		   	   		//Cada elemento esta separado por =:
			   		var patientData = parsedData[i].split('=');
					innerHtml += "<tr><td>"+patientData[0]+"</td></tr>";
		   		}
		   		document.getElementById('loadImage').style.visibility = "hidden";
		   		document.getElementById('queueTable').innerHTML = innerHtml;
		 	});
		}, 10000);


/*
function loadQueue() {
	document.getElementById('loadImage').style.visibility = "visible";
	$.get("/presentation/getUserQueue", function(data){
		//Me llega la lista separada por &:
   		var parsedData = data.split('&');
   		var innerHtml = "";
   		var i;
   		for(i in parsedData ) {
   	   		//Cada elemento esta separado por =:
	   		var patientData = parsedData[i].split('=');
			innerHtml += "<tr><td>"+patientData[0]+"</td></tr>";
   		}
   		document.getElementById('loadImage').style.visibility = "hidden";
   		document.getElementById('queueTable').innerHTML = innerHtml;
		 });
}
*/
</script>

