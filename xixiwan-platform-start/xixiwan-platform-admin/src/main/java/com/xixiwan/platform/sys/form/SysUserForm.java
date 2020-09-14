package com.xixiwan.platform.sys.form;

import com.xixiwan.platform.module.web.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserForm extends BaseForm {

    /**
     * 账号
     */
    private String username;

    /**
     * 名字
     */
    private String name;

    /**
     * 原密码
     */
    private String originalPassword;

    /**
     * 密码
     */
    private String password;

    /**
     * 通知id
     */
    private String noticeid;

    /**
     * 单一选择
     */
    private boolean singleSelect;

}
