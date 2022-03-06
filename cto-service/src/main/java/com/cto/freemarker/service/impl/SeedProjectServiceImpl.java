package com.cto.freemarker.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cto.freemarker.entity.SeedProject;
import com.cto.freemarker.entity.dto.SeedProjectQueryDTO;
import com.cto.freemarker.mapper.SeedProjectMapper;
import com.cto.freemarker.service.SeedProjectService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SeedProjectServiceImpl implements SeedProjectService {

    @Autowired
    private SeedProjectMapper seedProjectMapper;

    @Override
    public int add(SeedProject seedProject) {
        return seedProjectMapper.insert(seedProject);
    }

    @Override
    public List<SeedProject> queryAll() {
        return seedProjectMapper.selectList(null);
    }

    @Override
    public SeedProject queryById(Integer id) {
        return seedProjectMapper.selectById(id);
    }

    @Override
    public void deleteById(Integer id) {
        seedProjectMapper.deleteById(id);
    }

    @Override
    public List<SeedProject> queryByParam() {
        return null;
    }

    @Override
    public IPage<SeedProject> page(Page objectPage, SeedProjectQueryDTO search) {
        return seedProjectMapper.selectPage(objectPage, null);
    }

    @Override
    public void updateById(SeedProject seedProject) {
        seedProjectMapper.updateById(seedProject);
    }
}
