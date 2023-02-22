package com.records.demo.common.result;

public interface BaseResultCode {
    /**
     * 获取状态码
     * @return
     */
    Integer getCode();

    /**
     * 获取信息
     * @return
     */
    String getMessage();
}
