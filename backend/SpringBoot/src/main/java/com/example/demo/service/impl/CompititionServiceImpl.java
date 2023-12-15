package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Compitition;
import com.example.demo.mapper.CompititionMapper;
import com.example.demo.service.CompititionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CompititionServiceImpl implements CompititionService{
    @Resource
    CompititionMapper compititionMapper;
    @Override
    public Page<Compitition> load(Integer PageNum, Integer PageSize){
        Page<Compitition> complist = compititionMapper.selectPage(new Page<Compitition>(PageNum, PageSize), null);
        return complist;
    }
}
