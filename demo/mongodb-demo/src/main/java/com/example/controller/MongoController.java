package com.example.controller;

import com.example.entity.UserInfo;
import com.example.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PF.Tian
 * @since 2021/10/24
 */
@RestController
@RequestMapping("mongodb")
public class MongoController {

    @Autowired
    private MongoService mongoService;

    @GetMapping(value = "/save")
    public String save() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(11L);
        userInfo.setAge(18);
        userInfo.setName("张三");
        mongoService.save(userInfo);
        return "save success";
    }

    @GetMapping(value = "/findByName")
    public UserInfo findByName() {
        UserInfo userInfo = mongoService.findByName("张三");
        System.out.println("userInfo is " + userInfo);
        return userInfo;
    }

    @GetMapping(value = "/update")
    public void update() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(11L);
        userInfo.setAge(25);
        userInfo.setName("张三");
        mongoService.update(userInfo);
    }

    @GetMapping(value = "/deleteById")
    public void deleteById() {
        mongoService.deleteById(11);
    }

}
