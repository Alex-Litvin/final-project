<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Add speciality</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body>

<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="local"/>

<c:import url="/view/header.jsp" charEncoding="utf-8"/>

<h1><fmt:message key="message.add_speciality"/></h1>
<div>
    <form method="post" action="${pageContext.request.contextPath}/admin/specialities_add">
        <label>
            <select name="universityId">
                <c:forEach var="university" items="${requestScope.universities}">
                    <option value="${university.id}">
                        <c:out value="${university.title}"/>
                    </option>
                </c:forEach>
            </select>
        </label><br>
        <label>
            <input type="text" required maxlength="100" name="title">
        </label><fmt:message key="message.speciality_title"/><br>
        <label>
            <input type="number" min="0" required minlength="0" name="maxStudentCount">
        </label><fmt:message key="message.max_sudent_count"/><br>
        <label>
            <input type="number" min="0" required minlength="0" name="passmark">
        </label><fmt:message key="message.passmark"/><br>
        <label>
            <select name="firstSubject">
                <c:forEach var="subject" items="${requestScope.subjects}">
                    <option>
                        <c:out value="${subject.name().toLowerCase()}"/>
                    </option>
                </c:forEach>
            </select>
        </label><br>
        <label>
            <select name="secondSubject">
                <c:forEach var="subject" items="${requestScope.subjects}">
                    <option>
                        <c:out value="${subject.name().toLowerCase()}"/>
                    </option>
                </c:forEach>
            </select>
        </label><br>
        <label>
            <select name="thirdSubject">
                <c:forEach var="subject" items="${requestScope.subjects}">
                    <option>
                        <c:out value="${subject.name().toLowerCase()}"/>
                    </option>
                </c:forEach>
            </select>
        </label><br>
        <input type="submit" name="button" value="Submit">
    </form>

    <c:if test="${not empty requestScope.specialityAdded}">
        <h2><fmt:message key="${requestScope.specialityAdded}"/></h2>
    </c:if>
    <c:if test="${not empty requestScope.specialityExists}">
        <h2><fmt:message key="${requestScope.specialityExists}"/></h2>
    </c:if>
    <c:if test="${not empty requestScope.notUniqueSubject}">
        <h2><fmt:message key="${requestScope.notUniqueSubject}"/></h2>
    </c:if>
</div>
<a href="${pageContext.request.contextPath}/view/admin/admin_menu.jsp">Menu</a>
</body>
</html>
