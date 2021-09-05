package io.simpleframework.sms;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
    private final Map<String, String> params = new LinkedHashMap<>(8);
    /**
     * 手机号码列表
     */
    private final Set<String> phoneNumbers = new HashSet<>();

    public SmsRequest(String templateId) {
        this.templateId = templateId;
    }

    /**
     * 添加参数
     *
     * @param key 参数编码
     * @param val 参数值
     * @return this
     */
    public SmsRequest addParam(String key, String val) {
        this.params.put(key, val);
        return this;
    }

    /**
     * 添加参数
     *
     * @param params 参数
     * @return this
     */
    public SmsRequest addParams(Map<String, String> params) {
        if (params != null) {
            this.params.putAll(params);
        }
        return this;
    }

    /**
     * 添加手机号码
     *
     * @param phoneNumbers 手机号码。多值用逗号隔开
     * @return this
     */
    public SmsRequest addPhoneNumbers(String... phoneNumbers) {
        if (phoneNumbers == null) {
            return this;
        }
        String sep = ",";
        for (String phoneNumber : phoneNumbers) {
            if (phoneNumber == null) {
                continue;
            }
            for (String num : phoneNumber.split(sep)) {
                num = num.trim();
                if (!num.isEmpty()) {
                    this.phoneNumbers.add(num);
                }
            }
        }
        return this;
    }

    public String[] getPhoneNumberArray(boolean nationPrefix) {
        return this.phoneNumbers.stream()
                .map(number -> {
                    if (nationPrefix && !number.startsWith("+")) {
                        number = "+86" + number;
                    }
                    return number;
                })
                .toArray(value -> new String[0]);
    }

}
