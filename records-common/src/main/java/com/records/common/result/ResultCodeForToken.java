package com.records.common.result;

public enum ResultCodeForToken implements BaseResultCode {

    TOKEN_EXPIRED(600, "登录异常，请重新登录"),

    TOKEN_EXPIRED_REFRESH(602, "Token过期，已重新刷新"),

    ILLEGAL_AUTHENTICATION(604, "非法认证，请重新登录"),

    DENIED_AUTHENTICATION(606, "没有权限，请联系管理员"),


    REFRESH_TOKEN_OK(690, "token刷新成功");


    /**
     * 状态码
     */
    private final int status;

    /**
     * 状态信息
     */
    private final String info;

    ResultCodeForToken(int status, String info) {
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

    public int getStatus() {
        return status;
    }
}
