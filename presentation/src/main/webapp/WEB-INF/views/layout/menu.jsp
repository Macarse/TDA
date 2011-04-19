<div class="box">
	<ul id="tabMenu">
	  <li class="menu-pacientsontrain selected">
	  	<div class="menu-badge"> <div id="m-pacients" class="menu-pacientsontrain"></div> </div>
  		<img class="menu-loading" id="loadImage" src='${pageContext.request.contextPath}/themes/default/image/ajax-loader.gif' />
  		<img class="menu-icon-badge hide" id="menu-icon" src='${pageContext.request.contextPath}/themes/default/image/patient-icon.gif' />
	  </li>
	  <li class="menu-queue">
  		<img class="menu-icon-queue" id="menu-icon-queue" src='${pageContext.request.contextPath}/themes/default/image/queue-icon.gif' />
	  </li>
	  <li class="menu-chat">
  		<img class="menu-icon-chat" id="menu-icon-chat" src='${pageContext.request.contextPath}/themes/default/image/chat-icon.png' />
	  </li>
	</ul>
	<div class="boxTop"></div>

	<div class="boxBody">
	  <div id="menu-pacientsontrain" class="show">  
	  </div>  
	  
	  <div id="comments">
	    <ul>
	      <li><a>Listado</a></li>
	    </ul>
	  </div>
	  
	  <div id="category">
	  </div>
	</div>
	
	<div class="boxBottom"></div>
	
</div>


<script language='javascript' type='text/javascript'> 

var refreshId;

$(document).ready(function(){
	refreshPatients();
});

function refreshPatients() {
	clearInterval(refreshId);

	$('#loadImage').show();
	$('#menu-icon').hide();

	$.get(contextPath + "/getUserQueue", function(data){
		//Me llega la lista separada por &:
		if(data != ''){
	   		var parsedData = data.split('&');
	   		var innerHtml = "";
	   		var i;
	   		//alert(parsedData.length);
	   		for(i in parsedData ) {
	   	   		//Cada elemento esta separado por =:
		   		var patientData = parsedData[i].split('=');
		   		var str = patientData[0];
				innerHtml += "<li>" + replaceAll(str,'+',' ') + "</li>";
	   		}
	
	   		$('#m-pacients').html(parsedData.length);
	   		$('#menu-pacientsontrain').html('<ul>' + innerHtml + '</ul>');
		}else{
			$('#m-pacients').html("0");
			$('#menu-pacientsontrain').html('<ul><i>No hay pacientes</i></ul>');
		}
   		$('#loadImage').hide();
   		$('#menu-icon').show();
 	});

	 refreshId = setInterval(refreshPatients, 5000)
}

function replaceAll( text, busca, reemplaza ){
    while (text.toString().indexOf(busca) != -1){
        text = text.toString().replace(busca,reemplaza);
    }
    return text;
}

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

