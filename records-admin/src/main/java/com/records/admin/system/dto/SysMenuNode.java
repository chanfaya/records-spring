package com.records.admin.system.dto;

import com.records.admin.system.entiey.SysMenu;
import lombok.Data;

import java.util.List;

@Data
public class SysMenuNode extends SysMenu {

    private List<SysMenuNode> children;
}
