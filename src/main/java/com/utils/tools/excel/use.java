package com.utils.tools.excel;

import com.utils.tools.utils.DateTime;
import com.utils.tools.utils.excel.ExcelDownload;
import com.utils.tools.utils.excel.POIUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class use {

    //excel下载
    private static void demo1(HttpServletResponse response) throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        //创建Excel
        HSSFWorkbook wb = POIUtils.newWorkbook();
        //内容标题名称
        List<String> listName = new ArrayList<String>();
        listName.add("序号");
        listName.add("公司名称");
        listName.add("报名人名称");
        listName.add("报名人电话");
        ExcelDownload.createTitle(wb,list,listName);
        //导出的文件名称
        String fileName = "活动_"+ DateTime.convertDateTime(new Date(), "yyyy-MM-dd_HHmmss")+ ".xls";
        //生成excel文件
        String str = ExcelDownload.buildExcelFile(fileName, wb);
        if("error".equals(str)){
            return ;
        }
        //浏览器下载excel
        ExcelDownload.buildExcelDocument(fileName,wb,response);

        return ;
    }



}
