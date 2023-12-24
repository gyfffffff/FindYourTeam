package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@TableName("projects")
@Data
public class Project {
    @TableId(value = "pid", type = IdType.AUTO)
    int pid;
    String title;
    String intro;
    String gid;
    String startdate;
    String ddl;
    int done;
    String tag;
    String pic;
    String requirement;
    String status;


}