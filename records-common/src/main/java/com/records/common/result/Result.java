package com.records.common.result;

import lombok.Data;

/**
 * @author changfa
 */
@Data
public class Result<T extends Object> {

    /**
     * 业务状态：0成功 其余为失败或异常
     */
    private Integer status;
    /**
     * 响应结果对象
     */
    private T data;
    /**
     * 响应结果消息
     */
    private Object info;

    public Result(BaseResultCode status, T data) {
        this.status = status.getCode();
        this.data = data;
        this.info = status.getMessage();
    }

    public Result(BaseResultCode status) {
        this.status = status.getCode();
        this.data = null;
        this.info = status.getMessage();
    }
    public static <T> Result<T> getResult(BaseResultCode status, T data){
        return new Result<>(status, data);
    }

    public static Result getResult(BaseResultCode status){
        return new Result<>(status);
    }
}
