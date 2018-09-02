<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User menu</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body>
<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="local"/>

<c:import url="/view/header.jsp" charEncoding="utf-8"/>

<div align="center">
    <table>
        <span>
            <div>
                <a href="${pageContext.request.contextPath}/user/exam_registration">Exam registration</a>
            </div>
        </span>
        <span>
            <div>
                <a href="${pageContext.request.contextPath}/user/universities_specialities"><fmt:message key="message.universities"/></a>
            </div>
        </span>
        <span>
            <div>
                <a href="${pageContext.request.contextPath}/user/speciality_request"><fmt:message key="message.add_speciality_request"/></a>
            </div>
        </span>
        <span>
            <div>
                <a href="${pageContext.request.contextPath}/user/speciality_rating"><fmt:message key="message.speciality_rating"/></a>
            </div>
        </span>
        <span>
            <div>

            </div>
        </span>
    </table>
</div>

</body>
</html>
