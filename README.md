[![Maven Central](https://img.shields.io/maven-central/v/io.simpleframework/simple-sms.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.simpleframework%22%20AND%20a:%22simple-sms%22)

基于`阿里云/腾讯云/七牛`实现的简单短信客户端。

## 1 安装

```xml

<dependencies>
    <dependency>
        <groupId>io.simpleframework</groupId>
        <artifactId>simple-sms</artifactId>
        <version>1.0.1</version>
    </dependency>

    <!-- 本项目未引入各sms客户端，使用时请根据实际情况自行添加以下依赖之一 -->

    <!-- 阿里云 -->
    <dependency>
        <groupId>com.aliyun</groupId>
        <artifactId>aliyun-java-sdk-core</artifactId>
        <version>${aliyun-sdk.version}</version>
    </dependency>
    <!-- 腾讯 -->
    <dependency>
        <groupId>com.tencentcloudapi</groupId>
        <artifactId>tencentcloud-sdk-java</artifactId>
        <version>${tencentcloud-sdk.version}</version>
    </dependency>
    <!-- 七牛 -->
    <dependency>
        <groupId>com.qiniu</groupId>
        <artifactId>qiniu-java-sdk</artifactId>
        <version>${qiniu-sdk.version}</version>
    </dependency>
</dependencies>
```

## 2 快速开始

### 2.1 配置

Spring Boot 项目将根据 application.yml 中的 simple.sms 自动配置。

```yml
# application.yml
simple:
  sms:
    # 文件服务提供者 aliyun,tencent,qiniu
    provider:
    # 密钥id
    accessKey:
    # 密钥
    accessSecret:
    # 默认短信应用 id
    defaultAppId:
    # 默认短信签名
    defaultSignName:
    # 区域
    # 阿里云默认：cn-hangzhou
    # 腾讯云默认：ap-guangzhou
    region:
```

### 2.2 使用

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class Demo {
    @Autowired
    private SimpleSmsClient simpleSmsClient;

    public void test() {
        SmsRequest request = new SmsRequest("templateId").addPhoneNumbers("phoneNumbers");
        SmsResponse response = simpleSmsClient.send(request);
    }
}
```

[使用示例](https://github.com/loyayz/simple-sample)
