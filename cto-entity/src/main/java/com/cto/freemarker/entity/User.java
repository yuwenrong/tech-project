package com.cto.freemarker.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @program:
 * @description: 用户实体类
 * @author:
 * @create:
 */


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@TableName(value = "user")//指定表名
public class User extends Model<User> {
    private static final long serialVersionUID = -7585862229833387698L;
    //value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
    @TableId(value = "id", type = IdType.AUTO)//指定自增策略
    private Integer id;
    private String name;
    private String sex;
    private String pwd;
    private String email;
    private Integer age;
    @Version
    private Long version;

    //TODO 自动填充
    // 注意！这里需要标记为填充字段
    //数据库与java类型对照表
    //
    //LocalTime 对应 time
    //LocalDate 对应 date
    //LocalDateTime 对应 datetime(timestamp)

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //上次修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    //是否被删除，0表示未删除，1表示已经删除
    //@TableLogic  3.3.0,配置后可以忽略不配置
    private int flag;

    public void println() {
        System.out.println(this);
    }

    @Override
    public Serializable pkVal() {
        return id;
    }

}
