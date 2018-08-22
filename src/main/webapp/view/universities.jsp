<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
<c:if test="${!empty requestScope.universities}">
    <tr>
        <th>Id</th>
        <th>Title</th>
    </tr>
    <c:forEach var="university" items="${universities}">
        <jsp:useBean id="university" class="ua.training.model.University"/>
        <tr>
            <td><c:out value="${university.id}"/></td>
            <td><c:out value="${university.title}"/></td>
        </tr>
    </c:forEach>
</c:if>
</table>
</body>
</html>
