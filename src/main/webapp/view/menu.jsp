<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<fmt:setLocale value="${requestScope.locale}"/>
<fmt:setBundle basename="local"/>
<ul>
    <li><a href="${pageContext.request.contextPath}/?command=home"><fmt:message key="menu_home"/></a></li>
    <li><a href="${pageContext.request.contextPath}/?command=logout"><fmt:message key="logout"/></a></li>
    <li><a href="${pageContext.request.contextPath}/?command=showUniversities"><fmt:message key="menu_university"/></a></li>
    <li><a href="${pageContext.request.contextPath}/?locale=ua&command=localization">UA</a></li>
    <li><a href="${pageContext.request.contextPath}/?locale=en&command=localization">EN</a></li>
</ul>

</body>
</html>
