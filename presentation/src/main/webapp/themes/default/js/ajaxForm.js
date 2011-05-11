function autoSubmitFormAjax(){
		$('#ajaxSave').click();
		_isDirty = false;
	}

function submitFormAjax(){
	//Pongo el boton disabled y con otro texto:
	$('#ajaxSave').attr("disabled", "true");
	$('#ajaxSave').html("Guardando...");

    var options = { 
            //target:        '#output2',   // target element(s) to be updated with server response 
            //beforeSubmit:  showRequest,  // pre-submit callback 
            success: function() {
            	$('#ajaxSave').removeAttr('disabled');
    	    	$('#ajaxSave').html("Guardar");
            },
            // other available options: 
            //url:       url         // override for form's 'action' attribute 
            //type:      type        // 'get' or 'post', override for form's 'method' attribute 
            //dataType:  null        // 'xml', 'script', or 'json' (expected server response type) 
            //clearForm: true        // clear all form fields after successful submit 
            //resetForm: true        // reset the form after successful submit 
     
            // $.ajax options can be used here too, for example: 
            timeout:   3000
    };
	
	$('#myform').ajaxSubmit(options);
	_isDirty = false;
}