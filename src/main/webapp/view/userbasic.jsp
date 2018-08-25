<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User page</title>
</head>
<body>
<%@include file="menu.jsp" %>

<h1>Personal information</h1>
<div>
    <c:set var="user" value="${sessionScope.user}"/>
    <jsp:useBean id="user" class="ua.training.model.User"/>
    <td><c:out value="${user.firstName}"/></td>
    <td><c:out value="${user.middleName}"/></td>
    <td><c:out value="${user.secondName}"/></td>
    <td><c:out value="${user.email}"/></td>
    <td><c:out value="${user.mobile}"/></td>
    <td><c:out value="${user.exams}"/></td>
</div>

<h1>Registration for exams</h1>
<div>
    <form method="post" action="${pageContext.request.contextPath}/main">
        <label>
            <select name="subjectTitle">
                <option value="COMPUTER_SCIENCE">Computer science</option>
                <option value="GEOGRAPHY">Geography</option>
                <option value="HISTORY">History</option>
                <option value="UKRAINIAN_LANGUAGE">Ukrainian language</option>
                <option value="BIOLOGY">Biology</option>
                <option value="CHEMISTRY">Chemistry</option>
                <option value="ENGLISH">English</option>
                <option value="PHYSICS">Physics</option>
            </select>
        </label><br>
        <input type="hidden" name="command" value="registerForExam">
        <input type="submit" name="button" value="Submit">
    </form>
</div>

<c:out value="${requestScope.examAdded}"/>
<c:out value="${requestScope.examNotUnique}"/>
<c:out value="${requestScope.examCount}"/>


<%--<a href="${pageContext.request.contextPath}/?command=showUniversities">Show universities</a>--%>

<h1>List universities</h1>
<form method="get" action="${pageContext.request.contextPath}/main">
    <input type="hidden" name="command" value="showUniversities">
    <input type="submit" name="button" value="Show">
    <table>
        <tr>
            <td>Title</td>
        </tr>
        <c:forEach var="university" items="${requestScope.universities}">
            <jsp:useBean id="university" class="ua.training.model.University"/>
            <tr>
                <td><c:out value="${university.title}"/></td>
            </tr>
        </c:forEach>
    </table>
</form>

<h1>Show specialities by universities</h1>
<div>
    <form>
        <input type="hidden" name="command" value="showUniversities">
        <p><input type="submit" value="Reload"></p>
    </form>
</div>
<div>
    <form method="post" action="${pageContext.request.contextPath}/main">
        <label>
            <select name="universityId">
                <c:forEach var="university" items="${requestScope.universities}">
                    <option value="${university.id}">
                        <c:out value="${university.title}"/>
                    </option>
                </c:forEach>
            </select>
        </label>
        <input type="hidden" name="command" value="showDepartmentCommand">
        <p><input type="submit" value="Show"></p>
    </form>
</div>
<div>
    <table>
        <tr>
            <th>Speciality</th>
            <th>Max student count</th>
            <th>Required subject</th>
        </tr>
        <c:forEach var="speciality" items="${requestScope.specialitiesWithSubjects}">
            <jsp:useBean id="speciality" class="ua.training.model.Speciality"/>
            <tr>
                <td><c:out value="${speciality.title}"/></td>
                <td><c:out value="${speciality.maxStudentCount}"/></td>
                <td><c:out value="${speciality.requiredSubject}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>

<h1>Submit a request for speciality</h1>
<div>
    <form>
        <input type="hidden" name="command" value="showAvailableSpeciality">
        <p><input type="submit" value="Reload"></p>
    </form>
</div>
<div>
    <form method="post" action="${pageContext.request.contextPath}/main">
        <label>
            <select name="specialityId">
                <c:forEach var="speciality" items="${requestScope.allAvailableSpecialities}">
                    <option value="${speciality.id}">
                        <c:out value="${speciality.title} "/><c:out value="${speciality.universityTitle}"/>
                    </option>
                </c:forEach>
            </select>
        </label>
        <input type="hidden" name="command" value="requestForSpeciality">
        <input type="submit" name="button" value="Submit">
    </form>
</div>
</body>
</html>