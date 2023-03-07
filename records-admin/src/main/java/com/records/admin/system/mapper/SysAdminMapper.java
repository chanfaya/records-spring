package com.records.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.records.admin.system.entiey.SysAdmin;
import org.apache.ibatis.annotations.Mapper;

/**
* @author changfa
* @description 针对表【sys_admin(后台用户表，定义了后台用户的一些基本信息。)】的数据库操作Mapper
* @createDate 2023-03-02 17:20:55
* @Entity com.records.admin.system.SysAdmin
*/
@Mapper
public interface SysAdminMapper extends BaseMapper<SysAdmin> {

}




