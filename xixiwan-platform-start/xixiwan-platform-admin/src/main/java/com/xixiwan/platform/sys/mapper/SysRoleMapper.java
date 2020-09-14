package com.xixiwan.platform.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xixiwan.platform.sys.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author Sente
 * @since 2018-09-28
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

	List<SysRole> selectRoleByUserid(@Param("userid") Integer userid);

}
