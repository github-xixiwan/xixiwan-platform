package com.xixiwan.platform.sys.form;

import com.xixiwan.platform.module.web.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysLogOperationForm extends BaseForm {

    /**
     * 账号
     */
    private String username;

    /**
     * 名字
     */
    private String name;

    /**
     * 请求ip
     */
    private String ip;

    /**
     * 请求路径
     */
    private String requestpath;

    /**
     * 包名
     */
    private String packagename;

    /**
     * 方法名称
     */
    private String methodname;

    /**
     * 方法描述
     */
    private String methoddescribe;

    /**
     * 请求参数
     */
    private String parameters;

}
