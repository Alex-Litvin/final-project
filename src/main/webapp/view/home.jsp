<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="local"/>

<c:import url="/view/header.jsp" charEncoding="utf-8"/>

<div align="center">
    <form method="post" action="${pageContext.request.contextPath}/login_confirm">
        <table>
            <tr>
                <td align="center">
                    <a href="login"><fmt:message key="message.login"/></a>
                </td>
            </tr>
            <tr>
                <td align="center">
                    <a href="registration"><fmt:message key="message.registration"/></a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
