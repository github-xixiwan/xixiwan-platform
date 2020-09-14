package com.xixiwan.platform.sys.form;

import com.xixiwan.platform.module.web.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenuForm extends BaseForm {

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 菜单编码
     */
    private String code;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单父编码
     */
    private String pcode;

    /**
     * 菜单类型（1：目录 2：菜单 3：按钮）
     */
    private Integer mtype;

    /**
     * 菜单类型（1：目录 2：菜单 3：按钮）
     */
    private List<Integer> mtypeList;

    /**
     * 角色id
     */
    private Integer roleid;

    /**
     * 菜单状态（1：启用 0：禁用）
     */
    private Integer status;

}
