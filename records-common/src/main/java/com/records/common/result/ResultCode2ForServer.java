package com.records.common.result;

/**
 * @author changfa
 */

public enum ResultCode2ForServer implements BaseResultCode {

    // 查询
    OK(0, "ok"),
    SAVE_SUCCESS(200, "保存成功"),

    DELETE_SUCCESS(200,  "删除成功"),

    LOGIN_SUCCESS(200,  "登录成功"),

    LOGOUT_SUCCESS(200,  "退出成功"),


    UPDATE_SUCCESS(200,  "更新成功");

    /**
     * 状态码
     */
    private final int status;

    /**
     * 状态信息
     */
    private final String info;

    ResultCode2ForServer(int status, String info) {
        this.status = status;
        this.info = info;
    }

    @Override
    public Integer getCode() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return this.info;
    }
}
