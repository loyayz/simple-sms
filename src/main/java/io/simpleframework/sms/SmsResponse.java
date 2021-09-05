package io.simpleframework.sms;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
@NoArgsConstructor
public class SmsResponse implements Serializable {
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

    public SmsResponse(boolean success, String requestId, String code, String message) {
        this.success = success;
        this.requestId = requestId;
        this.code = code;
        this.message = message;
    }

    public static SmsResponse fail(Exception e) {
        SmsResponse result = new SmsResponse();
        result.setSuccess(false);
        result.setMessage(e.getMessage());
        result.setException(e);
        return result;
    }

}
