package com.xixiwan.platform.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.sys.entity.SysNotice;
import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.form.SysNoticeForm;

import java.util.List;

/**
 * <p>
 * 通知表 服务类
 * </p>
 *
 * @author Sente
 * @since 2018-11-14
 */
public interface ISysNoticeService extends IService<SysNotice> {

    IPage<SysNotice> selectPage(SysNoticeForm noticeForm);

    RestResponse<String> addNotice(SysNotice notice);

    RestResponse<String> editNotice(SysNotice notice);

    RestResponse<String> deleteNotice(SysNoticeForm noticeForm);

    RestResponse<String> repealNotice(SysNoticeForm noticeForm);

    RestResponse<String> releaseNotice(SysNoticeForm noticeForm);

    List<SysNotice> selectUserNoticeByForm(SysUser user, SysNoticeForm noticeForm);

}
