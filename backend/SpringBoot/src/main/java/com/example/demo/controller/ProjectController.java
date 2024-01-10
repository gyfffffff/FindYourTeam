package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Apply;
import com.example.demo.entity.Project;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                          @RequestParam String stuid){
        Page<Project> projectPage = projectService.getList(pageNum,pageSize,stuid);
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
    @PutMapping("/undo")
    public Result<?> undo(@RequestBody Project project){
        projectService.undo(project);
        return Result.success();
    }

    @GetMapping("/homeload")
    public Result<?> homeload(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "9") Integer pageSize){
        Page<Project> projectPage = projectService.homeload(pageNum,pageSize);
        return Result.success(projectPage);
    }

    @PostMapping("/apply")   // 申请项目, 点击申请加入后，弹出问卷，要求申请人提供邮箱，要求符合程度，意愿值
    public Result<?> apply(@RequestBody Apply apply){  // 申请人， 申请的项目
        System.out.println(apply.getEmail());
        if(apply.getMatch_level()==0){
            return Result.error("433", "请填写符合程度");
        }
        if(apply.getDesire_level()==0){
            return Result.error("434", "请填写意愿值");
        }
        if (!isValidEmail(apply.getEmail())) {
            return Result.error("432", "邮箱格式不正确");
        }
        projectService.apply(apply);
        return Result.success();
    }

    public boolean isValidEmail(String email) {
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

//    @PutMapping("/status")
//    public Result<?> status(Integer pid, String status){
//        Project project = projectService.getByID(pid);
//        project.setStatus(status);
//        projectService.update(project);
//        return Result.success();
//    }

    @GetMapping("/tag")
    public Result<?> tag(@RequestParam String pid, @RequestParam String tag) {
        System.out.println(pid);
        System.out.println(tag);
//        Project project = projectService.getByID(pid);
//        project.setTag(tag);
//        projectService.update(project);
        int pid1 = Integer.parseInt(pid);
        projectService.tag(pid1, tag);
        System.out.println(pid1);
        System.out.println(tag);
        return Result.success();
    }



//    @GetMapping("/download")
//    public Result<?> download(Integer resc_id) throws Exception {
//        Resource resource = projectService.download(resc_id);
//        return Result.success();
//    }
}
