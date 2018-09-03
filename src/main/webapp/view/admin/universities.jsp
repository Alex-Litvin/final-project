<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <form method="post" action="${pageContext.request.contextPath}/admin/universities">
        <table class="table table-striped">
            <c:if test="${!empty requestScope.universities}">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="message.id"/></th>
                    <th scope="col"><fmt:message key="message.title"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="university" items="${requestScope.universities}">
                    <jsp:useBean id="university" class="ua.training.model.entity.University"/>
                    <tr>
                        <td><c:out value="${university.id}"/></td>
                        <td><c:out value="${university.title}"/></td>
                        <td>
                            <button type="submit" class="btn btn-secondary" name="universityId" value="${university.id}">
                                <fmt:message key="message.delete"/></button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </c:if>
        </table>
    </form>
</div>

<form method="post" action="${pageContext.request.contextPath}/admin/universities">
    <div>
        <input type="text" name="title" required minlength="1" maxlength="100" placeholder="<fmt:message key="message.university_title"/>"/>
        <button class="btn btn-secondary" type="submit"><fmt:message key="message.add"/></button>
    </div>
</form>

<div class="alert alert-success" role="alert">
    <c:if test="${not empty requestScope.universityAdded}">
        <h2><fmt:message key="${requestScope.universityAdded}"/></h2>
    </c:if>
    <c:if test="${not empty requestScope.universityDeleted}">
        <h2><fmt:message key="${requestScope.universityDeleted}"/></h2>
    </c:if>
</div>
<div class="alert alert-danger" role="alert">
    <c:if test="${not empty requestScope.universityExists}">
        <h2><fmt:message key="${requestScope.universityExists}"/></h2>
    </c:if>
</div>

<a href="${pageContext.request.contextPath}/view/admin/admin_menu.jsp"><fmt:message key="message.menu"/></a>

</body>
</html>
