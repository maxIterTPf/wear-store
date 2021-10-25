package com.wearstore;

import com.wearstore.util.PrintApplicationInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * wear-store 项目启动入口
 */
@EnableConfigurationProperties
@ConfigurationPropertiesScan
@ServletComponentScan   // 使用@ServletComponentScan注解后，Servlet、Filter、Listener可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册，无需其他代码。
@SpringBootApplication
public class YiBaiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(YiBaiApplication.class, args);
        // 打印项目信息
        PrintApplicationInfo.print(context);
    }

}
