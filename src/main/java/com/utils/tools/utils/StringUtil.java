package com.utils.tools.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 */

public class StringUtil {
	
	/**
	 * 获取字符串字节数
	 * @param aString
	 * @return 字符串字节数
	 * @author AllenZhang
	 */
	public static int getByteAmountFromString(String aString) {
		int byteLength = 0;
		if (aString != null && aString.length() > 0) {
			byte[] _bytes = aString.getBytes();
			byteLength = _bytes.length;
		}
		return byteLength;
	}
	
    /**
     * 格式化 请求参数
     * @param parameter
     * @return
     * @throws Exception
     * @author AllenZhang
     */
    public static String formatParameter(String parameter) {
    	//清除空白字符，清除掉参数的首尾的空白字符，如果仅参数全由空白字符 (whitespace)组成则返回null
    	parameter = StringUtils.trimToNull(parameter);
    	
    	// "" 或 "\n \n\t" 或 null
   		if (StringUtils.isBlank(parameter)) {
   			parameter = "";
   		}
   		else if ("null".equalsIgnoreCase(parameter) || "\"null\"".equalsIgnoreCase(parameter)  || "'null'".equalsIgnoreCase(parameter)) {
   			parameter = "";
   		}
   		else if ("undefined".equalsIgnoreCase(parameter) || "\"undefined\"".equalsIgnoreCase(parameter)  || "'undefined'".equalsIgnoreCase(parameter)) {
   			parameter = "";
   		}
    	return parameter;
    }		
	
	/**
	 * Ajax 生成分页链接字符串
	 * @param recordTotal 总记录数
	 * @param recordTotalAPage 每页记录数
	 * @param pageIndex 第几页
	 * @param doSearchFunctionName 执行搜索的JS函数名称
	 * @param params 参数数组，用于传递参数值
	 * @param url 需要分页的地址
	 * @return 分页链接字符串
	 * @author AllenZhang
	 */
	public static String pageLinkString(int recordTotal, int recordTotalAPage, int pageIndex,
			                     String doSearchFunctionName) {
		String space= "&nbsp;&nbsp;";
		if (doSearchFunctionName == null) {
			doSearchFunctionName = "framework_doSearch";
		}
		else if ("".equals(doSearchFunctionName.trim())) {
			doSearchFunctionName = "framework_doSearch";
		}
		if (recordTotal > 0 && recordTotalAPage > 0 && pageIndex > 0) {
			int pages = recordTotal / recordTotalAPage;
			if ((recordTotal % recordTotalAPage) > 0) {
				pages++;
			}
			int prior = pageIndex - 1;
			if (prior <= 0) {
				prior = 1;
			}

			int next = pageIndex + 1;
			if (next >= pages) {
				next = pages;
			}
			if (pages > 1) {
				String reStr = createBoundForCutPage(pageIndex, 10, pages);
				int lowerLimit = Integer.valueOf(reStr.substring(0, reStr.indexOf("_")));
				int upperLimit = Integer.valueOf(reStr.substring(reStr.indexOf("_") + 1, reStr.length()));
				String pageHTML = "";
				for (int i = lowerLimit; i <= upperLimit ; i ++) {
					if (pageIndex != i) {
						pageHTML += "<a href='javascript:"+doSearchFunctionName+"("+i+");'>["+i+"]</a>" + "&nbsp;";
					}
					else {
						pageHTML += i + "&nbsp;&nbsp;";
					}
				}
				
				StringBuffer pageStr = new StringBuffer();
				pageStr.append("第&nbsp;"+pageIndex+"&nbsp;&nbsp;页&nbsp;/&nbsp;&nbsp;共&nbsp;"+pages+"&nbsp;&nbsp;页&nbsp;/&nbsp;&nbsp;共&nbsp;"+recordTotal+"&nbsp;&nbsp;条记录");
				pageStr.append(space);
				
				if (pageIndex > 1) {
					pageStr.append("<a href='javascript:void(0);' onclick='javascript:"+doSearchFunctionName+"(1);'>首页</a>");
				}
				else {
					pageStr.append("首页");
				}
				pageStr.append(space);
				
				
				if (pageIndex > 1) {
					pageStr.append("<a href='javascript:void(0);' onclick='javascript:"+doSearchFunctionName+"("+prior+");'>前页</a>");
				}
				else {
					pageStr.append("前页");
				}
				pageStr.append(space);
				
				if (!"".equals(pageHTML)) {
					pageStr.append(pageHTML);
					pageStr.append(space);
				}
				
				if (pageIndex < pages) {
					pageStr.append("<a href='javascript:void(0);' onclick='javascript:"+doSearchFunctionName+"("+next+");'>后页</a>");
				}
				else {
					pageStr.append("后页");
				}
				pageStr.append(space);
				
				if (pageIndex < pages) {
					pageStr.append("<a href='javascript:void(0);' onclick='javascript:"+doSearchFunctionName+"("+pages+");'>末页</a>");
				}
				else {
					pageStr.append("末页");
				}
				
				pageStr.append(space);
				
				String gotoPage = "跳转到，第 <input type='text' id='pageIndexForcreatePage' name='pageIndexForcreatePage' style='width:30px;height:14px;text-align:center;font-size:12px;' value='"+next+"' onkeypress='javascript:JSUtil.numberAllowed(event);' maxlength='10' /> 页 " +
						          " <input type='button' value='GO' class='gobutton' onclick='javascript:"+doSearchFunctionName+"(document.getElementById(\"pageIndexForcreatePage\").value);'/> ";
				pageStr.append(gotoPage);
				
				return pageStr.toString();
			}
			else {
				return "第 1 页 /"+space+"共 1 页 /"+space+"共 "+recordTotal+" 条记录";
			}
		}
		else {
			return "第 1 页 /"+space+"共 1 页 /"+space+"共 "+recordTotal+" 条记录";
		}
	}
	
