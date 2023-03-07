package com.records.admin.system.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class SysAdminLoginParam {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
