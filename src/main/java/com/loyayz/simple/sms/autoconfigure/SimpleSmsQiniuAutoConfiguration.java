package com.loyayz.simple.sms.autoconfigure;

import com.loyayz.simple.sms.SimpleSmsClient;
import com.loyayz.simple.sms.SimpleSmsProperties;
import com.loyayz.simple.sms.impl.QiniuSmsClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Configuration
@AutoConfigureAfter(SimpleSmsAutoConfiguration.class)
@ConditionalOnProperty(value = "simple.sms.provider", havingValue = "qiniu")
@RequiredArgsConstructor
public class SimpleSmsQiniuAutoConfiguration {
    private final SimpleSmsProperties smsProperties;

    @Bean
    @ConditionalOnMissingBean(com.qiniu.storage.Configuration.class)
    public com.qiniu.sms.Configuration qiniuSmsConfiguration() {
        return new com.qiniu.sms.Configuration();
    }

    @Bean
    @ConditionalOnMissingBean(SimpleSmsClient.class)
    public SimpleSmsClient simpleSmsClient(com.qiniu.sms.Configuration config) {
        return new QiniuSmsClient(smsProperties, config);
    }

}