	/**
	 * 生成分页链接区间生成优化算法
	 * 使用例子：
	 *   String reStr = createBoundForCutPage(180, 50, 1000);
	 *   int lowerLimit = Integer.valueOf(reStr.substring(0, reStr.indexOf("_")));
	 *   int upperLimit = Integer.valueOf(reStr.substring(reStr.indexOf("_") + 1, reStr.length()));
	 * 
	 *   结果
	 *   lowerLimit = 155
	 *   upperLimit = 205
	 *   
	 * @param currentPage 当前第几页
	 * @param bound 区间里最大显示数目
	 * @param totalPage 总共页数
	 * @return 优化后显示区间
	 * @author AllenZhang
	 */
	private static String createBoundForCutPage(int currentPage, int bound, int totalPage) {
		int lowerLimit = 0;
		int upperLimit = 0;
		int gap = 0;
		if (currentPage > 0 && bound > 0 && 
			totalPage > 0 && totalPage >= currentPage) {
			int boundHalf = bound / 2 + 1;
			if (currentPage < boundHalf) {
				lowerLimit = 1;
				if (totalPage > bound) {
					upperLimit = bound; 
				}
				else {
					upperLimit = totalPage;
				}
			}
			else if (currentPage > (totalPage - boundHalf)) {
				lowerLimit = totalPage - bound;
				if (lowerLimit <= 0) {
					lowerLimit = 1;
				}
				upperLimit = totalPage;
			}
			else {
				lowerLimit = currentPage - boundHalf;
				if (lowerLimit <= 0) {
					lowerLimit = 1;
				}
				upperLimit = currentPage + boundHalf;
				if (upperLimit >= totalPage) {
					upperLimit = totalPage;
				}
				gap = upperLimit - lowerLimit;
				if (gap > bound) {
					if (lowerLimit == 1) {
						upperLimit = upperLimit - (gap - bound); 
					}
					else if (upperLimit == totalPage) {
						lowerLimit = lowerLimit + (gap - bound);
					}
					else {
						lowerLimit = lowerLimit + ((gap - bound) / 2);
						upperLimit = upperLimit - ((gap - bound) / 2);
					}
				}
				else if (gap < bound) {
					if (lowerLimit == 1) {
						upperLimit = upperLimit + (gap - bound); 
					}
					else if (upperLimit == totalPage) {
						lowerLimit = lowerLimit - (gap - bound);
					}
					else {
						lowerLimit = lowerLimit - ((gap - bound) / 2);
						upperLimit = upperLimit + ((gap - bound) / 2);
					}
				}
			}
			while (upperLimit - lowerLimit >= bound) {
				lowerLimit++;
			}
		}
		return String.valueOf(lowerLimit) + "_" + String.valueOf(upperLimit);
	}
	
	/**
	 * 处理字符串中的空格
	 * @param aString
	 * @return
	 * @author AllenZhang
	 */
	public static String handleBlank(String aString) {
		if (aString != null && !"".equals(aString.trim())) {
			while (aString.indexOf("  ") > -1) {
				aString = aString.replace("  ", " ").replace("	", " ");
			}
			return aString.trim();
		}
		else {
			return "";
		}
	}
	
