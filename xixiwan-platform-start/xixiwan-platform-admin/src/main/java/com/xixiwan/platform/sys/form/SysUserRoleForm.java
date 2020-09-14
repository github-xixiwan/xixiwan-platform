package com.xixiwan.platform.sys.form;


import com.xixiwan.platform.module.web.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserRoleForm extends BaseForm {

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 角色集合
     */
    private Integer[] roleids;

}
