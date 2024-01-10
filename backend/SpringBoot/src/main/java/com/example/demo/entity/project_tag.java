package com.example.demo.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Date;

@TableName("project_tag")
@Data
public class project_tag {
    // pid, tag 是联合主键
    @TableId(value = "pid")
    private Integer pid;

    @TableId(value = "tag")
    private String tag;

    // 用 @TableField 表示外键关系
    @TableField(exist = false)
    private Project project; // 假设 Project 是 projects 表的实体类

}
