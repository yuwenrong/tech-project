package com.cto.freemarker.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@TableName(value = "tech_project")
public class TechProject extends Model<TechProject> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String source;

    private String beginTime;

    private String endTime;

    private String description;

    private Long createUser;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private Long updateUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Integer status;


}
