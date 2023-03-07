package com.records.admin.system.entiey;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 后台用户登录日志表
 * @TableName sys_admin_login_log
 */
@TableName(value ="sys_admin_login_log")
@Data
public class SysAdminLoginLog implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long adminId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private String ip;

    /**
     * 
     */
    private String address;

    /**
     * 浏览器登录类型
     */
    private String userAgent;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}