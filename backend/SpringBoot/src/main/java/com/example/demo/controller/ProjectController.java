package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Project;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping("/save")
    public Result<?> save(@RequestBody Project project){
        String res = projectService.add(project);
        if(res.equals("fail"))
            return Result.error("-1","请先创建团队！");
        else
            return Result.success();
    }

    @PostMapping("/mainpic")
    public Result<?> mainpic(MultipartFile file, Integer pid, HttpServletRequest request){
        try {
            if (file.isEmpty()) {
                return Result.error("431", "File is empty.");
            }
            String fileName = file.getOriginalFilename();
            String path = request.getServletContext().getRealPath("/upload/mainpic/");
            System.out.println(path);
            saveFile(file, path, file.getOriginalFilename());
            // 保存路径到数据库
//            Project project = new Project();
            projectService.savemainpic_path(pid,fileName);
            return Result.success(path);
        } catch (Exception e) {
            e.printStackTrace();  // 打印异常信息
            return Result.error("500", e.getMessage());
        }
    }
    public void saveFile(MultipartFile file, String path, String filename) throws Exception {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(path + filename);
        file.transferTo(newFile);
    }

    //这个方法后期要加uid进行load
    @GetMapping("/load")
    public Result<?> load(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "9") Integer pageSize,
                          @RequestParam String uid){
        Page<Project> projectPage = projectService.getList(pageNum,pageSize,uid);
        return Result.success(projectPage);
    }

    @GetMapping("/byid")
    public Result<?> byid(@RequestParam Integer pid){
        Project project = projectService.getByID(pid);
        if(project == null)
            return Result.error("-1","未查询到该项目！");
        return  Result.success(project);
    }

    @PutMapping("/done")
    public Result<?> done(@RequestBody Project project){
        projectService.done(project);
        return Result.success();
    }

    @PutMapping("/end")
    public Result<?> end(@RequestBody Project project){
        projectService.end(project);
        return Result.success();
    }

//    @GetMapping("/download")
//    public Result<?> download(Integer resc_id) throws Exception {
//        Resource resource = projectService.download(resc_id);
//        return Result.success();
//    }
}
