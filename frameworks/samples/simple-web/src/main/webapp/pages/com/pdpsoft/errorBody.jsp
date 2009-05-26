<%@ page import="javax.servlet.ServletException" %>
<%@ page language="java" isErrorPage="true" %>
<%@ include file="_tags_directives.jsp" %>
<%
    //This code could not be place in side tags! so put it in request scope
    if ((exception != null) && (exception instanceof ServletException)) {
        Throwable cause = ((ServletException) exception).getRootCause();
        if (cause != null) {
            StringBuffer rootCause = new StringBuffer();
            StackTraceElement[] element = cause.getStackTrace();
            for (StackTraceElement stackTraceElement : element) {
                rootCause.append(stackTraceElement);
                rootCause.append("<br>");
            }
            request.setAttribute("rootCause", rootCause);
        }
    }
%>
<br/><br/>
<layout:grid styleClass="error">
    <layout:row>
        <layout:column>
            <layout:img srcKey="icon.alert"/>
        </layout:column>
        <layout:column>
            <layout:message key="errors.errorpage.erroraccured"/>
            <layout:ol>
                <layout:li key="errors.errorpage.comment1"/>
                <layout:li key="errors.errorpage.comment2"/>
                <layout:li key="errors.errorpage.comment3"/>
                <layout:li key="errors.errorpage.comment4"/>
            </layout:ol>
        </layout:column>
    </layout:row>
</layout:grid>

<tags:showhide title="errors.general" display="none">
    <layout:grid cols="1">
        <layout:row>
            <b>Error Message:</b> ${requestScope['javax.servlet.error.message']}
        </layout:row>
        <layout:row>
            <b>Exception Type:</b> ${requestScope['javax.servlet.error.exception_type']}
        </layout:row>
        <layout:row>
            <b>Status Code:</b> ${requestScope['javax.servlet.error.status_code']}
        </layout:row>
        <layout:row>
            <b>Exception:</b> ${pageContext.exception}
        </layout:row>
        <layout:row>
            <b>Root Cause Trace:</b><br/>
            <blockquote>${rootCause}
        </layout:row>
        <layout:row>
            <b>Stack Trace:</b> <br/>
            <blockquote>
                <c:forEach items="${pageContext.exception.stackTrace}" var="listitem">
                    ${listitem}
                </c:forEach>
            </blockquote>
        </layout:row>
    </layout:grid>
</tags:showhide>
