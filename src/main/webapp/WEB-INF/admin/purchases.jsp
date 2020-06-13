<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="lang" />

    <title><fmt:message key="admin.purchases.title" /></title>

    <meta name="viewport" content="width = device-width, initial-scale = 1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/purchases.css" />">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
    </script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">
    </script>
    <script type="text/javascript" src="<c:url value="/static/js/purchases.js" />"></script>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item" style="margin-left: 10px">
            <button id="go-to-home" class="btn btn-secondary">
                <fmt:message key="admin.button.home" />
            </button>
            <button id="go-to-ingredients" class="btn btn-secondary">
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

<div class="container">
    <c:choose>
        <c:when test="${requestScope.receipts.size() > 0}">
            <table id="receipts-table" class="table">
                <thead>
                    <tr>
                        <th scope="col">
                            <fmt:message key="admin.purchases.table.1" />
                        </th>
                        <th scope="col">
                            <fmt:message key="admin.purchases.table.2" />
                        </th>
                        <th scope="col">
                            <fmt:message key="admin.purchases.table.3" />
                        </th>
                        <th scope="col">
                            <fmt:message key="admin.purchases.table.4" />
                        </th>
                        <th scope="col">
                            <fmt:message key="admin.purchases.table.5" />
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.receipts}" var="receipt">
                        <tr>
                            <td>
                                <div>
                                    ${receipt.getDateTime()}
                                </div>
                            </td>
                            <td>
                                <div>
                                    ${receipt.getProductName()}
                                </div>
                            </td>
                            <td>
                                <div>
                                    ${receipt.getPrice()} <fmt:message key="currency" />
                                </div>
                            </td>
                            <td>
                                <div>
                                    ${receipt.getAmount()}
                                </div>
                            </td>
                            <td>
                                <div>
                                    ${receipt.getUsername()}
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <h2><fmt:message key="user.cart.total.sum" />: ${requestScope.sum} <fmt:message key="currency" /></h2>
        </c:when>
        <c:otherwise>
            <h2 style="text-align: center">
                <fmt:message key="admin.purchases.receipts.msg" />
            </h2>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
