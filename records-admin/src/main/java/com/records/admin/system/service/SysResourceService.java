package com.records.admin.system.service;

import com.records.admin.system.entiey.SysResource;

import java.util.List;

/**
* @author changfa
* @description 针对表【sys_resource】的数据库操作Service
* @createDate 2023-03-02 15:02:43
*/
public interface SysResourceService {
//    /**
//     * 添加资源
//     */
//    int create(SysResource SysResource);
//
//    /**
//     * 修改资源
//     */
//    int update(Long id, SysResource SysResource);
//
//    /**
//     * 获取资源详情
//     */
//    SysResource getItem(Long id);
//
//    /**
//     * 删除资源
//     */
//    int delete(Long id);
//
//    /**
//     * 分页查询资源
//     */
//    List<SysResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * 查询全部资源
     */
    List<SysResource> listAll();

}
