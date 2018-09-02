<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Speciality request</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body>
<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="local"/>

<c:import url="/view/header.jsp" charEncoding="utf-8"/>

<div>
    <form method="post" action="${pageContext.request.contextPath}/user/add_speciality_request">
        <label>
            <select name="specialityId">
                <c:forEach var="speciality" items="${requestScope.allAvailableSpecialities}">
                    <option value="${speciality.id}">
                        <c:out value="${speciality.title} "/>
                        <c:out value="${speciality.universityTitle}"/>
                    </option>
                </c:forEach>
            </select>
        </label>
        <input type="submit" name="button" value="Submit">
    </form>
</div>

<c:if test="${param.error != null}">
    <c:if test="${param.error == 'alreadyRegistered'}">
        <fmt:message key="error_speciality_already_registered"/>
    </c:if>
    <c:if test="${param.error == 'maxCountRegistration'}">
        <fmt:message key="error_max_count_registration"/>
    </c:if>
</c:if>
<c:if test="${param.message != null}">
    <c:if test="${param.message == 'successRegistration'}">
        <fmt:message key="message.success_registration"/>
    </c:if>
</c:if>

<a href="${pageContext.request.contextPath}/view/user/user_menu.jsp">Menu</a>
</body>
</html>
