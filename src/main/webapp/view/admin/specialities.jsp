<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Specialities</title>
</head>
<body>
<h1>Add speciality for university</h1>
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
        </label>Speciality title<br>
        <label>
            <input type="number" min="0" required minlength="0" name="maxStudentCount">
        </label>Max student count<br>
        <label>
            <input type="number" min="0" required minlength="0" name="passmark">
        </label>Passmark<br>
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
    <c:out value="${requestScope.message}"/>
    <c:out value="${requestScope.notUnique}"/>
    <c:out value="${requestScope.specialityExists}"/>
</div>
</body>
</html>
