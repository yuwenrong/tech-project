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
@TableName(value = "tech_file_project")
public class TechFileProject extends Model<TechFileProject> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String url;

    private Long techId;

    private Long createUser;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private Long updateUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Integer status;

}
