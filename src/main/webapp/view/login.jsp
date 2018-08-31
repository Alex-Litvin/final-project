<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"--%>
          <%--integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">--%>
</head>
<body>
<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="local"/>

<c:import url="/view/admin/adminHeader.jsp" charEncoding="utf-8"/>

<div align="center">
    <form method="post" action="${pageContext.request.contextPath}/login_confirm">
        <table>
            <tr>
                <td><input type="email" name="email" placeholder="<fmt:message key="message.email"/>" size="25"></td>
            </tr>
            <tr>
                <td><input type="password" name="password" placeholder="<fmt:message key="message.password"/>" size="25"></td>
            </tr>
            <tr>
                <td>
                    <input type="hidden" name="command" value="login">
                    <button type="submit"><fmt:message key="message.login"/></button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
