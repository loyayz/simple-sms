package io.simpleframework.sms.impl;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.sms.Configuration;
import com.qiniu.sms.SmsManager;
import com.qiniu.util.Auth;
import io.simpleframework.sms.SimpleSmsClient;
import io.simpleframework.sms.SimpleSmsProperties;
import io.simpleframework.sms.SmsRequest;
import io.simpleframework.sms.SmsResponse;
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
    public SmsResponse send(SmsRequest request) {
        try {
            String[] phoneNumbers = request.getPhoneNumberArray(false);
            Response response = smsManager.sendMessage(request.getTemplateId(), phoneNumbers, request.getParams());
            return new SmsResponse(response.isOK(), response.reqId, String.valueOf(response.statusCode), response.toString());
        } catch (QiniuException e) {
            return SmsResponse.fail(e);
        }
    }

}
