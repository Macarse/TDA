<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<link rel="stylesheet" href="css/layout.css" type="text/css" media="screen, projection"> 
</head>
<body>
<div class="main-container">
	<div class="header-container">
		<tiles:insertAttribute name="header" />
	</div>
	<div class="menu-container">
		<tiles:insertAttribute name="menu" />
	</div>
	<div class="body-container">
		<tiles:insertAttribute name="body" />
	</div>
	<div class="footer-container">
		<tiles:insertAttribute name="footer" />
	</div>
</div>
</body>
</html>