package com.records.demo.dao;

import com.records.demo.dto.SysUserResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserDao {
    List<SysUserResult> getSysUser();

}
