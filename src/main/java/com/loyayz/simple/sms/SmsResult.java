package com.loyayz.simple.sms;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
@NoArgsConstructor
public class SmsResult implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 请求id
     */
    private String requestId;
    /**
     * 状态码
     */
    private String code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 异常
     */
    private Exception exception;

    public SmsResult(boolean success, String requestId, String code, String message) {
        this.success = success;
        this.requestId = requestId;
        this.code = code;
        this.message = message;
    }

    public static SmsResult fail(Exception e) {
        SmsResult result = new SmsResult();
        result.setSuccess(false);
        result.setMessage(e.getMessage());
        result.setException(e);
        return result;
    }

}
