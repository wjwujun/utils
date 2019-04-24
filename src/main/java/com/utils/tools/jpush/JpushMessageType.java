package com.utils.tools.jpush;


import java.util.ArrayList;

/**
 * 消息推送类型
 *
 * @Author: FengJian
 * @Date: 2019/4/16 0016 15:23
 */
public enum  JpushMessageType {

    PROMPTLY("PROMPTLY", "立即"),
    TIMING("TIMING", "定时");

    private  String key;
    private  String  value;

    private  JpushMessageType(String  key,String value){
        this.key=key;
        this.value=value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    /**
     * 封装列表，用于下拉菜单
     * @return
     */
    public static ArrayList<OptionBean> getOptionList (){
        ArrayList<OptionBean> list = new ArrayList<OptionBean>();
        AtticFileTypeEnum[] es = AtticFileTypeEnum.values();
        for (AtticFileTypeEnum e : es) {
            list.add(new OptionBean(e.getKey(), e.getValue()));
        }
        return list;
    }

}
