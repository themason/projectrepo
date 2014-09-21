<%@page language="java" contentType="text/html"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@page session="true"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Farming Simulator 2013 Career Save game properties</title>
	</head>
	<body>
		<h1>Career Savegame Properties</h1>
		<form action="upload" method="post" enctype="multipart/form-data">
	    	<input type="file" name="file" />
	    	<input type="submit" value="upload"/>
		</form>
	</body>
</html>