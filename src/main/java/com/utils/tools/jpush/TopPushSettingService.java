package com.utils.tools.jpush;


import java.util.List;
import java.util.Map;

/**
 * 业务接口：TopPushSettingService，请在处理动作里直接调用业务接口，而不是它的实现类。
 * 创建日期：2019年04月16日10时48分
 * @author ShunDianFrameWork
 */
public interface TopPushSettingService {
    /**
    * 保存对象
    * @param TopPushSettingDto Dto对象
    * @throws Exception
    * @author ShunDianFrameWork
    */
    void saveData(TopPushSettingDto dto) throws Exception;

    /**
    * 根据ID加载对象
    * @param guid
    * @throws Exception
    * @author ShunDianFrameWork
    */
    TopPushSettingDto loadDataById(String guid) throws Exception;

    /**
    * 根据ID编辑对象
    * @param TopPushSettingDto Dto对象
    * @throws Exception
    * @author ShunDianFrameWork
    */
    void editData(TopPushSettingDto dto) throws Exception;

    /**
    * 根据ID删除数据
    * @param guid
    * @throws Exception
    * @author ShunDianFrameWork
    */
    void deleteByPrimaryKeys(String guids) throws Exception;

    /**
     * 获取关掉接收消息的用户
     * @return
     * @throws Exception
     */
    List<String> getSettingsByEvent(String eventType) throws Exception;

    /**
     * 消息开关控制
     * @throws Exception
     */
    void messageSetting(String type, List<TopPushSettingDto> item) throws Exception;

    /**
     * 查询用户的设置信息
     * @return
     * @throws Exception
     */
    Map<String, String> getSettingByUser(String userId) throws Exception;

}
