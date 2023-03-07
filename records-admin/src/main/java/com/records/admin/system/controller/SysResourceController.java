package com.records.admin.system.controller;

/**
* @author changfa
* @description 针对表【sys_resource】的数据库操作Service
* @createDate 2023-03-02 15:02:43
*/
public class SysResourceController {

//    @Autowired
//    private UmsResourceService resourceService;
//    @Autowired
//    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;
//
//    @ApiOperation("添加后台资源")
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult create(@RequestBody UmsResource umsResource) {
//        int count = resourceService.create(umsResource);
//        dynamicSecurityMetadataSource.clearDataSource();
//        if (count > 0) {
//            return CommonResult.success(count);
//        } else {
//            return CommonResult.failed();
//        }
//    }
//
//    @ApiOperation("修改后台资源")
//    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult update(@PathVariable Long id,
//                               @RequestBody UmsResource umsResource) {
//        int count = resourceService.update(id, umsResource);
//        dynamicSecurityMetadataSource.clearDataSource();
//        if (count > 0) {
//            return CommonResult.success(count);
//        } else {
//            return CommonResult.failed();
//        }
//    }
//
//    @ApiOperation("根据ID获取资源详情")
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<UmsResource> getItem(@PathVariable Long id) {
//        UmsResource umsResource = resourceService.getItem(id);
//        return CommonResult.success(umsResource);
//    }
//
//    @ApiOperation("根据ID删除后台资源")
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult delete(@PathVariable Long id) {
//        int count = resourceService.delete(id);
//        dynamicSecurityMetadataSource.clearDataSource();
//        if (count > 0) {
//            return CommonResult.success(count);
//        } else {
//            return CommonResult.failed();
//        }
//    }
//
//    @ApiOperation("分页模糊查询后台资源")
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<CommonPage<UmsResource>> list(@RequestParam(required = false) Long categoryId,
//                                                      @RequestParam(required = false) String nameKeyword,
//                                                      @RequestParam(required = false) String urlKeyword,
//                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
//                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
//        List<UmsResource> resourceList = resourceService.list(categoryId,nameKeyword, urlKeyword, pageSize, pageNum);
//        return CommonResult.success(CommonPage.restPage(resourceList));
//    }
//
//    @ApiOperation("查询所有后台资源")
//    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<List<UmsResource>> listAll() {
//        List<UmsResource> resourceList = resourceService.listAll();
//        return CommonResult.success(resourceList);
//    }
}
