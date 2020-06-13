package ua.testing.model.util;

public interface BundleKeys {
    String DB_URL = "db.url";
    String DB_USERNAME = "db.username";
    String DB_PASSWORD = "db.password";

    String SQL_FIND_BY_USERNAME_USER = "find.by.username.user";
    String SQL_FIND_ALL_USER = "find.all.user";
    String SQL_SAVE_USER = "insert.user";
    String SQL_UPDATE_USER = "update.user";
    String SQL_UPDATE_PASSWORD_USER = "update.password.user";
    String SQL_UPDATE_USERNAME_USER = "update.username.user";
    String SQL_UPDATE_DEDUCT_FROM_BALANCE_USER = "update.deduct.from.balance.user";
    String SQL_UPDATE_REPLENISH_BALANCE_USER = "update.replenish.balance.user";
    String SQL_DELETE_USER = "delete.user";

    String SQL_FIND_BY_EN_NAME_PRODUCT = "find.by.en.name.product";
    String SQL_FIND_BY_RU_NAME_PRODUCT = "find.by.ru.name.product";
    String SQL_FIND_BY_NAME_PRODUCT = "find.by.name.product";
    String SQL_FIND_ALL_PRODUCT = "find.all.product";
    String SQL_FIND_ALL_ORDER_BY_EN_NAME_PRODUCT = "find.all.order.by.en.name.product";
    String SQL_FIND_ALL_ORDER_BY_RU_NAME_PRODUCT = "find.all.order.by.ru.name.product";
    String SQL_FIND_ALL_ORDER_BY_AMOUNT_PRODUCT = "find.all.order.by.amount.product";
    String SQL_FIND_ALL_ORDER_BY_CATEGORY_PRODUCT = "find.all.order.by.category.product";
    String SQL_FIND_ALL_ORDER_BY_PRICE_PRODUCT = "find.all.order.by.price.product";
    String SQL_FIND_ALL_AVAILABLE_PRODUCT = "find.all.available.product";
    String SQL_SAVE_PRODUCT = "insert.product";
    String SQL_UPDATE_PRODUCT = "update.product";
    String SQL_UPDATE_DECREASE_AMOUNT_PRODUCT = "update.decrease.amount.product";
    String SQL_UPDATE_AMOUNT_AND_PRICE_PRODUCT = "update.amount.and.price.product";
    String SQL_DELETE_PRODUCT = "delete.product";
    String SQL_DELETE_BY_NAME_PRODUCT = "delete.by.name.product";

    String SQL_FIND_ALL_INGREDIENT = "find.all.ingredient";
    String SQL_FIND_BY_NAME_INGREDIENT = "find.by.name.ingredient";
    String SQL_FIND_ALL_WITH_AMOUNT_GREATER_THAN_ZERO = "find.all.with.amount.greater.than.zero.ingredient";
    String SQL_FIND_AMOUNT_INGREDIENT = "find.amount.ingredient";
    String SQL_FIND_ALL_ORDER_BY_EN_NAME_INGREDIENT = "find.all.sort.by.en.name.ingredient";
    String SQL_FIND_ALL_ORDER_BY_RU_NAME_INGREDIENT = "find.all.sort.by.ru.name.ingredient";
    String SQL_FIND_ALL_ORDER_BY_AMOUNT_INGREDIENT = "find.all.sort.by.amount.ingredient";
    String SQL_SAVE_INGREDIENT = "insert.ingredient";
    String SQL_UPDATE_INGREDIENT = "update.ingredient";
    String SQL_UPDATE_INCREASE_AMOUNT_INGREDIENT = "update.increase.amount.ingredient";
    String SQL_UPDATE_DECREASE_AMOUNT_INGREDIENT = "update.decrease.amount.ingredient";
    String SQL_DELETE_INGREDIENT = "delete.ingredient";
    String SQL_DELETE_BY_NAME_INGREDIENT = "delete.by.name.ingredient";

    String SQL_FIND_ALL_CART = "find.all.cart";
    String SQL_FIND_ITEMS_CART = "find.all.items.by.username.cart";
    String SQL_SAVE_CART = "insert.cart";
    String SQL_UPDATE_CART = "update.cart";
    String SQL_INCREASE_AMOUNT_CART = "update.increase.item.amount.cart";
    String SQL_DECREASE_AMOUNT_CART = "update.decrease.item.amount.cart";
    String SQL_DELETE_CART = "delete.cart";
    String SQL_DELETE_BY_USERNAME_CART = "delete.by.username.cart";
    String SQL_DELETE_ITEM_CART = "delete.item.by.username.cart";

    String SQL_FIND_ALL_RECEIPT = "find.all.receipt";
    String SQL_FIND_ALL_BY_USERNAME_RECEIPT = "find.all.by.username.receipt";
    String SQL_SAVE_RECEIPT = "insert.receipt";
    String SQL_UPDATE_RECEIPT = "update.receipt";
    String SQL_DELETE_RECEIPT = "delete.receipt";
}
