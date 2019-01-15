package com.fct.core.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author xstarfct
 * @version 2019/1/10 4:47 PM
 */
@Data
public class UmsPermissionDO implements Serializable {
    private static final long serialVersionUID = -1171111893002083283L;
    private Long    id;
    /**
     * 父级权限id
     */
    private Long    pid;
    /**
     * 名称
     */
    private String  name;
    /**
     * 权限值
     */
    private String  value;
    /**
     * 图标
     */
    private String  icon;
    /**
     * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     */
    private Integer type;
    /**
     * 前端资源路径
     */
    private String  uri;
    /**
     * 启用状态；0->禁用；1->启用
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date    createTime;
    /**
     * 排序
     */
    private Integer sort;

    @Override
    public String toString() {
        return "UmsPermissionDO{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", icon='" + icon + '\'' +
                ", type=" + type +
                ", uri='" + uri + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", sort=" + sort +
                '}';
    }
}
