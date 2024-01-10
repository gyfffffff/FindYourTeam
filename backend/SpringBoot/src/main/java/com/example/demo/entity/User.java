package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("users")
@Data
public class User {
    @TableId(value = "uid", type = IdType.AUTO)
    Integer uid;
    String password = "1234";
    String name;
    String stuid;
    String avatar;
}
