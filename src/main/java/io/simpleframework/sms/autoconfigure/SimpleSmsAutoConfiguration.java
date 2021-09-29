package io.simpleframework.sms.autoconfigure;

import io.simpleframework.sms.SimpleSmsProperties;
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
    public SimpleSmsProperties simpleSmsProperties() {
        return new SimpleSmsProperties();
    }

}
