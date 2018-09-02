<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body>
<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="local"/>

<c:import url="/view/header.jsp" charEncoding="utf-8"/>

<h1><fmt:message key="message.registration"/></h1>
<div align="center">
    <form method="post" action="${pageContext.request.contextPath}/registration_confirm">
        <table>
            <tr>
                <td>
                    <input type="text" name="firstName" maxlength="50"
                           placeholder="<fmt:message key="message.first_name"/>" size="25">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="middleName" maxlength="50"
                           placeholder="<fmt:message key="message.middle_name"/>" size="25">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="secondName" maxlength="50"
                           placeholder="<fmt:message key="message.second_name"/>" size="25">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="email" name="email" maxlength="100" pattern="^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\.)+[a-z]{2,6}$"
                           placeholder="<fmt:message key="message.email"/>" size="25">
                </td>
            </tr>
            <tr>
                <td><input type="password" name="password" maxlength="100"
                           placeholder="<fmt:message key="message.password"/>" size="25">
                </td>
            </tr>
            <tr>
                <td>
                    <button type="submit"><fmt:message key="message.register"/></button>
                </td>
            </tr>
        </table>
    </form>
</div>

<c:if test="${param.error != null}">
    <c:if test="${param.error == 'emptyField'}">
        <fmt:message key="error_empty_field"/>
    </c:if>
    <c:if test="${param.error == 'userExists'}">
        <fmt:message key="error_user_exists"/>
    </c:if>
</c:if><br>

<a href="${pageContext.request.contextPath}/login">Log In</a><br>

</body>
</html>