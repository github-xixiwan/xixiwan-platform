package com.xixiwan.platform.base;

import com.xixiwan.platform.sys.entity.SysMenu;
import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.form.SysMenuForm;
import com.xixiwan.platform.sys.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public abstract class BaseMvcController {

    @Resource
    protected HttpServletRequest request;

    @Resource
    protected HttpServletResponse response;

    @Resource
    protected ISysMenuService sysMenuService;

    @Resource
    protected ISysRoleService sysRoleService;

    @Resource
    protected ISysUserService sysUserService;

    @Resource
    protected ISysRoleMenuService sysRoleMenuService;

    @Resource
    protected ISysUserRoleService sysUserRoleService;

    @Resource
    protected ISysForgetService sysForgetService;

    @Resource
    protected ISysDictService sysDictService;

    @Resource
    protected ISysLogLoginService sysLogLoginService;

    @Resource
    protected ISysLogOperationService sysLogOperationService;

    @Resource
    protected ISysNoticeService sysNoticeService;

    @Resource
    protected ISysNoticeUserService sysNoticeUserService;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public User getPrincipal() {
        Object object = getAuthentication().getPrincipal();
        return object instanceof User ? (User) object : null;
    }

    public SysUser getSysUser() {
        User user = getPrincipal();
        return user != null ? sysUserService.selectUserByUsername(user.getUsername()) : null;
    }

    public SysMenu selectMenuMoreByCode(String code) {
        return sysMenuService.selectMenuMoreByCode(code);
    }

    public List<SysMenu> getMenuList(Integer userid, String pcode, Integer mtype, List<Integer> mtypeList) {
        SysMenuForm menuForm = new SysMenuForm();
        menuForm.setUserid(userid);
        menuForm.setPcode(pcode);
        menuForm.setMtype(mtype);
        menuForm.setMtypeList(mtypeList);
        return sysMenuService.selectUserMenuByForm(menuForm);
    }

}
