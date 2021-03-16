package com.loyayz.simple.sms;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
public interface SimpleSmsClient {

    /**
     * 发送短信
     *
     * @param info 短信内容
     * @return 结果
     */
    SmsResult send(SmsRequest info);

}
