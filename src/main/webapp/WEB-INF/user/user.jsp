<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="lang" />

    <title><fmt:message key="user.title" /></title>

    <meta name="viewport" content="width = device-width, initial-scale = 1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/user.css" />">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
    </script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">
    </script>
    <script type="text/javascript" src="<c:url value="/static/js/user.js" />"></script>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="user.options.name" />
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <button class="dropdown-item" data-toggle="modal" data-target="#password-change">
                        <fmt:message key="user.options.item.1" />
                    </button>

                    <button class="dropdown-item" data-toggle="modal" data-target="#username-change">
                        <fmt:message key="user.options.item.2" />
                    </button>

                    <button class="dropdown-item" data-toggle="modal" data-target="#balance-replenish">
                        <fmt:message key="user.options.item.3" />
                    </button>
                </div>
            </div>
        </li>

        <li class="nav-item" style="margin-left: 10px">
            <button id="go-to-orders" class="btn btn-secondary">
                <fmt:message key="user.navbar.item.1" />
            </button>
        </li>

        <li class="nav-item" style="margin-left: 10px">
            <button id="go-to-cart" class="btn btn-secondary">
                <fmt:message key="user.navbar.item.2" />
            </button>
        </li>

        <li class="nav-item" style="margin-left: 20px">
            <div class="row">
                <div class="col-sm" style="color: gainsboro">
                    <fmt:message key="user.balance.text" /> <span id="balance"></span> <fmt:message key="currency" />
                </div>
            </div>
        </li>
    </ul>

    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <button id="en" class="btn btn-secondary">EN</button>
            <button id="ru" class="btn btn-secondary" style="margin-right: 10px">RU</button>
        </li>

        <li class="nav-item">
            <button id="button-logout" class="btn btn-secondary">
                <fmt:message key="user.button.logout" />
            </button>
        </li>
    </ul>
</nav>

