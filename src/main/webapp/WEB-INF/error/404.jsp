<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <fmt:setLocale value = "${sessionScope.locale}" />
    <fmt:setBundle basename = "lang" />

    <title><fmt:message key="error.404.title" /></title>

    <meta name="viewport" content="width = device-width, initial-scale = 1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/404.css" />">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/static/js/404.js" />"></script>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <button id="en" class="btn btn-secondary">EN</button>
            <button id="ru" class="btn btn-secondary" style="margin-right: 10px">RU</button>
        </li>
    </ul>
</nav>

<div class="container" style="margin-top: 100px">
    <div class="jumbotron">
        <h1 style="text-align: center"><fmt:message key="error.404.main.text" /></h1>
    </div>
    <div class="d-flex align-items-center justify-content-center">
        <button id="return" class="btn btn-secondary"><fmt:message key = "error.404.button" /></button>
    </div>
</div>
</body>
</html>
