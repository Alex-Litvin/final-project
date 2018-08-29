<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>

</head>
<body>
<h1>registration</h1>

<form method="post" action="${pageContext.request.contextPath}/main">
    <label><input type="text" required maxlength="60" name="firstName"></label>First name<br>
    <label><input type="text" required maxlength="60" name="middleName"></label>Middle name<br>
    <label><input type="text" required maxlength="60" name="secondName"></label>Second name<br>
    <label><input type="email" required maxlength="60" name="email" pattern="^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\.)+[a-z]{2,6}$"></label>Email<br>
    <label><input type="text" required maxlength="60" name="password"></label>Password<br>
    <input type="hidden" name="command" value="registration">
    <input type="submit" name="button" value="Submit">
</form>
<a href="${pageContext.request.contextPath}/main">Log In</a><br>

<c:out value="${error}"/>

</body>
</html>