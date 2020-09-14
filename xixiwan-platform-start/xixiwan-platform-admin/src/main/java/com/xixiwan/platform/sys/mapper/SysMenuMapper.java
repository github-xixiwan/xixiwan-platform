package com.xixiwan.platform.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xixiwan.platform.sys.entity.SysMenu;
import com.xixiwan.platform.sys.form.SysMenuForm;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author Sente
 * @since 2018-09-13
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	List<SysMenu> selectUserMenuByForm(SysMenuForm menuForm);

}
