package com.cto.freemarker.entity.query;

import lombok.Data;

import java.util.Date;

@Data
public class SeedProjectParamQuery {

    private String name;

    private int source;

    private Date beginTime;

    private Date endTime;
}
