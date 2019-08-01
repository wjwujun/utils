package com.utils.tools.excel;

import com.utils.tools.utils.DateTime;
import com.utils.tools.utils.excel.ExcelDownload;
import com.utils.tools.utils.excel.POIUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

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

    //导出数据和跳转页面
    public String packageExcel(List<Map<String,Object>> list) throws Exception{
        HSSFWorkbook wb = POIUtils.newWorkbook();//创建Excel

        HSSFCellStyle titleStyle = POIStyle.titleCellStyle(wb);//标题栏样式
        HSSFCellStyle contentStyle = POIStyle.contentCellStyle(wb);//表格内容样式



        HSSFCellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(HSSFColor.RED.index);
        style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());//设置背景色  黄色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  //设置背景色

        style.setAlignment(HorizontalAlignment.CENTER); // 居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直

        //A级客户背景色
        HSSFCellStyle styleRed = wb.createCellStyle();
        styleRed.setFillForegroundColor(HSSFColor.RED.index);
        styleRed.setFillForegroundColor(IndexedColors.RED.getIndex());//设置背景色 红色
        styleRed.setFillPattern(FillPatternType.SOLID_FOREGROUND);  //设置背景色
        styleRed.setAlignment(HorizontalAlignment.CENTER); // 居中
        styleRed.setVerticalAlignment(VerticalAlignment.CENTER);//垂直


        //企业用户BCD级用户背景色
        HSSFCellStyle styleGreen = wb.createCellStyle();
        styleGreen.setFillForegroundColor(HSSFColor.RED.index);
        styleGreen.setFillForegroundColor(IndexedColors.DARK_YELLOW.getIndex());//设置背景色
        styleGreen.setFillPattern(FillPatternType.SOLID_FOREGROUND);  //设置背景色
        styleGreen.setAlignment(HorizontalAlignment.CENTER); // 居中
        styleGreen.setVerticalAlignment(VerticalAlignment.CENTER);//垂直




        //默认cell样式
        HSSFCellStyle styleDefault = wb.createCellStyle();
        styleDefault.setAlignment(HorizontalAlignment.CENTER); // 居中
        styleDefault.setVerticalAlignment(VerticalAlignment.CENTER);//垂直
        HSSFSheet sheet = POIUtils.createSheet(wb, "用户配送行为分析");//创建sheet
        sheet.setDefaultColumnWidth(12);             //设置宽度
        sheet.setDefaultRowHeight((short)800);         //设置高度




        //第1行
        HSSFRow row0 = POIUtils.createRow(sheet, 0);//创建行

        HSSFCell likeCell = row0.createCell((short)16);

        //设置跳转页面
        likeCell.setCellValue("dddddd");
        HSSFHyperlink link = wb.getCreationHelper().createHyperlink(HyperlinkType.DOCUMENT);
        // "#"表示本文档    "明细页面"表示sheet页名称  "A10"表示第几列第几行
        link.setAddress("#明细页面!A10");
        likeCell.setHyperlink(link);
        /* 设置为超链接的样式*/
        HSSFCellStyle linkStyle = wb.createCellStyle();
        HSSFFont cellFont= wb.createFont();
        cellFont.setUnderline((byte) 1);
        cellFont.setColor(HSSFColor.BLUE.index);
        linkStyle.setFont(cellFont);
        likeCell.setCellStyle(linkStyle);



        POIUtils.createCell(row0, 0, "源本理菜宝运营数据统计", titleStyle);
        POIUtils.addMergedRegion(sheet, 0, 0, 0,13);


        //第2行
        HSSFRow row1= POIUtils.createRow(sheet,1);
        row1.setHeight((short) 500);
        POIUtils.createCell(row1,0,"搜索条件",contentStyle);
        POIUtils.addMergedRegion(sheet,1,1,0,5);
        POIUtils.createCell(row1,6,"代理商：",contentStyle);
        POIUtils.addMergedRegion(sheet,1,1,6,7);

        POIUtils.createCell(row1,8,"成都代理商",contentStyle);
        POIUtils.addMergedRegion(sheet,1,1,8,9);

        POIUtils.createCell(row1,10,"年份：",contentStyle);
        POIUtils.addMergedRegion(sheet,1,1,10,11);
        POIUtils.createCell(row1,12,"2019",contentStyle);
        POIUtils.addMergedRegion(sheet,1,1,12,13);


        //第3行
        HSSFRow row2= POIUtils.createRow(sheet,2);
        POIUtils.createCell(row2,0,"日期",contentStyle);
        POIUtils.addMergedRegion(sheet,2,2,0,1);

        for (int i = 1; i < 13; i++) {
            POIUtils.createCell(row2,i+1,i+"月",contentStyle);
        }

        //第4行
        HSSFRow row3= POIUtils.createRow(sheet,3);
        POIUtils.createCell(row3,0,"增长用户数",contentStyle);
        POIUtils.addMergedRegion(sheet,3,3,0,1);

        //第5行
        HSSFRow row4= POIUtils.createRow(sheet,4);
        POIUtils.createCell(row4,0,"总用户数",contentStyle);
        POIUtils.addMergedRegion(sheet,4,4,0,1);

        //第6行
        HSSFRow row5= POIUtils.createRow(sheet,5);
        POIUtils.createCell(row5,0,"标准版用户数",contentStyle);
        POIUtils.addMergedRegion(sheet,5,10,0,0);
        POIUtils.createCell(row5,1,"总数",contentStyle);

        HSSFRow row6= POIUtils.createRow(sheet,6);
        POIUtils.createCell(row6,1,"活跃用户数",contentStyle);

        HSSFRow row7= POIUtils.createRow(sheet,7);
        POIUtils.createCell(row7,1,"A级用户数",styleRed);

        HSSFRow row8= POIUtils.createRow(sheet,8);
        POIUtils.createCell(row8,1,"B级用户数",style);

        HSSFRow row9= POIUtils.createRow(sheet,9);
        POIUtils.createCell(row9,1,"C级用户数",style);

        HSSFRow row10= POIUtils.createRow(sheet,10);
        POIUtils.createCell(row10,1,"D级用户数",style);

        //专业版用户数
        HSSFRow row11= POIUtils.createRow(sheet,11);
        POIUtils.createCell(row11,0,"专业版用户数",contentStyle);
        POIUtils.addMergedRegion(sheet,11,16,0,0);
        POIUtils.createCell(row11,1,"总数",contentStyle);

        HSSFRow row12= POIUtils.createRow(sheet,12);
        POIUtils.createCell(row12,1,"活跃用户数",contentStyle);

        HSSFRow row13= POIUtils.createRow(sheet,13);
        POIUtils.createCell(row13,1,"A级用户数",styleRed);

        HSSFRow row14= POIUtils.createRow(sheet,14);
        POIUtils.createCell(row14,1,"B级用户数",style);

        HSSFRow row15= POIUtils.createRow(sheet,15);
        POIUtils.createCell(row15,1,"C级用户数",style);

        HSSFRow row16= POIUtils.createRow(sheet,16);
        POIUtils.createCell(row16,1,"D级用户数",style);


        //企业版用户数
        HSSFRow row17= POIUtils.createRow(sheet,17);
        POIUtils.createCell(row17,0,"企业版用户数",contentStyle);
        POIUtils.addMergedRegion(sheet,17,22,0,0);
        POIUtils.createCell(row17,1,"总数",contentStyle);

        HSSFRow row18= POIUtils.createRow(sheet,18);
        POIUtils.createCell(row18,1,"活跃用户数",contentStyle);

        HSSFRow row19= POIUtils.createRow(sheet,19);
        POIUtils.createCell(row19,1,"A级用户数",styleRed);

        HSSFRow row20= POIUtils.createRow(sheet,20);
        POIUtils.createCell(row20,1,"B级用户数",styleGreen);

        HSSFRow row21= POIUtils.createRow(sheet,21);
        POIUtils.createCell(row21,1,"C级用户数",styleGreen);

        HSSFRow row22= POIUtils.createRow(sheet,22);
        POIUtils.createCell(row22,1,"D级用户数",styleGreen);

        /******************明细*******************/
        HSSFSheet sheetDetail = POIUtils.createSheet(wb, "明细页面" );//创建sheet
        sheetDetail.setDefaultColumnWidth(12);             //设置宽度
        sheetDetail.setDefaultRowHeight((short)200);         //设置高度

        sheetDetail.createRow(0);

        //第1行
        HSSFRow rowDetail0 = POIUtils.createRow(sheetDetail, 0);//创建行
        POIUtils.createCell(rowDetail0, 0, "源本理菜宝", titleStyle);
        POIUtils.addMergedRegion(sheetDetail, 0, 0, 0,13);


        return super.createExportFileOSS(multipart, wb, ossUtil,ossFolderTmp);
    }



}
