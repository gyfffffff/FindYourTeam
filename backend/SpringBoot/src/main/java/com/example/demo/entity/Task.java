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
    Integer tid;
    String title;
    String intro;
    String executorStuid;
    String startdate;
    String enddate;
    Integer done=0;
    Integer pid;
    String interfaceStuid;
    String importanceLevel;
}
