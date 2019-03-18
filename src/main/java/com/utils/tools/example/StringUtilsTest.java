package com.utils.tools.example;

import org.apache.commons.lang.StringUtils;

public class StringUtilsTest {


    public static void main(String[] args) {
        demo1();
    }



    private static void demo1() {
        /*1.字符串以prefix开始*/
        StringUtils.startsWith("sssdf","");//结果是：true
        StringUtils.startsWith("sssdf","");//结果是：true
        StringUtils.startsWith("sssdf","s");//结果是：true
        StringUtils.startsWith("sssdf","ss");//结果是：true
        StringUtils.startsWith("sssdf","sss");//结果是：true
        StringUtils.startsWith("sssdf","sssdf");//结果是：true
        StringUtils.startsWith("sssdf","f");//结果是：false
        StringUtils.startsWith("sssdf","sssdf");//结果是：true


    }


}
