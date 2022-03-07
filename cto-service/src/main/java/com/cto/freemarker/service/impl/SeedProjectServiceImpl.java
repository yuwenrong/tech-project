package com.cto.freemarker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cto.freemarker.entity.SeedProject;
import com.cto.freemarker.entity.dto.SeedProjectQueryDTO;
import com.cto.freemarker.mapper.SeedProjectMapper;
import com.cto.freemarker.service.SeedProjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

        LambdaQueryWrapper queryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotEmpty(search.getLand())) {
            queryWrapper.eq("land", search.getLand());
        }
        if(StringUtils.isNotEmpty(search.getFunctionArea())) {
            queryWrapper.eq("function_area", search.getFunctionArea());
        }
        if(StringUtils.isNotEmpty(search.getTreeType())) {
            queryWrapper.eq("tree_type", search.getTreeType());
        }
        if(StringUtils.isNotEmpty(search.getBuildBeginTime())) {
            queryWrapper.apply("date_format (build_time,'%Y-%m-%d') >= {0}",
                    ""+ search.getBuildBeginTime() + "");
        }
        if(StringUtils.isNotEmpty(search.getBuildEndTime())) {
            queryWrapper.apply("date_format (build_time,'%Y-%m-%d') <={0}",
                    ""+search.getBuildEndTime()+"");
        }
        return seedProjectMapper.selectPage(objectPage, queryWrapper);
    }

    @Override
    public void updateById(SeedProject seedProject) {
        seedProjectMapper.updateById(seedProject);
    }
}
