<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title><s:text name="HelloWorld.message"/></title>
</head>

<body>
<h2><s:property value="message"/></h2>

<h3>Message</h3>
<ul>
    <li>
    	<s:form action="HelloWorld">
    		<s:textfield key="message" />
    	</s:form>
    </li>
</ul>

</body>
</html>
