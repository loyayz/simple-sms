package io.simpleframework.sms;

import lombok.Data;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SimpleSmsProperties {

    /**
     * 短信服务提供者
     * aliyun,tencent,qiniu
     */
    private String provider;
    /**
     * accessKey
     */
    private String accessKey;
    /**
     * accessSecret
     */
    private String accessSecret;
    /**
     * 默认短信应用 id
     */
    private String defaultAppId;
    /**
     * 默认短信签名
     */
    private String defaultSignName;
    /**
     * 区域
     * 阿里云默认：cn-hangzhou
     * 腾讯云默认：ap-guangzhou
     */
    private String region;

}
