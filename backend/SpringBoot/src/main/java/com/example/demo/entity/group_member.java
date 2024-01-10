package com.example.demo.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Date;

@TableName("group_member")
@Data
public class group_member {
    @TableId(value = "gid", type = IdType.AUTO)
    String gid;
    @TableId(value = "stuid", type = IdType.AUTO)
    String stuid;
    @TableField(exist = false)
    private Group group;
}
