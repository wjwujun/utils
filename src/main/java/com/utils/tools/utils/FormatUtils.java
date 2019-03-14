/**
 * @Project Name:yuanben-common
 * @File Name:FormatUtils.java
 * @Package Name:com.shundian.common.util
 * @Date:2017年2月13日下午5:46:13
 * @author:CaoJian
 * @Copyright (c) 2017, www.ybveg.com All Rights Reserved.
 */
package com.utils.tools.utils;


import java.text.DecimalFormat;

/**
 * 格式化工具类
 * 用于格式化价格、重量、商品名等
 */
public class FormatUtils {
	
	public static final String DECIMAL_FORMAT = "##0.00";
	private static final DecimalFormat DEFAULT_DF = new DecimalFormat(DECIMAL_FORMAT);
	
	/**
	 * formatNumber:格式化数字
	 * @author CaoJian
	 * @param str
	 * @return
	 */
	public static String formatNumber (String str) {
		String result = "";

		if (StringUtils.isNotBlank(str)) {
			DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT);
			double db = 0D;
			try {
				db = Double.parseDouble(str);
			} catch (Exception e) {
				db = 0D;
			}
			result = df.format(db);
		}
		return result;
	}
	/**
	 * formatNumber:格式化数字
	 * @author zf
	 * @param str
	 * @return
	 */
	public static double formatNumberToDouble (String str) {
		if (StringUtils.isNotBlank(str)) {
			double db = 0D;
			try {
				db = Double.parseDouble(str);
			} catch (Exception e) {
				db = 0D;
			}
			return Double.valueOf(DEFAULT_DF.format(db));
		}
		return 0;
	}
	
	/**
	 * formatNumberNoPointZero:格式化数字，如果小数点后面全为0则去掉0
	 * @author CaoJian
	 * @param str
	 * @return
	 */
	public static String formatNumberNoPointZero (String str) {
		String result = "";
		if (StringUtils.isNotBlank(str)) {
			result = formatNumber(str);
			//如果小数点后面全为0，则去掉小数点后面的0
			int i = result.indexOf(".");
			if (i > -1) {
				String tmp = result.substring(i+1);
				if (Integer.parseInt(tmp) == 0) {
					result = result.substring(0,i);
				}
			}
		}
		return result;
	}	
	
	/**
	 * formatUnitPrice:格式化单价
	 * @author CaoJian
	 * @param price 价格
	 * @param unit 单位
	 * @return
	 */
	public static String formatUnitPrice (String price, String unit) {
		price = formatNumber(price);
		if (StringUtils.isNotBlank(price)) {
			if (StringUtils.isNotBlank(unit)) {
				return price+"元/"+unit;
			} else {
				return price+"元";
			}
		} else {
			return "--";
		}
	}
	
	/**
	 * formatPrice:格式化价格 100.00元
	 * @author CaoJian
	 * @param price
	 * @return
	 */
	public static String formatPrice (String price) {
		price = formatNumber(price);
		if (StringUtils.isNotBlank(price)) {
			return price+"元";
		} else {
			return "--";
		}
	}
	
	/**
	 * formatPrice:格式化价格 100.00
	 * @author CaoJian
	 * @param price
	 * @return
	 */
	public static String formatPriceNoYuan (String price) {
		price = formatNumber(price);
		if (StringUtils.isNotBlank(price)) {
			return price;
		} else {
			return "--";
		}
	}
	
	/**
	 * formatAmount:格式化下单量（数量/重量）
	 * @author CaoJian
	 * @param price
	 * @return
	 */
	public static String formatAmount (String amount, String unit) {
		amount = formatNumberNoPointZero(amount);
		if (StringUtils.isNotBlank(amount)) {
			if (StringUtils.isNotBlank(unit)) {
				return amount+unit;
			} else {
				return amount;
			}
		} else {
			return "--";
		}
	}
	
	/**
	 * formatAmountNoUnit:格式化下单量（不带单位）
	 * @author CaoJian
	 * @param amount
	 * @param unit
	 * @return
	 */
	public static String formatAmountNoUnit (String amount) {
		amount = formatNumberNoPointZero(amount);
		if (StringUtils.isNotBlank(amount)) {
			return amount;
		} else {
			return "--";
		}
	}
	
}

