package com.utils.tools.httpclient;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/*
* post请求
* */
public class SendSignHttpsClient {
    /**
     * post
     */
    public static String doPost(String url,String data) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);

        // 设置post参数
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        parameters.add(new BasicNameValuePair("data", data));
        // 构造一个form表单式的实体
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters,"utf-8");
        // 将请求实体设置到httpPost对象中
        httpPost.setEntity(formEntity);
        //伪装浏览器请求
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        CloseableHttpResponse response = null;
        String  content=null;
        try {
            // 执行请求
            response = httpclient.execute(httpPost);
            content = EntityUtils.toString(response.getEntity(), "UTF-8");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            response.close();
            httpclient.close();
        }
        return  content;
    }
    /**
     *Get
     */
    public static void doGet(String url,String data) throws IOException, URISyntaxException {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 定义请求的参数
        URI uri = new URIBuilder(url).setParameter("data", data).build();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(uri);
        //response 对象
        CloseableHttpResponse response = null;
        try {
            // 执行http get请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //内容写入文件
                System.out.println("get内容："+content);
            }
        } finally {
            response.close();
            httpclient.close();
        }
    }


}
