package com.records.admin.system.service.impl;

import com.records.admin.system.entiey.SysResource;
import com.records.admin.system.mapper.SysResourceMapper;
import com.records.admin.system.service.SysAdminCacheService;
import com.records.admin.system.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author changfa
* @description 针对表【sys_resource】的数据库操作Service实现
* @createDate 2023-03-02 15:02:43
*/
@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    private SysResourceMapper resourceMapper;
//    @Autowired
//    private SysAdminCacheService adminCacheService;
//    @Override
//    public int create(SysResource SysResource) {
//        SysResource.setCreateTime(new Date());
//        return resourceMapper.insert(SysResource);
//    }

//    @Override
//    public int update(Long id, SysResource SysResource) {
//        SysResource.setId(id);
//        int count = resourceMapper.updateByPrimaryKeySelective(SysResource);
//        adminCacheService.delResourceListByResource(id);
//        return count;
//    }
//
//    @Override
//    public SysResource getItem(Long id) {
//        return resourceMapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public int delete(Long id) {
//        int count = resourceMapper.deleteByPrimaryKey(id);
//        adminCacheService.delResourceListByResource(id);
//        return count;
//    }

//    @Override
//    public List<SysResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
//        PageHelper.startPage(pageNum,pageSize);
//        SysResourceExample example = new SysResourceExample();
//        SysResourceExample.Criteria criteria = example.createCriteria();
//        if(categoryId!=null){
//            criteria.andCategoryIdEqualTo(categoryId);
//        }
//        if(StrUtil.isNotEmpty(nameKeyword)){
//            criteria.andNameLike('%'+nameKeyword+'%');
//        }
//        if(StrUtil.isNotEmpty(urlKeyword)){
//            criteria.andUrlLike('%'+urlKeyword+'%');
//        }
//        return resourceMapper.selectByExample(example);
//    }

    @Override
    public List<SysResource> listAll() {
        return resourceMapper.selectByMap(Map.of());
    }
}




