package com.utils.tools;


import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class stringTest {

     @Test
     public  void  stringTest(){
         Calendar calendar = Calendar.getInstance();
         calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) -30);
         Date today = calendar.getTime();
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String result = format.format(today);
         System.out.println("111111111111111111");
         System.out.println(result);

     }

}
