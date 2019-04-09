package com.utils.tools.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.utils.tools.utils.DateTime;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;


/**
 * POI工具类 依赖库：poi-3.9-20121203.jar 用于生成和读取EXCEL内容
 *
 * @author caojian
 */
public class POIUtils {

  /*************************************写入Excel文件**************************************/

  /**
   * 创建新的Excel工作单
   */
  public static HSSFWorkbook newWorkbook() {
    return new HSSFWorkbook();
  }

  /**
   * 创建新的sheet
   */
  public static HSSFSheet createSheet(HSSFWorkbook wb, String sheetName) {
    return wb.createSheet(sheetName);
  }

  /**
   * 创建新的sheet，并指定列宽度
   */
  public static HSSFSheet createSheet(HSSFWorkbook wb, String sheetName, int[] cellWidths) {
    HSSFSheet sheet = wb.createSheet(sheetName);
    if (cellWidths != null && cellWidths.length > 0) {
      for (int i = 0; i < cellWidths.length; i++) {
        sheet.setColumnWidth(i, cellWidths[i] * 256);
      }
    }
    return sheet;
  }
  
  public static HSSFSheet createSheet(HSSFWorkbook wb, String sheetName, List<Integer> cellWidths) {
    HSSFSheet sheet = wb.createSheet(sheetName);
    if (cellWidths != null && cellWidths.size() > 0) {
      for (int i = 0; i < cellWidths.size(); i++) {
        sheet.setColumnWidth(i, cellWidths.get(i).intValue() * 256);
      }
    }
    return sheet;
  }

  public static Sheet createSheet(Workbook wb, String sheetName, int[] cellWidths) {
    Sheet sheet = wb.createSheet(sheetName);
    if (cellWidths != null && cellWidths.length > 0) {
      for (int i = 0; i < cellWidths.length; i++) {
        sheet.setColumnWidth(i, cellWidths[i] * 256);
      }
    }
    return sheet;
  }

  /**
   * 单元格合并设置
   */
  public static int addMergedRegion(HSSFSheet sheet, int firstRow, int lastRow, int firstCol,
      int lastCol) {
    return sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
  }

  public static int addMergedRegion(Sheet sheet, int firstRow, int lastRow, int firstCol,
      int lastCol) {
    return sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
  }

  /**
   * 创建一行表格
   *
   * @param rowNum 索引，从0开始
   */
  public static HSSFRow createRow(HSSFSheet sheet, int rowNum) {
    HSSFRow row = sheet.createRow(rowNum);
    row.setHeightInPoints(20);
    return row;
  }

  public static Row createRow(Sheet sheet, int rowNum) {
    Row row = sheet.createRow(rowNum);
    row.setHeightInPoints(20);
    return row;
  }

  /**
   * 创建一行表格，指定高度
   */
  public static HSSFRow createRow(HSSFSheet sheet, int rowNum, float height) {
    HSSFRow row = sheet.createRow(rowNum);
    row.setHeightInPoints(height);
    return row;
  }

  /**
   * 创建一个表格
   *
   * @param column 索引，从0开始
   */
  public static HSSFCell createCell(HSSFRow row, int column) {
    return row.createCell(column);
  }

  /**
   * 创建一个表格 指定样式和表格内容
   */
  public static HSSFCell createCell(HSSFRow row, int column, String value, HSSFCellStyle style) {
    HSSFCell cell = row.createCell(column);
    cell.setCellValue(value);
    cell.setCellStyle(style);
    return cell;
  }

  /**
   * 创建一个表格 指定样式和表格内容
   *
   * @param value 数字数据
   */
  @SuppressWarnings("static-access")
  public static HSSFCell createCell(HSSFRow row, int column, double value, HSSFCellStyle style,
      HSSFDataFormat df) {
    HSSFCell cell = row.createCell(column);
    cell.setCellValue(value);
    style.setDataFormat(df.getBuiltinFormat("#,##0.00"));//保留两位小数点
    cell.setCellStyle(style);
    return cell;
  }

  public static Cell createCell(Row row, int column, String value, CellStyle style) {
    Cell cell = row.createCell(column);
    cell.setCellValue(value);
    cell.setCellStyle(style);
    return cell;
  }

  public static Cell createCell(Row row, int column, double value, CellStyle style, DataFormat df) {
    Cell cell = row.createCell(column);
    style.setDataFormat(df.getFormat("#,##0.00"));//保留两位小数点
    cell.setCellValue(value);
    cell.setCellStyle(style);
    return cell;
  }

  /*************************************读取Excel文件**************************************/

