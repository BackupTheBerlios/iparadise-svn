<%@ include file="_tags_directives.jsp" %>
<c:set value="${pageContext.request.contextPath}" var="mycontext" scope="request"/>

<head>
    <title><bean:message key="title.main"/></title>
    <link rel="stylesheet" href="${mycontext}/com/css/blue.css" type="text/css">
    <link rel="stylesheet" href="${mycontext}/com/css/default.css" type="text/css">
    <link rel="stylesheet" href="${mycontext}/com/css/print.css" type="text/css" media="print">
    <link rel="stylesheet" href="${mycontext}/com/css/calendar.css" type="text/css"/>
    <%-- begining of struts layout stuff --%>
    <layout:skin/>
    <script type="text/javascript" src="${mycontext}/com/js/javascript.js"></script>
    <script type="text/javascript" src="${mycontext}/com/js/application.js"></script>
    <script type="text/javascript" src="${mycontext}/com/js/colorPicker.js"></script>
    <script type="text/javascript" src="${mycontext}/com/js/swap.js"></script>
    <%-- end of struts layout stuff --%>
    <%-- begining of jsCalendar stuff -THE ORDER IS IMPORTANT! --%>
    <script type="text/javascript" src="${mycontext}/com/js/calendar.js"></script>
    <script type="text/javascript" src="${mycontext}/com/js/calendar-setup.js"></script>
    <%-- end of jsCalendar stuff --%>
</head>
