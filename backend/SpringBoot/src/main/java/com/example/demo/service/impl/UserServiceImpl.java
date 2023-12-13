package com.example.demo.service.impl;

import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public String updateAvatar(MultipartFile pic, String uid, HttpServletRequest request) {
        String path = request.getServletContext().getRealPath("/upload/avatar/");
        String filename = pic.getOriginalFilename();
        try {
            saveFile(pic,path,filename);
            User user = userMapper.selectById(uid);
            user.setAvatar(path+filename);
            userMapper.updateById(user);
//            User user = userMapper.selectById(uid);
//            user.setAvatar(pic.getBytes());
//            userMapper.updateById(user);

        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
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
