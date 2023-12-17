package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Apply;
import com.example.demo.entity.Project;

import java.util.List;

public interface ProjectService {

    public String add(Project project);

    public Page<Project> getList(Integer pageNum, Integer pageSize, String gid);

    public Project getByID(Integer pid);

    public String done(Project project);

    public String end(Project project);

    public String undo(Project project);

    public String savemainpic_path(Integer pid, String path);

    public Page<Project> homeload(Integer pageNum, Integer pageSize);

    public String apply(Apply apply);
}
