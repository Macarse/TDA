<div class="box">
	<ul id="tabMenu">
	  <li class="menu-pacientsontrain selected" title="Pacientes en el tren">
	  	<div class="menu-badge"> <div id="m-pacients" class="menu-pacientsontrain"></div> </div>
  		<img class="menu-loading" id="loadImage" src='${pageContext.request.contextPath}/themes/default/image/ajax-loader.gif' />
  		<img class="menu-icon-badge hide" id="menu-icon" src='${pageContext.request.contextPath}/themes/default/image/patient-icon.gif' />
	  </li>
	  <li class="menu-queue" title="Mi cola de pacientes">
  		<img class="menu-icon-queue" id="menu-icon-queue" src='${pageContext.request.contextPath}/themes/default/image/queue-icon.gif'/>
	  </li>
	  <li class="menu-chat" title="Chat">
  		<img class="menu-icon-chat" id="menu-icon-chat" src='${pageContext.request.contextPath}/themes/default/image/chat-icon.png'/>
	  </li>
	</ul>
	<div class="boxTop"></div>

	<div class="boxBody">
	  <div id="menu-pacientsontrain" class="show">  
	  </div>  
	  
	  <div id="menu-patientqueue"></div>
	  
	  <div id="onlineusers">
	  </div>
	</div>
	
	<div class="boxBottom"></div>
</div>

<div id="queue-dialog" style="display: none" "title="Seleccione una Opcion">
	<div>Ir a </div>
	<div>
		<div id='queuemenu-socialform1' class='queuemenu-button'><a href='#' class='button-text button-search fg-button-nf ui-state-default ui-corner-all'>Formulario Trabajador Social</a></div>
		<div id='queuemenu-pediaform1' class='queuemenu-button'><a href='#' class='button-text button-search fg-button-nf ui-state-default ui-corner-all'>Formulario Pediatría</a></div>
		<div id='queuemenu-nurseform1' class='queuemenu-button'><a href='#' class='button-text button-search fg-button-nf ui-state-default ui-corner-all'>Formulario Enfermero</a></div>
		<div id='queuemenu-dentform1' class='queuemenu-button'><a href='#' class='button-text button-search fg-button-nf ui-state-default ui-corner-all'>Formulario Dentista</a></div>
	</div>
	<div>Enviar a </div>
	<div id='queuemenu-usersonline'>
	</div>
</div>

<div id="patientlist-dialog" style="display: none" title="Seleccione una Opcion">
	<div>Ir a </div>
	<div>
		<div id='queuemenu-socialform2' class='queuemenu-button'><a href='#' class='button-text button-search fg-button-nf ui-state-default ui-corner-all'>Formulario Trabajador Social</a></div>
		<div id='queuemenu-pediaform2' class='queuemenu-button'><a href='#' class='button-text button-search fg-button-nf ui-state-default ui-corner-all'>Formulario Pediatría</a></div>
		<div id='queuemenu-nurseform2' class='queuemenu-button'><a href='#' class='button-text button-search fg-button-nf ui-state-default ui-corner-all'>Formulario Enfermero</a></div>
		<div id='queuemenu-dentform2' class='queuemenu-button'><a href='#' class='button-text button-search fg-button-nf ui-state-default ui-corner-all'>Formulario Dentista</a></div>
	</div>
	<div>Enviar a </div>
	<div id='patientlist-usersonline'>
	</div>
</div>


