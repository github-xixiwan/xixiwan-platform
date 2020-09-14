package com.xixiwan.platform.face.feign.basic.form;


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

}