  /**
   * 读取Excel sheet
   */
  @SuppressWarnings("resource")
  public static HSSFSheet readExcelSheet(String file, int sheetIndex) {
    HSSFSheet sheet = null;
    try {
      POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
      HSSFWorkbook wb = new HSSFWorkbook(fs);
      sheet = wb.getSheetAt(sheetIndex);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sheet;
  }

  @SuppressWarnings("resource")
  public static HSSFSheet readExcelSheet(File file, int sheetIndex) {
    HSSFSheet sheet = null;
    try {
      POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
      HSSFWorkbook wb = new HSSFWorkbook(fs);
      sheet = wb.getSheetAt(sheetIndex);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sheet;
  }

  @SuppressWarnings("resource")
  public static HSSFSheet readExcelSheet(FileInputStream fis, int sheetIndex) {
    HSSFSheet sheet = null;
    try {
      POIFSFileSystem fs = new POIFSFileSystem(fis);
      HSSFWorkbook wb = new HSSFWorkbook(fs);
      sheet = wb.getSheetAt(sheetIndex);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sheet;
  }

  /**
   * 读取一个表格字符串内容
   */
  public static String readCellValue(HSSFSheet sheet, int rowIndex, int cellIndex) {
    if (sheet != null) {
      HSSFRow row = sheet.getRow(rowIndex);
      if (row != null) {
        HSSFCell cell = row.getCell(cellIndex);
        return getStringCellValue(cell);
      } else {
        return "";
      }
    } else {
      return "";
    }
  }

  /**
   * 读取一个表格字符串内容 指定日期格式
   */
  public static String readCellValue(HSSFSheet sheet, int rowIndex, int cellIndex,
      String dateFormat) {
    if (sheet != null) {
      HSSFRow row = sheet.getRow(rowIndex);
      if (row != null) {
        HSSFCell cell = row.getCell(cellIndex);
        return getStringCellValue(cell, dateFormat);
      } else {
        return "";
      }
    } else {
      return "";
    }
  }

  /**
   * 读取一行表格内容
   */
  public static ArrayList<String> readRowValue(HSSFSheet sheet, int rowIndex, boolean filtEmpty) {
    ArrayList<String> result = null;
    if (sheet != null) {
      HSSFRow row = sheet.getRow(rowIndex);
      if (row != null) {
        int b = row.getFirstCellNum();
        int e = row.getLastCellNum();
        if (b >= 0 && e > b) {
          result = new ArrayList<String>();
          for (int i = b; i < e; i++) {
            HSSFCell cell = row.getCell(i);
            String v = getStringCellValue(cell);
            if (v != null) {
              v = v.trim();
              if (filtEmpty) {
                if (!"".equals(v)) {
                  result.add(v);
                }
              } else {
                result.add(v);
              }
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * 读取一行表格内容 指定日期格式
   */
  public static ArrayList<String> readRowValue(HSSFSheet sheet, int rowIndex, boolean filtEmpty,
      String dateFormat) {
    ArrayList<String> result = null;
    if (sheet != null) {
      HSSFRow row = sheet.getRow(rowIndex);
      if (row != null) {
        int b = row.getFirstCellNum();
        int e = row.getLastCellNum();
        if (b >= 0 && e > b) {
          result = new ArrayList<String>();
          for (int i = b; i < e; i++) {
            HSSFCell cell = row.getCell(i);
            String v = getStringCellValue(cell, dateFormat);
            if (v != null) {
              v = v.trim();
              if (filtEmpty) {
                if (!"".equals(v)) {
                  result.add(v);
                }
              } else {
                result.add(v);
              }
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * 读取一行表格内容 指定日期格式 指定开始列和结束列索引
   */
  public static ArrayList<String> readRowValue(HSSFSheet sheet, int rowIndex, int startCellIndex,
      int endCellIndex, boolean filtEmpty, String dateFormat) {
    ArrayList<String> result = null;
    if (sheet != null) {
      HSSFRow row = sheet.getRow(rowIndex);
      if (row != null) {
        if (startCellIndex >= 0 && endCellIndex > startCellIndex) {
          result = new ArrayList<String>();
          for (int i = startCellIndex; i < endCellIndex; i++) {
            HSSFCell cell = row.getCell(i);
            String v = getStringCellValue(cell, dateFormat);
            if (v != null) {
              v = v.trim();
              if (filtEmpty) {
                if (!"".equals(v)) {
                  result.add(v);
                }
              } else {
                result.add(v);
              }
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * 读取一张表格的数据
   */
  public static ArrayList<ArrayList<String>> readSheetData(HSSFSheet sheet, int startRowIndex,
      boolean filtEmpty) {
    ArrayList<ArrayList<String>> result = null;
    if (sheet != null) {
      int lastRowIndex = sheet.getLastRowNum();
      if (startRowIndex >= 0 && lastRowIndex >= startRowIndex) {
        result = new ArrayList<ArrayList<String>>();
        for (int i = startRowIndex; i <= lastRowIndex; i++) {
          ArrayList<String> lt = readRowValue(sheet, i, filtEmpty);
          if (lt != null && !lt.isEmpty()) {
            boolean b = false;
            for (String s : lt) {
              if (s != null && !"".equals(s.trim())) {
                b = true;
                break;
              }
            }
            if (b) {
              result.add(lt);
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * 读取一张表格的数据 指定日期格式
   */
  public static ArrayList<ArrayList<String>> readSheetData(HSSFSheet sheet, int startRowIndex,
      boolean filtEmpty, String dateFormat) {
    ArrayList<ArrayList<String>> result = null;
    if (sheet != null) {
      int lastRowIndex = sheet.getLastRowNum();
      if (startRowIndex >= 0 && lastRowIndex >= startRowIndex) {
        result = new ArrayList<ArrayList<String>>();
        for (int i = startRowIndex; i <= lastRowIndex; i++) {
          ArrayList<String> lt = readRowValue(sheet, i, filtEmpty, dateFormat);
          if (lt != null && !lt.isEmpty()) {
            boolean b = false;
            for (String s : lt) {
              if (s != null && !"".equals(s.trim())) {
                b = true;
                break;
              }
            }
            if (b) {
              result.add(lt);
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * 读取一张表格的数据 指定日期格式 指定表头，已表头为标准读取行数据
   */
  public static ArrayList<ArrayList<String>> readSheetData(HSSFSheet sheet, int headRowIndex,
      int startRowIndex, boolean filtEmpty, String dateFormat) {
    ArrayList<ArrayList<String>> result = null;
    if (sheet != null) {
      HSSFRow headRow = sheet.getRow(headRowIndex);
      int bc = headRow.getFirstCellNum();//开始列索引
      int ec = headRow.getLastCellNum();//结束列索引
      //System.out.println(bc+"~"+ec);
      int lastRowIndex = sheet.getLastRowNum();//结束行索引
      if (startRowIndex >= 0 && lastRowIndex >= startRowIndex) {
        result = new ArrayList<ArrayList<String>>();
        for (int i = startRowIndex; i <= lastRowIndex; i++) {
          ArrayList<String> lt = readRowValue(sheet, i, bc, ec, filtEmpty, dateFormat);
          if (lt != null && !lt.isEmpty()) {
            boolean b = false;
            for (String s : lt) {
              if (s != null && !"".equals(s.trim())) {
                b = true;
                break;
              }
            }
            if (b) {
              result.add(lt);
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * 获取表格字符串内容
   */
  @SuppressWarnings("deprecation")
  public static String getStringCellValue(Cell cell) {
    if (cell != null) {
      String cellValue = "";
      switch (cell.getCellType()) {

        case HSSFCell.CELL_TYPE_STRING://字符串
          cellValue = cell.getRichStringCellValue().getString().trim();
          break;

        case HSSFCell.CELL_TYPE_NUMERIC://数字或日期
          if (HSSFDateUtil.isCellDateFormatted(cell)) {
            //日期
            cellValue = DateTime.convertDateTime(cell.getDateCellValue(), "yyyy-MM-dd HH:mm:ss");
          } else {
            //数字
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellValue = cell.getRichStringCellValue().getString().trim();
          }
          break;

        case HSSFCell.CELL_TYPE_BOOLEAN://布尔
          cellValue = String.valueOf(cell.getBooleanCellValue());
          break;

        case HSSFCell.CELL_TYPE_FORMULA://公式
          cellValue = cell.getCellFormula();
          break;

        case HSSFCell.CELL_TYPE_BLANK://空值
          cellValue = "";
          break;

        case HSSFCell.CELL_TYPE_ERROR://错误
          cellValue = "";
          break;

        default://默认值
          cell.setCellType(HSSFCell.CELL_TYPE_STRING);
          cellValue = cell.getRichStringCellValue().getString().trim();
          break;
      }

      return cellValue;
    } else {
      return "";
    }
  }

  /**
   * 获取表格字符串内容 指定日期格式
   */
  @SuppressWarnings("deprecation")
  private static String getStringCellValue(HSSFCell cell, String dateFormat) {
    if (cell != null) {
      String cellValue = "";
      switch (cell.getCellType()) {

        case HSSFCell.CELL_TYPE_STRING://字符串
          cellValue = cell.getRichStringCellValue().getString();
          break;

        case HSSFCell.CELL_TYPE_NUMERIC://数字或日期
          if (HSSFDateUtil.isCellDateFormatted(cell)) {
            //日期
            cellValue = DateTime.convertDateTime(cell.getDateCellValue(), dateFormat);
          } else {
            //数字
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellValue = cell.getRichStringCellValue().getString();
          }
          break;

        case HSSFCell.CELL_TYPE_BOOLEAN://布尔
          cellValue = String.valueOf(cell.getBooleanCellValue());
          break;

        case HSSFCell.CELL_TYPE_FORMULA://公式
          cellValue = cell.getCellFormula();
          break;

        case HSSFCell.CELL_TYPE_BLANK://空值
          cellValue = "";
          break;

        case HSSFCell.CELL_TYPE_ERROR://错误
          cellValue = "";
          break;

        default://默认值
          cell.setCellType(HSSFCell.CELL_TYPE_STRING);
          cellValue = cell.getRichStringCellValue().getString();
          break;
      }

      return cellValue;
    } else {
      return "";
    }
  }

}
