<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <link href="../css/header_style.css" rel="stylesheet" type="text/css">
    <title>Header</title>
</head>
<body>

<div class="menu">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="login.jsp"><fmt:message key="introductory_campaign"/></a>
        </div>
        <div>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="registration.jsp">
                    <span class="glyphicon glyphicon-user"></span>
                    <fmt:message key="register"/></a>
                </li>
                <li>
                    <form>
                        <input name="language" type="image" value="en"
                        ${language=='en' ? 'selected' : '' } src = "../image/flag_en.png" style="height: 24px; width: 32px;">
                        <input name="language" type="image" value="ua"
                        ${language=='ua' ? 'selected' : '' } src = "../image/flag_ua.png" style="height: 24px; width: 32px;">
                    </form>
                </li>
            </ul>
        </div>
    </div>
</div>

</body>
</html>
