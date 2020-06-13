package ua.testing.model.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public class CurrencyConverter {
    public static BigDecimal centsToDollarsWithLocale(long cents, String currency, HttpServletRequest request) {
        BigDecimal dollars = new BigDecimal(cents).movePointLeft(2);

        if (request.getSession().getAttribute("locale").equals(new Locale("ru", "RU"))) {
            return dollars.multiply(new BigDecimal(currency)).setScale(2, RoundingMode.HALF_EVEN);
        }

        return dollars;
    }

    public static long dollarsToCents(String dollars) {
        return new BigDecimal(dollars).multiply(new BigDecimal(100)).longValue();
    }

    public static long dollarsToCentsWithLocale(String dollars, String currency, HttpServletRequest request) {
        BigDecimal amount = new BigDecimal(dollars);

        if (request.getSession().getAttribute("locale").equals(new Locale("ru", "RU"))) {
            return amount.multiply(new BigDecimal(currency)).setScale(2, RoundingMode.HALF_EVEN).
                    multiply(new BigDecimal(100)).longValue();
        }

        return amount.multiply(new BigDecimal(100)).longValue();
    }
}
