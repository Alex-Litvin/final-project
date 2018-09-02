<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
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
                <td><input type="email" name="email" placeholder="<fmt:message key="message.email"/>" size="25"></td>
            </tr>
            <tr>
                <td><input type="password" name="password" placeholder="<fmt:message key="message.password"/>" size="25"></td>
            </tr>
            <tr>
                <td>
                    <button type="submit"><fmt:message key="message.login"/></button>
                </td>
            </tr>
        </table>
    </form>
</div>

<div class="alert alert-danger" role="alert">
    <c:if test="${param.error != null}">
        <c:if test="${param.error == 'email'}">
            <fmt:message key="error_email"/>
        </c:if>
        <c:if test="${param.error == 'password'}">
            <fmt:message key="error_password"/>
        </c:if>
        <c:if test="${param.error == 'authError'}">
            <fmt:message key="error_authentication"/>
        </c:if>
    </c:if><br>
</div>

<a href="${pageContext.request.contextPath}/registration">Registration</a><br>
</body>
</html>
