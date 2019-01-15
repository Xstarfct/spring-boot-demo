package com.fct.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fct.core.domain.UmsPermissionDO;
import com.fct.core.domain.UmsRolePermissionRelationDO;

/**
 * @author xstarfct
 * @version 2019/1/10 4:43 PM
 */
public interface UmsRolePermissionRelationDAO {
    /**
     * 批量插入角色和权限关系
     *
     * @param list
     * @return
     */
    int insertList(@Param("list") List<UmsRolePermissionRelationDO> list);

    /**
     * 根据角色获取权限
     *
     * @param roleId
     * @return
     */
    List<UmsPermissionDO> getPermissionList(@Param("roleId") Long roleId);
}
