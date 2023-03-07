package com.records.admin.system.entiey;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_admin_depart
 */
@TableName(value ="sys_admin_depart")
@Data
public class SysAdminDepart implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 员工id
     */
    private Long adminId;

    /**
     * 部门id
     */
    private Long departId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}