<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Universities</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body>
<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="local"/>

<c:import url="/view/header.jsp" charEncoding="utf-8"/>

<h1><fmt:message key="message.universities"/></h1>
<div>
    <form method="get" action="${pageContext.request.contextPath}/user/show_specialities">
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
    </form>
</div>
<div>
    <table class="table table-striped">
        <c:if test="${!empty requestScope.specialitiesWithSubjects}">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="message.speciality_title"/></th>
                <th scope="col"><fmt:message key="message.required_subjects"/></th>
                <th scope="col"><fmt:message key="message.study_places"/></th>
                <th scope="col"><fmt:message key="message.passmark"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="speciality" items="${requestScope.specialitiesWithSubjects}">
                <jsp:useBean id="speciality" class="ua.training.model.entity.Speciality"/>
                <tr>
                    <td><c:out value="${speciality.title}"/></td>
                    <td><c:out value="${speciality.requiredSubject}"/></td>
                    <td><c:out value="${speciality.maxStudentCount}"/></td>
                    <td><c:out value="${speciality.passmark}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </c:if>
    </table>
</div>
<a href="${pageContext.request.contextPath}/view/user/user_menu.jsp"><fmt:message key="message.menu"/></a>
</body>
</html>
