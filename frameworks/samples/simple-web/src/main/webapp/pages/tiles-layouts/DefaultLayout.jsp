<%@ include file="/pages/com/pdpsoft/_tags_directives.jsp" %>

<%@ include file="/pages/com/pdpsoft/_request_attribute_setter.jsp" %>
<%--@todo locale seems to be deprecated and must be replaced with lang--%>
<c:set value="${pageContext.request.contextPath}" var="mycontext" scope="request"/>
<html:html lang="true">
    <%@ include file="/pages/com/pdpsoft/_headinfo.jsp" %>
    <body class="rightToLeft">
    <!--------------------------start of the table---------------------------------->
    <table width="100%" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td valign="top" width="100%" height="500">
                <table width="100%" align="center" cellpadding="0" cellspacing="0" border="0">
                    <!-- The patter is one td in each tr, the td itself may have table too-->
                    <tr>
                        <td colspan="2"><tiles:insert attribute="header"/></td>
                    </tr>
                    <!-- menu is responsible for tr td pattern-->
                    <tr>
                        <td height="12" colspan="2"><img
                                src="${mycontext}/com/images/temp_space_to_content.jpg"
                                width="784" height="12"></td>
                    </tr>
                    <tr>
                        <td width="1%">&nbsp;</td>
                        <td>
                            <center><html:errors/></center>
                            <tiles:insert attribute="body-content"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <%--<tiles:insert attribute="footer"/>--%>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    </body>
</html:html>