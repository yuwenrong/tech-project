package com.cto.freemarker.entity.dto;

import com.cto.freemarker.entity.query.SeedProjectParamQuery;
import lombok.Data;

@Data
public class SeedProjectQueryDTO {

    private int pageNum;

    private int pageSize;

    private SeedProjectParamQuery searchParam;
}
