<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href="content/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="content/css/custom.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/playlists">Spotitube</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/playlists">Playlists</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#">
                    <span class="glyphicon glyphicon-user"></span>
                    <strong>
                            <c:out value="${sessionScope.USER}"/>
                    </strong>
                </a>
            </li>
        </ul>
    </div>
</nav>