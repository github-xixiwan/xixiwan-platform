package com.xixiwan.platform.sys.form;

import com.xixiwan.platform.module.web.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysLogLoginForm extends BaseForm {

    /**
     * 账号
     */
    private String username;

    /**
     * 名字
     */
    private String name;

    /**
     * 登录ip
     */
    private String ip;

}
