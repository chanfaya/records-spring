package com.records.demo.service;

import com.records.demo.common.result.Result;
import com.records.demo.dto.SysUserResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author changfa
 */
public interface SysUserService {
    Result<List<SysUserResult>> getSysUser();
}
