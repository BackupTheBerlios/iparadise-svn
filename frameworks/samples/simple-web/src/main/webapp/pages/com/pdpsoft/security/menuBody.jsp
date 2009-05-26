<%@ include file="/pages/com/pdpsoft/_tags_directives.jsp" %>

<c:set value="${pageContext.request.contextPath}" var="mycontext" scope="request"/>
<img src="${mycontext}/com/images/logo_small.gif" alt="logo"/>
<br/>
<layout:treeview styleClass="treeView" autoIncrement="false" name="treeView" width="100%" expandedLevelsAtFirst="1">

  <layout:menuItem key="menu.home" image="i_root.gif"
                   link="${mycontext}/Welcome.do?&amp;select_tab_id=none&amp;select_tab_id=none&amp;_menu=menu.home"
                   target="mainFrame">

    <layout:menuItem
        key=' ${sessionScope.USER_SESSION_KEY.userData["firstname"]} ${sessionScope.USER_SESSION_KEY.userData["lastname"]}'
        image="i_changePassword.gif"
        link="${mycontext}/ChangePassword/EditForm.do?dispatch=Show&amp;mode=inspect&amp;select_tab_id=none"
        target="mainFrame"/>

    <layout:policy policy="Security Manager">
      <layout:menuItem key="menu.configuration" image="configurationMain.gif"
                       link="${mycontext}/Overview.do?overviewMode=configuration&amp;select_tab_id=none&amp;_menu=menu.configuration"
                       target="mainFrame">
        <layout:policy policy="Security Manager">
          <layout:menuItem key="menu.securityManager" image="securityManager.gif"
                           link="${mycontext}/Summery.do?summeryMode=securityManager&amp;select_tab_id=none&amp;_menu=menu.securityManager"
                           target="mainFrame">
            <layout:policy policy="All Users List">
              <layout:menuItem key="menu.securityManager.users" image="users.gif"
                               link="${mycontext}/AppUser/SearchForm.do?dispatch=Show&amp;select_tab_id=none&amp;_menu=menu.securityManager.users"
                               target="mainFrame"/>
            </layout:policy>
            <layout:policy policy="All Roles List">
              <layout:menuItem key="menu.securityManager.roles" image="roles.gif"
                               link="${mycontext}/AppRole/SearchForm.do?dispatch=Show&amp;select_tab_id=none&amp;_menu=menu.securityManager.roles"
                               target="mainFrame"/>
            </layout:policy>
          </layout:menuItem>
        </layout:policy>

        <layout:policy policy="Action Report Manager">
          <layout:menuItem key="menu.actionReport" image="actionReportMain.gif"
                           link="${mycontext}/Summery.do?summeryMode=actionReport&amp;select_tab_id=none&amp;_menu=menu.actionReport"
                           target="mainFrame">
            <layout:policy policy="Action Report List">
              <layout:menuItem key="menu.actionReport.actionReports" image="actionReports.gif"
                               link="${mycontext}/ActionReport/SearchForm.do?dispatch=Show&amp;select_tab_id=none&amp;_menu=menu.actionReport.actionReports"
                               target="mainFrame"/>
            </layout:policy>
          </layout:menuItem>
        </layout:policy>

      </layout:menuItem>
    </layout:policy>

  </layout:menuItem>

  <layout:menuItem key="menu.logoff" image="i_signout.gif"
                   link="${mycontext}/Logoff.do?dispatch=Logoff&amp;select_tab_id=none"
                   target="_parent"/>
</layout:treeview>