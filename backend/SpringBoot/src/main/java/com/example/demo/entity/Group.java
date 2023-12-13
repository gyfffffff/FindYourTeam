package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Random;

@TableName("group_table")
@Data
public class Group {
    @TableId(value = "group_key",type = IdType.AUTO)
    @TableField("group_key")
    int groupKey;
    String uid;
    int resp;
    String gid;
    int pid;
    @TableField("group_name")
    String groupName;

    public Group() {
        // Generate a random 10-digit string for gid
        this.gid = generateRandomString(10);
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = new Random().nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }
}
