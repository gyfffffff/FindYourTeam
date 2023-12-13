package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    public String updateAvatar(MultipartFile pic, String uid, HttpServletRequest request);
}
