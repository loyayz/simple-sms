package com.loyayz.simple.sms.autoconfigure;

import com.loyayz.simple.sms.SimpleSmsClient;
import com.loyayz.simple.sms.SimpleSmsProperties;
import com.loyayz.simple.sms.impl.AliyunSmsClient;
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
@ConditionalOnProperty(value = "simple.sms.provider", havingValue = "aliyun")
@RequiredArgsConstructor
public class SimpleSmsAliyunAutoConfiguration {
    private final SimpleSmsProperties smsProperties;

    @Bean
    @ConditionalOnMissingBean(SimpleSmsClient.class)
    public SimpleSmsClient simpleSmsClient() {
        return new AliyunSmsClient(smsProperties);
    }

}
