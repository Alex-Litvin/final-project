<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show specialities</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
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
        .button1 {padding: 10px 24px;}
    </style>
</head>
<body>

<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="local"/>

<c:import url="/view/header.jsp" charEncoding="utf-8"/>

<h1><fmt:message key="message.show_specialities_by_university"/></h1>
<div>
    <form method="post" action="${pageContext.request.contextPath}/admin/show_specialities">
        <label>
            <select name="universityId">
                <c:forEach var="university" items="${requestScope.universities}">
                    <option value="${university.id}">
                        <c:out value="${university.title}"/>
                    </option>
                </c:forEach>
            </select>
        </label>
        <p><input type="submit" class="button button1" value="Select"/></p>
    </form>
</div>
<div>
    <form method="post" action="${pageContext.request.contextPath}/admin/speciality_delete">
        <table>
            <c:if test="${!empty requestScope.specialitiesWithSubjects}">
                <tr>
                    <th>Speciality</th>
                    <th>Required subjects</th>
                    <th>Study Places</th>
                    <th>Passmark</th>
                </tr>
                <c:forEach var="speciality" items="${requestScope.specialitiesWithSubjects}">
                    <jsp:useBean id="speciality" class="ua.training.model.entity.Speciality"/>
                    <tr>
                        <td><c:out value="${speciality.title}"/></td>
                        <td><c:out value="${speciality.requiredSubject}"/></td>
                        <td><c:out value="${speciality.maxStudentCount}"/></td>
                        <td><c:out value="${speciality.passmark}"/></td>
                        <td>
                            <button type="submit" class="button button1" name="specialityId" value="${speciality.id}">
                                <fmt:message key="message.delete"/></button>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </form>
</div>
<a href="${pageContext.request.contextPath}/view/admin/admin_menu.jsp">Menu</a>
</body>
</html>
