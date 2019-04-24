package com.utils.tools.jpush;

import com.shundian.common.obj.OptionBean;

import java.util.ArrayList;

/**
 * 消息推送群体
 *
 * @Author: FengJian
 * @Date: 2019/4/16 0016 15:23
 */
public enum JpushAudienceType {

    ALL("ALL", "全部用户"),
    ASSIGN("ASSIGN", "指定用户"),
    YUANBEN("YUANBEN", "源本学员"),
    PRESENT("PRESENT", "配送商");

    private  String key;
    private  String  value;

    private JpushAudienceType(String  key, String value){
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
