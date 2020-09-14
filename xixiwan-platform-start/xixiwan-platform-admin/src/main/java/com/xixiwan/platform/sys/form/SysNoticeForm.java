package com.xixiwan.platform.sys.form;

import com.xixiwan.platform.module.web.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysNoticeForm extends BaseForm {

    /**
     * ID
     */
    private Integer id;

    /**
     * 通知类型
     */
    private String type;

    /**
     * 对象类型（1：单个用户 2：多个用户 3：所有用户）
     */
    private String objecttype;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 通知状态（1：未发布 2：已发布 3：已撤销）
     */
    private Integer status;

    /**
     * ID数组
     */
    private Integer[] ids;

    /**
     * 用户ID数组
     */
    private Integer[] userids;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 是否阅读（1：是 0：否）
     */
    private Integer isread;

}
