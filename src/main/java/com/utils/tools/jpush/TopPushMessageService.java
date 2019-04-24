package com.utils.tools.jpush;


import java.util.List;

/**
 * 业务接口：TopPushMessageService，请在处理动作里直接调用业务接口，而不是它的实现类。
 * 创建日期：2019年04月16日10时48分
 * @author ShunDianFrameWork
 */
public interface TopPushMessageService {
    /**
    * 保存对象
    * @param TopPushMessageDto Dto对象
    * @throws Exception
    * @author ShunDianFrameWork
    */
    void saveData(TopPushMessageDto dto) throws Exception;

    /**
    * 根据ID加载对象
    * @param guid
    * @throws Exception
    * @author ShunDianFrameWork
    */
    TopPushMessageDto loadDataById(String guid) throws Exception;

    /**
    * 根据ID编辑对象
    * @param TopPushMessageDto Dto对象
    * @throws Exception
    * @author ShunDianFrameWork
    */
    void editData(TopPushMessageDto dto) throws Exception;

    /**
    * 根据ID删除数据
    * @param guid
    * @throws Exception
    * @author ShunDianFrameWork
    */
    void deleteByPrimaryKeys(String guids) throws Exception;

    /**
     * 查询用户的消息列表
     * @return
     * @throws Exception
     */

    /**
     * 查询用户群体
     * @param type （配送商，源本学员）
     * @return
     * @throws Exception
     */
    List<String> getAudienceUser(String type) throws Exception;

}
