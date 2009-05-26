<%@ include file="/pages/com/pdpsoft/_tags_directives.jsp" %>

<html>
<head>
    <title><bean:message key="title.main"/></title>
    <script language="javascript" type="text/javascript">
        if (this.top != self) this.top.location.href = location.href;
    </script>
</head>
<c:set value="${pageContext.request.contextPath}" var="mycontext" scope="request"/>
<frameset cols="*,20%" frameborder="yes" border="0" framespacing="0">
  <frame src="${mycontext}/pages/com/pdpsoft/security/welcome.jsp" name="mainFrame" id="mainFrame" scrolling="yes" noresize/>
  <frame src="${mycontext}/pages/com/pdpsoft/security/menu.jsp" name="leftFrame" id="leftFrame" noresize/>
</frameset>
<noframes>
    <body>
    <h2>Frames support is required</h2>
    <p>Sorry, your browser does not support frames, but it is required by Application.</p>
    <p>Please, get another browser to gain access to Site.</p>
    </body>
</noframes>
</html>