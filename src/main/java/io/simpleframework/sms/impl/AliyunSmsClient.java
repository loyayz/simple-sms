package io.simpleframework.sms.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.gson.Gson;
import io.simpleframework.sms.SimpleSmsClient;
import io.simpleframework.sms.SimpleSmsProperties;
import io.simpleframework.sms.SmsRequest;
import io.simpleframework.sms.SmsResponse;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class AliyunSmsClient implements SimpleSmsClient {
    private final SimpleSmsProperties smsProperties;
    private final IAcsClient acsClient;

    private static final String DOMAIN = "dysmsapi.aliyuncs.com";
    private static final String VERSION = "2017-05-25";
    private static final String ACTION = "SendSms";

    public AliyunSmsClient(SimpleSmsProperties smsProperties) {
        String region = smsProperties.getRegion();
        if (region == null) {
            region = "cn-hangzhou";
        }
        IClientProfile profile = DefaultProfile.getProfile(region, smsProperties.getAccessKey(), smsProperties.getAccessSecret());
        this.acsClient = new DefaultAcsClient(profile);
        this.smsProperties = smsProperties;
    }

    @Override
    public SmsResponse send(SmsRequest request) {
        CommonRequest req = new CommonRequest();
        try {
            req.setSysMethod(MethodType.POST);
            req.setSysDomain(DOMAIN);
            req.setSysVersion(VERSION);
            req.setSysAction(ACTION);
            req.putQueryParameter("SignName", this.signName(request.getSignName()));
            req.putQueryParameter("PhoneNumbers", String.join(",", request.getPhoneNumbers()));
            req.putQueryParameter("TemplateCode", request.getTemplateId());
            req.putQueryParameter("TemplateParam", new Gson().toJson(request.getParams()));
            CommonResponse response = acsClient.getCommonResponse(req);
            return toResult(response);
        } catch (Exception e) {
            return SmsResponse.fail(e);
        }
    }

    private String signName(String req) {
        if (req != null) {
            return req;
        }
        return smsProperties.getDefaultSignName();
    }

    private static SmsResponse toResult(CommonResponse response) {
        Map res = new Gson().fromJson(response.getData(), Map.class);
        String code = res.get("Code").toString();
        boolean success = response.getHttpStatus() == 200 && "OK".equalsIgnoreCase(code);
        Object reqId = res.get("RequestId");
        Object msg = res.get("Message");
        String requestId = reqId == null ? null : reqId.toString();
        String message = msg == null ? "" : msg.toString();
        return new SmsResponse(success, requestId, code, message);
    }

}
