package com.xixiwan.platform.face.feign.order.form;


import com.xixiwan.platform.module.web.form.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderForm extends BaseForm {

    /**
     * 订单ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

}
