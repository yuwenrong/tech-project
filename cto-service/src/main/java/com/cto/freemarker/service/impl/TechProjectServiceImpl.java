package com.cto.freemarker.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cto.freemarker.entity.TechProject;
import com.cto.freemarker.entity.dto.TechProjectQueryDTO;
import com.cto.freemarker.mapper.TechProjectMapper;
import com.cto.freemarker.service.TechProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechProjectServiceImpl implements TechProjectService {

    @Autowired
    private TechProjectMapper techProjectMapper;


    @Override
    public int add(TechProject techProject) {
        return techProjectMapper.insert(techProject);
    }

    @Override
    public List<TechProject> queryAll() {
        return techProjectMapper.selectList(null);
    }

    @Override
    public TechProject queryById(Integer id) {
        return techProjectMapper.selectById(id);
    }

    @Override
    public void deleteById(Integer id) {
        techProjectMapper.deleteById(id);
    }

    @Override
    public List<TechProject> queryByParam() {
        return null;
    }

    @Override
    public void updateById(TechProject techProject) {
        techProjectMapper.updateById(techProject);
    }

    @Override
    public IPage<TechProject> page(Page page, TechProjectQueryDTO search) {
        return techProjectMapper.selectPage(page, null);
    }
}
