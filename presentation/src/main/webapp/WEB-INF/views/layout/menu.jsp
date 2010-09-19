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

<div class="box">
<ul id="tabMenu">
  <li class="posts selected"></li>
  <li class="comments"></li>
  <li class="category"></li>
  <li class="famous"></li>
</ul>
<div class="boxTop"></div>

<div class="boxBody">
  
  <div id="posts" class="show">
    <ul>
      <li>Create a Simple CSS + Javascript Tooltip with jQuery.</li>
      <li>Simple JQuery Modal Window Tutorial.</li>
      <li>Navigation List menu + Jquery Animate Effect Tutorial.</li>
      <li>Exclusive RSS Icons from Queness.</li>
      <li>50 Monochromatic Website Designs.</li>
      <li>Food Plates and Creative Dishware Designs.</li>
      <li>Breadcrumbs In Web Design: Examples And Best Practices.</li>
      <li class="last">New Smashing Freebies For Designers and Bloggers.</li>
    </ul>  
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
      <li><a href="http://www.queness.com/tag/ajax">ajax</a></li>
      <li><a href="http://www.queness.com/tag/css-html">css-html</a></li>
      <li><a href="http://www.queness.com/tag/freebies">freebies</a></li>
      <li><a href="http://www.queness.com/tag/icon">icon</a></li>
      <li><a href="http://www.queness.com/tag/inspiration">inspiration</a></li>
      <li><a href="http://www.queness.com/tag/javascript">javascript</a></li>
      <li><a href="http://www.queness.com/tag/logo">logo</a></li>
      <li><a href="http://www.queness.com/tag/photography">photography</a></li>
      <li><a href="http://www.queness.com/tag/photoshop">photoshop</a></li>
      <li><a href="http://www.queness.com/tag/php">php</a></li>
      <li><a href="http://www.queness.com/tag/seo">seo</a></li>
      <li><a href="http://www.queness.com/tag/tutorial">tutorial</a></li>
      <li><a href="http://www.queness.com/tag/usability">usability</a></li>
      <li><a href="http://www.queness.com/tag/wallpaper">wallpaper</a></li>
      <li class="last"><a href="http://www.queness.com/tag/wordpress">wordpress</a></li>
    </ul>  
  </div>
  
  <div id="famous">
    <ul>
      <li>Simple JQuery Modal Window Tutorial.</li>
      <li>Create a Simple CSS + Javascript Tooltip with jQuery.</li>
      <li>Navigation List menu + Jquery Animate Effect Tutorial.</li>
      <li class="last">Exclusive RSS Icons from Queness.</li>
    </ul>  
  </div>
</div>

<div class="boxBottom"></div>

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

