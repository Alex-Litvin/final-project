<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Students</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body>
<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="local"/>

<c:import url="/view/header.jsp" charEncoding="utf-8"/>

<body class="m-3">
<div class="row col-md-6" style="margin: auto">
    <table class="table table-striped table-bordered table-sm">
        <tr>
            <th><fmt:message key="message.id"/></th>
            <th><fmt:message key="message.output.first_name"/></th>
            <th><fmt:message key="message.output.middle_name"/></th>
            <th><fmt:message key="message.output.second_name"/></th>
            <th><fmt:message key="message.output.status"/></th>
            <th><fmt:message key="message.output.email"/></th>
        </tr>

        <c:forEach var="user" items="${requestScope.users}">
            <jsp:useBean id="user" class="ua.training.model.entity.User"/>
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.middleName}"/></td>
                <td><c:out value="${user.secondName}"/></td>
                <td><c:out value="${user.status}"/></td>
                <td><c:out value="${user.email}"/></td>
            </tr>
        </c:forEach>
    </table>

<nav aria-label="Navigation for students">
    <ul class="pagination">
        <c:if test="${requestScope.currentPage != 1}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/admin/students?currentPage=${requestScope.currentPage-1}">Previous</a>
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
                        <a class="page-link" href="${pageContext.request.contextPath}/admin/students?currentPage=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
            <li class="page-item">
                <a class="page-link" href="${pageContext.request.contextPath}/admin/students?currentPage=${requestScope.currentPage+1}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>
</div>
<a href="${pageContext.request.contextPath}/view/admin/admin_menu.jsp"><fmt:message key="message.menu"/></a>
</body>
</html>
