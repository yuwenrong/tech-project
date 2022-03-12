package com.cto.freemarker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cto.freemarker.entity.TechFileProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface TechFileMapper extends BaseMapper<TechFileProject> {

    @Select("SELECT * FROM tech_file_project WHERE sex = #{sex}")
    IPage<TechFileProject> selectPageByParam(Page<?> page, String sex);
}
