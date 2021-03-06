package com.cto.freemarker.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cto.freemarker.entity.TechFileProject;
import com.cto.freemarker.entity.TechProject;
import com.cto.freemarker.entity.dto.TechProjectQueryDTO;

import java.util.List;

public interface TechProjectService {

    int add(TechProject techProject);

    List<TechProject> queryAll();

    TechProject queryById(Integer id);

    void deleteById(Integer id);

    List<TechProject> queryByParam();

    IPage<TechProject> page(Page<Object> objectPage, TechProjectQueryDTO search);

    void updateById(TechProject techProject);

    IPage<TechFileProject> filePage(Page<Object> objectPage);
}
