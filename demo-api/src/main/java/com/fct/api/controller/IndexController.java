package com.fct.api.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fct.api.dto.CommonApiResult;
import com.fct.core.dao.UmsRolePermissionRelationDAO;
import com.fct.service.test.HelloService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * index 控制器
 *
 * @author xstarfct
 * @version 2019/1/10 6:10 PM
 */
@Api(description = "index示例")
@RestController
@RequestMapping("/index")
public class IndexController {

    @Resource
    private UmsRolePermissionRelationDAO umsRolePermissionRelationDAO;

    @Reference(version = "${demo.service.version}")
    private HelloService helloService;

    @ApiOperation("根据角色ID获取全部权限")
    @RequestMapping(value = "permission/{roleId}", method = RequestMethod.GET)
    public Object getPermissionByRoleId(@PathVariable("roleId") Long roleId) {
        return new CommonApiResult().success(umsRolePermissionRelationDAO.getPermissionList(roleId));
    }

    @ApiOperation("测试dubbo服务是否调的通")
    @RequestMapping(value = "/sayHello/{name}", method = RequestMethod.GET)
    public String sayHello(@PathVariable("name") String name) {
        return helloService.sayHello(name);
    }

}
