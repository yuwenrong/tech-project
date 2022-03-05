package com.cto.freemarker.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cto.freemarker.controller.base.BaseController;
import com.cto.freemarker.entity.TechProject;
import com.cto.freemarker.entity.dto.TechProjectQueryDTO;
import com.cto.freemarker.service.TechProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("tech")
public class TechProjectController extends BaseController {
    @Autowired
    TechProjectService techProjectService;

    @RequestMapping("page")
    @ResponseBody
    public IPage<TechProject> list(TechProjectQueryDTO search) {
        return techProjectService.page(new Page<>(search.getPageNum(), search.getPageSize()), search);
    }




}
