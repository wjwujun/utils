package com.utils.tools;


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

     @Test
     public  void  stringTest() throws IOException {
         LineIterator it = FileUtils.lineIterator(new File("E:/cxyapi.txt"), "UTF-8");

         try {
             while (it.hasNext()) {
                 System.out.println("1111111111111");
                 String line = it.nextLine();
                 System.out.println(line);
             }
         } finally {
             LineIterator.closeQuietly(it);
         }

     }



}
