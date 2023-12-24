package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Apply;
import com.example.demo.entity.Group;
import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import com.example.demo.mapper.ApplyMapper;
import com.example.demo.mapper.GroupMapper;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.service.ProjectService;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Resource
    ProjectMapper projectMapper;
    @Resource
    GroupMapper groupMapper;
    @Resource
    TaskMapper taskMapper;

    @Resource
    ApplyMapper applyMapper;

    @Override
    public String add(Project project){
        //这里需要先判断project.getGid在Group::getGid里面有没有，首先要建GroupMapper
        Group res = groupMapper.selectOne(Wrappers.<Group>lambdaQuery().eq(Group::getGid,project.getGid()));
        if(res==null)
            return "fail";
        else{
            projectMapper.insert(project);
            UpdateWrapper<Group> updateWrapper = new UpdateWrapper<Group>().eq("gid",project.getGid()).set("pid",project.getPid());
            groupMapper.update(null, updateWrapper);
            return "success";
        }
    }


    @Override
    public Page<Project> getList(Integer pageNum, Integer pageSize, String uid){
        //改：先由uid找到gid，再通过gid查project
        LambdaQueryWrapper<Project> wrapper = Wrappers.<Project>lambdaQuery().inSql(Project::getGid, "select gid from `task`.group_table where uid = '"+uid+"'");
        Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return projectPage;
    }

    @Override
    public Project getByID(Integer pid){
        Project project = projectMapper.selectById(pid);
        return project;
    }

    @Override
    public String done(Project project) {
        UpdateWrapper<Task> taskUpdateWrapper = new UpdateWrapper<Task>().eq("pid", project.getPid()).set("done", 1);
        taskMapper.update(null, taskUpdateWrapper);
        UpdateWrapper<Project> updateWrapper = new UpdateWrapper<Project>().eq("pid", project.getPid()).set("done", 1);
        projectMapper.update(null, updateWrapper);
        return "success";
    }

    @Override
    public String end(Project project){
        UpdateWrapper<Task> taskUpdateWrapper = new UpdateWrapper<Task>().eq("pid",project.getPid()).set("done",3);
        taskMapper.update(null,taskUpdateWrapper);
        UpdateWrapper<Project> updateWrapper = new UpdateWrapper<Project>().eq("pid",project.getPid()).set("done",2);
        projectMapper.update(null,updateWrapper);
        return "success";
    }

    @Override
    public String undo(Project project){
        UpdateWrapper<Task> taskUpdateWrapper = new UpdateWrapper<Task>().eq("pid",project.getPid()).set("done",0);
        taskMapper.update(null,taskUpdateWrapper);
        UpdateWrapper<Project> updateWrapper = new UpdateWrapper<Project>().eq("pid",project.getPid()).set("done",0);
        projectMapper.update(null,updateWrapper);
        return "success";
    }

    @Override
    public String savemainpic_path(Integer pid, String path){

        System.out.println("path:"+path);
        return "success";
    }

    @Override
    public Page<Project> homeload(Integer pageNum, Integer pageSize){
        Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum,pageSize),null);
        return projectPage;
    }

    @Override
    public String apply(Apply apply){
        applyMapper.insert(apply);
        return "success";
    }

    @Override
    public Project update(Project project){
        projectMapper.updateById(project);
        return project;
    }

    @Override
    public Page<Project> searchByLabel(String label) {
        LambdaQueryWrapper<Project> wrapper;
        if (label.startsWith("s")) {
            String status = label;  // 获取除去前缀 "s" 后的部分
            wrapper = Wrappers.<Project>lambdaQuery().eq(Project::getStatus, status);
        } else {
            wrapper = Wrappers.<Project>lambdaQuery().eq(Project::getDone, label);
        }

        Page<Project> projectPage = projectMapper.selectPage(new Page<>(1, 9), wrapper);
        return projectPage;
    }

    @Override
    public Page<Project> searchByTitle(String keyword) {
        LambdaQueryWrapper<Project> wrapper = Wrappers.<Project>lambdaQuery().like(Project::getTitle, keyword);
        Page<Project> projectPage = projectMapper.selectPage(new Page<>(1, 9), wrapper);
        return projectPage;
    }

}