<script language='javascript' type='text/javascript'> 
	var refreshId;
	var selectedId;
	var userType = "socialworker";
	
	function refreshPatients() {
		clearInterval(refreshId);
	
		$('#loadImage').show();
		$('#menu-icon').hide();
	
		$.get(contextPath + "/getPatientsInTrain", function(data){
			//Me llega la lista separada por &:
			if(data != ''){
		   		var patients = eval(data);
		   		var innerHtml = "";
		   		var i;

		   		if(patients.length > 0){
			   		for(i=0; i<patients.length; i++ ) {
			   	   		//Cada elemento esta separado por =:
						innerHtml += "<li>" + patients[i].firstName + ' ' + patients[i].lastName +  " <a href='" + contextPath + "/patient/getform/" + patients[i].id + "/" + userType + "'><img src='" + contextPath + "/themes/default/image/edit.gif'/></a> <a href='#' onclick='showPatientMenu(" + patients[i].id + ")'> <img src='" + contextPath + "/themes/default/image/refresh.png'/> </a></li>";
			   		}
				}else{
					innerHtml = '<i>No hay Pacientes en el tren</i>';
				}
		
		   		$('#m-pacients').html(patients.length);
		   		$('#menu-pacientsontrain').html('<ul>' + innerHtml + '</ul>');
			}
	   		$('#loadImage').hide();
	   		$('#menu-icon').show();
	 	});
	
		$.get(contextPath + "/patientqueue/get", function(data){
			if(data != ''){
		   		var patients = eval(data);
		   		var innerHtml = "";
		   		var i;

		   		if(patients.length > 0){
			   		for(i=0; i<patients.length; i++ ) {
			   	   		//Cada elemento esta separado por =:
			   	   		innerHtml += "<li>" + patients[i].firstName + ' ' + patients[i].lastName +  " <a href='" + contextPath + "/patient/getform/" + patients[i].id + "/" + userType + "'><img src='" + contextPath + "/themes/default/image/edit.gif'/></a> <a href='#' onclick='showQueueMenu(" + patients[i].id + ")'> <img src='" + contextPath + "/themes/default/image/refresh.png'/> </a></li>";
						//innerHtml += "<li onclick='showQueueMenu(" + patients[i].id + ")'>" + patients[i].firstName + ' ' + patients[i].lastName +  "</li>";
			   		}
				}else{
					innerHtml = '<i>No tiene pacientes en cola</i>';
				}
		
		   		//$('#m-pacients').html(parsedData.length);
		   		$('#menu-patientqueue').html('<ul>' + innerHtml + '</ul>');
			}
	   		$('#loadImage').hide();
	   		$('#menu-icon').show();
	 	});
		
		refreshId = setInterval(refreshPatients, 5000);
	}
	
	function replaceAll( text, busca, reemplaza ){
	    while (text.toString().indexOf(busca) != -1){
	        text = text.toString().replace(busca,reemplaza);
	    }
	    return text;
	}
	
	function showQueueMenu(id){
		selectedId = id;
		refreshPopupLinks();
		$("#queue-dialog").dialog('open');
	}

	function showPatientMenu(id){
		selectedId = id;
		refreshPopupLinks();
		$("#patientlist-dialog").dialog('open');
	}

	function refreshPopupLinks(){
		 document.getElementById('queuemenu-socialform1').onclick = function() {
   		 	window.location.href = contextPath + '/patient/getform/' + selectedId + '/socialworker';
   		 }
   		 document.getElementById('queuemenu-socialform2').onclick = function() {
   		 	window.location.href = contextPath + '/patient/getform/' + selectedId + '/socialworker';
   		 }

		 document.getElementById('queuemenu-pediaform1').onclick = function() {
   		 	window.location.href = contextPath + '/patient/getform/' + selectedId + '/pediatrician';
		 }
		 document.getElementById('queuemenu-pediaform2').onclick = function() {
			 window.location.href = contextPath + '/patient/getform/' + selectedId + '/pediatrician';
		 }

		 document.getElementById('queuemenu-nurseform1').onclick = function() {
   		 	window.location.href = contextPath + '/patient/getform/' + selectedId + '/nurse';
		 }
		 document.getElementById('queuemenu-nurseform2').onclick = function() {
			 window.location.href = contextPath + '/patient/getform/' + selectedId + '/nurse';
		 }

		 document.getElementById('queuemenu-dentform1').onclick = function() {
			 window.location.href = contextPath + '/patient/getform/' + selectedId + '/dentist';
		 }
		 document.getElementById('queuemenu-dentform2').onclick = function() {
			 window.location.href = contextPath + '/patient/getform/' + selectedId + '/dentist';
		 }
	}

	function redirect2(url){
		window.location.href = url;
	}

	function refreshOnlineUsers() {
		$.ajax({
			  url: contextPath + "/getOnlineUsers",
			  cache: false,
			  dataType: "json",
			  success: function(data) {
				  var htmlUsers = "";
				  var queueUsers = "";
				  var listUsers = "<a href=\"#\" onclick=\"sendTo2('${user.username}');\" class=\"queuemenu-userbutton button-text button-search fg-button ui-state-default ui-corner-all\">Cargar en Cola</a>";
				  var aux;
				  
				  $.each(data, function(i,item) {
					  htmlUsers += "<li><a href=\"javascript:void(0)\" onclick=\"javascript:chatWith('" +
					  	item.username +"')\">" + item.username +"</a></li>";

					  	aux = "'" + item.username + "'";
					    queueUsers += "<a href=\"#\" onclick=\"sendTo(" + aux + ");\" class=\"queuemenu-userbutton button-text button-search fg-button ui-state-default ui-corner-all\">" + item.username + "</a>";
					    listUsers += "<a href=\"#\" onclick=\"sendTo2(" + aux + ");\" class=\"queuemenu-userbutton button-text button-search fg-button ui-state-default ui-corner-all\">" + item.username + "</a>";
				  });
		       
		       if(data != ''){
		    	   $("#onlineusers").html('<ul>' + htmlUsers + '</ul>');
				   $("#queuemenu-usersonline").html(queueUsers);
		       }else{
		    	   $('#onlineusers').html('<ul><i>No hay usuarios conectados</i></ul>');
		       }
		       
			   $("#patientlist-usersonline").html(listUsers);

			  }
		});
	}

	function sendTo2(username){
		$.get(contextPath + "/patientqueue/assigntos?patient=" + selectedId + "&medic=" + username, function(){
		});
		$("#patientlist-dialog").dialog('close');
	}

	function sendTo(username){
		$.get(contextPath + "/patientqueue/assigntos?patient=" + selectedId + "&medic=" + username, function(){
		});
		$("#queue-dialog").dialog('close');
	}

	function redirectTo(url){
		window.location.href = url;
	}

	$(document).ready(function(){
		refreshPatients();

		$("#queue-dialog").dialog({ 
			autoOpen: false,
			modal: true,
			resizable: false,
			buttons: { "Cerrar": function() { $(this).dialog("close"); } }
			 });

		$("#patientlist-dialog").dialog({ 
			autoOpen: false,
			modal: true,
			resizable: false,
			buttons: { "Cerrar": function() { $(this).dialog("close"); } }
			 });
		/*
		 $("#queuemenu-socialform").click(function(){
			 window.location.href = contextPath + '/patient/' + selectedId + '/socialworker/new';
		 });

		 $("#queuemenu-pediaform").click(function(){
			 window.location.href = contextPath + '/patient/' + selectedId + '/pediatrician/new';
		 });

		 $("#queuemenu-nurseform").click(function(){
			 window.location.href = contextPath + '/patient/' + selectedId + '/nurse/new';
		 });

		 $("#queuemenu-dentform").click(function(){
			 window.location.href = contextPath + '/patient/' + selectedId + '/dentist/new';
		 });
		*/
		 refreshOnlineUsers();
		 var refreshOnlineUsersTimer = setInterval(refreshOnlineUsers, 5000);


		 $("[title]").tooltip({
			 track: true, 
		     delay: 0, 
			 showURL: false, 
			 opacity: 1, 
			 fixPNG: true, 
			 showBody: " - ", 
			 extraClass: "pretty fancy", 
			 top: -15, 
			 left: 5 
		 });
	});
</script>

