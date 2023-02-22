package com.records.demo.common.result;

public enum ResultCode5ForServer implements BaseResultCode {

    INNER_SERVER_ERROR(500, "内部服务错误"),

    SAVE_ERROR(5000, "保存失败"),

    DELETE_ERROR(5002, "删除失败"),

    UPDATE_ERROR(5004, "更新失败"),

    PARAMETER_INCOMPLETE(5006, "参数校验失败"),

    DUPLICATE_NAME(5008, "数据重复"),

    BEI_YONG(500000, "结尾备用，在上面添加就行");




    /**
     * 状态码
     */
    private final int status;

    /**
     * 状态信息
     */
    private final String info;

    ResultCode5ForServer(int statu, String info) {
        this.status = statu;
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