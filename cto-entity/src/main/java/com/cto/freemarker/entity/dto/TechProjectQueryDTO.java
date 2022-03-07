package com.cto.freemarker.entity.dto;

import lombok.Data;

@Data
public class TechProjectQueryDTO {

    private int pageNum;

    private int pageSize;

    private String name;

    private String source;

    private String beginTime;

    private String endTime;
}
