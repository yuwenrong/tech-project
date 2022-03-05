package com.cto.freemarker.entity.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TechProjectDTO {


    private int id;

    private String name;

    private int source;

    private Date beginTime;

    private Date endTime;

    private List<ApproveFileDTO> approveFileList;

    private List<ProResultDTO> resultList;

    private String ext;

}
