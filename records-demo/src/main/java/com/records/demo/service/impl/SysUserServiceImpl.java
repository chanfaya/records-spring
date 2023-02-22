package com.records.demo.service.impl;

import com.records.demo.common.result.Result;
import com.records.demo.common.result.ResultCode2ForServer;
import com.records.demo.dao.SysUserDao;
import com.records.demo.dto.SysUserResult;
import com.records.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    private SysUserDao sysUserDao;

    @Autowired
    public SysUserServiceImpl(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    @Override
    public Result<List<SysUserResult>> getSysUser() {
        return new Result<>(ResultCode2ForServer.OK, sysUserDao.getSysUser());
    }
}
