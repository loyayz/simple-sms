package io.simpleframework.sms;

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
    SmsResponse send(SmsRequest info);

}
