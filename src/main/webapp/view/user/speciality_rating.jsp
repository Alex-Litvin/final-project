<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Speciality rating</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body>
<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="local"/>

<c:import url="/view/header.jsp" charEncoding="utf-8"/>

<h1><fmt:message key="message.show_speciality_rating"/></h1>
<div>
    <form method="post" action="${pageContext.request.contextPath}/user/show_rating">
        <label>
            <select name="specialityId">
                <c:forEach var="speciality" items="${requestScope.specialityRequests}">
                    <option value="${speciality.id}">
                        <c:out value="${speciality.title} "/><c:out value="${speciality.universityTitle}"/>
                    </option>
                </c:forEach>
            </select>
        </label>
        <button type="submit"><fmt:message key="message.select"/></button>
    </form>
</div>
<div>
    <table>
        <tr>
            <th>First name</th>
            <th>Middle name</th>
            <th>Last name</th>
            <th>University</th>
            <th>Speciality</th>
            <th>Max student count</th>
            <th>Passmark</th>
            <c:forEach var="specialityResultDto" items="${requestScope.specialityResultDtos}" begin="0" end="0">
                <jsp:useBean id="specialityResultDto" class="ua.training.model.entity.SpecialityResultDto"/>
                <c:forEach var="exam" items="${specialityResultDto.userExams}">
                    <th><c:out value="${exam.title}"/></th>
                </c:forEach>
            </c:forEach>
            <th>Total mark</th>
            <th>Enter status</th>
        </tr>
        <c:forEach var="specialityResultDto" items="${requestScope.specialityResultDtos}">
            <tr>
                <td><c:out value="${specialityResultDto.firstName}"/></td>
                <td><c:out value="${specialityResultDto.middleName}"/></td>
                <td><c:out value="${specialityResultDto.secondName}"/></td>
                <td><c:out value="${specialityResultDto.universityTitle}"/></td>
                <td><c:out value="${specialityResultDto.specialityTitle}"/></td>
                <td><c:out value="${specialityResultDto.maxStudentCount}"/></td>
                <td><c:out value="${specialityResultDto.specialityPassmark}"/></td>
                <c:forEach var="exam" items="${specialityResultDto.userExams}">
                    <td><c:out value="${exam.mark}"/></td>
                </c:forEach>
                <td><c:out value="${specialityResultDto.totalUserMark}"/></td>
                <td><c:out value="${specialityResultDto.status.name().toLowerCase()}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
<a href="${pageContext.request.contextPath}/view/user/user_menu.jsp">Menu</a>
</body>
</html>
