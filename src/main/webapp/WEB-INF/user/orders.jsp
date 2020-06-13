<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="lang" />

    <title><fmt:message key="user.orders.title" /></title>

    <meta name="viewport" content="width = device-width, initial-scale = 1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/orders.css" />">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
    </script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">
    </script>
    <script type="text/javascript" src="<c:url value="/static/js/orders.js" />"></script>
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
                <button id="go-to-cart" class="btn btn-secondary">
                    <fmt:message key="user.navbar.item.2" />
                </button>
            </li>

            <li class="nav-item" style="margin-left: 10px">
                <button id="go-to-menu" class="btn btn-secondary">
                    <fmt:message key="user.navbar.item.3" />
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
                <button id="button-logout" type="submit" class="btn btn-secondary">
                    <fmt:message key="user.button.logout" />
                </button>
            </li>
        </ul>
    </nav>

    <div class="container">
        <c:choose>
            <c:when test="${requestScope.receipts.size() > 0}">
                <div id="receipts-div">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">
                                <fmt:message key="user.order.date" />
                            </th>
                            <th scope="col">
                                <fmt:message key="user.order.product.name" />
                            </th>
                            <th>
                                <fmt:message key="user.order.price" />
                            </th>
                            <th scope="col">
                                <fmt:message key="user.order.amount" />
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.receipts}" var="receipt" >
                            <tr>
                                <td>
                                        ${receipt.getDateTime()}
                                </td>
                                <td>
                                        ${receipt.getProductName()}
                                </td>
                                <td>
                                        ${receipt.getPrice()}
                                </td>
                                <td>
                                        ${receipt.getAmount()}
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <h3><fmt:message key="user.cart.total.sum" />: ${requestScope.sum}</h3>
                </div>
            </c:when>
            <c:otherwise>
                <h2 style="text-align: center">
                    <fmt:message key="user.orders.empty.msg" />
                </h2>
            </c:otherwise>
        </c:choose>

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
    </div>
</body>
</html>
