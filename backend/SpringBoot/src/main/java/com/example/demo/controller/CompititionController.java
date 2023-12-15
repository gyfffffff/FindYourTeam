package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Compitition;
import com.example.demo.service.CompititionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compitition")
@CrossOrigin
public class CompititionController {
    @Autowired
    CompititionService compititionService;

    @GetMapping("/load")
    public Result<?> load(Integer pageNum, Integer pageSize){
        Page<Compitition> complist = compititionService.load(pageNum, pageSize);
        return Result.success(complist);
    }
}
