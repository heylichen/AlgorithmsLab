package heylichen.utils;

import java.math.BigDecimal;

public final class DoubleUtils {
    private DoubleUtils() {
    }

    public static double add(double a, double b) {
        BigDecimal bd1 = BigDecimal.valueOf(a);
        BigDecimal bd2 = BigDecimal.valueOf(b);
        return bd1.add(bd2).doubleValue();
    }

}
