<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
	<div style="clear: both;">Enviar a </div>
		<div id='patientlist-users'>
		<c:forEach var="userl" items="${userList}">
		<div>
			<a href="#" onclick="sendTo('${userl.username}');" class="queuemenu-userbutton button-text button-search fg-button ui-state-default ui-corner-all">${userl.username}</a>
		</div> 
		</c:forEach>
	</div>
	<div style="clear: both;">Ir a </div>
	<div>
		<div id='queuemenu-socialform1' class='queuemenu-button'><a href='#' class='button-text button-search fg-button-nf ui-state-default ui-corner-all'>Formulario Trabajador Social</a></div>
		<div id='queuemenu-pediaform1' class='queuemenu-button'><a href='#' class='button-text button-search fg-button-nf ui-state-default ui-corner-all'>Formulario Pediatría</a></div>
		<div id='queuemenu-nurseform1' class='queuemenu-button'><a href='#' class='button-text button-search fg-button-nf ui-state-default ui-corner-all'>Formulario Enfermero</a></div>
		<div id='queuemenu-dentform1' class='queuemenu-button'><a href='#' class='button-text button-search fg-button-nf ui-state-default ui-corner-all'>Formulario Dentista</a></div>
	</div>
</div>

<div id="patientlist-dialog" style="display: none" title="Seleccione una Opcion">
	<div style="clear: both;">Enviar a </div>
	<div id='patientlist-users'>
		<c:forEach var="userl" items="${userList}">
		<div>
			<a href="#" onclick="sendTo2('${userl.username}');" class="queuemenu-userbutton button-text button-search fg-button ui-state-default ui-corner-all">${userl.username}</a>
		</div> 
		</c:forEach>
	</div>
	<div style="clear: both;">Ir a </div>
	<div>
		<div id='queuemenu-socialform2' class='queuemenu-button'><a href='#' class='button-text button-search fg-button-nf ui-state-default ui-corner-all'>Formulario Trabajador Social</a></div>
		<div id='queuemenu-pediaform2' class='queuemenu-button'><a href='#' class='button-text button-search fg-button-nf ui-state-default ui-corner-all'>Formulario Pediatría</a></div>
		<div id='queuemenu-nurseform2' class='queuemenu-button'><a href='#' class='button-text button-search fg-button-nf ui-state-default ui-corner-all'>Formulario Enfermero</a></div>
		<div id='queuemenu-dentform2' class='queuemenu-button'><a href='#' class='button-text button-search fg-button-nf ui-state-default ui-corner-all'>Formulario Dentista</a></div>
	</div>
</div>


<script language='javascript' type='text/javascript'> 
	var refreshId;
	var selectedId;
	var userType = '<c:out value="${user.currRole}"></c:out>';
	var userRole = '${user.currRole}';
	

	if( userType == "admin" )
		userType = "socialworker";

	var chatTimeout = [5000, 30000];
	var queueMinTimeout = [5000, 30000];
	var processQueue = 3;
	var idWatchdogTimer = 0;

	function refreshPatients() {
		$('#loadImage').show();
		$('#menu-icon').hide();

		if(processQueue == 3){

			processQueue = 0;
			
			$.get(contextPath + "/getPatientsInTrain", function(data){
				//Me llega la lista separada por &:
				if(data != ''){
			   		var patients = eval(data);
			   		var innerHtml = "";
			   		var i;
	
			   		if(patients.length > 0){
				   		for(i=0; i<patients.length; i++ ) {
				   	   		//Cada elemento esta separado por =:
							innerHtml += "<li><a href='" + contextPath + "/patient/getform/" + patients[i].id + "/" + userType + "'>" + patients[i].firstName + ' ' + patients[i].lastName +  "</a> <a href='" + contextPath + "/patient/getform/" + patients[i].id + "/" + userType + "'><img src='" + contextPath + "/themes/default/image/edit.gif' style='border: 0px;'/></a> <a href='#' onclick='showPatientMenu(" + patients[i].id + ")'> <img src='" + contextPath + "/themes/default/image/refresh.png' style='border: 0px;'/> </a></li>";
				   		}
					}else{
						innerHtml = '<i>No hay Pacientes en el tren</i>';
					}
			
			   		$('#m-pacients').html(patients.length);
			   		$('#menu-pacientsontrain').html('<ul>' + innerHtml + '</ul>');
				}
		   		$('#loadImage').hide();
		   		$('#menu-icon').show();
	
		   		processQueue +=1;
		 	});
		
			$.get(contextPath + "/patientqueue/get", function(data){
				if(data != ''){
			   		var patients = eval(data);
			   		var innerHtml = "";
			   		var i;
	
			   		if(patients.length > 0){
				   		for(i=0; i<patients.length; i++ ) {
				   	   		//Cada elemento esta separado por =:
				   	   		innerHtml += "<li><a href='" + contextPath + "/patient/getform/" + patients[i].id + "/" + userType + "'>" + patients[i].firstName + ' ' + patients[i].lastName +  " </a> <a href='" + contextPath + "/patient/getform/" + patients[i].id + "/" + userType + "'><img src='" + contextPath + "/themes/default/image/edit.gif' style='border: 0px;'/></a> <a href='#' onclick='showQueueMenu(" + patients[i].id + ")' > <img src='" + contextPath + "/themes/default/image/refresh.png' style='border: 0px;'/> </a></li>";
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
		   		processQueue +=1;
		 	});

			$.ajax({
				  url: contextPath + "/getOnlineUsers",
				  cache: false,
				  dataType: "json",
				  success: function(data) {
					  var listUsers = "<a href=\"#\" onclick=\"sendTo2('${user.username}');\" class=\"queuemenu-userbutton button-text button-search fg-button ui-state-default ui-corner-all\">Cargar en Cola</a>";
					  var aux;
					  var htmlUsers = "";
					  $.each(data, function(i,item) {
						  htmlUsers += "<li><a href=\"javascript:void(0)\" onclick=\"javascript:chatWith('" +
						  	item.username +"')\">" + item.username +"</a></li>";

						  	aux = "'" + item.username + "'";
					  });
			       
			       if(data != ''){
			    	   $("#onlineusers").html('<ul>' + htmlUsers + '</ul>');
			       }else{
			    	   $('#onlineusers').html('<ul><i>No hay usuarios conectados</i></ul>');
			       }

			       processQueue +=1;
				  }
			});

			//borro el watchdog
			clearTimeout(idWatchdogTimer);
			//seteo el nuevo timeout
			idWatchdogTimer = setTimeout(checkQueueTimer, queueTimeout[1]);
		}
		
		setTimeout(refreshPatients, queueTimeout[0]);
	}

	function checkQueueTimer(){
		processQueue = 3;
		refreshPatients();
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

	function sendTo2(username){
		$.get(contextPath + "/patientqueue/assigntos?patient=" + selectedId + "&medic=" + username, function(){
			location.reload(true);
		});
		$("#patientlist-dialog").dialog('close');
	}

	function sendTo(username){
		$.get(contextPath + "/patientqueue/assigntos?patient=" + selectedId + "&medic=" + username, function(){
			location.reload(true);
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