<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <fmt:setLocale value = "${sessionScope.locale}" />
    <fmt:setBundle basename = "lang" />

    <title><fmt:message key = "sign.up.title" /></title>

    <meta name="viewport" content="width = device-width, initial-scale = 1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/register.css" />">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/static/js/register.js" />"></script>

    <title>Sign up</title>
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

<div class="container" style="margin-top: 50px">
    <div class="page-header">
        <h1 style="text-align: center"><fmt:message key = "sign.up.main.text" /></h1>
    </div>

    <div class="d-flex align-items-center justify-content-center">
        <div class="jumbotron" style="width: 450px">
            <div class="d-flex align-items-center justify-content-center">
                <form>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><fmt:message key = "sign.up.username" /></span>
                        </div>
                        <input required type="text" name="username" class="form-control" aria-label="Default"
                               aria-describedby="inputGroup-sizing-default">
                    </div>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><fmt:message key = "sign.up.password" /></span>
                        </div>
                        <input id="first-password" required type="password" name="password" class="form-control" aria-label="Default"
                               aria-describedby="inputGroup-sizing-default">
                    </div>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><fmt:message key = "sign.up.password.repeat" /></span>
                        </div>
                        <input id="second-password" required type="password" name="password" class="form-control" aria-label="Default"
                               aria-describedby="inputGroup-sizing-default">
                    </div>

                    <button type="submit" class="btn btn-secondary">
                        <fmt:message key = "sign.up.button.submit" />
                    </button>

                    <button id="go-to-login" type="button" class="btn btn-secondary">
                        <fmt:message key = "sign.up.button.login" />
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="d-flex align-items-center justify-content-center">
    <div class="panel-body" style="text-align: center; width: 450px">
        <div>
            <div id="ok-msg" class="alert alert-info" role="alert" style="display: none">
                <fmt:message key = "sign.up.msg.ok" />
            </div>

            <div id="error-msg" class="alert alert-danger" role="alert" style="display: none">
                <fmt:message key = "sign.up.msg.error" />
            </div>

            <div id="error-msg-password" class="alert alert-danger" role="alert" style="display: none">
                <fmt:message key = "sign.up.msg.error.password" />
            </div>
        </div>
    </div>
</div>
</body>
</html>
