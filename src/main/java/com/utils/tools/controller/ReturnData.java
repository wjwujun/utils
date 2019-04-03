package com.utils.tools.controller;

import java.io.Serializable;


/***
 *  富文本 图片上传 返回数据格式
 * @date 2019/4/2 0002 15:35
 * @param
 */
public class ReturnData  implements Serializable {


    private String state;
    private String fileId ;
    private String url ;
    private String title ;
    private String original ;
    private String msg ;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
