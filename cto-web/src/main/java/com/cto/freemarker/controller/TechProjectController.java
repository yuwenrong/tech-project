package com.cto.freemarker.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cto.freemarker.common.SessionUtil;
import com.cto.freemarker.controller.base.BaseController;
import com.cto.freemarker.entity.AdminUser;
import com.cto.freemarker.entity.Role;
import com.cto.freemarker.entity.TechProject;
import com.cto.freemarker.entity.dto.TechProjectQueryDTO;
import com.cto.freemarker.service.IRoleService;
import com.cto.freemarker.service.TechProjectService;
import com.cto.freemarker.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("tech")
public class TechProjectController extends BaseController {

    @Autowired
    TechProjectService techProjectService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping
    @RequiresPermissions("tech")
    public String index(Model model) {
        return "tech/index";
    }

    @RequestMapping("page")
    @ResponseBody
    public IPage<TechProject> list(TechProjectQueryDTO search) {
        return techProjectService.page(new Page<>(search.getPageNum(), search.getPageSize()), search);
    }

    @RequestMapping(value = "/add")
    public String add(Model model) {
        Role role = new Role();
        role.setStatus("1");
        List<Role> roleList = roleService.list(Wrappers.lambdaQuery(role));
        model.addAttribute("roleList", roleList);
        return "tech/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Long id, Model model) {
        if (id != null) {
            TechProject techProject = techProjectService.queryById(id.intValue());
            model.addAttribute("techProject", techProject);
        }
        Role role = new Role();
        role.setStatus("1");
        List<Role> roleList = roleService.list(Wrappers.lambdaQuery(role));
        model.addAttribute("roleList", roleList);
        return "tech/edit";
    }


    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdate(TechProject techProject) {
        Date date = new Date();
        if (techProject.getId() == null) {
            techProject.setCreateTime(date);
            techProject.setCreateUser(getCurrentUser().getId());
            techProjectService.add(techProject);
        } else {
            techProject.setUpdateTime(date);
            techProject.setUpdateUser(getCurrentUser().getId());
            techProjectService.updateById(techProject);
        }
        return Result.ok();
    }


    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(Long id, Model model) {
        techProjectService.deleteById(id.intValue());
        return Result.ok();
    }
}
