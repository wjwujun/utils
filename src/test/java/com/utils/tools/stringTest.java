package com.utils.tools;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.utils.tools.entity.pageCount;
import com.utils.tools.utils.fileUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class stringTest {
    public class AA{
        String name;
        String sex;
        int  age;

        public AA(String name, String sex, int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        @Override
        public String toString() {
            return "AA{" +
                    "name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
     @Test
     public  void  stringTest() throws IOException {
         pageCount pa1 = new pageCount();
         pa1.setPage("1");
         pa1.setCount(10);
         pageCount pa2 = new pageCount();
         pa2.setPage("1");
         pa2.setCount(20);

         System.out.println(pa2.compareTo(pa1));

     }

    @Test
    public void stringJson(){
        //json字符串-简单对象型与JSONObject之间的转换
         String  JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";
        /*数据源为json*/
       JSONObject jsonObject = JSON.parseObject(JSON_OBJ_STR);
        System.out.println(jsonObject.getString("studentName")+":"+jsonObject.getInteger("studentAge"));


    }


}
