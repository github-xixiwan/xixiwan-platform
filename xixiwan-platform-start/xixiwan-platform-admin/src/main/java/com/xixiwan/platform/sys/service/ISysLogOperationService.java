package com.xixiwan.platform.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xixiwan.platform.sys.entity.SysLogOperation;
import com.xixiwan.platform.sys.form.SysLogOperationForm;

/**
 * <p>
 * 操作日志 服务类
 * </p>
 *
 * @author Sente
 * @since 2018-11-13
 */
public interface ISysLogOperationService extends IService<SysLogOperation> {

	IPage<SysLogOperation> selectPage(SysLogOperationForm logOperationForm);

}
