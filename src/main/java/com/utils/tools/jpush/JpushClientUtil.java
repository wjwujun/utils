package com.utils.tools.jpush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.schedule.ScheduleResult;
import com.alibaba.dubbo.config.annotation.Reference;
import com.shundian.yuanben.enums.openx.JpushAudienceType;
import com.shundian.yuanben.enums.openx.JpushMessageType;
import com.shundian.yuanben.openx.service.TopPushMessageService;
import com.shundian.yuanben.openx.service.TopPushSettingService;
import com.shundian.yuanben.pojo.dto.TopPushMessageDto;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 极光推送 工具类
 *
 * @Author: Feng Jian
 * @Date: 2019/4/9 0009 9:32
 */
public class JpushClientUtil {
    private static final Log log = LogFactory.getLog(JpushClientUtil.class);
    @Autowired
    TopPushSettingService topPushSettingService;

    @Autowired
    TopPushMessageService topPushMessageService;

    private JPushClient jPushClient = null;
    private String appKey;
    private String masterSecret;
    // 是否为生产环境
    private boolean isApnsProduction;

    public JpushClientUtil(String appKey, String masterSecret, boolean isApnsProduction){
        this.appKey = appKey;
        this.masterSecret = masterSecret;
        this.isApnsProduction = isApnsProduction;
        init();
    }

    /**
     * 获取JPushClient
     * @return
     */
    public void init(){
        ClientConfig clientConfig = ClientConfig.getInstance();
        this.jPushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
    }

    /**
     * 创建自定义 推送
     * @param dto   > audience(ALL 全部用户， ASSIGN 指定用户(必须传List<String> users,集合)
     * @return
     */
    public PushPayload createCustomPush(TopPushMessageDto dto) {
        PushPayload.Builder builder = PushPayload.newBuilder();
        // 推送内容
        String message = dto.getMessage();
        // 推送平台
        String platform = dto.getPlatformType();
        // 其他自定义参数
        Map<String, String> extras = dto.getExtras();
        switch (platform){
            case "ALL":
                builder.setPlatform(Platform.all());
                builder.setNotification(Notification.newBuilder()
                    .setAlert(message)
                    .addPlatformNotification(AndroidNotification.newBuilder()
                            .addExtras(extras)
                            .build())
                    .addPlatformNotification(IosNotification.newBuilder()
                            .incrBadge(1)
                            .addExtras(extras)
                            .build())
                    .build());
                break;
            case "ANDROID":
                builder.setPlatform(Platform.android());
                builder.setNotification(Notification.newBuilder()
                        .setAlert(message)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .addExtras(extras)
                                .build())
                        .build());
                break;
            case "IOS":
                builder.setPlatform(Platform.ios());
                builder.setNotification(Notification.newBuilder()
                    .setAlert(message)
                    .addPlatformNotification(IosNotification.newBuilder()
                            .addExtras(extras)
                            .incrBadge(1)
                            .build())
                    .build());
                break;
        }
        // 推送目标
        String audience = dto.getAudienceType();
        List<String> users = dto.getUsers();
        switch (audience){
            case "ALL":
                if (users.size() > 0){
                    builder.setAudience(Audience.tag_not(users));
                }else{
                    builder.setAudience(Audience.all());
                }
                break;
            case "ASSIGN":
                builder.setAudience(Audience.alias(users));
                break;
        }
        // True 表示推送生产环境，False 表示要推送开发环境；如果不指定则为推送生产环境。
        builder.setOptions(Options.newBuilder().setApnsProduction(isApnsProduction).build());
        PushPayload pushPayload = builder.build();
        return pushPayload;
    }

    /**
     * 立即/定时 发送自定义消息
     * @param dto
     */
    public void sendCustomPush(TopPushMessageDto dto){
        String pushType = dto.getPushType();
        String audience = dto.getAudienceType();

        try {
            // 获取关掉接收消息的用户
            List<String> notPushUsers = topPushSettingService.getSettingsByEvent(dto.getPushEvent());
            if (StringUtils.equals(audience, JpushAudienceType.ALL.getKey())){
                // 推送给所有用户
                // 此处逻辑为:移动端用户关闭当前事件的消息接收，就会在该用户打事件（枚举>JpushMessageEventEnum）tag，推送时 not_tag 用户。
                List<String> notTag = new ArrayList<String>(1);
                notTag.add(dto.getPushEvent());
                dto.setUsers(notTag);
            }else if (StringUtils.equals(audience, JpushAudienceType.ASSIGN.getKey())){
                // 推送给指定用户
                List<String> users = dto.getUsers();
                users.removeAll(notPushUsers);
                dto.setUsers(users);
            }else if (StringUtils.equals(audience, JpushAudienceType.ASSIGN.getKey())){
                // 推送给配送商
                List<String> users = topPushMessageService.getAudienceUser(JpushAudienceType.ASSIGN.getKey());
                users.removeAll(notPushUsers);
                dto.setUsers(users);
            }else if (StringUtils.equals(audience, JpushAudienceType.ASSIGN.getKey())){
                // 推送给源本学员
                List<String> users = topPushMessageService.getAudienceUser(JpushAudienceType.YUANBEN.getKey());
                users.removeAll(notPushUsers);
                dto.setUsers(users);
            }

            PushPayload pushPayload = createCustomPush(dto);

            // 是否立即推送
            if (StringUtils.equals(pushType, JpushMessageType.PROMPTLY.getKey())){
                // 立即推送
                PushResult pushResult = jPushClient.sendPush(pushPayload);
                dto.setMsgId(String.valueOf(pushResult.msg_id));
                dto.setSendNo(String.valueOf(pushResult.sendno));
                log.info("消息推送成功！推送内容:" + dto.getMessage() + "，返回信息:" + pushResult);
            }else{
                // 定时推送
                ScheduleResult scheduleResult = jPushClient.createSingleSchedule(dto.getScheduleName(), dto.getScheduleTime(), pushPayload);
                dto.setMsgId(String.valueOf(scheduleResult.getSchedule_id()));
                log.info("定时消息推送成功【"+ dto.getScheduleName() +"】！推送内容:" + dto.getMessage() + ",定时时间:" + dto.getScheduleTime() + "，返回信息:" + scheduleResult);
            }
            // 保存消息
            topPushMessageService.saveData(dto);
        } catch (APIConnectionException e) {
            log.info("【JpushClientUtil > sendCustomPush】推送失败，异常:APIConnectionException,信息如下：");
            log.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            log.info("【JpushClientUtil > sendCustomPush】推送失败，异常:APIRequestException,信息如下：");
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
        } catch (Exception e) {
            log.info("【JpushClientUtil > sendCustomPush】推送失败，异常:Exception,信息如下：");
            log.info("HTTP Status: " + e.getMessage());
        }
    }

}
