<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>

<%@ attribute name="title" required="true" %>
<%@ attribute name="styleClass" %>
<%@ attribute name="display" required="true" %>

<%--keep the panel open if its form was submited--%>
<c:if test="${param.dispatch=='RunForm'}">
  <c:set var="display" value=""/>
  <%--change the style of grides by changing the background of header--%>
  <style type="text/css">
    th.list1, table.report1 thead tr th, th.numberList1 {
      background-image: url( ../../${sessionScope.ImagePath}/backgroundheaderfilteron.jpg );
    }
  </style>
</c:if>
<c:if test="${requestScope.search_restored=='true'}">
  <c:set var="display" value=""/>
  <%--change the style of grides by changing the background of header--%>
  <style type="text/css">
    th.list1, table.report1 thead tr th, th.numberList1 {
      background-image: url( ../../${sessionScope.ImagePath}/backgroundheaderfilteron.jpg );
    }
  </style>
</c:if>

<c:if test="${styleClass==null}">
  <c:set var="styleClass" value="showhide"/>
</c:if>
<table border="0" class="${styleClass}" cellpadding="0" cellspacing="0">
  <tr>
    <td align="right" nowrap="nowrap">
      <a href="#" onclick="javascript:ReverseContentDisplay('${title}', '${sessionScope.ImagePath}',
            '<bean:message key="lbl.toggle.show"/>', '<bean:message key="lbl.toggle.hide"/>')">
        <c:choose>
          <c:when test="${display == 'none'}">
            <span id="${title}Message" style="vertical-align:middle;"><bean:message
                                key="lbl.toggle.show"/></span>
            <layout:img src="${sessionScope.ImagePath}/show_layer.gif" border="0" align="middle"
                        styleId="${title}Image"/>
          </c:when>          
          <c:otherwise>
            <span id="${title}Message" style="vertical-align:middle;"><bean:message
                                key="lbl.toggle.hide"/></span>
            <layout:img src="${sessionScope.ImagePath}/hide_layer.gif" border="0" align="middle"
                        styleId="${title}Image"/>
          </c:otherwise>
        </c:choose>
      </a>
    </td>
    <td style="background-image: url( ../../${sessionScope.ImagePath}/backgroundheader.jpg )" width="5%"></td>
    <td style="background-image: url( ../../${sessionScope.ImagePath}/backgroundheader.jpg )" nowrap="nowrap"><b>
      <bean:message key="${title}"/></b></td>
    <td style="background-image: url( ../../${sessionScope.ImagePath}/backgroundheader.jpg )" width="90%"></td>
  </tr>
  <tr><td colspan="4">
    <div id="${title}" style="display:${display};">
      <table width="100%"><tr><td colspan="2">
        <jsp:doBody/>
      </td></tr></table>
    </div>
  </td>
  </tr>
</table>