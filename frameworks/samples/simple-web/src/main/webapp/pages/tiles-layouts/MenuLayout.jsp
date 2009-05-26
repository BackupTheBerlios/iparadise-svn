<%@ include file="/pages/com/pdpsoft/_tags_directives.jsp" %>

<%@ include file="/pages/com/pdpsoft/_request_attribute_setter.jsp" %>
<%--@todo locale seems to be deprecated and must be replaced with lang--%>
<html:html lang="true">
    <%@ include file="/pages/com/pdpsoft/_headinfo.jsp" %>
    <body class="treeView">
    <!--------------------------start of the table---------------------------------->
    <table width="100%" align="center" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <td>
                <tiles:insert attribute="body-content"/>
            </td>
        </tr>
    </table>
    </body>
</html:html>