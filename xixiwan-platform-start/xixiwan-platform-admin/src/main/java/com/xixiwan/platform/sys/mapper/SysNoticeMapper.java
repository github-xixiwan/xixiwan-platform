package com.xixiwan.platform.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xixiwan.platform.sys.entity.SysNotice;
import com.xixiwan.platform.sys.form.SysNoticeForm;

import java.util.List;

/**
 * <p>
 * 通知表 Mapper 接口
 * </p>
 *
 * @author Sente
 * @since 2018-11-14
 */
public interface SysNoticeMapper extends BaseMapper<SysNotice> {

	List<SysNotice> selectUserNoticeByForm(SysNoticeForm noticeForm);

}
