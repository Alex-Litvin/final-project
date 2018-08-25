<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Applicants</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body class="m-3">
<div class="row col-md-6">
    <table class="table table-striped table-bordered table-sm">
        <tr>
            <th>Id</th>
            <th>First name</th>
            <th>Middle name</th>
            <th>Second name</th>
            <th>Status</th>
            <th>Mobile</th>
            <th>Email</th>
            <th>Exams</th>
        </tr>

        <c:forEach var="user" items="${requestScope.users}">
            <jsp:useBean id="user" class="ua.training.model.User"/>
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.middleName}"/></td>
                <td><c:out value="${user.secondName}"/></td>
                <td><c:out value="${user.status}"/></td>
                <td><c:out value="${user.mobile}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.exams}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>

<nav aria-label="Navigation for applicants">
    <ul class="pagination">
        <c:if test="${requestScope.currentPage != 1}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/main?command=showApplicantPagination
                   &recordsPerPage=${requestScope.recordsPerPage}
                   &currentPage=${requestScope.currentPage-1}">Previous</a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
            <c:choose>
                <c:when test="${requestScope.currentPage eq i}">
                    <li class="page-item active"><a class="page-link">
                            ${i} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/main?command=showApplicantPagination
                        &recordsPerPage=${requestScope.recordsPerPage}
                        &currentPage=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/main?command=showApplicantPagination
                &recordsPerPage=${requestScope.recordsPerPage}
                &currentPage=${requestScope.currentPage+1}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>

<a href="${pageContext.request.contextPath}/?command=showUniversities">Back</a>

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
</body>
</html>
