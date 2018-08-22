<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Log In</h1>

<form method="post" action="${pageContext.request.contextPath}/main">

    <label><input type="email" required maxlength="60" name="email"></label>email<br>

    <label><input type="password" required maxlength="60" name="password"></label>password<br>

    <input type="hidden" name="command" value="login">
    <input type="submit" name="button" value="Submit">

</form>

<c:out value="${notFound}"/>

<a href="${pageContext.request.contextPath}/view/registration.jsp">Create new account</a>

</body>
</html>
