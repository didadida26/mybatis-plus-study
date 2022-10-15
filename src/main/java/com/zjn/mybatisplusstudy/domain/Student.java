package com.zjn.mybatisplusstudy.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @author zjn
 */

@Data
@TableName(value = "student")
public class Student {
    @TableId(type= IdType.AUTO)
    private Long id;
    private String name;

    @TableField(select = false)
    private String password;
    private Integer age;
    private String tel;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Byte deleted;
    /**
     * 乐观锁
     */
    @Version
    private Integer version;
}