package com.wearstore.controller;

import com.wearstore.util.ErrorI18N;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PF.Tian
 * @since 2021/08/17
 */
@Slf4j
@RestController
@RequestMapping("web")
@ApiModel(value = "MainWeb页面", description = "测试页面")
public class MainWeb {

    // 注入对象
    @Autowired
    private Environment env;

    @ApiOperation("测试接口")
    @GetMapping("test")
    public String test() {
        String port = env.getProperty("server.port");
        log.info(ErrorI18N.get("hello.word"));
        return "Hello " + port;
    }

}
