<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="menu.jsp" %>

<form action="${pageContext.request.contextPath}/main" method="post">
<table>
<c:if test="${!empty requestScope.universities}">
    <tr>
        <th>Id</th>
        <th>Title</th>
    </tr>
    <c:forEach var="university" items="${requestScope.universities}">
        <jsp:useBean id="university" class="ua.training.model.University"/>
        <tr>
            <td><c:out value="${university.id}"/></td>
            <td><c:out value="${university.title}"/></td>
            <%--<td><a href="${pageContext.request.contextPath}/?command=editUniversity&universityId=${university.id}&universityTitle=<c:out value="${university.title}"/>">Edit</a></td>--%>
            <td><a href="${pageContext.request.contextPath}/?command=deleteUniversity&universityId=${university.id}">Delete</a></td>
        </tr>
    </c:forEach>
</c:if>
</table>
</form>

<h1>Add university</h1>
<form method="post" action="${pageContext.request.contextPath}/main">
    <label>
        <input type="text" required maxlength="100" name="title">
    </label>University title<br>
    <input type="hidden" name="command" value="addUniversity">
    <input type="submit" name="button" value="Submit">
</form>

<h1>Add speciality for university</h1>
<form method="post" action="${pageContext.request.contextPath}/main">
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
    </label>Speciality title<br>
    <label>
        <input type="number" min="0" required minlength="0" name="maxStudentCount">
    </label>Max student count<br>
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
    <input type="hidden" name="command" value="addSpeciality">
    <input type="submit" name="button" value="Submit">
</form>
<c:out value="${requestScope.message}"/>
<c:out value="${requestScope.notUnique}"/>


<h1>Show speciality by university</h1>
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
    <p><input type="submit" value="Select"></p>
</form>

<table>
    <c:forEach var="speciality" items="${requestScope.specialitiesWithSubjects}">
        <jsp:useBean id="speciality" class="ua.training.model.Speciality"/>
        <tr>
            <td>Speciality</td>
            <td>Required subjects</td>
            <td>Study Places</td>
        </tr>
        <tr>
            <td><c:out value="${speciality.title}"/></td>
            <td><c:out value="${speciality.requiredSubject}"/></td>
            <td><c:out value="${speciality.maxStudentCount}"/></td>
            <td><a href="${pageContext.request.contextPath}/?command=showApplicant&specialityId=${speciality.id}&currentPage=1&recordsPerPage=5">Show applicant</a></td>
            <td><a href="${pageContext.request.contextPath}/?command=deleteSpeciality&specialityId=${speciality.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
