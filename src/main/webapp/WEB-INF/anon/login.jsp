<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <fmt:setLocale value = "${sessionScope.locale}" />
    <fmt:setBundle basename = "lang" />

    <title><fmt:message key = "login.title" /></title>

    <meta name="viewport" content="width = device-width, initial-scale = 1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/login.css" />">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/static/js/login.js" />"></script>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <button id="en" class="btn btn-secondary">EN</button>
            <button id="ru" class="btn btn-secondary">RU</button>
        </li>
    </ul>
</nav>

<div class="container">
    <div class="page-header" style="margin-top: 50px">
        <h1 style="text-align: center"><fmt:message key = "login.main.text" /></h1>
    </div>

    <div class="d-flex align-items-center justify-content-center">
        <div class="jumbotron" style="width: 450px">
            <div class="d-flex align-items-center justify-content-center">
                <form>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><fmt:message key = "login.username" /></span>
                        </div>
                        <input required type="text" name="username" class="form-control" aria-label="Default"
                               aria-describedby="inputGroup-sizing-default">
                    </div>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><fmt:message key = "login.password" /></span>
                        </div>
                        <input required type="password" name="password" class="form-control" aria-label="Default"
                               aria-describedby="inputGroup-sizing-default">
                    </div>

                    <button type="submit" class="btn btn-secondary">
                        <fmt:message key = "login.button.login" />
                    </button>

                    <button type="button" id="go-to-register" class="btn btn-secondary">
                        <fmt:message key = "login.button.sign.up" />
                    </button>
                </form>
            </div>
        </div>
    </div>

    <div class="d-flex align-items-center justify-content-center">
        <div class="panel-body" style="text-align: center; width: 450px">
            <div>
                <div style="display: none" id="logged-out-msg" class="alert alert-info" role="alert">
                    <fmt:message key = "login.msg.logged.out" />
                </div>
            </div>
            <div>
                <div style="display: none" id="error-msg" class="alert alert-danger" role="alert">
                    <fmt:message key = "login.msg.error" />
                </div>
            </div>
            <div>
                <div style="display: none" id="duplicate-msg" class="alert alert-danger" role="alert">
                    <fmt:message key = "login.msg.duplicate" />
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
