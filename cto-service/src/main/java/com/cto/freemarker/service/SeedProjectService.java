package com.cto.freemarker.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cto.freemarker.entity.SeedProject;
import com.cto.freemarker.entity.dto.SeedProjectQueryDTO;

import java.util.List;

public interface SeedProjectService {

    int add(SeedProject techProject);

    List<SeedProject> queryAll();

    SeedProject queryById(Integer id);

    void deleteById(Integer id);

    List<SeedProject> queryByParam();

    IPage<SeedProject> page(Page<Object> objectPage, SeedProjectQueryDTO search);

    void updateById(SeedProject techProject);
}
