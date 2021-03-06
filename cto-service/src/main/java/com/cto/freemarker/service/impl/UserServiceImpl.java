package com.cto.freemarker.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cto.freemarker.entity.User;
import com.cto.freemarker.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-08-25 03:01
 */
@Service
public class UserServiceImpl {
    @Autowired
    private UserMapper userMapper;


    //register
    public synchronized int register(User user) throws Exception {
        if (isEmpty(user.getName())) {
            return userMapper.insert(user);
        } else {
            throw new Exception("此用户名已经被注册");
        }
    }

    private boolean isEmpty(String name) {
        QueryWrapper<User> uqw = new QueryWrapper<>();
        uqw.eq("name", name);
        return userMapper.selectCount(uqw) == 0;
    }

    //添加一条数据
    public int add(User user) {
        return userMapper.insert(user);
    }

    //添加多条数据
    public void add(List<User> users) {
        for (User user : users) {
            add(user);
        }
    }

    //查询全部
    public List<User> queryAll() {
        return userMapper.selectList(null);
    }

    //通过Id查询
    public User queryById(User user) {
        return userMapper.selectById(user.getId());
    }

    //通过Id查询批量查询
    public List<User> queryByIds() {
        List<Integer> idList = new ArrayList<>();
        idList.add(10);
        idList.add(11);
        return userMapper.selectBatchIds(idList);
    }

    //通过姓名模糊查询
    public List<User> queryByName1(String name) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("name", name); //参数为表中的列名，要查询的条件
        return userMapper.selectList(userQueryWrapper);
    }

    public List<User> queryByNameMap(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        return userMapper.selectByMap(map);
    }

    //通过姓名精确查询
    public List<User> queryByName2(String name) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", name);
        return userMapper.selectList(userQueryWrapper);
    }

    // 计数
    public int count() {
        return userMapper.selectCount(null);
    }

    // 通过ID删除
    public int deleteById(User user) {
        return userMapper.deleteById(user.getId());
    }

    // 通过条件删除
    public void deleteBy(String column, Object val) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.eq(column, val);
        int num = userMapper.delete(userQueryWrapper);
        System.out.println("影响行数：" + num);
    }

    public void delete(Map<String, Object> map) {
        userMapper.deleteByMap(map);
    }

    //通过id批量删除
    public void deleteByIds() {
        List<Integer> idList = new ArrayList<>();
        idList.add(10);
        idList.add(11);
        int num = userMapper.deleteBatchIds(idList);
        System.out.println("影响行数：" + num);
    }

    // 根据条件更新
    public void changeBy(User user, String column, Object val) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq(column, val);
        int num = userMapper.update(user, userQueryWrapper);
        System.out.println("影响行数：" + num);
    }

    // 通过ID修改信息
    public void changeUserById(User user) {
        int num = userMapper.updateById(user);
        System.out.println("影响行数：" + num);
    }


}
