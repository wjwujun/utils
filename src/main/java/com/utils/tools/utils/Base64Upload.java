package com.utils.tools.utils;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;


/*
* 文件上传：
*    base64 转换成 MultipartFile
* */
public class Base64Upload implements MultipartFile {
    private final byte[] imgContent;
    private final String header;

    public Base64Upload(byte[] imgContent, String header) {
        this.imgContent = imgContent;
        this.header = header.split(";")[0];
    }

    @Override
    public String getName() {
        // TODO - implementation depends on your requirements
        return System.currentTimeMillis() + Math.random() + "." + header.split("/")[1];
    }

    @Override
    public String getOriginalFilename() {
        // TODO - implementation depends on your requirements
        return System.currentTimeMillis() + (int)Math.random() * 10000 + "." + header.split("/")[1];
    }

    @Override
    public String getContentType() {
        // TODO - implementation depends on your requirements
        return header.split(":")[1];
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(imgContent);
    }

    /*
    * 上传字符串的指定格式：data:image/png;base64,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
    * 字符串开始不要加：<img src='
    *      结束不要加: '/>
    * */
    public static MultipartFile getMultipart(String base64) {
        try {
            String[] baseStrs = base64.split(",");

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            b = decoder.decodeBuffer(baseStrs[1]);

            for(int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new Base64Upload(b, baseStrs[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