	/**
	 * 切字符串，把源字符串切为指定长度的字符串。
	 * @param sourceStr 源字符串
	 * @param strLength 指定长度
	 * @return 切后字符串
	 * @author AllenZhang
	 */
	public static String sliceString(String sourceStr, int strLength) {
		if (sourceStr != null) {
			if (strLength > 0 && sourceStr.length() > strLength) {
				return sourceStr.substring(0, strLength) + "...";
			}
			else {
				return sourceStr;
			}
		}
		else {
			return "";
		}
	}	

	/**
	 * 格式化字节数量
	 * @param numBytes
	 * @return 格式化后的字节数量
	 * @author AllenZhang
	 */
	public static String toByteFormat(float numBytes) {
		String[] labels = {"bytes", "KB", "MB", "GB"};
		int labelIndex = 0;
		while (labelIndex < labels.length - 1 && numBytes > 1024) {
			numBytes /= 1024;
			labelIndex++;
		}
		return (Math.round(numBytes * 10) / 10f) + labels[labelIndex];
	}
	
	/**
     * 包裹主键字符串 ep:"1,2"->"'1','2'"
     * @param ids
     * @return
     * @author ozd
     */
    public static String wrapIds(String ids) {
    	if(ids == null){
    		return null;
    	}
    	String[] idArr = ids.split(",");
        if (idArr == null || idArr.length <= 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < idArr.length; i++) {
            sb.append("'").append(idArr[i]).append("'");
            if (i != idArr.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
	
	/**
     * 包裹主键字符串 ep:"1,2"->"'1','2'"
     * @param ids
     * @return
     * @author ozd
     */
    public static String wrapIds(String[] ids) {
        if (ids == null || ids.length <= 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ids.length; i++) {
            sb.append("'").append(ids[i]).append("'");
            if (i != ids.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
    
    /**
     * 包裹主键字符串 ep:"1,2"->"'1','2'"
     * @param ids
     * @return
     * @author ozd
     */
    public static String wrapIds(List<String> idList) {
        if (idList == null || idList.size() <= 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < idList.size(); i++) {
            sb.append("'").append(idList.get(i)).append("'");
            if (i != idList.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
    
    /**
     * 判断是否有空值,有空值则返回false
     */
    public static <T> boolean isNotBlank(Object...val){
    	for (Object obj : val) {
			if(!isNotBlanks(obj)){
				return false;
			}
		}
    	return true;
    }
    
    /**
     * 判断是否为空
     */
    private static <T> boolean isNotBlanks(T val) {
    	if(val == null){
    		return false;
    	}
    	
    	// 字符串
    	if(val instanceof String){
    		if("".equals(val.toString().trim())){
    			return false;
    		}
    		return true;
    	}
    	
    	// 集合ArrayList
    	if(val instanceof List<?>){
    		if(((List<?>)val).isEmpty()){
    			return false;
    		}
    		return true;
    	}
    	
    	// 集合HashMap
    	if(val instanceof Map<?, ?>){
    		if(((Map<?, ?>)val).isEmpty()){
    			return false;
    		}
    		return true;
    	}
    	
    	// ...
    	
    	// 对象
    	if(val instanceof Object){
    		if("".equals(val.toString().trim())){
    			return false;
    		}
    		return true;
    	}
    	
        return false;
    }
    
    public static String decimalFormat(DecimalFormat df, Map<String, Object> map, String key){
		return decimalFormat(df, map, key, null);
	}
	
    public static String decimalFormat(DecimalFormat df, Map<String, Object> map, String key, String suffix){
		String str = "";
		if(map == null || map.isEmpty()){
			return str;
		}
		
		Object obj = map.get(key);
		if(obj == null){
			return "0";
		}
		if(obj instanceof BigDecimal){
			str = df.format(((BigDecimal)obj).doubleValue());
		}else if(obj instanceof Double){
			str = df.format(((Double)obj).doubleValue());
		}else if(obj instanceof Float){
			str = df.format(((Float)obj).floatValue());
		}else{
			str = obj.toString();
		}
		
		return StringUtil.isNotBlank(suffix)? str + suffix : str;
	}
    
    /**
     * 去除字符串中的空格、回车、换行符、制表符
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
    
    public static String listToStr(List<String> list){
    	if(list == null){
    		return null;
    	}
    	String rtString = "";
    	for (String str : list) {
			rtString += str + ",";
		}
    	if(rtString.length() > 0){
    		rtString = rtString.substring(0, rtString.length() - 1);
    	}
    	return rtString;
    }
    
    public static double objToDouble(Object obj){
    	if(!isNotBlank(obj)){
    		return 0;
    	}
    	
    	if(NumberUtils.isNumber(obj.toString())){
    		return Double.valueOf(obj.toString()).doubleValue();
    	}
    	return 0;
    }
}
