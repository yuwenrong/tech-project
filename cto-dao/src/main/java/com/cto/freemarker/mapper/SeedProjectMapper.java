package com.cto.freemarker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cto.freemarker.entity.SeedProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SeedProjectMapper extends BaseMapper<SeedProject> {

    @Select("SELECT * FROM tech_project WHERE sex = #{sex}")
    IPage<SeedProject> selectPageByParam(Page<?> page, String sex);
}
