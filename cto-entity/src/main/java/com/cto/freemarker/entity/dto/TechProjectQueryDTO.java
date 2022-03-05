package com.cto.freemarker.entity.dto;

import com.cto.freemarker.entity.query.TechProjectParamQuery;
import lombok.Data;

@Data
public class TechProjectQueryDTO {

    private int pageNum;

    private int pageSize;

    private TechProjectParamQuery searchParam;
}
