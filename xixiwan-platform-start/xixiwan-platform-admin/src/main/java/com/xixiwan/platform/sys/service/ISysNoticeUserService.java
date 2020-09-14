package com.xixiwan.platform.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xixiwan.platform.sys.entity.SysNotice;
import com.xixiwan.platform.sys.entity.SysNoticeUser;
import com.xixiwan.platform.sys.entity.SysUser;

/**
 * <p>
 * 通知和用户关联表 服务类
 * </p>
 *
 * @author Sente
 * @since 2018-11-15
 */
public interface ISysNoticeUserService extends IService<SysNoticeUser> {

	SysNotice readNotice(SysUser user, Integer id);

}
