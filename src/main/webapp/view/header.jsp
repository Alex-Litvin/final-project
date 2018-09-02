<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body>
<c:set var="language" value="${not empty param.language ? param.language :
        not empty language ? language : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="local"/>

<span>
    <div>
        <form method="post" action="${pageContext.request.contextPath}/logout">
            <button type="submit">Logout</button>
        </form>
    </div>
</span>
<span>
    <div>
        <form method="post">
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
                <option value="ua" ${language == 'ua' ? 'selected' : ''}>Ua</option>
            </select>
        </form>
    </div>
</span>

</body>
</html>
