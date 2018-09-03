<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add exam mark</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body>

<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="local"/>

<c:import url="/view/header.jsp" charEncoding="utf-8"/>

<div>
    <form method="post" action="${pageContext.request.contextPath}/admin/show_user_exams">
        <label>
            <select name="userId">
                <c:forEach var="user" items="${requestScope.users}">
                    <jsp:useBean id="user" class="ua.training.model.entity.User"/>
                    <option value="${user.id}">
                        <c:out value="${user.firstName} "/>
                        <c:out value="${user.middleName} "/>
                        <c:out value="${user.secondName}"/>
                    </option>
                </c:forEach>
            </select>
        </label>
        <button type="submit"><fmt:message key="message.select"/></button>
    </form>
</div>

<h1><fmt:message key="message.set_exam_mark"/></h1>
<div>
    <form method="post" action="${pageContext.request.contextPath}/admin/add_exam_mark">
        <input type="hidden" name="userId" value="${requestScope.user.id}">
        <label>
            <c:out value="${user.firstName} "/>
            <c:out value="${user.middleName} "/>
            <c:out value="${user.secondName}"/>
        </label><br>
        <label>
            <select name="examTitle">
                <c:forEach var="exam" items="${requestScope.userExams}">
                    <jsp:useBean id="exam" class="ua.training.model.entity.Exam"/>
                    <option value="${exam.title}">
                        <c:out value="${exam.title}"/>
                    </option>
                </c:forEach>
            </select>
        </label>
        <label>
            <input type="number" min="0" max="100" name="mark" placeholder="<fmt:message key="message.mark"/>" value="0">
        </label>
        <button type="submit"><fmt:message key="message.add"/></button>
    </form>
    <div class="alert alert-success" role="alert">
        <c:if test="${not empty requestScope.examMarkAdded}">
            <h2><fmt:message key="${requestScope.examMarkAdded}"/></h2>
        </c:if>
    </div>
    <div class="alert alert-danger" role="alert">
        <c:if test="${param.error != null}">
            <c:if test="${param.error == 'markNotValid'}">
                <h2><fmt:message key="error_mark_not_valid"/></h2>
            </c:if>
            <c:if test="${param.error == 'examNotSelected'}">
                <h2><fmt:message key="error_exam_not_selected"/></h2>
            </c:if>
            <c:if test="${param.error == 'userNotSelected'}">
                <h2><fmt:message key="error_user_not_selected"/></h2>
            </c:if>
        </c:if><br>
    </div>
</div>
<a href="${pageContext.request.contextPath}/view/admin/admin_menu.jsp"><fmt:message key="message.menu"/></a>
</body>
</html>
