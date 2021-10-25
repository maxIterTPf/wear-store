package com.example.controller;


import com.example.service.ESRestService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author PF.Tian
 * @since 2021/10/16
 */
@RestController
@RequestMapping("/es")
@ApiModel(value = "ESRestController", description = "ES测试控制器")
public class ESRestController {

    @Autowired
    private ESRestService service;

    @PostMapping("/add")
    @ApiOperation("增加数据")
    public String add() {
        return service.add();
    }

    @PostMapping("/update")
    @ApiOperation("修改数据")
    public String update() {
        return service.update();
    }

    @PostMapping("/insertBatch")
    @ApiOperation("批量增加数据")
    public String insertBatch() {
        return service.insertBatch();
    }

    @PostMapping("/deleteByQuery")
    @ApiOperation("根据条件删除")
    public void delete() {
        service.delete();
    }

    @PostMapping("/deleteById")
    @ApiOperation("根据id删除")
    public String deleteById() {
        return service.deleteById();
    }

    @PostMapping("/searchData")
    @ApiOperation("根据条件查询")
    public List searchData() {
        return service.searchData();
    }


}
