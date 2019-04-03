package com.utils.tools;


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



}
