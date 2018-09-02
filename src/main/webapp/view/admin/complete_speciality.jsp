<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Complete speciality</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body>

<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="local"/>

<c:import url="/view/header.jsp" charEncoding="utf-8"/>

<h1><fmt:message key="message.complete_speciality"/></h1>
<div>
    <form method="get" action="${pageContext.request.contextPath}/admin/show_specialities_by_university">
        <label>
            <select name="universityId">
                <c:forEach var="university" items="${requestScope.universities}">
                    <option value="${university.id}">
                        <c:out value="${university.title}"/>
                    </option>
                </c:forEach>
            </select>
        </label>
        <button type="submit"><fmt:message key="message.select"/></button>
    </form><br>
</div>

<div>
    <form method="post" action="${pageContext.request.contextPath}/admin/complete_speciality_registration">
        <input type="hidden" name="universityId" value="${requestScope.university.id}">
        <label>
            <c:out value="${requestScope.university.title}"/>
        </label><br>
        <label>
            <select name="specialityId">
                <c:forEach var="speciality" items="${requestScope.specialitiesByUniversity}">
                    <option value="${speciality.id}">
                        <c:out value="${speciality.title}"/>
                    </option>
                </c:forEach>
            </select>
        </label>
        <button type="submit"><fmt:message key="message.select"/></button>
    </form>
    <c:if test="${not empty requestScope.specialityClosed}">
        <h2><fmt:message key="${requestScope.specialityClosed}"/></h2>
    </c:if>
    <c:if test="${param.error != null}">
        <c:if test="${param.error == 'specialityNotSelected'}">
            <fmt:message key="error_speciality_not_selected"/>
        </c:if>
        <c:if test="${param.error == 'universityNotSelected'}">
            <fmt:message key="error_university_not_selected"/>
        </c:if>
    </c:if><br>
</div>
<a href="${pageContext.request.contextPath}/view/admin/admin_menu.jsp">Menu</a>
</body>
</html>
