package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;


@TableName("projects")
@Data
public class Project {
    @TableId(value = "pid", type = IdType.AUTO)
    Integer pid;
    String title;
    String intro;
    String gid;
    String startdate;
    String enddate;
    Integer done;
    String mainpic;
    String requirement = "暂不招募队友";
    String Tags;
}