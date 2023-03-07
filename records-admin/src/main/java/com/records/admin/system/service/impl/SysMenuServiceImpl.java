package com.records.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.records.admin.system.dto.SysMenuNode;
import com.records.admin.system.entiey.SysMenu;
import com.records.admin.system.mapper.SysMenuMapper;
import com.records.admin.system.service.SysMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author changfa
* @description 针对表【sys_menu】的数据库操作Service实现
* @createDate 2023-03-02 15:02:43
*/
@Service
public class SysMenuServiceImpl implements SysMenuService{

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public int create(SysMenu SysMenu) {
        return 0;
    }

    @Override
    public int update(Long id, SysMenu SysMenu) {
        return 0;
    }

    @Override
    public SysMenu getItem(Long id) {
        return null;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public List<SysMenu> list(Long parentId, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public List<SysMenuNode> treeList() {
        List<SysMenu> menuList = menuMapper.selectList(new QueryWrapper<>());
        List<SysMenuNode> result = menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
        return result;
    }

    @Override
    public int updateHidden(Long id, Integer hidden) {
        return 0;
    }

    /**
     * 将UmsMenu转化为UmsMenuNode并设置children属性
     */
    private SysMenuNode covertMenuNode(SysMenu menu, List<SysMenu> menuList) {
        SysMenuNode node = new SysMenuNode();
        BeanUtils.copyProperties(menu, node);
        List<SysMenuNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}




