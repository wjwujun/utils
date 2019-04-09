package com.utils.tools.utils.md5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密字符串
 * @author CaoJian
 * @version 1.0
 */
public class MD5 {
    
    /*十六进制下数字到字符的映射数组*/
    private static final String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    
    private static MessageDigest messagedigest = null;
    
    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * MD5加密字符串（大写）
     * @param inputString    输入的字符串
     * @return    返回加密后的字符串（大写）
     */
    public static String createMD5_upperCase(String inputString){
        String result = encodeByMD5(inputString);
        return null == result ? "" : result;
    }
    
    /**
     * MD5加密字符串（小写）
     * @param inputString    输入的字符串
     * @return    返回加密后的字符串（小写）
     */
    public static String createMD5_lowerCase(String inputString){
        String result = encodeByMD5(inputString);
        return null == result ? "" : result.toLowerCase();
    }
    
    /**
     * MD5验证，验证输入的字符串是否与加密字符串相同
     * @param md5String    MD5加密的字符串
     * @param inputString    输入的字符串
     * @return    验证结果，boolean类型 true 相同；false 不同
     */
    public static boolean validateMD5(String md5String, String inputString) {
    	if (null == md5String || null == inputString) {
    		return false;
    	}
        if(md5String.equalsIgnoreCase(encodeByMD5(inputString))) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 对字符串进行MD5加密(默认返回大写)
     * @author CaoJian
     * @date Oct 19, 2009
     * @modify Oct 19, 2009
     * @param originString
     * @return
     */
    private static String encodeByMD5(String originString) {
        if (originString != null) {
            try{
                //创建具有指定算法名称的信息摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md.digest(originString.getBytes());
                //将得到的字节数组变成字符串返回
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    /** 
     * 转换字节数组为十六进制字符串
     * @param b    字节数组
     * @return    十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
    
    /**
     * 将一个字节转化成十六进制形式的字符串
     * @author CaoJian
     * @date Oct 19, 2009
     * @modify Oct 19, 2009
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
        	n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
    
    private static void appendHexPair(byte bt, StringBuffer sb) {
        String c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换    
        // 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同    
        String c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换    
        sb.append(c0);
        sb.append(c1);
    }
    
    private static String bufferToHex(byte bytes[], int m, int n) {    
        StringBuffer sb = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], sb);
        }
        return sb.toString();
    }
    
    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }
    
    /**
     * 获取输入流数据的MD5编码值,可用于判断图片文件流是否改变
     */
    public static String createMD5_lowerCase(InputStream is){
        byte[] buffer = new byte[1024];
        int numRead = 0;
        try {
        	while ((numRead = is.read(buffer)) > 0) {
                messagedigest.update(buffer, 0, numRead);
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(is != null){
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					// ignore
				}
			}
		}
        String toHex = bufferToHex(messagedigest.digest());
        return toHex == null ? "" : toHex.toLowerCase();
    }
    
    /**
     * 获取文件数据的MD5编码值,可用于判断图片文件流是否改变
     */
    public static String createMD5_lowerCase(File file){    
        InputStream fis = null;
        try {
        	fis = new FileInputStream(file);    
            byte[] buffer = new byte[1024];    
            int numRead = 0;    
            while ((numRead = fis.read(buffer)) > 0) {    
                messagedigest.update(buffer, 0, numRead);    
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(fis != null){
				try {
					fis.close();
					fis = null;
				} catch (IOException e) {
					// ignore
				}
			}
		}
        
        String toHex = bufferToHex(messagedigest.digest());
        return toHex == null ? "" : toHex.toLowerCase();
    }
    
    public static void main(String[] args) {
		System.out.println(MD5.createMD5_upperCase("gaoxinqu"));
	}
}