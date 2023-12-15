package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Compitition;

public interface CompititionService {
    public Page<Compitition> load(Integer PageNum, Integer PageSize);
}
