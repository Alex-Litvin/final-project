<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Universities</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        .button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }
        .button1 {padding: 4px 15px;}
    </style>
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
        <button class="button button1" type="submit"><fmt:message key="message.select"/></button>
    </form>
</div>
<div>
    <table>
        <c:if test="${!empty requestScope.specialitiesWithSubjects}">
            <tr>
                <th><fmt:message key="message.speciality_title"/></th>
                <th><fmt:message key="message.reqiured_subjects"/></th>
                <th><fmt:message key="message.study_places"/></th>
                <th><fmt:message key="message.passmark"/></th>
            </tr>
            <c:forEach var="speciality" items="${requestScope.specialitiesWithSubjects}">
                <jsp:useBean id="speciality" class="ua.training.model.entity.Speciality"/>
                <tr>
                    <td><c:out value="${speciality.title}"/></td>
                    <td><c:out value="${speciality.requiredSubject}"/></td>
                    <td><c:out value="${speciality.maxStudentCount}"/></td>
                    <td><c:out value="${speciality.passmark}"/></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
<a href="${pageContext.request.contextPath}/view/user/user_menu.jsp">Menu</a>
</body>
</html>
