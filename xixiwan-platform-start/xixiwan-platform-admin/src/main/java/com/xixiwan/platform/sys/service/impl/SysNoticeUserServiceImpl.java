package com.xixiwan.platform.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xixiwan.platform.constant.CommonConsts;
import com.xixiwan.platform.sys.entity.SysNotice;
import com.xixiwan.platform.sys.entity.SysNoticeUser;
import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.mapper.SysNoticeMapper;
import com.xixiwan.platform.sys.mapper.SysNoticeUserMapper;
import com.xixiwan.platform.sys.service.ISysNoticeUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 通知和用户关联表 服务实现类
 * </p>
 *
 * @author Sente
 * @since 2018-11-15
 */
@Service
@Transactional
public class SysNoticeUserServiceImpl extends ServiceImpl<SysNoticeUserMapper, SysNoticeUser>
		implements ISysNoticeUserService {

	@Resource
	private SysNoticeUserMapper sysNoticeUserMapper;

	@Resource
	private SysNoticeMapper sysNoticeMapper;

	@Override
	public SysNotice readNotice(SysUser user, Integer id) {
		SysNotice sysNotice = null;
		if (user != null && id != null) {
			SysNoticeUser sysNoticeUser = sysNoticeUserMapper.selectById(id);
			if (sysNoticeUser != null) {
				sysNotice = sysNoticeMapper.selectById(sysNoticeUser.getNoticeid());
				sysNoticeUser.setIsread(CommonConsts.COMMON_1);
				sysNoticeUser.setReadtime(LocalDateTime.now());
				SysNoticeUser updateSysNoticeUser = new SysNoticeUser();
				updateSysNoticeUser.setId(id);
				updateSysNoticeUser.setUserid(user.getId());
				Wrapper<SysNoticeUser> updateWrapper = new QueryWrapper<>(updateSysNoticeUser);
				sysNoticeUserMapper.update(sysNoticeUser, updateWrapper);
			}
		}
		return sysNotice;
	}

}
