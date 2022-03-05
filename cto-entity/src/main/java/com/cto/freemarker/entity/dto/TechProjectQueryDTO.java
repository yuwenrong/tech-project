package com.cto.freemarker.entity.dto;

import com.cto.freemarker.entity.TechProject;
import lombok.Data;

@Data
public class TechProjectQueryDTO {

    private int pageNum;

    private int pageSize;

    private TechProject searchParam;
}
