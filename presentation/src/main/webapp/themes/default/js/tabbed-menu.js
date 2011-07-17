$(document).ready(function() {	
  //Get all the LI from the #tabMenu UL
  $('#tabMenu > li').click(function(){
        
    //remove the selected class from all LI    
    $('#tabMenu > li').removeClass('selected');
    
    //Reassign the LI
    $(this).addClass('selected');
    
    //Hide all the DIV in .boxBody
    $('.boxBody div').hide();//slideUp('1500');
    
    //Look for the right DIV in boxBody according to the Navigation UL index, therefore, the arrangement is very important.
    $('.boxBody div:eq(' + $('#tabMenu > li').index(this) + ')').slideDown('1500');
    
  }).mouseover(function() {

    //Add and remove class, Personally I dont think this is the right way to do it, anyone please suggest    
    $(this).addClass('mouseover');
    $(this).removeClass('mouseout');   
    
  }).mouseout(function() {
    
    //Add and remove class
    $(this).addClass('mouseout');
    $(this).removeClass('mouseover');    
    
  });

  //Mouseover with animate Effect for Category menu list
  $('.boxBody #category li').mouseover(function() {

    //Change background color and animate the padding
    $(this).css('backgroundColor','#888');
    $(this).children().animate({paddingLeft:"20px"}, {queue:false, duration:300});
  }).mouseout(function() {
    
    //Change background color and animate the padding
    $(this).css('backgroundColor','');
    $(this).children().animate({paddingLeft:"0"}, {queue:false, duration:300});
  });  
	
  //Mouseover effect for Posts, Comments, Famous Posts and Random Posts menu list.
  $('.boxBody li').click(function(){
    window.location = $(this).find("a").attr("href");
  }).mouseover(function() {
    $(this).css('backgroundColor','#888');
  }).mouseout(function() {
    $(this).css('backgroundColor','');
  });  	
	
});

function nextTab( tabName ) {
	var $tabs = $(tabName).tabs();
	var selected = $tabs.tabs('option', 'selected'); // => 0
	$(tabName).tabs('select', selected+1);
	return false;
}

function checkTabErrors(tabName, tabs){
    var firstTab = -1;
    for(i in tabs){
        if($("#" + tabs[i] + " span[id*='errors']").length > 0){
            if(firstTab == -1){
            	firstTab = i;
            }
        	var data = $("a[href*='" + tabs[i] + "']").html();
        	$("a[href*='" + tabs[i] + "']").html("<span class='form-errors'>" + data + "</span>");
        }
    }
    
    if(firstTab > -1){
    	$(tabName).tabs('select', parseInt(firstTab));
    	return true;
    }
    
    return false;
}

function changeTab(e, did){
	var code = (e.keyCode ? e.keyCode : e.which);
	if(code == 9){
		nextTab('#form-tabs');
		$("#" + did).focus();
	}
}

function previousTab( tabName ) {
	var $tabs = $(tabName).tabs();
	var selected = $tabs.tabs('option', 'selected'); // => 0
	$(tabName).tabs('select', selected-1);
	return false;
}

function nextTabUnload() {
	if( _isDirty )
  		return 'Hubo cambios que no fueron guardados, ¿está seguro que desea salir?';
}