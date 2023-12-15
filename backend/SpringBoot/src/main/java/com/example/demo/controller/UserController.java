package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Resource
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Result<?> save(@RequestBody User user){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getXuehao,user.getXuehao()));
        System.out.println(user.getName());
        if(res!=null){
            System.out.println(res.getUid());
            return Result.error("-1",""+res.getUid());
        }
        userMapper.insert(user);

        return Result.success(res.getUid());
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUid,user.getUid()).eq(User::getPassword,user.getPassword()));
        if(res==null){
            return Result.error("-1","ID或密码错误！");
        }
        return Result.success(res);
    }

    @GetMapping("byid") Result<?> byid(@RequestParam String uid){
        User user = userMapper.selectById(uid);
        if(user == null){
            return Result.error("-1","用户不存在");
        }
        return Result.success(user);
    }

    @GetMapping("byxuehao") Result<?> byxuehao(@RequestParam String xuehao){
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getXuehao,xuehao));
        if(user == null){
            return Result.error("-1","用户不存在");
        }
        return Result.success(user);
    }

    @PostMapping("/avatar")
    public Result<?> avatar(@RequestBody MultipartFile pic, String uid, HttpServletRequest request){
        String res = userService.updateAvatar(pic, uid, request);
        if(res.equals("failed"))
            return Result.error("-1","上传失败！");
        else
            return Result.success();
    }

    @GetMapping("/getavatar")
    public Result<?> avatar(String xuehao) {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getXuehao, xuehao));
        String path = user.getAvatar();
        return Result.success(path);
    }

    public void saveFile(MultipartFile file, String path, String filename) throws Exception {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(path + filename);
        file.transferTo(newFile);
    }
}
