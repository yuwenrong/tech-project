package com.cto.freemarker.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@TableName(value = "tech_project")
public class TechProject extends Model<TechProject> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private int source;

    private Date beginTime;

    private Date endTime;

    private String description;

    private Integer createUser;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private Integer updateUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private int flag;


}
