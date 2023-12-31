package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Date;

@TableName("tasks")
@Data
public class Task {
    @TableId(value = "tid", type = IdType.AUTO)
    int tid;
    String title;
    String intro;
    String uid;
    String xuehao;
    Date startdate;
    Date ddl;
    int ptid;
    int done;
    int coop;
    int emer;
    int pid;
}
