package com.utils.tools.jpush;

/**
 * 数据对象：TopPushSettingDto，此对象作为业务POJO对象。
 * 创建日期：2019年04月16日10时48分
 * @author ShunDianFrameWork
 */
public class TopPushSettingDto implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String guid;

    private String userGuid;

    /**
    * 数据为推送事件类型枚举 
    */
    private String eventType;

    private String createTime;
    private String createTime_DateFormat = "yyyy-MM-dd";


    public void setGuid(String guid) {
        if (guid != null) {guid = guid.trim();}
        this.guid = guid;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setUserGuid(String userGuid) {
        if (userGuid != null) {userGuid = userGuid.trim();}
        this.userGuid = userGuid;
    }

    public String getUserGuid() {
        return this.userGuid;
    }

    public void setEventType(String eventType) {
        if (eventType != null) {eventType = eventType.trim();}
        this.eventType = eventType;
    }

    public String getEventType() {
        return this.eventType;
    }

    public void setCreateTime(String createTime) {
        if (createTime != null) {createTime = createTime.trim();}
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime_DateFormat(String createTime_DateFormat) {
        this.createTime_DateFormat = createTime_DateFormat;
    }

    public String getCreateTime_DateFormat() {
        return this.createTime_DateFormat;
    }


}
