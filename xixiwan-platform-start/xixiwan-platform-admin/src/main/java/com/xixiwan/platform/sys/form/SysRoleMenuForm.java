package com.xixiwan.platform.sys.form;

import com.xixiwan.platform.module.web.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleMenuForm extends BaseForm {

    /**
     * 角色id
     */
    private Integer roleid;

    /**
     * 菜单集合
     */
    private Integer[] menuids;

}
