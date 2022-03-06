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
@TableName(value = "seed_project")
public class SeedProject extends Model<SeedProject> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String land;// 地块

    private String functionArea;//功能区

    private String treeType;//树种

    private String buildTime;//建设时间

    private String density;// 密度

    private String seedSource;// 种源

    private String description;//备注

    private Long createUser;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private Long updateUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Integer status;
}
