package com.cto.freemarker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cto.freemarker.entity.TechProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface TechProjectMapper extends BaseMapper<TechProject> {

    @Select("SELECT * FROM tech_project WHERE sex = #{sex}")
    IPage<TechProject> selectPageByParam(Page<?> page, String sex);
}
