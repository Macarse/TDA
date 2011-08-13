<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style type="text/css">
div, p { 
	margin:0;
	padding:0;
}

.left{
    border-top:7.5px solid transparent;
    border-bottom:7.5px solid transparent;
    border-left: 7.5px solid #fff;
    height:25px;
    position:absolute;top:0;left:0;
}
.right{
    border-top:7.5px solid transparent;
    border-bottom:7.5px solid transparent;
    border-right: 7.5px solid #fff;
    height:25px;
    position:absolute;top:0;right:0;
}
.bottom {
    border-bottom:7.5px solid #fff;
    border-right:7.5px solid transparent;
    border-left: 7.5px solid transparent;
    position:absolute;bottom:0;left:0;
    width:10px;
}

.top{
    border-top:7.5px solid #fff;
    border-right:7.5px solid transparent;
    border-left: 7.5px solid transparent;
    position:absolute;top:0;left:0;
    width:10px;
}

.center{
    background:#fff;
    height:25px;
    position:absolute;top:7.5px;left:7.5px;
    width:10px;
}
.diente{
    float:left;
    height:40px;
    margin: 0 5px 5px 0;
    position:relative;
    width:25px;
}
.marco{
    background-image:url('/presentation/themes/default/image/diente.png');
    height:40px;
    pointer-events:none;
    position:absolute;top:0;left:0;
    width:25px;
}

.tachado{
    background-image:url('/presentation/themes/default/image/diente_tachado.png');
    height:40px;
    pointer-events:none;
    position:absolute;top:0;left:0;
    width:25px;
    display: none;
}

#fila1_izq_mandibula8{
    overflow:hidden;
    width:250px;
    float:left;
}
#fila1_der_mandibula8{
    overflow:hidden;
    width:250px;
    float:right;
}

#fila2_izq_mandibula8{
    overflow:hidden;
    width:250px;
    float:left;
}
#fila2_der_mandibula8{
    overflow:hidden;
    width:250px;
    float:right;
}

#fila3_izq_mandibula5{
    overflow:hidden;
    width:150px;
    float: left;
}

#fila3_der_mandibula5{
    overflow:hidden;
    width:150px;
    float: left;
    margin-left: 20px;
}

#fila4_izq_mandibula5{
    overflow:hidden;
    width:150px;
    float: left;
}

#fila4_der_mandibula5{
    overflow:hidden;
    width:150px;
    float: left;
    margin-left: 20px;
}

#odontograma{
    background-image:url('/presentation/themes/default/image/odontograma.png');
}

#fila1{
    overflow:hidden;
    width:510px;
    padding-top: 20px;
    margin-bottom: 5px;
}

#fila2{
    overflow:hidden;
    width:510px;
    padding-bottom: 10px;
}

#fila3{
    overflow:hidden;
    width: 510px;
    padding-top: 10px;
    margin-left: 90px;
    margin-bottom: 5px;
    margin-top: 10px;
}

#fila4{
    overflow:hidden;
    width: 510px;
    padding-bottom: 20px;
    margin-left: 90px;
}

</style>

<script type="text/javascript">

// Src: https://gist.github.com/399624
jQuery.fn.single_double_click = function(single_click_callback, double_click_callback, timeout) {
	  return this.each(function(){
	    var clicks = 0, self = this;
	    jQuery(this).click(function(event){
	      clicks++;
	      if (clicks == 1) {
	        setTimeout(function(){
	          if(clicks == 1) {
	            single_click_callback.call(self, event);
	          } else {
	            double_click_callback.call(self, event);
	          }
	          clicks = 0;
	        }, timeout || 300);
	      }
	    });
	  });
	}

var dientes = [];
var crossColor = 'rgb(1, 1, 1)';
var arr = ['rgb(255, 255, 255)', 'rgb(255, 0, 0)', 'rgb(0, 0, 255)'];
var arrDict = new Array();

