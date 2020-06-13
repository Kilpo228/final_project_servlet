<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <fmt:setLocale value = "${sessionScope.locale}" />
    <fmt:setBundle basename = "lang" />

    <title><fmt:message key="admin.ingredient.title" /></title>

    <title><fmt:message key = "login.title" /></title>

    <meta name="viewport" content="width = device-width, initial-scale = 1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/ingredient.css" />">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
    </script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">
    </script>
    <script type="text/javascript" src="<c:url value="/static/js/ingredient.js" />"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item" style="margin-left: 10px">
            <button id="go-to-home" class="btn btn-secondary">
                <fmt:message key="admin.button.home" />
            </button>
            <button id="go-to-purchases" class="btn btn-secondary">
                <fmt:message key="admin.button.purchases" />
            </button>
            <button class="btn btn-secondary" data-toggle="modal" data-target="#new-ingredient-modal">
                <fmt:message key="admin.new.ingredient.button" />
            </button>
        </li>
    </ul>

    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <button id="en" class="btn btn-secondary">EN</button>
            <button id="ru" class="btn btn-secondary" style="margin-right: 10px">RU</button>
        </li>
        <li class="nav-item">
            <button id="button-logout" class="btn btn-secondary">
                <fmt:message key="admin.button.logout" />
            </button>
        </li>
    </ul>
</nav>

<div class="container" style="margin-right: 270px">
    <c:choose>
        <c:when test="${requestScope.ingredients.size() > 0}">
            <h2 style="text-align: center; margin-right: 270px">
                <fmt:message key="admin.ingredient.table.text" />
            </h2>
            <table id="ingredients-table" class="table">
                <thead>
                <tr>
                    <th scope="col">
                        <a id="a-en-name">
                            <fmt:message key="admin.ingredient.table.1" />
                        </a>
                    </th>
                    <th scope="col">
                        <a id="a-ru-name">
                            <fmt:message key="admin.ingredient.table.2" />
                        </a>
                    </th>
                    <th scope="col">
                        <a id="a-amount">
                            <fmt:message key="admin.ingredient.table.3" />
                        </a>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.ingredients}" var="ingredient">
                    <tr>
                        <td class="name${ingredient.hashCode()}">
                                ${ingredient.getEnName()}
                        </td>
                        <td>
                                ${ingredient.getRuName()}
                        </td>
                        <td class="amount${ingredient.hashCode()}">
                                ${ingredient.getAmount()}
                        </td>
                        <td style="border: 0">
                            <button id="${ingredient.hashCode()}" class="btn btn-secondary plus-button" style="width: 35px; height: 38px">+</button>
                            <button id="${ingredient.hashCode()}" class="btn btn-secondary minus-button" style="width: 35px; height: 38px">-</button>
                            <button id="${ingredient.hashCode()}" class="btn btn-danger ingredient-delete-button">
                                <fmt:message key="admin.ingredient.delete.button" />
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <h2 style="text-align: center">
                <fmt:message key="admin.ingredient.absent.msg" />
            </h2>
        </c:otherwise>
    </c:choose>

    <div class="modal fade" id="new-ingredient-modal" tabindex="-1" role="dialog" aria-labelledby="new-ingredient-modal" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="password-modal-header">
                        <fmt:message key="admin.modal.new.ingredient.header" />
                    </h5>
                </div>
                <div class="modal-body">
                    <input id="modal-new-ingredient-input-en-name" type="text" placeholder="<fmt:message key="admin.modal.new.ingredient.input.1" />" aria-label="<fmt:message key="admin.modal.new.ingredient.input.1" />" class="form-control" aria-describedby="basic-addon1" style="width: 200px"><br>
                    <input id="modal-new-ingredient-input-ru-name" type="text" placeholder="<fmt:message key="admin.modal.new.ingredient.input.2" />" aria-label="<fmt:message key="admin.modal.new.ingredient.input.2" />" class="form-control" aria-describedby="basic-addon1" style="width: 200px">
                </div>
                <div class="modal-footer">
                    <div style="display: none; margin-bottom: 4px; height: 38px; line-height: 38px; padding: 0 15px" id="empty-fields-new-ingredient-error" class="alert alert-danger" role="alert">
                        <fmt:message key="user.modal.error.fields" />
                    </div>
                    <div style="display: none; margin-bottom: 4px; height: 38px; line-height: 38px; padding: 0 15px" id="incorrect-new-ingredient-error" class="alert alert-danger" role="alert">
                        <fmt:message key="admin.modal.new.ingredient.error.name" />
                    </div>
                    <div style="display: none; margin-bottom: 4px; height: 38px; line-height: 38px; padding: 0 15px" id="ok-new-ingredient-msg" class="alert alert-info" role="alert">
                        <fmt:message key="admin.modal.new.ingredient.ok.msg" />
                    </div>

                    <button id="modal-new-ingredient-ok-button" type="button" class="btn btn-secondary">
                        <fmt:message key="user.modal.button.ok" />
                    </button>
                    <button id="modal-new-ingredient-close-button" type="button" class="btn btn-primary">
                        <fmt:message key="user.modal.button.close" />
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
