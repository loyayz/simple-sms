package io.simpleframework.sms;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SmsRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 短信应用
     * 为空时取 SimpleSmsProperties.defaultAppId
     */
    private String appId;
    /**
     * 短信签名
     * 为空时取 SimpleSmsProperties.defaultSignName
     */
    private String signName;
    /**
     * 短信模板
     */
    private String templateId;
    /**
     * 参数列表
     */
    private Map<String, String> params = new LinkedHashMap<>(8);
    /**
     * 号码列表
     */
    private List<String> phoneNumbers = new ArrayList<>();

    public SmsRequest(String templateId) {
        this.templateId = templateId;
    }

    public SmsRequest addParams(String key, String val) {
        params.put(key, val);
        return this;
    }

    public SmsRequest addPhoneNumber(String phoneNumber) {
        phoneNumbers.add(phoneNumber);
        return this;
    }

    public String[] getNationPhoneNumbers() {
        return phoneNumbers.stream()
                .map(number -> {
                    if (!number.startsWith("+")) {
                        number = "+86" + number;
                    }
                    return number;
                })
                .toArray(value -> new String[0]);
    }

}
