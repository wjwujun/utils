package com.utils.tools.utils;

import java.math.BigDecimal;

/**
 * Double计算类，精确到小数点两位,不考虑正负
 */
public class DoubleMath {
    private static int SCALE = 2;
    //加
    public static double add(double d1, double d2){
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.add(b2).setScale(SCALE,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public static double add(double d1, double d2,int scale){
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.add(b2).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    //减
    public static double sub(double d1, double d2){
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.subtract(b2).setScale(SCALE,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    //乘
    public static double mul(double d1, double d2){
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.multiply(b2).setScale(SCALE,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    //乘
    public static double mul(double d1, double d2,int scale){
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.multiply(b2).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    //除
    public static double div(double d1, double d2){
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2,SCALE,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public static double div(double d1, double d2,int scale){
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
