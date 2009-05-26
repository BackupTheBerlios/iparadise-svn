<%! private List<String> sort(Enumeration parameterEnumration) {
    List<String> list = new ArrayList<String>();
    while (parameterEnumration.hasMoreElements()) {
        list.add((String) parameterEnumration.nextElement());
    }
    Collections.sort(list);
    return list;
}%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.List" %>
<%@ include file="_tags_directives.jsp" %>

<P>
    <%


        boolean showRequestParam = true;
        boolean showRequestAtribute = true;
        boolean showSessionAttribute = true;
        boolean showApplicationAttribute = false;
        List<String> paramsName;


    %>
    <% if (showRequestParam) {%>
<table class="statusPage" border="1">
    <tr>
        <th colspan="2"> Request Parameters</th>
    </tr>
    <%
        Enumeration parameterEnumration = request.getParameterNames();
        paramsName = sort(parameterEnumration);
        for (String paramName : paramsName) {
    %>
    <tr>
        <th class="statusPage"><%= paramName%>
        </th>
        <td><%= request.getParameter(paramName) %>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%} %>

<P>
    <% if (showRequestAtribute) {%>
<table class="statusPage" border="1">
    <tr>
        <th colspan="2"> Request Attributes</th>
    </tr>
    <%
        Enumeration attributeEnum = request.getAttributeNames();
        paramsName = sort(attributeEnum);
        for (String paramName : paramsName) {
    %>
    <tr>
        <th class="statusPage"><%= paramName%>
        </th>
        <td><%= request.getAttribute(paramName).toString().replaceAll(",", "<br>") %>
        </td>
    </tr>

    <%
        }
    %>
</table>
<%}%>
<%if (showSessionAttribute) {%>
<table class="statusPage" border="1">
    <tr>
        <th colspan="2"> Session Attributes</th>
    </tr>
    <%
        Enumeration appAttributeEnum = session.getAttributeNames();
        paramsName = sort(appAttributeEnum);
        for (String paramName : paramsName) {
    %>
    <tr>
        <th class="statusPage"><%= paramName%>
        </th>
        <td><%= session.getAttribute(paramName) %>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%}%>

<P>

    <%if (showApplicationAttribute) {%>
<table class="statusPage" border="1">
    <tr>
        <th colspan="2"> Application Attributes</th>
    </tr>
    <%
        Enumeration appAttributeEnum = application.getAttributeNames();
        paramsName = sort(appAttributeEnum);
        for (String paramName : paramsName) {
    %>
    <tr>
        <th class="statusPage"><%= paramName%>
        </th>
        <td><%= application.getAttribute(paramName).toString().replaceAll(",", "<br>").replaceAll("/", "/<br>") %>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%}%>
