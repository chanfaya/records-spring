package com.records.admin.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.records.admin.system.dto.SysAdminLoginParam;
import com.records.admin.system.dto.SysAdminParam;
import com.records.admin.system.dto.UpdateAdminPasswordParam;
import com.records.admin.system.entiey.SysAdmin;
import com.records.admin.system.entiey.SysRole;
import com.records.admin.system.service.SysAdminService;
import com.records.admin.system.service.SysRoleService;
import com.records.common.result.Result;
import com.records.common.result.ResultCode2ForServer;
import com.records.common.result.ResultCode5ForServer;
import com.records.common.result.ResultCodeForToken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author changfa
* @description 针对表【sys_admin(后台用户表，定义了后台用户的一些基本信息。)】的数据库操作Service
* @createDate 2023-03-02 15:01:11
*/
@RestController
@RequestMapping("/admin")
public class SysAdminController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private SysAdminService adminService;
    @Autowired
    private SysRoleService roleService;

    /**
     * 注册
     * @param SysAdminParam
     * @return
     */
    @PostMapping("/register")
    public Result<SysAdmin> register(@Validated @RequestBody SysAdminParam SysAdminParam) {
        SysAdmin SysAdmin = adminService.register(SysAdminParam);
        if (SysAdmin == null) {
            return Result.getResult(ResultCodeForToken.DENIED_AUTHENTICATION);
        }
        return Result.getResult(ResultCode2ForServer.SAVE_SUCCESS, SysAdmin);
    }

    /**
     * 登录
     * @param SysAdminLoginParam
     * @return
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody SysAdminLoginParam SysAdminLoginParam) {
        String token = adminService.login(SysAdminLoginParam.getUsername(), SysAdminLoginParam.getPassword());
        if (token == null) {
            return Result.getResult(ResultCode5ForServer.LOGIN_ERROR);
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return Result.getResult(ResultCode2ForServer.LOGIN_SUCCESS, tokenMap);
    }

    /**
     * 刷新token
     * @param request
     * @return
     */
    @GetMapping("/refreshToken")
    public Result refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if (refreshToken == null) {
            return Result.getResult(ResultCodeForToken.TOKEN_EXPIRED_REFRESH);
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return Result.getResult(ResultCodeForToken.REFRESH_TOKEN_OK, tokenMap);
    }

    /**
     * 获取当前用户登录信息
     * @param principal
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Result getAdminInfo(Principal principal) {
        if(principal==null){
            return Result.getResult(ResultCodeForToken.DENIED_AUTHENTICATION);
        }
        String username = principal.getName();
        SysAdmin SysAdmin = adminService.getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", SysAdmin.getUsername());
        data.put("menus", roleService.getMenuList(SysAdmin.getId()));
        data.put("icon", SysAdmin.getIcon());
        List<SysRole> roleList = adminService.getRoleList(SysAdmin.getId());
        if(!CollectionUtils.isEmpty(roleList)){
            List<String> roles = roleList.stream().map(SysRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return Result.getResult(ResultCode2ForServer.OK, data);
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Result logout() {
        return Result.getResult(ResultCode2ForServer.LOGOUT_SUCCESS);
    }

//    /**
//     * 根据用户名或姓名分页获取用户列表
//     */
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public Result<IPage<SysAdmin>> list(@RequestParam(value = "keyword", required = false) String keyword,
//                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
//                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
//        List<SysAdmin> adminList = adminService.list(keyword, pageSize, pageNum);
//        return Result.getResult(ResultCode2ForServer.OK, adminList);
//    }

    /**
     * 获取指定用户信息
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<SysAdmin> getItem(@PathVariable Long id) {
        SysAdmin admin = adminService.getItem(id);
        return Result.getResult(ResultCode2ForServer.OK,admin);
    }

    /**
     * 修改指定用户信息
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Result update(@PathVariable Long id, @RequestBody SysAdmin admin) {
        int count = adminService.update(id, admin);
        if (count > 0) {
            return Result.getResult(ResultCode2ForServer.OK, count);
        }
        return Result.getResult(ResultCode5ForServer.UPDATE_ERROR);
    }

    /**
     * 修改指定用户密码
     */
//    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
//    public Result updatePassword(@Validated @RequestBody UpdateAdminPasswordParam updatePasswordParam) {
//        int status = adminService.updatePassword(updatePasswordParam);
//        if (status > 0) {
//            return CommonResult.success(status);
//        } else if (status == -1) {
//            return CommonResult.failed("提交参数不合法");
//        } else if (status == -2) {
//            return CommonResult.failed("找不到该用户");
//        } else if (status == -3) {
//            return CommonResult.failed("旧密码错误");
//        } else {
//            return CommonResult.failed();
//        }
//    }
//
//    /**
//     * 删除指定用户信息
//     */
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
//    public Result delete(@PathVariable Long id) {
//        int count = adminService.delete(id);
//        if (count > 0) {
//            return CommonResult.success(count);
//        }
//        return CommonResult.failed();
//    }
//
//    /**
//     * 修改帐号状态
//     */
//    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
//    public Result updateStatus(@PathVariable Long id,@RequestParam(value = "status") Integer status) {
//        SysAdmin SysAdmin = new SysAdmin();
//        SysAdmin.setStatus(status);
//        int count = adminService.update(id,SysAdmin);
//        if (count > 0) {
//            return CommonResult.success(count);
//        }
//        return CommonResult.failed();
//    }
//
//    /**
//     * 给用户分配角色
//     */
//    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
//    public Result updateRole(@RequestParam("adminId") Long adminId,
//                                   @RequestParam("roleIds") List<Long> roleIds) {
//        int count = adminService.updateRole(adminId, roleIds);
//        if (count >= 0) {
//            return CommonResult.success(count);
//        }
//        return CommonResult.failed();
//    }
//
//    /**
//     * 获取指定用户的角色
//     */
//    @RequestMapping(value = "/role/{adminId}", method = RequestMethod.GET)
//    public Result<List<SysRole>> getRoleList(@PathVariable Long adminId) {
//        List<SysRole> roleList = adminService.getRoleList(adminId);
//        return Result.getResult(ResultCode2ForServer.OK, roleList);
//    }

}
