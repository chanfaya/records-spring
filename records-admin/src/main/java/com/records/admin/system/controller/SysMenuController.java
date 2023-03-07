package com.records.admin.system.controller;

import com.records.admin.system.dto.SysMenuNode;
import com.records.admin.system.service.SysMenuService;
import com.records.common.result.Result;
import com.records.common.result.ResultCode2ForServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @author changfa
* @description 针对表【sys_menu】的数据库操作Service
* @createDate 2023-03-02 15:02:43
*/
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService menuService;
//
//    @ApiOperation("添加后台菜单")
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult create(@RequestBody UmsMenu umsMenu) {
//        int count = menuService.create(umsMenu);
//        if (count > 0) {
//            return CommonResult.success(count);
//        } else {
//            return CommonResult.failed();
//        }
//    }
//
//    @ApiOperation("修改后台菜单")
//    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult update(@PathVariable Long id,
//                               @RequestBody UmsMenu umsMenu) {
//        int count = menuService.update(id, umsMenu);
//        if (count > 0) {
//            return CommonResult.success(count);
//        } else {
//            return CommonResult.failed();
//        }
//    }
//
//    @ApiOperation("根据ID获取菜单详情")
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<UmsMenu> getItem(@PathVariable Long id) {
//        UmsMenu umsMenu = menuService.getItem(id);
//        return CommonResult.success(umsMenu);
//    }
//
//    @ApiOperation("根据ID删除后台菜单")
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult delete(@PathVariable Long id) {
//        int count = menuService.delete(id);
//        if (count > 0) {
//            return CommonResult.success(count);
//        } else {
//            return CommonResult.failed();
//        }
//    }
//
//    @ApiOperation("分页查询后台菜单")
//    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<CommonPage<UmsMenu>> list(@PathVariable Long parentId,
//                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
//                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
//        List<UmsMenu> menuList = menuService.list(parentId, pageSize, pageNum);
//        return CommonResult.success(CommonPage.restPage(menuList));
//    }
//
    /**
     * 树形结构返回所有菜单列表
     */
    @GetMapping("/treeList")
    public Result<List<SysMenuNode>> treeList() {
        List<SysMenuNode> list = menuService.treeList();
        return Result.getResult(ResultCode2ForServer.OK, list);
    }
//
//    @ApiOperation("修改菜单显示状态")
//    @RequestMapping(value = "/updateHidden/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult updateHidden(@PathVariable Long id, @RequestParam("hidden") Integer hidden) {
//        int count = menuService.updateHidden(id, hidden);
//        if (count > 0) {
//            return CommonResult.success(count);
//        } else {
//            return CommonResult.failed();
//        }
//    }

}
