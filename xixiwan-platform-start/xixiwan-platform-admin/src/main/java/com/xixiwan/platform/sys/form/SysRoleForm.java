package com.xixiwan.platform.sys.form;


import com.xixiwan.platform.module.web.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleForm extends BaseForm {

    /**
     * 角色权限
     */
    private String authorities;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 角色状态（1：启用 0：禁用）
     */
    private Integer status;

}
