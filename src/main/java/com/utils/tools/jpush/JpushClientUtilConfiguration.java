package com.utils.tools.jpush;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 极光 客户端工具配置类
 *
 * @Author: Feng Jian
 * @Date: 2019/4/9
 */
@Configuration
public class JpushClientUtilConfiguration {


    private String appKey="cba5a11ebe21b1930912848e";

    private String masterSecret="176961f42049923120e897b0";

    private boolean isApnsProduction=false;

    @Bean
    public JpushClientUtil jpushClient() {
        return new JpushClientUtil(appKey, masterSecret, isApnsProduction);
    }
}
