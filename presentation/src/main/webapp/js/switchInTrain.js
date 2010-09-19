function switchInTrain(patientId) {
	$.post(contextPath + '/switchInTrain', 'patientId='+patientId, function(data, textStatus, XMLHttpRequest){
		    if ( textStatus == "success" ) {
		    	var text = $("#switchbutton" + patientId + " > span:nth-child(2)");
		    	text.text(data);
		    	var span = $("#switchbutton" + patientId + " > span:first-child");
		    	span.toggleClass("ui-icon-arrowreturnthick-1-s", "ui-icon-arrowreturnthick-1-n");
		    }
    	});
}