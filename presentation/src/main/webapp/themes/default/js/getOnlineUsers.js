

function refreshOnlineUsers() {
	$.ajax({
		  url: contextPath + "/getOnlineUsers",
		  cache: false,
		  dataType: "json",
		  success: function(data) {
			  var htmlUsers = "<ul>";
			  
			  $.each(data, function(i,item) {
				  htmlUsers += "<li><a href=\"javascript:void(0)\" onclick=\"javascript:chatWith('" +
				  	item.username +"')\">" + item.username +"</a></li>";
			  });
	       htmlUsers += "</ul>";
	       $("#category").html(htmlUsers);

		  }
	});
}

$(document).ready(function() {
	refreshOnlineUsers();
	var refreshOnlineUsersTimer = setInterval(refreshOnlineUsers, 5000);
  });