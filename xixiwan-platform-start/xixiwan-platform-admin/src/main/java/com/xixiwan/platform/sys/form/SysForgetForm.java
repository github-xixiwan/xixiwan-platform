package com.xixiwan.platform.sys.form;

import com.xixiwan.platform.module.web.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysForgetForm extends BaseForm {

    /**
     * 重置类型(1：邮箱 2：手机）
     */
    private Integer rtype;

    /**
     * 重置值
     */
    private String rvalue;

}
