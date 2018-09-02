<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Exam registration</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body>
<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="local"/>

<c:import url="/view/header.jsp" charEncoding="utf-8"/>

<h1><fmt:message key="message.exam_registration"/></h1>
<div>
    <form method="post" action="${pageContext.request.contextPath}/user/add_exam">
        <label>
            <select name="subjectTitle">
                <c:forEach var="subject" items="${requestScope.subjects}">
                    <option value="${subject.name().toLowerCase()}">
                        <c:out value="${subject.name().toLowerCase()}"/>
                    </option>
                </c:forEach>
            </select>
        </label>
        <button type="submit"><fmt:message key="message.add"/></button>
    </form>
</div>

<c:if test="${not empty requestScope.examQuantity}">
    <h2><fmt:message key="${requestScope.examQuantity}"/></h2>
</c:if>
<c:if test="${not empty requestScope.examNotUnique}">
    <h2><fmt:message key="${requestScope.examNotUnique}"/></h2>
</c:if>
<c:if test="${not empty requestScope.examAdded}">
    <h2><fmt:message key="${requestScope.examAdded}"/></h2>
</c:if>

<a href="${pageContext.request.contextPath}/view/user/user_menu.jsp">Menu</a>
</body>
</html>
