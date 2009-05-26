<%--****************************************************************************
* File: server\resources\body\security\LogonBody.jsp
*
* Copyright:   (c) Peykasa Ltd. 2008
*
* Maintenance/Change History:
*
* Ver.  Date        Author            Description
*------------------------------------------------------------------------------
* 1.0  13/05/2008   Saman Shojatalab  Created.
****************************************************************************--%>
<%@ include file="/pages/com/pdpsoft/_tags_directives.jsp" %>
<c:set value="${pageContext.request.contextPath}" var="mycontext" scope="request"/>
<center dir="rtl">

  <style>
    .signInFix {
      margin-left: 0px !important;
      margin-right: 182px !important;
      margin-right: 0px;
      margin-left: 30px;
    }
  </style>

  <p/>
  <layout:form  action="/iParadise-security.do?reqCode=signin" key="frm.login" styleClass="logonform" >
    <layout:row>
      <layout:message key="msg.logon" styleClass="logonmsg"/></layout:row>
    <layout:line/>
    <layout:text key="lbl.username" property="systemUserEntity.userName" isRequired="true" mode="E,E,I" />
    <layout:password key="lbl.password" property="systemUserEntity.password" isRequired="true" mode="E,E,H" redisplay="true"/>

        <pdp:dateText key="lbl.username" styleId="myDate"
                       property="myDate" style="width:80" 
                       styleClass="clsText" isRequired="true"  mode="e,e,r" maxlength="10"/>
       <calendar:solar_date name="myDate" srcKey="icons.date" altKey="date.altKey"/>
       <pdp:dateLabel property="myDateLabel" />

      <layout:formActions>
      <div class="signInFix">
          <layout:image src="${mycontext}/com/images/button_signin.jpg" styleClass="inputImage"/>
      </div>
    </layout:formActions>
  </layout:form>

  <script type="text/javascript">
    document.getElementById('username').focus();
  </script>
</center>