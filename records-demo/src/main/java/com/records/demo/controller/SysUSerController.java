package com.records.demo.controller;

import com.records.demo.common.result.Result;
import com.records.demo.dto.SysUserResult;
import com.records.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys")
public class SysUSerController {

    private SysUserService sysUserService;

    @Autowired
    public SysUSerController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @GetMapping("/user")
    public Result<List<SysUserResult>> getSysUser (){
        return sysUserService.getSysUser();
    }
}
