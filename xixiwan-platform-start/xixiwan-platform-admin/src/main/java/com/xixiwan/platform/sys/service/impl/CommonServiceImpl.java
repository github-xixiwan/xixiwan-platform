package com.xixiwan.platform.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xixiwan.platform.sys.entity.SysDict;
import com.xixiwan.platform.sys.mapper.SysDictMapper;
import com.xixiwan.platform.sys.service.CommonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class CommonServiceImpl implements CommonService {

	@Resource
	private SysDictMapper sysDictMapper;

	@Override
	public SysDict selectDictByCode(String code, String pcode) {
		if (StringUtils.isBlank(code) || StringUtils.isBlank(pcode)) {
			return null;
		}
		SysDict sysDict = new SysDict();
		sysDict.setCode(code);
		sysDict.setPcode(pcode);
		Wrapper<SysDict> queryWrapper = new QueryWrapper<>(sysDict);
		return sysDictMapper.selectOne(queryWrapper);
	}

	@Override
	public String getDictNameByCode(String code, String pcode) {
		SysDict sysDict = selectDictByCode(code, pcode);
		return sysDict != null ? sysDict.getName() : null;
	}

}