<div class="container" style="margin-top: 100px">
    <div class="row">
        <div class="col">
            <div class="filter-menu">
                <div style="padding-top: 4px">
                    <input id="search-input" type="text" class="form-control"
                           placeholder="<fmt:message key="user.menu.filter.search.placeholder" />"
                           aria-label="<fmt:message key="user.menu.filter.search.placeholder" />"
                           aria-describedby="basic-addon1" style="width: 170px">
                </div>

                <hr>

                <span style="margin-top: -7px"><fmt:message key="user.menu.filter.price.filter.span" /></span>

                <div>
                    <input id="min-range" type="number" min="0" class="form-control"
                           placeholder="<fmt:message key="user.menu.filter.price.min.placeholder" />" aria-label="Min"
                           aria-describedby="basic-addon1" style="width: 170px">
                </div>

                <div>
                    <input id="max-range" type="number" min="0" class="form-control"
                           placeholder="<fmt:message key="user.menu.filter.price.max.placeholder" />" aria-label="Max"
                           aria-describedby="basic-addon1" style="width: 170px">
                </div>

                <hr>

                <div>
                    <div style="width: 125px">
                        <input style="transform: scale(1.2)" class="form-check-input" type="checkbox" value="" id="filter-pizza">
                        <label class="form-check-label" for="filter-pizza">
                            <fmt:message key="user.menu.filter.item.1" />
                        </label>
                    </div>
                </div>

                <div>
                    <div style="width: 125px">
                        <input style="transform: scale(1.2)" class="form-check-input" type="checkbox" value="" id="filter-sushi">
                        <label class="form-check-label" for="filter-sushi">
                            <fmt:message key="user.menu.filter.item.2" />
                        </label>
                    </div>
                </div>

                <div>
                    <div style="width: 125px">
                        <input style="transform: scale(1.2)" class="form-check-input" type="checkbox" value="" id="filter-burgers">
                        <label class="form-check-label" for="filter-burgers">
                            <fmt:message key="user.menu.filter.item.3" />
                        </label>
                    </div>
                </div>

                <div>
                    <div style="width: 125px">
                        <input style="transform: scale(1.2)" class="form-check-input" type="checkbox" value="" id="filter-cold-drinks">
                        <label class="form-check-label" for="filter-cold-drinks">
                            <fmt:message key="user.menu.filter.item.4" />
                        </label>
                    </div>
                </div>

                <div>
                    <div style="width: 125px">
                        <input style="transform: scale(1.2)" class="form-check-input" type="checkbox" value="" id="filter-tea">
                        <label class="form-check-label" for="filter-tea">
                            <fmt:message key="user.menu.filter.item.5" />
                        </label>
                    </div>
                </div>

                <div>
                    <div style="width: 125px">
                        <input style="transform: scale(1.2)" class="form-check-input" type="checkbox" value="" id="filter-juice">
                        <label class="form-check-label" for="filter-juice">
                            <fmt:message key="user.menu.filter.item.6" />
                        </label>
                    </div>
                </div>

                <hr>

                <span><fmt:message key="user.menu.filter.sort.by.span" /></span>

                <div>
                    <div style="width: 170px">
                        <select class="form-control" id="sort-by">
                            <option id="sort-none"><fmt:message key="user.menu.filter.sort.by.none" /></option>
                            <option id="sort-name" value="1"><fmt:message key="user.menu.filter.sort.by.item.1" /></option>
                            <option id="sort-price-bigger" value="2"><fmt:message key="user.menu.filter.sort.by.item.2" /></option>
                            <option id="sort-price-smaller" value="3"><fmt:message key="user.menu.filter.sort.by.item.3" /></option>
                        </select>
                    </div>
                </div>

                <hr>

                <div>
                    <button id="search-button" class="btn btn-secondary">
                        <fmt:message key="user.menu.filter.button.search" />
                    </button>
                </div>
            </div>
        </div>

        <div class="col">
            <div class="menu-items" style="margin-top: -10px">
                <ul class="menu-ul">
                    <c:forEach items="${requestScope.products}" var="item">
                        <li class="menu-li">
                            <div class="row" style="margin-bottom: 35px">
                                <div class="col item ${item.hashCode()}item" style="height: 50px">
                                        ${item.getName()}
                                </div>
                            </div>

                            <hr style="margin-bottom: -15px">

                            <div class="row">
                                <div class="col">
                                    <div class="menu-item">
                                        <span style="margin-right: 25px"><fmt:message key="user.menu.item.price.span" />: ${item.getPrice()} <fmt:message key="currency" /></span>
                                        <button id="${item.hashCode()}" class="btn btn-secondary button-buy" style="width: 83px; height: 38px">
                                            <c:choose>
                                                <c:when test="${requestScope.cart.contains(item.getName())}">
                                                    <fmt:message key="user.menu.item.button.remove" />
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:message key="user.menu.item.button.buy" />
                                                </c:otherwise>
                                            </c:choose>
                                        </button>
                                        <button id="${item.hashCode()}" class="btn btn-secondary button-details" style="width: 83px; height: 38px" data-toggle="modal" data-target="#product-details">
                                            <fmt:message key="user.menu.item.button.details" />
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <c:if test="${requestScope.products.size() == 0}">
                    <div style="padding-left: 350px">
                        <h1>
                            <fmt:message key="user.filter.no.results.msg" />
                        </h1>
                    </div>
                </c:if>
            </div>

            <c:if test="${requestScope.pages > 1}">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:forEach var="a" begin="1" end="${requestScope.pages}" step="1">
                            <li class="page-item">
                                <button class="page-link" style="background-color: rgb(108, 117, 125); border-color: rgb(108, 117, 125); color: gainsboro">${a}</button>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </c:if>
        </div>
    </div>

    <div class="modal fade" id="password-change" tabindex="-1" role="dialog" aria-labelledby="password-change" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="password-modal-header">
                        <fmt:message key="user.modal.password.header" />
                    </h5>
                </div>
                <div class="modal-body">
                    <input id="modal-password-current" type="text" placeholder="<fmt:message key="user.modal.password.current" />" aria-label="<fmt:message key="user.modal.password.current" />" class="form-control" aria-describedby="basic-addon1" style="width: 200px"><br>
                    <input id="modal-password-new" type="text" placeholder="<fmt:message key="user.modal.password.new" />" aria-label="<fmt:message key="user.modal.password.new" />" class="form-control" aria-describedby="basic-addon1" style="width: 200px">
                </div>
                <div class="modal-footer">
                    <div style="display: none; margin-bottom: 4px; height: 38px; line-height: 38px; padding: 0 15px" id="empty-fields-password-error" class="alert alert-danger" role="alert">
                        <fmt:message key="user.modal.error.fields" />
                    </div>
                    <div style="display: none; margin-bottom: 4px; height: 38px; line-height: 38px; padding: 0 15px" id="incorrect-password-error" class="alert alert-danger" role="alert">
                        <fmt:message key="user.modal.password.error.incorrect" />
                    </div>
                    <div style="display: none; margin-bottom: 4px; height: 38px; line-height: 38px; padding: 0 15px" id="ok-password" class="alert alert-info" role="alert">
                        <fmt:message key="user.modal.password.msg.ok" />
                    </div>

                    <button id="modal-password-ok-button" type="button" class="btn btn-secondary">
                        <fmt:message key="user.modal.button.ok" />
                    </button>
                    <button id="modal-password-close-button" type="button" class="btn btn-primary">
                        <fmt:message key="user.modal.button.close" />
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="username-change" tabindex="-1" role="dialog" aria-labelledby="username-change" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="username-modal-header">
                        <fmt:message key="user.modal.username.header" />
                    </h5>
                </div>
                <div class="modal-body">
                    <fmt:message key="user.modal.username.current" />: <span id="current-username">${requestScope.currentUsername}</span><br>
                    <div style="padding-top: 10px">
                        <input id="modal-username-new" type="text" placeholder="<fmt:message key="user.modal.username.new" />" aria-label="<fmt:message key="user.modal.username.new" />" class="form-control" aria-describedby="basic-addon1" style="width: 200px">
                    </div>
                </div>
                <div class="modal-footer">
                    <div style="display: none; margin-bottom: 4px; height: 38px; line-height: 38px; padding: 0 15px" id="empty-fields-username-error" class="alert alert-danger" role="alert">
                        <fmt:message key="user.modal.error.fields" />
                    </div>
                    <div style="display: none; margin-bottom: 4px; height: 38px; line-height: 38px; padding: 0 15px" id="ok-username" class="alert alert-info" role="alert">
                        <fmt:message key="user.modal.username.msg.ok" />
                    </div>

                    <button id="modal-username-ok-button" type="button" class="btn btn-secondary">
                        <fmt:message key="user.modal.button.ok" />
                    </button>
                    <button id="modal-username-close-button" type="button" class="btn btn-primary">
                        <fmt:message key="user.modal.button.close" />
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="balance-replenish" tabindex="-1" role="dialog" aria-labelledby="balance-replenish" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="balance-replenish-modal-header">
                        <fmt:message key="user.modal.balance.header" />
                    </h5>
                </div>
                <div class="modal-body">
                    <fmt:message key="user.modal.balance.current" />: <span id="current-balance-span"></span> <fmt:message key="currency" /><br>
                    <div style="padding-top: 10px">
                        <input id="modal-balance-amount" type="number" min="0"
                               placeholder="<fmt:message key="user.modal.balance.amount" /> <fmt:message key="currency" />"
                               aria-label="<fmt:message key="user.modal.balance.amount" />"
                               class="form-control"
                               aria-describedby="basic-addon1"
                               style="width: 245px">
                    </div>
                </div>
                <div class="modal-footer">
                    <div style="display: none; margin-bottom: 4px; height: 38px; line-height: 38px; padding: 0 15px" id="empty-fields-balance-error" class="alert alert-danger" role="alert">
                        <fmt:message key="user.modal.error.fields" />
                    </div>
                    <div style="display: none; margin-bottom: 4px; height: 38px; line-height: 38px; padding: 0 15px" id="amount-balance-error" class="alert alert-danger" role="alert">
                        <fmt:message key="user.modal.balance.error.amount" />
                    </div>
                    <div style="display: none; margin-bottom: 4px; height: 38px; line-height: 38px; padding: 0 15px" id="ok-balance" class="alert alert-info" role="alert">
                        <fmt:message key="user.modal.balance.msg.ok" />
                    </div>

                    <button id="modal-balance-replenish-ok-button" type="button" class="btn btn-secondary">
                        <fmt:message key="user.modal.button.ok" />
                    </button>
                    <button id="modal-balance-replenish-close-button" type="button" class="btn btn-primary">
                        <fmt:message key="user.modal.button.close" />
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="product-details" tabindex="-1" role="dialog" aria-labelledby="product-details" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="product-details-header">
                        <fmt:message key="user.modal.product.details.header" />
                    </h5>
                </div>
                <div class="modal-body">
                    <fmt:message key="user.modal.product.details.name" />: <span id="product-name"></span><br>
                    <span id="ingredient-span"><fmt:message key="user.modal.product.details.ingredients" />: </span><span id="product-ingredients"></span>
                </div>
                <div class="modal-footer">
                    <button id="modal-product-details-close-button" type="button" class="btn btn-primary">
                        <fmt:message key="user.modal.button.close" />
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
