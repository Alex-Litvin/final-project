<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="menu.jsp" %>

<c:set var="user" value="${sessionScope.user}"/>
<jsp:useBean id="user" class="ua.training.model.User"/>
<td><c:out value="${user.firstName}"/></td>
<td><c:out value="${user.middleName}"/></td>
<td><c:out value="${user.secondName}"/></td>
<td><c:out value="${user.email}"/></td>
<td><c:out value="${user.mobile}"/></td>

</body>
</html>