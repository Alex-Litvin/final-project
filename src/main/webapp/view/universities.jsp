<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Universities</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<h1>Universities</h1>
<div>
    <form method="post" action="${pageContext.request.contextPath}/universities">
        <table>
            <c:if test="${!empty requestScope.universities}">
                <tr>
                    <th>Id</th>
                    <th>Title</th>
                </tr>
                <c:forEach var="university" items="${requestScope.universities}">
                    <jsp:useBean id="university" class="ua.training.model.entity.University"/>
                    <tr>
                        <td><c:out value="${university.id}"/></td>
                        <td><c:out value="${university.title}"/></td>
                        <td>
                        <td>
                        <label>
                            <button type="submit" name="universityId" value="${university.id}">delete</button>
                        </label>
                    </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </form>
    <c:out value="${requestScope.universityDeleted}"/>
</div>

<form method="post" action="${pageContext.request.contextPath}/universities">
    <div class="input-group mb-3">
        <input type="text" name="title" class="form-control" placeholder="University title"
               aria-label="University title" aria-describedby="basic-addon2">
        <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="submit">Add</button>
        </div>
    </div>
    <c:out value="${requestScope.universityNotUnique}"/>
    <c:out value="${requestScope.universityAdded}"/>
</form>
</body>
</html>
