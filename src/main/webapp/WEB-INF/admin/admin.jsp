<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="lang" />

    <title><fmt:message key="admin.title" /></title>

    <meta name="viewport" content="width = device-width, initial-scale = 1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/admin.css" />">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
    </script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">
    </script>
    <script type="text/javascript" src="<c:url value="/static/js/admin.js" />"></script>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item" style="margin-left: 10px">
            <button id="go-to-purchases" class="btn btn-secondary">
                <fmt:message key="admin.button.purchases" />
            </button>
            <button class="btn btn-secondary" data-toggle="modal" data-target="#new-product-modal">
                <fmt:message key="admin.button.new.product" />
            </button>
            <button id="go-to-ingredient" class="btn btn-secondary">
                <fmt:message key="admin.button.ingredients" />
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

<div class="container" style="margin-right: 300px">
    <c:choose>
        <c:when test="${requestScope.products.size() > 0}">
            <h2 style="text-align: center; padding-bottom: 15px; margin-left: -150px">
                <fmt:message key="admin.table.header" />
            </h2>
            <table id="products-table" class="table">
                <thead>
                <tr>
                    <th scope="col">
                        <a id="a-en">
                            <fmt:message key="admin.table.th.1" />
                        </a>
                    </th>
                    <th scope="col">
                        <a id="a-ru">
                            <fmt:message key="admin.table.th.2" />
                        </a>
                    </th>
                    <th scope="col">
                        <a id="a-amount">
                            <fmt:message key="admin.table.th.3" />
                        </a>
                    </th>
                    <th scope="col">
                        <a id="a-category">
                            <fmt:message key="admin.table.th.4" />
                        </a>
                    </th>
                    <th scope="col">
                        <a id="a-price">
                            <fmt:message key="admin.table.th.5" />
                        </a>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.products}" var="product">
                    <tr>
                        <td class="enName${product.hashCode()}">
                            <div>
                                    ${product.getEnProductName()}
                            </div>
                        </td>
                        <td class="ruName${product.hashCode()}">
                            <div>
                                    ${product.getRuProductName()}
                            </div>
                        </td>
                        <td class="amount${product.hashCode()}">
                            <div>
                                    ${product.getAmount()}
                            </div>
                        </td>
                        <td class="category${product.hashCode()}">
                            <div>
                                    ${product.getCategory()}
                            </div>
                        </td>
                        <td class="price${product.hashCode()}">
                            <div>
                                    ${product.getPrice()} <fmt:message key="currency" />
                            </div>
                        </td>
                        <td style="border: 0">
                            <button id="${product.hashCode()}" class="btn btn-secondary edit-button" data-toggle="modal" data-target="#edit-modal">
                                <fmt:message key="admin.button.edit" />
                            </button>
                            <button id="${product.hashCode()}" class="btn btn-danger delete-button">
                                <fmt:message key="admin.button.delete" />
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <h2 style="text-align: center">
                <fmt:message key="admin.absent.products.msg" />
            </h2>
        </c:otherwise>
    </c:choose>

    <div class="modal fade" id="edit-modal" tabindex="-1" role="dialog" aria-labelledby="edit-modal" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <div>
                        <fmt:message key="admin.modal.header.div" />: <span id="edit-modal-header-span"></span>
                    </div>
                </div>
                <div class="modal-body">
                    <span><fmt:message key="admin.modal.body.span.amount" />: </span><span id="edit-modal-span-current-amount"></span>

                    <div style="padding-right: -10px">
                        <input id="edit-modal-input-amount" type="number" min="0" placeholder="<fmt:message key="admin.modal.placeholder.amount" />" class="form-control" style="width: 180px">
                    </div>

                    <span><fmt:message key="admin.modal.body.span.price" />: </span><span id="edit-modal-span-current-price"></span> <fmt:message key="currency" />

                    <div>
                        <input id="edit-modal-input-price" type="number" min="0" placeholder="<fmt:message key="admin.modal.placeholder.price" />" class="form-control" style="width: 180px">
                    </div>
                </div>
                <div class="modal-footer">
                    <div style="display: none; margin-bottom: 4px; height: 38px; line-height: 38px; padding: 0 15px" id="empty-fields-error" class="alert alert-danger" role="alert">
                        <fmt:message key="admin.modal.field.msg" />
                    </div>
                    <div style="display: none; margin-bottom: 4px; height: 38px; line-height: 38px; padding: 0 15px" id="error-msg" class="alert alert-danger" role="alert">
                        <fmt:message key="admin.modal.error.msg" />
                    </div>
                    <div style="display: none; margin-bottom: 4px; height: 38px; line-height: 38px; padding: 0 15px" id="ok-msg" class="alert alert-info" role="alert">
                        <fmt:message key="admin.modal.ok.msg" />
                    </div>
                    <button id="edit-modal-ok-button" class="btn btn-secondary">
                        <fmt:message key="admin.modal.button.ok" />
                    </button>
                    <button id="edit-modal-close-button" class="btn btn-primary">
                        <fmt:message key="admin.modal.button.close" />
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="new-product-modal" tabindex="-1" role="dialog" aria-labelledby="new-product-modal" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <div>
                        <fmt:message key="admin.modal.new.product.header" />
                    </div>
                </div>
                <div class="modal-body">
                    <div style="padding-bottom: 10px">
                        <input id="modal-new-product-en-name-input" type="text" placeholder="<fmt:message key="admin.modal.new.product.placeholder.en.name" />" aria-label="<fmt:message key="admin.modal.new.product.placeholder.en.name" />" class="form-control" aria-describedby="basic-addon1">
                    </div>

                    <div style="padding-bottom: 10px">
                        <input id="modal-new-product-ru-name-input" type="text" placeholder="<fmt:message key="admin.modal.new.product.placeholder.ru.name" />" aria-label="<fmt:message key="admin.modal.new.product.placeholder.ru.name" />" class="form-control" aria-describedby="basic-addon1">
                    </div>

                    <div style="padding-bottom: 10px">
                        <select class="form-control" id="modal-new-product-category">
                            <c:forEach items="${requestScope.categories}" var="category">
                                <option>${category}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div style="padding-bottom: 10px">
                        <input id="modal-new-product-price-input" type="number" placeholder="<fmt:message key="admin.modal.new.product.placeholder.price" />" aria-label="<fmt:message key="admin.modal.new.product.placeholder.price" />" class="form-control" aria-describedby="basic-addon1">
                    </div>

                    <c:forEach items="${requestScope.ingredients}" var="ingredient">
                        <div style="padding-left: 23px" class="div-ingredients">
                            <input name="ingredient-checkbox" style="transform: scale(1.2)" class="form-check-input" type="checkbox" value="${ingredient.getName()}" id="check${ingredient.hashCode()}">
                            <label class="form-check-label" for="check${ingredient.hashCode()}">
                                ${ingredient.getName()}
                            </label>
                        </div>
                    </c:forEach>
                </div>
                <div class="modal-footer">
                    <div style="display: none; margin-bottom: 4px; height: 38px; line-height: 38px; padding: 0 15px" id="empty-fields-error-modal" class="alert alert-danger" role="alert">
                        <fmt:message key="admin.modal.field.msg" />
                    </div>
                    <div style="display: none; margin-bottom: 4px; height: 38px; line-height: 38px; padding: 0 15px" id="error-name-msg-modal" class="alert alert-danger" role="alert">
                        <fmt:message key="admin.modal.error.msg" />
                    </div>
                    <div style="display: none; margin-bottom: 4px; height: 38px; line-height: 38px; padding: 0 15px" id="error-ingredient-msg-modal" class="alert alert-danger" role="alert">
                        <fmt:message key="admin.modal.new.product.error.ingredient.msg" />
                    </div>
                    <div style="display: none; margin-bottom: 4px; height: 38px; line-height: 38px; padding: 0 15px" id="ok-msg-modal" class="alert alert-info" role="alert">
                        <fmt:message key="admin.modal.ok.msg" />
                    </div>
                    <button id="button-ok-modal" class="btn btn-secondary">
                        <fmt:message key="admin.modal.button.ok" />
                    </button>
                    <button id="button-close-modal" class="btn btn-primary">
                        <fmt:message key="admin.modal.button.close" />
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
