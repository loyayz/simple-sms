package com.loyayz.simple.sms.autoconfigure;

import com.loyayz.simple.sms.SimpleSmsProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Configuration
public class SimpleSmsAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(SimpleSmsProperties.class)
    @ConfigurationProperties(prefix = "simple.sms")
    public SimpleSmsProperties simpleOssProperties() {
        return new SimpleSmsProperties();
    }

}
