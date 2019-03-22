package com.utils.tools.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class fileUtils {

    /*
    *写入内容
    * 如果指定的文件不存在则新建。
    * */
    public static void writeFile(String filePath,String data) throws IOException {
        File file = new File(filePath);
        FileUtils.write(file, data,"UTF-8",true);
    }

    /*
    * 追加写入
    * 如果指定的文件不存在则新建。
    * */
    public static void writeAppend(String filePath,List<String> lines) throws IOException {
        File file = new File(filePath);
        FileUtils.writeLines(file,lines,true);
    }
    /*
     * 写入字符串
     * 向指定的文件中写入字符串，如果指定的文件不存在则新建。
     * */
    public static void writeStr(String filePath,String Str) throws IOException {
        File file = new File(filePath);
        FileUtils.writeStringToFile(file,Str, "UTF-8",true);
    }


    /*
    * 全部读取
    * 内容全部在内存
    * */
    public static String readFile(String filePath,String Str) throws IOException {
        File file = new File(filePath);
        String s = FileUtils.readFileToString(file, "UTF-8");
        return   s;
    }

    /*
    *
    * 全部读取返回lst
    * */
    /*
     * 全部读取
     * 内容全部在内存
     * */
    public static List<String> readLine(String filePath,String Str) throws IOException {
        File file = new File(filePath);
        List<String> strings = FileUtils.readLines(file, "UTF-8");
        return   strings;
    }

    /*
    * 迭代器
    * 一行一行读取，不用将内容全部放在内存中
    * */
    public void readIterator(String filePath) throws IOException {
        File file = new File(filePath);
         LineIterator it = FileUtils.lineIterator(file, "UTF-8");
         try {
                while (it.hasNext()) {
                    System.out.println("读取开始>>>>>>>>>>>>>>>>>");
                    String line = it.nextLine();
                    System.out.println(line);
                }
         } finally {
                LineIterator.closeQuietly(it);
         }
        System.out.println("读取结束>>>>>>>>>>>>>>>>>");
    }
}
