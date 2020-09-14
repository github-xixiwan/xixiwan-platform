package com.xixiwan.platform.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.module.web.constant.WebConsts;
import com.xixiwan.platform.constant.CommonConsts;
import com.xixiwan.platform.exception.WebException;
import com.xixiwan.platform.exception.enums.WebEnum;
import com.xixiwan.platform.sys.dto.Node;
import com.xixiwan.platform.sys.dto.NodeState;
import com.xixiwan.platform.sys.entity.SysMenu;
import com.xixiwan.platform.sys.entity.SysRoleMenu;
import com.xixiwan.platform.sys.form.SysMenuForm;
import com.xixiwan.platform.sys.mapper.SysMenuMapper;
import com.xixiwan.platform.sys.mapper.SysRoleMenuMapper;
import com.xixiwan.platform.sys.service.CommonService;
import com.xixiwan.platform.sys.service.ISysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author Sente
 * @since 2018-09-13
 */
@Service
@Transactional
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Resource
    private CommonService commonService;

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public IPage<SysMenu> selectPage(SysMenuForm menuForm) {
        menuForm.setSortNames(Lists.newArrayList("levels", "num"));
        menuForm.setSortOrders(WebConsts.SORTORDER_ASC);
        Page<SysMenu> page = commonService.getPage(menuForm);
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        String name = menuForm.getName();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        String pcode = menuForm.getPcode();
        if (StringUtils.isNotBlank(pcode)) {
            queryWrapper.and(q -> q.eq("code", pcode).or().eq("pcode", pcode));
        }
        IPage<SysMenu> iPage = sysMenuMapper.selectPage(page, queryWrapper);
        if (iPage != null && iPage.getTotal() > 0) {
            List<SysMenu> list = iPage.getRecords();
            for (SysMenu sysMenu : list) {
                sysMenu.setPname(getMenuNameByCode(sysMenu.getPcode()));
            }
        }
        return iPage;
    }

    @Override
    public SysMenu selectMenuByCode(String code) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setCode(code);
        Wrapper<SysMenu> queryWrapper = new QueryWrapper<>(sysMenu);
        return sysMenuMapper.selectOne(queryWrapper);
    }

    public String getMenuNameByCode(String code) {
        SysMenu sysMenu = selectMenuByCode(code);
        return sysMenu != null ? sysMenu.getName() : null;
    }

    @Override
    public SysMenu selectMenuMoreByCode(String code) {
        SysMenu sysMenu = selectMenuByCode(code);
        if (sysMenu != null) {
            sysMenu.setPname(getMenuNameByCode(sysMenu.getPcode()));
        }
        return sysMenu;
    }

    @Override
    public List<SysMenu> selectUserMenuByForm(SysMenuForm menuForm) {
        return sysMenuMapper.selectUserMenuByForm(menuForm);
    }

    public List<SysRoleMenu> selectRoleMenuList(SysRoleMenu roleMenu) {
        Wrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>(roleMenu);
        return sysRoleMenuMapper.selectList(queryWrapper);
    }

    public Boolean isExists(List<SysRoleMenu> roleMenuList, Integer menuid) {
        for (SysRoleMenu sysRoleMenu : roleMenuList) {
            if (menuid.equals(sysRoleMenu.getMenuid())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public List<Node> selectNodeList(SysMenuForm menuForm) {
        List<Node> rootList = Lists.newArrayList();
        List<SysRoleMenu> roleMenuList = Lists.newArrayList();
        Integer roleid = menuForm.getRoleid();
        if (roleid != null && roleid > 0) {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setRoleid(roleid);
            roleMenuList = selectRoleMenuList(roleMenu);
        }
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        Integer mtype = menuForm.getMtype();
        if (mtype != null) {
            queryWrapper.eq("mtype", mtype);
        }
        Integer status = menuForm.getStatus();
        if (status != null) {
            queryWrapper.eq("status", status);
        }
        List<Integer> mtypeList = menuForm.getMtypeList();
        if (mtypeList != null && !mtypeList.isEmpty()) {
            queryWrapper.in("mtype", mtypeList);
        }
        List<SysMenu> menuList = sysMenuMapper.selectList(queryWrapper);
        if (menuList != null && !menuList.isEmpty()) {
            for (SysMenu sysMenu : menuList) {
                // 父节点是top-level的，为根节点。
                if (sysMenu.getPcode().equals(CommonConsts.TOP_LEVEL)) {
                    Node node = new Node();
                    BeanUtils.copyProperties(sysMenu, node);
                    node.setText(sysMenu.getName());
                    node.setIcon("fa " + sysMenu.getIcon());
                    // 选中
                    if (roleMenuList != null && !roleMenuList.isEmpty()) {
                        NodeState state = new NodeState();
                        state.setChecked(isExists(roleMenuList, sysMenu.getId()));
                        node.setState(state);
                    }
                    rootList.add(node);
                }
            }
            // 排序
            rootList.sort(Comparator.comparing(Node::getNum));
            // 子菜单
            for (Node node : rootList) {
                // 获取根节点下的所有子节点 使用getChild方法
                List<Node> childList = getChild(node.getCode(), menuList, roleMenuList);
                if (childList != null && !childList.isEmpty()) {
                    // 给根节点设置子节点
                    node.setNodes(childList);
                }
            }
        }
        return rootList;
    }

    public List<Node> getChild(String code, List<SysMenu> menuList, List<SysRoleMenu> roleMenuList) {
        List<Node> childList = Lists.newArrayList();
        for (SysMenu sysMenu : menuList) {
            if (sysMenu.getPcode().equals(code)) {
                Node node = new Node();
                BeanUtils.copyProperties(sysMenu, node);
                node.setText(sysMenu.getName());
                node.setIcon("fa " + sysMenu.getIcon());
                // 选中
                if (roleMenuList != null && !roleMenuList.isEmpty()) {
                    NodeState state = new NodeState();
                    state.setChecked(isExists(roleMenuList, sysMenu.getId()));
                    node.setState(state);
                }
                childList.add(node);
            }
        }
        // 递归
        for (Node node : childList) {
            List<Node> nodeList = getChild(node.getCode(), menuList, roleMenuList);
            if (nodeList != null && !nodeList.isEmpty()) {
                node.setNodes(nodeList);
            }
        }
        // 排序
        childList.sort(Comparator.comparing(Node::getNum));
        // 如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.isEmpty()) {
            return Lists.newArrayList();
        }
        return childList;
    }

    @Override
    public RestResponse<String> addMenu(SysMenu menu) {
        if (menu == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        String code = menu.getCode();
        if (StringUtils.isBlank(code)) {
            throw new WebException(WebEnum.ERROR_0009);
        }
        if (selectMenuByCode(code) != null) {
            throw new WebException(WebEnum.ERROR_0010);
        }
        String pcode = menu.getPcode();
        if (StringUtils.isBlank(pcode)) {
            menu.setPcode(CommonConsts.TOP_LEVEL);
        }
        if (sysMenuMapper.insert(menu) > 0) {
            return RestResponse.success("保存成功");
        }
        return RestResponse.failure("保存失败");
    }

    @Override
    public RestResponse<String> editMenu(SysMenu menu) {
        if (menu == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        Integer id = menu.getId();
        if (id == null) {
            throw new WebException(WebEnum.ERROR_0011);
        }
        SysMenu sysMenu = sysMenuMapper.selectById(id);
        if (sysMenu == null) {
            throw new WebException(WebEnum.ERROR_0012);
        }
        if (sysMenuMapper.updateById(menu) > 0) {
            return RestResponse.success("修改成功");
        }
        return RestResponse.failure("修改失败");
    }

    @Override
    public RestResponse<String> deleteMenu(SysMenu menu) {
        if (menu == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        Integer id = menu.getId();
        if (id == null || id.equals(0)) {
            throw new WebException(WebEnum.ERROR_0011);
        }
        menu = sysMenuMapper.selectById(id);
        if (menu == null) {
            throw new WebException(WebEnum.ERROR_0012);
        }
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pcode", menu.getCode());
        List<SysMenu> list = sysMenuMapper.selectList(queryWrapper);
        if (list != null && !list.isEmpty()) {
            throw new WebException(WebEnum.ERROR_0013);
        }
        if (sysMenuMapper.deleteById(id) > 0) {
            // 删除角色对应的菜单
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuid(id);
            Wrapper<SysRoleMenu> roleMenuQueryWrapper = new QueryWrapper<>(sysRoleMenu);
            sysRoleMenuMapper.delete(roleMenuQueryWrapper);
            return RestResponse.success("删除成功");
        }
        return RestResponse.failure("删除失败");
    }

}
