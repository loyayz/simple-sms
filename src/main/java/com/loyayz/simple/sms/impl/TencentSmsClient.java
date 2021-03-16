package com.loyayz.simple.sms.impl;

import com.loyayz.simple.sms.SimpleSmsClient;
import com.loyayz.simple.sms.SimpleSmsProperties;
import com.loyayz.simple.sms.SmsRequest;
import com.loyayz.simple.sms.SmsResult;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
public class TencentSmsClient implements SimpleSmsClient {
    private final SimpleSmsProperties smsProperties;
    private final SmsClient smsClient;

    public TencentSmsClient(SimpleSmsProperties smsProperties) {
        Credential cred = new Credential(smsProperties.getAccessKey(), smsProperties.getAccessSecret());
        String region = smsProperties.getRegion();
        if (null == region) {
            region = "ap-guangzhou";
        }
        this.smsClient = new SmsClient(cred, region);
        this.smsProperties = smsProperties;
    }

    @Override
    public SmsResult send(SmsRequest request) {
        try {
            SendSmsRequest req = new SendSmsRequest();
            req.setSmsSdkAppid(this.appId(request.getAppId()));
            req.setSign(this.signName(request.getSignName()));
            req.setTemplateID(request.getTemplateId());
            req.setPhoneNumberSet(request.getNationPhoneNumbers());
            req.setTemplateParamSet(request.getParams().values().toArray(new String[0]));
            SendSmsResponse response = smsClient.SendSms(req);
            return toResult(response);
        } catch (Exception e) {
            return SmsResult.fail(e);
        }
    }

    private String appId(String req) {
        if (req != null) {
            return req;
        }
        return smsProperties.getDefaultAppId();
    }

    private String signName(String req) {
        if (req != null) {
            return req;
        }
        return smsProperties.getDefaultSignName();
    }

    private static SmsResult toResult(SendSmsResponse response) {
        SendStatus[] statuses = response.getSendStatusSet();
        String code = statuses == null ? null : statuses[0].getCode();
        String message = statuses == null ? "" : statuses[0].getMessage();
        boolean success = "OK".equalsIgnoreCase(code);
        return new SmsResult(success, response.getRequestId(), code, message);
    }

}
