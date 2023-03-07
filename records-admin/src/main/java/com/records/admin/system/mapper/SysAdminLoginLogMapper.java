package com.records.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.records.admin.system.entiey.SysAdminLoginLog;
import org.apache.ibatis.annotations.Mapper;

/**
* @author changfa
* @description 针对表【sys_admin_login_log(后台用户登录日志表)】的数据库操作Mapper
* @createDate 2023-03-02 17:20:55
* @Entity com.records.admin.system.SysAdminLoginLog
*/
@Mapper
public interface SysAdminLoginLogMapper extends BaseMapper<SysAdminLoginLog> {

}




