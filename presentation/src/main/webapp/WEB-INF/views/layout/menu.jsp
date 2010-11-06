<div class="menu-items-container">
	<div>
		<ul class="menu">
		  <li><a href="${pageContext.request.contextPath}/applicationUser/"><span>Usuarios</span></a></li>
		  <li><a href="${pageContext.request.contextPath}/patient/"><span>Pacientes</span></a></li>
		  <li><a href="${pageContext.request.contextPath}/item/"><span>Items</span></a></li>
		</ul>
	</div>
</div>

<div style="text-align:center; padding-top:20px;">
	<div>Pacientes actualmente en el tren</div>
	<div id="queue" style="padding-top:20px;">
		<div id="queueTable"></div>
		<img id="loadImage" src='${pageContext.request.contextPath}/themes/default/image/ajax-loader.gif'>
	</div>
</div>

<script language='javascript' type='text/javascript'> 

var refreshId = setInterval(function(){
			$('#loadImage').show();

			$.get(contextPath + "/getUserQueue", function(data){
				//Me llega la lista separada por &:
		   		var parsedData = data.split('&');
		   		var innerHtml = "";
		   		var i;
		   		for(i in parsedData ) {
		   	   		//Cada elemento esta separado por =:
			   		var patientData = parsedData[i].split('=');
			   		var str = patientData[0];
					innerHtml += "<div>" + str.replace('+',' ') + "</div>";
		   		}
		   		
		   		$('#loadImage').hide();
		   		$('#queueTable').html(innerHtml);
		 	});
		}, 5000);
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

