package com.cto.freemarker.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cto.freemarker.controller.base.BaseController;
import com.cto.freemarker.entity.Role;
import com.cto.freemarker.entity.SeedProject;
import com.cto.freemarker.entity.dto.SeedProjectQueryDTO;
import com.cto.freemarker.service.IRoleService;
import com.cto.freemarker.service.SeedProjectService;
import com.cto.freemarker.utils.Result;
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
@RequestMapping("seed")
public class SeedProjectController extends BaseController {

    @Autowired
    SeedProjectService seedProjectService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping
    @RequiresPermissions("seed")
    public String index(Model model) {
        return "seed/index";
    }

    @RequestMapping("page")
    @ResponseBody
    public IPage<SeedProject> list(SeedProjectQueryDTO search) {
        return seedProjectService.page(new Page<>(search.getPageNum(), search.getPageSize()), search);
    }

    @RequestMapping(value = "/add")
    public String add(Model model) {
        Role role = new Role();
        role.setStatus("1");
        List<Role> roleList = roleService.list(Wrappers.lambdaQuery(role));
        model.addAttribute("roleList", roleList);
        return "seed/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Long id, Model model) {
        if (id != null) {
            SeedProject seedProject = seedProjectService.queryById(id.intValue());
            model.addAttribute("seedProject", seedProject);
        }
        Role role = new Role();
        role.setStatus("1");
        List<Role> roleList = roleService.list(Wrappers.lambdaQuery(role));
        model.addAttribute("roleList", roleList);
        return "seed/edit";
    }


    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdate(SeedProject seedProject) {
        Date date = new Date();
        if (seedProject.getId() == null) {
            seedProject.setCreateTime(date);
            seedProject.setCreateUser(getCurrentUser().getId());
            seedProjectService.add(seedProject);
        } else {
            seedProject.setUpdateTime(date);
            seedProject.setUpdateUser(getCurrentUser().getId());
            seedProjectService.updateById(seedProject);
        }
        return Result.ok();
    }


    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(Long id, Model model) {
        seedProjectService.deleteById(id.intValue());
        return Result.ok();
    }
}
