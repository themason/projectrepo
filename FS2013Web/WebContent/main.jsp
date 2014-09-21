<%@page language="java" contentType="text/html"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@page session="true" 
	import="java.util.*, java.io.*, java.math.BigInteger, javax.xml.bind.*, fs2013.*"%>
<html>
	<head>
		<title>Farming Simulator 2013 Career Save game properties</title>
	</head>
	<body>
		<h1>properties</h1>
		<% // Scriptlet 1: check whether the savegamePropertiesList is ready
			@SuppressWarnings("unchecked")
			Map<String, String> savegamePropertiesList = 
				(Map<String, String>)session.getAttribute("careerSavegameProperties.list");
			
		if (savegamePropertiesList == null) {
			response.sendRedirect("savegame");
		} else {
		 %>
	    	<form name="updateForm" action="savegame" method="POST">
	    	 	<input type="hidden" name="doThis" value="update">
	    	 	Property:
		    	<select name="property"> 
				<%  
				for (String propertyName : savegamePropertiesList.keySet()) {
					out.println("<option>"+propertyName+"</option>"); 
				}
				%>
				</select>
			<input type="text" name="propertyValue">
				
			<input type="submit" value="Update properties">
	    	</form>
	    	<form name="uploadForm" action="savegame" method="POST">
	    		<input type="hidden" name="doThis" value="upload">
	    		<input type="submit" value="Upload new file"/>
	    	</form>
	    	
  	    	<table border="1"> 
   				<tr><td>MONEY</td><td><%=savegamePropertiesList.get("money")%></td></tr>  
   				<tr><td>LOAN</td><td><%=savegamePropertiesList.get("loan")%></td></tr> 
   			</table>
   			<p/>
   			<%
   			String careerSavegameXmlOutput = (String)session.getAttribute("careerSavegame.xml");
   			if (careerSavegameXmlOutput != null) {
   					out.println("\""+careerSavegameXmlOutput.replace("&","&amp;").replace("<", "&amp;lt;").replace(">", "&amp;gt;").replace("\"", "&amp;quot;"));
   			}
		 }%>
		 
		 <p/>
	</body>
</html>