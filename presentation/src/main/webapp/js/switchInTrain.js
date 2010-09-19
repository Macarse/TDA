function switchInTrain(patientId) {
	$.post('presentation/switchInTrain', 'patientId='+patientId, function(data){
	    alert(data);
	    });
}