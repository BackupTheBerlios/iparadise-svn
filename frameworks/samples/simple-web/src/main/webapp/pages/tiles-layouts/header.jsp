<!-- Site logo and hedear can be placed here-->
<%@ include file="/pages/com/pdpsoft/_tags_directives.jsp" %>

<%
    //Create a comeple path of request uri with parameters set to url
    String servlet_path = (String) request.getAttribute("javax.servlet.forward.servlet_path");
    servlet_path = servlet_path + "?" + request.getQueryString();
    request.setAttribute("_page_uri", servlet_path);
%>

<%-- Save the last VALID page_uri the last_page variable in session the valid uri is the one that has a coresponfing value in
 menu.xml file. if no valid url found put home page url in it--%>
<c:set value="${pageContext.request.contextPath}" var="mycontext" scope="request"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
       background="${mycontext}/com/images/top_bg.jpg">
    <tr>
        <td colspan="3" height="5"/>
    </tr>

    <tr>
        <td width="5%">
            <%--<layout:img srcKey="icon.top.${sessionScope['active_menu']}"/>--%>
            <layout:img src="${mycontext}/com/images/si_crumb.gif" />
        </td>
        <td align="right" class="crumbs">
            <font size="3"><b>
                <layout:message key="${sessionScope['active_menu']}"/>
            </b></font>
        </td>
        <td>
            <div align="left" class="navi_header">
                <a href="#" class="navi_header">
                    <table cellpadding="0" cellspacing="0">
                        <tr>
                            <br>
                            <!--td>
                <%--<layout:link href="#" onclick="printPage()" styleClass="no-print">
                  <layout:img srcKey="icon.common.print"/>
                </layout:link>--%>
              </td-->
                            <td>&nbsp;</td>
                            <!--td>
                <%--<layout:link href="#" onclick="printPage()" styleClass="no-print">
                  <layout:message key="lbl.print"/>
                </layout:link>--%>
              </td-->
                            <td><layout:link onclick="javascript:openHelpWindow('${requestScope['_jspPageName']}');"
                                             href="#">
                                <layout:img border="0" src="${mycontext}/com/images/button_help.gif" /></layout:link></td>
                            <td>&nbsp;</td>
                            <td><layout:link onclick="javascript:openHelpWindow('${requestScope['_jspPageName']}');"
                                             href="#">
                                <layout:message key="lbl.help"/></layout:link></td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </a>
                <br/>
            </div>
        </td>
    </tr>

    <tr>
        <td colspan="3">
        </td>
    </tr>

</table>