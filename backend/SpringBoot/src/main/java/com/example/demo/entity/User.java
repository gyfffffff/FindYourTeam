package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("usertable")
@Data
public class User {
    @TableId(value = "uid", type = IdType.AUTO)
    String uid;
    String password;
    String name;
    String company;
    String grade;
    String avatar;
}
