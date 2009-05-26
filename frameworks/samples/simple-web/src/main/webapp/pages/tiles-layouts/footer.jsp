<!--footer-->
<%@ include file="/pages/com/pdpsoft/_tags_directives.jsp" %>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td height="17"></td>
    </tr>
    <tr>
        <td><logic:present name="USER_SESSION_KEY">
            <layout:message key="lbl.account.selected" styleClass="font_white_9px"/>:
            <span class="font_white_9px_bold">
                <bean:write name="USER_SESSION_KEY" property="userSelectedItems(select_account_name)"/>
            </span>
        </logic:present>
        </td>
    </tr>
</table>