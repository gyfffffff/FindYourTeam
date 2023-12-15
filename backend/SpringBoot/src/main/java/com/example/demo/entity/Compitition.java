package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("compitition")
@Data
public class Compitition {
    @TableId(value = "comp_id", type = IdType.AUTO)
    String comp_id;
    String title;
    String tag;
    String date;
}
