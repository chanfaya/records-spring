package com.records.admin.system.entiey;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_depart_role
 */
@TableName(value ="sys_depart_role")
@Data
public class SysDepartRole implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long departId;

    /**
     * 
     */
    private Long roleId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}