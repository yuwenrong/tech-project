package com.cto.freemarker.entity.dto;

import lombok.Data;

@Data
public class TechFileQueryDTO {

    private Long techId;

    private int pageNum;

    private int pageSize;

    private String name;

    private String url;


}
