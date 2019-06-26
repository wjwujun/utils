package com.utils.tools.common;

/*
 * 返回code
 *
 * */
public class StatusCode {
    public static final int OK=200;//成功

    public static final int ERROR=201;//失败
    public static final int ADD_ERROR=202; //添加失败！
    public static final int UPDATE_ERROR=203; //更新失败!
    public static final int DELETE_ERROR=204; //删除失败!
    public static final int QUERRY_NOT_FOND=205; //查询失败!

    public static final int LOGINERROR =206;//用户名或密码错误
    public static final int ACCESSERROR =207;//权限不足
    public static final int REMOTEERROR =208;//远程调用失败
    public static final int REPERROR =209;//重复操作
    public static final int INVALID_FILE_TYPE=210; //无效的文件类型!
    public static final int UPLOAD_ERROR=211; //文件上传失败!
    public static final int DATA_IS_EMPTY=212; //文件上传失败!

}

/*
public enum  StatusCode {
    OK (200, "成功"),
    ERROR (201, "失败"),
    ADD_ERROR (2002, "添加失败"),
    UPDATE_ERROR (2003, "更新失败"),
    DELETE_ERROR (2004, "删除失败"),
    QUERRY_NOT_FOND (2005, "查询失败"),

    KEY_ERROR (2007, "key错误"),
    ACCESS_ERROR (2008, "权限不足");


    private Integer key;
    private String value;

    private StatusCode(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }


}
*/




