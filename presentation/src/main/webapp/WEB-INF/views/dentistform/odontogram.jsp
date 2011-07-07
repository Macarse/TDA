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

#box {
    width: 20px;
    height: 50px;
}
.left{
    border-top:15px solid transparent;
    border-bottom:15px solid transparent;
    border-left: 15px solid #fff;
    height:50px;
    position:absolute;top:0;left:0;
}
.right{
    border-top:15px solid transparent;
    border-bottom:15px solid transparent;
    border-right: 15px solid #fff;
    height:50px;
    position:absolute;top:0;right:0;
}
.bottom {
    border-bottom:15px solid #fff;
    border-right:15px solid transparent;
    border-left: 15px solid transparent;
    position:absolute;bottom:0;left:0;
    width:20px;
}

.top{
    border-top:15px solid #fff;
    border-right:15px solid transparent;
    border-left: 15px solid transparent;
    position:absolute;top:0;left:0;
    width:20px;
}

.center{
    background:#fff;
    height:50px;
    position:absolute;top:15px;left:15px;
    width:20px;
}
.diente{
    float:left;
    height:80px;
    margin: 0 10px 10px 0;
    position:relative;
    width:50px;
}
.marco{
    background-image:url('/presentation/themes/default/image/diente.png');
    height:80px;
    pointer-events:none;
    position:absolute;top:0;left:0;
    width:50px;
}
#fila1_izq_mandibula8{
    overflow:hidden;
    width:500px;
    float:left;
}
#fila1_der_mandibula8{
    overflow:hidden;
    width:500px;
    float:right;
}

#fila2_izq_mandibula8{
    overflow:hidden;
    width:500px;
    float:left;
}
#fila2_der_mandibula8{
    overflow:hidden;
    width:500px;
    float:right;
}

#fila3_izq_mandibula5{
    overflow:hidden;
    width:300px;
    float: left;
}

#fila3_der_mandibula5{
    overflow:hidden;
    width:300px;
    float: left;
    margin-left: 40px;
}

#fila4_izq_mandibula5{
    overflow:hidden;
    width:300px;
    float: left;
}

#fila4_der_mandibula5{
    overflow:hidden;
    width:300px;
    float: left;
    margin-left: 40px;
}

#fila1{
    overflow:hidden;
    width:1020px;
}

#fila2{
    overflow:hidden;
    width:1020px;
}

#fila3{
    overflow:hidden;
    width: 1020px;
    margin-left: 180px;
    margin-top: 20px;
}

#fila4{
    overflow:hidden;
    width: 1020px;
    margin-left: 180px;
}

</style>

<script type="text/javascript">

$(document).ready(function(){

	var arr = ['#ffffff', '#ff0000', '#0000ff', '#00ff00', '#000000'];
	var dientes = [],
	    i = 0;
	$(".diente p").each(function() {
	
	    var p = $(this),
	        place = p.attr("class"),
	        newColor = 1;
	    dientes[i++] = p;
	
	    p.click(function() {
	        if (place === 'center') {
	            p.removeAttr("style").css("background-color", arr[newColor]);
	        } else {
	            p.removeAttr("style").css("border-" + place + "-color", arr[newColor]);
	        }
	
	        newColor = (newColor + 1) % arr.length;
	    });
	});
});
</script>

<div id="fila1">
    <div id="fila1_izq_mandibula8">
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
    </div>
    
    <div id="fila1_der_mandibula8">
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
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
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
    </div>
    
    <div id="fila2_der_mandibula8">
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
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
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
    </div>

        <div id="fila3_der_mandibula5">
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
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
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
    </div>

        <div id="fila4_der_mandibula5">
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
        <div class="diente">
            <p class="top"></p>
            <p class="right"></p>
            <p class="bottom"></p>
            <p class="left"></p>
            <p class="center"></p>
            <p class="marco"></p>
        </div>
    </div>
</div>