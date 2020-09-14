package com.xixiwan.platform.sys.form;

import com.xixiwan.platform.module.web.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysDictForm extends BaseForm {

    /**
     * 父级字典
     */
    private String pcode;

    /**
     * 名称
     */
    private String name;

    /**
     * ID数组
     */
    private Integer[] ids;

}
