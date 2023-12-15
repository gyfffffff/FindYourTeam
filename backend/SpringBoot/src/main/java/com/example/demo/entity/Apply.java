package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("apply")
@Data
public class Apply {
    @TableId(value = "apply_id", type = IdType.AUTO)
    private Integer apply_id;
    private String uid;
    private String pid;
    private Integer status;  // 1 accept; 0 reject; -1 wait
    private Float match_level;
    private Float desire_level;
    private String email;

    public Apply(String uid, String pid, Integer status, Float match_level, Float desire_level, String email) {
        this.uid = uid;
        this.pid = pid;
        this.status = status;
        this.match_level = match_level;
        this.desire_level = desire_level;
        this.email = email;
    }

}
