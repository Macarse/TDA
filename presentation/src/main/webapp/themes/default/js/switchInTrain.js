buttonEnabled = true;

function switchInTrain(patientId) {
	if( buttonEnabled ) {
		$.post(contextPath + '/switchInTrain', 'patientId='+patientId, function(data, textStatus, XMLHttpRequest){
			    if ( textStatus == "success" ) {
			    	//var text = $("#switchbutton" + patientId + " > span:nth-child(2)");
			    	//text.text(data);
			    	//var span = $("#switchbutton" + patientId + " > span:first-child");
			    	//span.toggleClass("ui-icon-arrowreturnthick-1-s", "ui-icon-arrowreturnthick-1-n");
			    	window.location.reload();
			    }
	    	});
		buttonEnabled = false;
		var refreshOnlineUsersTimer = setTimeout(setButtonEnabled, 2000);
	}
}

function setButtonEnabled() {
	buttonEnabled = true;
	
}