package com.loyayz.simple.sms.impl;

import com.loyayz.simple.sms.SimpleSmsClient;
import com.loyayz.simple.sms.SimpleSmsProperties;
import com.loyayz.simple.sms.SmsRequest;
import com.loyayz.simple.sms.SmsResult;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.sms.Configuration;
import com.qiniu.sms.SmsManager;
import com.qiniu.util.Auth;
import lombok.RequiredArgsConstructor;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class QiniuSmsClient implements SimpleSmsClient {
    private final SmsManager smsManager;

    public QiniuSmsClient(SimpleSmsProperties smsProperties) {
        this(smsProperties, null);
    }

    public QiniuSmsClient(SimpleSmsProperties smsProperties, Configuration config) {
        Auth auth = Auth.create(smsProperties.getAccessKey(), smsProperties.getAccessSecret());
        if (config == null) {
            this.smsManager = new SmsManager(auth);
        } else {
            this.smsManager = new SmsManager(auth, config);
        }
    }

    @Override
    public SmsResult send(SmsRequest request) {
        try {
            String[] phoneNumbers = request.getPhoneNumbers().toArray(new String[0]);
            Response response = smsManager.sendMessage(request.getTemplateId(), phoneNumbers, request.getParams());
            return new SmsResult(response.isOK(), response.reqId, String.valueOf(response.statusCode), response.toString());
        } catch (QiniuException e) {
            return SmsResult.fail(e);
        }
    }

}
