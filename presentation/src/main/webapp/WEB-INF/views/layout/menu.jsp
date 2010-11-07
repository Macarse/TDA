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
	      <li><a>jQuery Tabbed Navigation Menu. <span> - kevin</span></a></li>
	      <li><a>You can add links in here! <span> - kevin</span></a></li>
	      <li><a>It's easy to understand. Though it's not the best, but hell yeah it works. <span> - kevin</span></a></li>
	      <li><a>I hope you will like it. <span> - kevin</span></a></li>
	      <li><a>My next tutorial will be jQuery-based Accordion. <span> - kevin</span></a></li>
	      <li class="last"><a>And, I'm working on a free wordpress template as well : ) <span> - kevin</span></a></li>
	    </ul>
	  </div>
	  
	  <div id="category">
	    <ul>
	      <li><a href="javascript:void(0)" onclick="javascript:chatWith('admin')">Admin</a></li>
	      <li><a href="javascript:void(0)" onclick="javascript:chatWith('social')">Social</a></li>
	      <li><a href="javascript:void(0)" onclick="javascript:chatWith('dentista')">dentista</a></li>
   	      <li class="last"><a href="http://www.queness.com/tag/wordpress">wordpress</a></li>
	    </ul>  
	  </div>
	</div>
	
	<div class="boxBottom"></div>
	
</div>


<script language='javascript' type='text/javascript'> 

var refreshId = setInterval(refreshPatients, 335000);

function refreshPatients() {
	clearInterval(refreshId);

	$('#loadImage').show();
	$('#menu-icon').hide();

	$.get(contextPath + "/getUserQueue", function(data){
		//Me llega la lista separada por &:
   		var parsedData = data.split('&');
   		var innerHtml = "";
   		var i;
   		//alert(parsedData.length);
   		for(i in parsedData ) {
   	   		//Cada elemento esta separado por =:
	   		var patientData = parsedData[i].split('=');
	   		var str = patientData[0];
			innerHtml += "<li>" + str.replace('+',' ') + "</li>";
   		}

   		$('#m-pacients').html(parsedData.length);
   		$('#menu-pacientsontrain').html('<ul>' + innerHtml + '</ul>');
   		$('#loadImage').hide();
   		$('#menu-icon').show();
 	});

	 refreshId = setInterval(refreshPatients, 5000)
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

