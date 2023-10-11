package com.heylichen.commons;

import org.junit.Test;

public class BitArithmetic {

    @Test
    public void addTest() {
        System.out.println(add(121, 222));
        System.out.println(add(19, 37));
    }

    @Test
    public void subtractTest() {
        System.out.println(subtract(343, 121));
        System.out.println(subtract(56, 37));
    }

    @Test
    public void multiplyTest() {
        System.out.println(multiply(3, 7));
        System.out.println(multiply(-3, 19));
        System.out.println(multiply(3, -19));
    }

    @Test
    public void divideTest() {
        System.out.println(divide(22, 7));
        System.out.println(divide(21, 7));
        System.out.println(divide(-57, 19));
        System.out.println(divide(57, -19));
    }

    private int add(int a, int b) {
        int part1 = a ^ b;
        int part2 = (a & b) << 1;
        if (part2 == 0) {
            return part1;
        } else {
            return add(part1, part2);
        }
    }

    private int subtract(int a, int b) {
        int minusB = minus(b);
        return add(a, minusB);
    }

    private int multiply(int a, int b) {
        boolean positiveResult = isResultPositive(a, b);
        a = forcePositive(a);
        b = forcePositive(b);

        int result = 0;
        int i = 0;
        while (b > 0) {
            if ((b & 1) > 0) {
                result = add(result, a << i);
            }
            b = b >>> 1;
            i = add(i, 1);
        }
        if (!positiveResult) {
            result = minus(result);
        }
        return result;
    }

    private int minus(int b) {
        return add(~b, 1);
    }

    private int divide(int a, int b) {
        boolean positiveResult = isResultPositive(a, b);
        a = forcePositive(a);
        b = forcePositive(b);
        int result = dividePositive(a, b);
        if (positiveResult) {
            return result;
        } else {
            return minus(result);
        }
    }

    private int dividePositive(int a, int b) {
        if (a < b) {
            return 0;
        }
        return 1 + dividePositive(subtract(a, b), b);
    }

    private boolean isResultPositive(int a, int b) {
        int positiveA = a > 0 ? 1 : 0;
        int positiveB = b > 0 ? 1 : 0;
        return (positiveA ^ positiveB) > 0 ? false : true;
    }

    private int forcePositive(int a) {
        return a > 0 ? a : minus(a);
    }
}
