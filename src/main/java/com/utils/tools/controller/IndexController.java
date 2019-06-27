package com.utils.tools.controller;

import com.google.common.util.concurrent.RateLimiter;
import net.coobird.thumbnailator.Thumbnails;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/utils")
public class IndexController {
/*    @Autowired
    private OSSClientUtil ossClient;

    @Value("${oss.folder}")
    private String folder;

    @Value("${oss.http.url}")
    private String ossPath;

    @Value("${spring.http.multipart.location}")
    private String filePath;*/

    //每1s产生5个令牌，也就是说该接口1s只允许调用5次
    private RateLimiter rateLimiter = RateLimiter.create(5,1, TimeUnit.SECONDS);

    @GetMapping("/login")
    public void login(){
        if(rateLimiter.tryAcquire()) {
            //获取到令牌，进行逻辑处理
            System.out.println( "处理成功");
            System.out.println("-----------------------------------");

        }else {
            //未获取到令牌
            System.out.println( "请求频繁");
        }
    }

    @Test
    public void testApi(){
        System.out.println("111111111111");
    }


    /**
     * 文件上传
     */
/*
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public AjaxResponse<Object> imgUpload(MultipartHttpServletRequest request) {
        AjaxResponse<Object> resp = new AjaxResponse<>(true, "上传成功!");
        try {
            MultipartFile imgFile = request.getFile("bsLic");

            //保存到临时目录
            File dest = new File(filePath+"/" + imgFile.getOriginalFilename());
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            //压缩图片
            String str=dest.getParent()+"/"+StringUtils.substringBefore(dest.getName(),".");
            Thumbnails.of(imgFile.getInputStream()) .scale(1f).outputQuality(0.5f).outputFormat("jpg").toFile(str);
            File file = new File(str + ".jpg");

            //修改图片需要传入参数，以便FASTDFS删除文件
            String delFileId = request.getParameter("delFileId");
            if(StringUtils.isNotBlank(delFileId)){
//            	dfsClient.deleteFile(delFileId);
                ossClient.deleteFile(delFileId);
            }
            if (imgFile == null) {
                resp.setState(false);
                resp.setMsg("请选择上传文件!");
                return resp;
            }
//            String fileUrl = dfsClient.uploadFile(imgFile);
            String fileUrl = ossClient.uploadFile(file,folder);
            if (StringUtils.isBlank(fileUrl)) {
                resp.setState(false);
                resp.setMsg("上传失败!");
                return resp;
            }

            Map<String,String> map = new HashMap<>();
            //需要保存地址
            map.put("fileId", fileUrl);
            // 可直接访问的URL
//            map.put("urlPath", dFSBasePath + fileUrl);
            map.put("urlPath", ossPath + fileUrl);
            map.put("title", StringUtils.substringAfter(fileUrl,"/"));
            map.put("original",StringUtils.substringAfter(fileUrl,"/"));
            resp.ok(map);

            //删除图片
            file.delete();
        } catch (Exception e) {
            log.error(e.getMessage());
            this.packErrorResponse("文件上传失败!", e);
        }
        return resp;
    }
*/

    /***
     * 富文本图片上传
     * @author wj
     * @date 2019/4/2 0002 15:53
     * @param [request]
     */
   /* @ResponseBody
    @RequestMapping(value = "/TextUpload", method = RequestMethod.POST)
    public ReturnData TextUpload(MultipartHttpServletRequest request) {
        ReturnData resp = new ReturnData();

        try {
            // 转换request，解析出request中的文件
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            String fileName = multiRequest.getFileNames().next();
            MultipartFile imgFile = multiRequest.getFile(fileName);
            //保存到临时目录
            File dest = new File(filePath+"/" + imgFile.getOriginalFilename());
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            //压缩图片
            String str=dest.getParent()+"/"+StringUtils.substringBefore(dest.getName(),".");
            Thumbnails.of(imgFile.getInputStream()) .scale(1f).outputQuality(0.5f).outputFormat("jpg").toFile(str);
            File file = new File(str + ".jpg");

            if (imgFile == null) {
                resp.setState("FAILED");
                resp.setMsg("请选择上传文件!");
                return resp;
            }
            String fileUrl = ossClient.uploadFile(file,folder);
            if (StringUtils.isBlank(fileUrl)) {
                resp.setState("FAILED");
                resp.setMsg("上传失败!");
                return resp;
            }

            resp.setState("SUCCESS");
            resp.setFileId(fileUrl);
            resp.setUrl( ossPath + fileUrl);
            resp.setTitle(StringUtils.substringAfter(fileUrl,"/"));
            resp.setOriginal(StringUtils.substringAfter(fileUrl,"/"));

            //删除图片
            file.delete();
        } catch (Exception e) {
            log.error(e.getMessage());
            this.packErrorResponse("文件上传失败!", e);
            resp.setMsg(e.getMessage());
            resp.setState("ERROR");
        }
        return resp;
    }
*/

/*
    @ResponseBody
    @RequestMapping(value = "/uploadBase64", method = RequestMethod.POST)
    public AjaxResponse<Object> uploadBase(String baseStr) throws IOException {
        AjaxResponse<Object> resp = new AjaxResponse<>(true, "上传成功!");

        MultipartFile imgFile = Base64Upload.getMultipart(baseStr);


        //修改图片需要传入参数，以便FASTDFS删除文件
        String delFileId = request.getParameter("delFileId");
        if(StringUtils.isNotBlank(delFileId)){
//                dfsClient.deleteFile(delFileId);
            ossClient.deleteFile(delFileId);
        }
        if (imgFile == null) {
            resp.setState(false);
            resp.setMsg("请选择上传文件!");
            return resp;
        }
//            String fileUrl = dfsClient.uploadFile(imgFile);
        String fileUrl = ossClient.uploadFile(imgFile,folder);
        if (StringUtils.isBlank(fileUrl)) {
            resp.setState(false);
            resp.setMsg("上传失败!");
            return resp;
        }

        Map<String,String> map = new HashMap<>();
        //需要保存地址
        map.put("fileId", fileUrl);
        // 可直接访问的URL
//            map.put("urlPath", dFSBasePath + fileUrl);
        map.put("urlPath", ossPath + fileUrl);
        resp.ok(map);

        return resp;
    }
*/

    /***
     * 删除图片
     * @author wj
     * @date 2019/3/15 8:56
     * @param [baseStr]
     */
/*
    @ResponseBody
    @RequestMapping(value = "/delFile", method = RequestMethod.POST)
    public AjaxResponse<Object> delFile(String baseStr) throws IOException {
        AjaxResponse<Object> resp = new AjaxResponse<>();

        //修改图片需要传入参数，以便FASTDFS删除文件
        String delFileId = request.getParameter("delFileId");
        if(StringUtils.isNotBlank(delFileId)){
//            int i = dfsClient.deleteFile(delFileId);
            boolean  bool = ossClient.deleteFile(delFileId);
            if(!bool){
                resp.setMsg("删除失败!");
                resp.setState(false);
            }else{
                resp.setMsg("删除成功!");
                resp.setState(true);
            }
        }

        return resp;
    }
*/

}
