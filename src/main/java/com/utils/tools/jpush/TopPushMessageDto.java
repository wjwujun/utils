package com.utils.tools.jpush;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据对象：TopPushMessageDto，此对象作为业务POJO对象。
 * 创建日期：2019年04月17日13时53分
 * @author ShunDianFrameWork
 */
public class TopPushMessageDto implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String guid;

    private String message;

    private String pushEvent;

    /**
    * 所有用户   指定用户
    */
    private String audienceType;

    /**
    * 安卓  苹果  所有
    */
    private String platformType;

    private String pushState;

    /**
    * 立即   定时
    */
    private String pushType;

    private String scheduleName;

    private String scheduleTime;
    private String scheduleTime_DateFormat = "yyyy-MM-dd";

    private String msgId;

    private String sendNo;

    private String relationId;

    private String relationType;

    private String createTime;
    private String createTime_DateFormat = "yyyy-MM-dd";

    private List<String> users =new ArrayList<String>();

    private Map<String, String> extras = new HashMap<String, String>();


    public void setGuid(String guid) {
        if (guid != null) {guid = guid.trim();}
        this.guid = guid;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setMessage(String message) {
        if (message != null) {message = message.trim();}
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setPushEvent(String pushEvent) {
        if (pushEvent != null) {pushEvent = pushEvent.trim();}
        this.pushEvent = pushEvent;
    }

    public String getPushEvent() {
        return this.pushEvent;
    }

    public void setAudienceType(String audienceType) {
        if (audienceType != null) {audienceType = audienceType.trim();}
        this.audienceType = audienceType;
    }

    public String getAudienceType() {
        return this.audienceType;
    }

    public void setPlatformType(String platformType) {
        if (platformType != null) {platformType = platformType.trim();}
        this.platformType = platformType;
    }

    public String getPlatformType() {
        return this.platformType;
    }

    public void setPushState(String pushState) {
        if (pushState != null) {pushState = pushState.trim();}
        this.pushState = pushState;
    }

    public String getPushState() {
        return this.pushState;
    }

    public void setPushType(String pushType) {
        if (pushType != null) {pushType = pushType.trim();}
        this.pushType = pushType;
    }

    public String getPushType() {
        return this.pushType;
    }

    public void setScheduleName(String scheduleName) {
        if (scheduleName != null) {scheduleName = scheduleName.trim();}
        this.scheduleName = scheduleName;
    }

    public String getScheduleName() {
        return this.scheduleName;
    }

    public void setScheduleTime(String scheduleTime) {
        if (scheduleTime != null) {scheduleTime = scheduleTime.trim();}
        this.scheduleTime = scheduleTime;
    }

    public String getScheduleTime() {
        return this.scheduleTime;
    }

    public void setScheduleTime_DateFormat(String scheduleTime_DateFormat) {
        this.scheduleTime_DateFormat = scheduleTime_DateFormat;
    }

    public String getScheduleTime_DateFormat() {
        return this.scheduleTime_DateFormat;
    }

    public void setMsgId(String msgId) {
        if (msgId != null) {msgId = msgId.trim();}
        this.msgId = msgId;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public void setSendNo(String sendNo) {
        if (sendNo != null) {sendNo = sendNo.trim();}
        this.sendNo = sendNo;
    }

    public String getSendNo() {
        return this.sendNo;
    }

    public void setRelationId(String relationId) {
        if (relationId != null) {relationId = relationId.trim();}
        this.relationId = relationId;
    }

    public String getRelationId() {
        return this.relationId;
    }

    public void setRelationType(String relationType) {
        if (relationType != null) {relationType = relationType.trim();}
        this.relationType = relationType;
    }

    public String getRelationType() {
        return this.relationType;
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

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public Map<String, String> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }
}