$(document).ready(function() {

	arrDict['rgb(255, 255, 255)'] = 0;
	arrDict['rgb(255, 0, 0)'] = 1;
	arrDict['rgb(0, 0, 255)'] = 2;

	var i = 0;
	$(".diente p").each(function() {

	    var p = {'obj': $(this), 'color': 1 };

	    var place = p.obj.attr("class");
	    dientes[i++] = p;

	    p.obj.single_double_click( function() {

	    	if ( isCrossed(p) ) {
	    		return;
	    	}

	    	var index = -1;
	    	var actualIndex = -1;

	    	if ( p.color == 0 ) {
	    		index = arr.lenght - 1;
	    	} else {
	    		index = p.color - 1;
	    	}

	    	if (place === 'center') {
		    	actualIndex = arrDict[p.obj.css("background-color")];
	    	} else {
	    		actualIndex = arrDict[p.obj.css("border-" + place + "-color")];
	    	}

	    	if ( index !=  actualIndex ) {
	    		p.color =  (actualIndex + 1) % arr.length;
	    	}

	        if (place === 'center') {
	            p.obj.removeAttr("style").css("background-color", arr[p.color]);
	        } else {
	            p.obj.removeAttr("style").css("border-" + place + "-color", arr[p.color]);
	        }

	        p.color = (p.color + 1) % arr.length;

	    }, function() {
	    	if ( !isCrossed(p) ) {
	    		showCross(p);
	    	} else {
	    		hideCross(p);
	    	}

	    }, 200);
	});

	var tooths = '<c:out value="${toothString}"></c:out>';
	setData(tooths);
});

function getData() {
	var ret = "";

	for( i=0 ; i< dientes.length ; i = i+5 ) {
		for (j=0 ; j < 4 ; j++ ) {
			ret += dientes[i+j].obj.css("border-" + dientes[i+j].obj.attr("class") + "-color") + ":";
		}

		ret += dientes[i+j].obj.css("background-color") + ";";
	}

	return ret;
}

function setData(text) {

	if ( text.length == 0 ) {
		return;
	}

	text =  text.substring(0, text.length-1); 
	var tooths = text.split(';');
	var i = 0;

	for( j=0 ; j < tooths.length ; j++ ) {

		if ( tooths[j].search('1, 1, 1') != -1) {
			showCross(dientes[i]);
			i=i+5;

		} else {

			hideCross(dientes[i]);
			var parts = tooths[j].split(':');

			for( k=0 ; k < parts.length ; k++ ) {

				dientes[i].color = arrDict[parts[k]];
				var place = dientes[i].obj.attr("class");

				if ( place === 'center' ) {
					dientes[i].obj.removeAttr("style").css("background-color", arr[dientes[i].color]);
		        } else {
		        	dientes[i].obj.removeAttr("style").css("border-" + place + "-color", arr[dientes[i].color]);
		        }

				dientes[i].color = (dientes[i].color + 1) % arr.length;
				i++;
			}
		}
	}

	return;
}
function isCrossed(part) {
    var parent = $(part.obj.parent().get(0));
    var tachado = $(parent.children('.tachado').get(0));
    return tachado.is(':visible');
}

function showCross(part) {
    var parent = $(part.obj.parent().get(0));
    var tachado = $(parent.children('.tachado').get(0));
    tachado.show();

    var parts = parent.children('p');

    for( i=0 ; i < parts.length ; i++ ) {
    	var place = $(parts[i]).attr("class");

		if ( place === 'center' ) {
			$(parts[i]).removeAttr("style").css("background-color", crossColor);
        } else {
        	$(parts[i]).removeAttr("style").css("border-" + place + "-color", arr[0]);
        }
    }
}

function hideCross(part) {
	var parent = $(part.obj.parent().get(0));
	var tachado = $(parent.children('.tachado').get(0));
    tachado.hide();

    var parts = parent.children('p');

    for( i=0 ; i < parts.length ; i++ ) {
    	var place = $(parts[i]).attr("class");

		if ( place === 'center' ) {
			$(parts[i]).removeAttr("style").css("background-color", arr[0]);
        } else {
        	$(parts[i]).removeAttr("style").css("border-" + place + "-color", arr[0]);
        }
    }
}

</script>

<div id="odontograma">
<div id="fila1">
    <div id="fila1_izq_mandibula8">
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>


        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
    </div>
    
    <div id="fila1_der_mandibula8">
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
    </div>
</div>

<div id="fila2">
    <div id="fila2_izq_mandibula8">
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
    </div>
    
    <div id="fila2_der_mandibula8">
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
    </div>
</div>

<div id="fila3">
    <div id="fila3_izq_mandibula5">
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
    </div>

        <div id="fila3_der_mandibula5">
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
    </div>
</div>

<div id="fila4">
    <div id="fila4_izq_mandibula5">
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
    </div>

        <div id="fila4_der_mandibula5">
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <div class="tachado"></div>
            <div class="marco"></div>

        </div>
    </div>
</div>
</div>