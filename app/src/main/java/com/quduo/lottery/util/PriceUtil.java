package com.quduo.lottery.util;

import java.text.DecimalFormat;

/**
 * Created by scene on 17-7-20.
 */

public class PriceUtil {
    public static String getPrice(double price) {
        DecimalFormat df = new DecimalFormat("0.00");
        String newPrice = String.valueOf(df.format((price/100d)));
        if (newPrice.endsWith(".00")) {
            return newPrice.substring(0, newPrice.length() - 3);
        } else {
            return newPrice;
        }
    }
    public static String getPrice(int price) {
        DecimalFormat df = new DecimalFormat("0.00");
        String newPrice = String.valueOf(df.format((price/100d)));
        if (newPrice.endsWith(".00")) {
            return newPrice.substring(0, newPrice.length() - 3);
        } else {
            return newPrice;
        }
    }
    public static String getPrice(long price) {
        DecimalFormat df = new DecimalFormat("0.00");
        String newPrice = String.valueOf(df.format((price/100d)));
        if (newPrice.endsWith(".00")) {
            return newPrice.substring(0, newPrice.length() - 3);
        } else {
            return newPrice;
        }
    }
}
