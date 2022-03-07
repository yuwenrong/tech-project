package com.cto.freemarker.entity.dto;

import lombok.Data;

@Data
public class SeedProjectQueryDTO {

    private int pageNum;

    private int pageSize;

    private String land;

    private String functionArea;

    private String treeType;

    private String buildBeginTime;

    private String buildEndTime;
}
