package com.utils.tools.utils.excel;

import com.utils.tools.utils.DateTime;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/***
 * excel下载导出
 * @author wj
 * @date 2019/4/9 0009 11:07
 * @param
 */
public class ExcelDownload {
    //设置excel文件头部
    public  static void createTitle(HSSFWorkbook wb, List<Map<String, Object>> list, List<String> listName){
        HSSFSheet sheet = wb.createSheet("参加活动公司");
        //设置字体颜色
        Font fontTitle = wb.createFont();
        fontTitle.setFontHeightInPoints((short) 14); //字体大小
        fontTitle.setColor(HSSFColor.BROWN.index); //字体颜色
        fontTitle.setFontName("宋体"); //字体
        //设置标题单元格类型
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFont(fontTitle);
        cellStyle.setFillForegroundColor(IndexedColors.LIME.getIndex());
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直
        cellStyle.setWrapText(true);//设置自动换行w
        
        sheet.autoSizeColumn(1, true);//自适应宽度

        //活动名称
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 3);// 合并单元格,从下标从0开始，参数：起始行号 终止行号 起始列号 终止列号
        sheet.addMergedRegion(region);   //在sheet里增加合并单元格
        HSSFRow row1 = sheet.createRow(0);
        row1.setHeightInPoints(20);   //设置高度
        HSSFCell cell = row1.createCell(0);
        cell.setCellStyle(cellStyle);
        if(list.get(0).get("activityName")==null){
            cell.setCellValue("活动名称为空，请检查！");
        }else {
            cell.setCellValue(list.get(0).get("activityName").toString());
        }

        //活动地址，活动时间
        CellRangeAddress region1 =  new CellRangeAddress(1, 1, 0, 1);
        CellRangeAddress region2 =  new CellRangeAddress(1, 1, 2, 3);
        sheet.addMergedRegion(region1);   //0-1
        sheet.addMergedRegion(region2);   //2-3
        Row row2 = sheet.createRow(1);
        row2.setHeightInPoints(20);  //设置高度
        Cell cell1 = row2.createCell(0);
        cell1.setCellStyle(cellStyle);
        cell1.setCellValue("活动时间");//  设置内容
        Cell cell2 = row2.createCell(2);
        cell2.setCellStyle(cellStyle);
        cell2.setCellValue("活动地点");//  设置内容

        CellRangeAddress region3 = new CellRangeAddress(2, 2, 0, 1);
        CellRangeAddress region4 = new CellRangeAddress(2, 2, 2, 3);
        sheet.addMergedRegion(region3);   //0-1
        sheet.addMergedRegion(region4);   //2-3
        HSSFRow row3 = sheet.createRow(2);
        row3.setHeightInPoints(20);    //设置高度
        HSSFCell cell3 = row3.createCell(0);
        cell3.setCellStyle(cellStyle);

        if(list.get(0).get("activityTime")==null){
            cell3.setCellValue(DateTime.convertDateTime(new Date(), "yyyy-MM-dd HH:mm:ss")+"<活动时间没有填写>");
        }else{
            cell3.setCellValue(list.get(0).get("activityTime").toString());
        }
        HSSFCell cell4 = row3.createCell(2);
        cell4.setCellStyle(cellStyle);
        if(list.get(0).get("activityAddr")==null) {
            cell4.setCellValue("源本生鲜<活动地址没有填写>");
        }else {
            cell4.setCellValue(list.get(0).get("activityAddr").toString());
        }
        //设置内容标题
        HSSFCellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(HorizontalAlignment.CENTER); // 居中
        cellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);//垂直
        
        HSSFRow row4 = sheet.createRow(3);
        for (int i = 0; i < listName.size(); i++) {
            sheet.setColumnWidth(i, 5000);   //设置宽度
            HSSFCell cell5 = row4.createCell(i);
            cell5.setCellStyle(cellStyle1);
            cell5.setCellValue(listName.get(i));

        }
        //新增数据行，并且设置单元格数据
        int rowNum=4;
        for (Map<String, Object> map : list) {
            HSSFRow row5 = sheet.createRow(rowNum);
            HSSFCell cell5 = row5.createCell(0);
            cell5.setCellStyle(cellStyle1);
            cell5.setCellValue(rowNum-3);

            HSSFCell cell6 = row5.createCell(1);
            cell6.setCellStyle(cellStyle1);
            cell6.setCellValue(map.get("companyName").toString());

            HSSFCell cell7 = row5.createCell(2);
            cell7.setCellStyle(cellStyle1);
            cell7.setCellValue(map.get("enterName").toString());

            HSSFCell cell8 = row5.createCell(3);
            cell8.setCellStyle(cellStyle1);
            cell8.setCellValue(map.get("enterTel").toString());

            rowNum++;
        }
    }

    //生成excel文件
    public static String buildExcelFile(String filename, HSSFWorkbook workbook){
        try {
            //文件存在删除
            File infile = new File(filename);
            if (infile.exists()) {
                infile.delete();
            }
            //写入内容
            FileOutputStream fos = null;

            fos = new FileOutputStream(filename);
            workbook.write(fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return filename;
    }

    //浏览器下载excel
    public static void buildExcelDocument(String filename, HSSFWorkbook workbook, HttpServletResponse response) throws Exception{
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }


}
