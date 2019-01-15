package com.fct.core.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * @author xstarfct
 * @version 2019/1/10 4:45 PM
 */
@Data
public class UmsRolePermissionRelationDO implements Serializable {
    private static final long serialVersionUID = -4341028127397511809L;

    private Long id;
    private Long roleId;
    private Long permissionId;

    @Override
    public String toString() {
        return "UmsRolePermissionRelationDO{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", permissionId=" + permissionId +
                '}';
    }
}
