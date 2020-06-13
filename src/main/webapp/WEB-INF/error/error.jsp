<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <fmt:setLocale value = "${sessionScope.locale}" />
    <fmt:setBundle basename = "lang" />

    <title><fmt:message key="error.title" /></title>

    <meta name="viewport" content="width = device-width, initial-scale = 1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/error.css" />">
</head>

<body>

<div class="container" style="margin-top: 100px">
    <div class="jumbotron">
        <div class="d-flex align-items-center justify-content-center">
            <h1><fmt:message key="error.main.text" /></h1>
        </div>
    </div>
</div>
</body>
</html>
