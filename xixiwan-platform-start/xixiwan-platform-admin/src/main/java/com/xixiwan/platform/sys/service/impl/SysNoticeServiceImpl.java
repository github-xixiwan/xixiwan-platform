package com.xixiwan.platform.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.xixiwan.platform.module.common.rest.RestResponse;
import com.xixiwan.platform.module.web.constant.WebConsts;
import com.xixiwan.platform.module.web.form.BaseForm;
import com.xixiwan.platform.constant.CommonConsts;
import com.xixiwan.platform.enums.RangesTypeEnum;
import com.xixiwan.platform.exception.WebException;
import com.xixiwan.platform.exception.enums.WebEnum;
import com.xixiwan.platform.sys.entity.SysDict;
import com.xixiwan.platform.sys.entity.SysNotice;
import com.xixiwan.platform.sys.entity.SysNoticeUser;
import com.xixiwan.platform.sys.entity.SysUser;
import com.xixiwan.platform.sys.form.SysNoticeForm;
import com.xixiwan.platform.sys.mapper.SysNoticeMapper;
import com.xixiwan.platform.sys.mapper.SysNoticeUserMapper;
import com.xixiwan.platform.sys.mapper.SysUserMapper;
import com.xixiwan.platform.sys.service.CommonService;
import com.xixiwan.platform.sys.service.ISysNoticeService;
import com.xixiwan.platform.sys.service.ISysNoticeUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 通知表 服务实现类
 * </p>
 *
 * @author Sente
 * @since 2018-11-14
 */
@Service
@Transactional
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements ISysNoticeService {

    @Resource
    private CommonService baseService;

    @Resource
    private SysNoticeMapper sysNoticeMapper;

    @Resource
    private SysNoticeUserMapper sysNoticeUserMapper;

    @Resource
    private ISysNoticeUserService sysNoticeUserService;

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public IPage<SysNotice> selectPage(SysNoticeForm noticeForm) {
        noticeForm.setSortNames(Lists.newArrayList("modifytime"));
        noticeForm.setSortOrders(WebConsts.SORTORDER_DESC);
        Page<SysNotice> page = baseService.getPage(noticeForm);
        BaseForm form = baseService.getBaseForm(noticeForm);
        QueryWrapper<SysNotice> queryWrapper = new QueryWrapper<>();
        String rangesValue = RangesTypeEnum.getNameByIndex(form.getRangesType());
        if (StringUtils.isNotBlank(rangesValue)) {
            queryWrapper.between(rangesValue, form.getStartDateTime(), form.getEndDateTime());
        }
        String type = noticeForm.getType();
        if (StringUtils.isNotBlank(type)) {
            queryWrapper.eq("type", type);
        }
        String objecttype = noticeForm.getObjecttype();
        if (StringUtils.isNotBlank(objecttype)) {
            queryWrapper.eq("objecttype", objecttype);
        }
        Integer status = noticeForm.getStatus();
        if (status != null && !status.equals(0)) {
            queryWrapper.eq("status", status);
        }
        String title = noticeForm.getTitle();
        if (StringUtils.isNotBlank(title)) {
            queryWrapper.like("title", title);
        }
        String content = noticeForm.getContent();
        if (StringUtils.isNotBlank(content)) {
            queryWrapper.like("content", content);
        }
        IPage<SysNotice> iPage = sysNoticeMapper.selectPage(page, queryWrapper);
        if (iPage != null && iPage.getTotal() > 0) {
            List<SysNotice> list = iPage.getRecords();
            for (SysNotice sysNotice : list) {
                sysNotice.setTypename(
                        baseService.getDictNameByCode(sysNotice.getType(), CommonConsts.DICT_NOTICE_TYPE));
            }
        }
        return iPage;
    }

    @Override
    public RestResponse<String> addNotice(SysNotice notice) {
        if (notice == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        if (sysNoticeMapper.insert(notice) > 0) {
            return RestResponse.success("保存成功");
        }
        return RestResponse.failure("保存失败");
    }

    @Override
    public RestResponse<String> editNotice(SysNotice notice) {
        if (notice == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        Integer id = notice.getId();
        if (id == null) {
            throw new WebException(WebEnum.ERROR_0011);
        }
        SysNotice sysNotice = sysNoticeMapper.selectById(id);
        if (sysNotice == null) {
            throw new WebException(WebEnum.ERROR_0012);
        }
        if (!CommonConsts.NOTICE_STATUS_1.equals(sysNotice.getStatus())) {
            throw new WebException(WebEnum.ERROR_0039);
        }
        if (sysNoticeMapper.updateById(notice) > 0) {
            return RestResponse.success("修改成功");
        }
        return RestResponse.failure("修改失败");
    }

    @Override
    public RestResponse<String> deleteNotice(SysNoticeForm noticeForm) {
        if (noticeForm == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        Integer[] ids = noticeForm.getIds();
        if (ids == null || ids.length == 0) {
            throw new WebException(WebEnum.ERROR_0011);
        }
        int num = 0;
        for (Integer id : ids) {
            SysNotice sysNotice = sysNoticeMapper.selectById(id);
            if (sysNotice == null || !CommonConsts.NOTICE_STATUS_1.equals(sysNotice.getStatus())) {
                continue;
            }
            if (sysNoticeMapper.deleteById(id) > 0) {
                num++;
            }
        }
        if (num > 0) {
            return RestResponse.success("删除成功");
        }
        return RestResponse.failure("删除失败");
    }

    @Override
    public RestResponse<String> repealNotice(SysNoticeForm noticeForm) {
        if (noticeForm == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        Integer[] ids = noticeForm.getIds();
        if (ids == null || ids.length == 0) {
            throw new WebException(WebEnum.ERROR_0011);
        }
        int num = 0;
        for (Integer id : ids) {
            SysNotice sysNotice = sysNoticeMapper.selectById(id);
            if (sysNotice == null || !CommonConsts.NOTICE_STATUS_2.equals(sysNotice.getStatus())) {
                continue;
            }
            sysNotice.setStatus(CommonConsts.NOTICE_STATUS_3);
            if (sysNoticeMapper.updateById(sysNotice) > 0) {
                // 删除通知对应的用户
                SysNoticeUser sysNoticeUser = new SysNoticeUser();
                sysNoticeUser.setNoticeid(id);
                Wrapper<SysNoticeUser> sysNoticeUserQueryWrapper = new QueryWrapper<>(sysNoticeUser);
                sysNoticeUserMapper.delete(sysNoticeUserQueryWrapper);
                num++;
            }
        }
        if (num > 0) {
            return RestResponse.success("撤销成功");
        }
        return RestResponse.failure("撤销失败");
    }

    @Override
    public RestResponse<String> releaseNotice(SysNoticeForm noticeForm) {
        if (noticeForm == null) {
            throw new WebException(WebEnum.ERROR_0002);
        }
        Integer id = noticeForm.getId();
        if (id == null || id.equals(0)) {
            throw new WebException(WebEnum.ERROR_0011);
        }
        SysNotice sysNotice = sysNoticeMapper.selectById(id);
        if (sysNotice == null) {
            throw new WebException(WebEnum.ERROR_0012);
        }
        if (!CommonConsts.NOTICE_STATUS_1.equals(sysNotice.getStatus())) {
            throw new WebException(WebEnum.ERROR_0040);
        }
        List<SysNoticeUser> sysNoticeUserList = Lists.newArrayList();
        if (CommonConsts.NOTICE_OBJECTTYPE_3.equals(sysNotice.getObjecttype())) {
            List<SysUser> sysUserList = sysUserMapper.selectList(null);
            if (sysUserList == null || sysUserList.isEmpty()) {
                throw new WebException(WebEnum.ERROR_0041);
            }
            for (SysUser sysUser : sysUserList) {
                SysNoticeUser sysNoticeUser = new SysNoticeUser();
                sysNoticeUser.setNoticeid(id);
                sysNoticeUser.setUserid(sysUser.getId());
                sysNoticeUserList.add(sysNoticeUser);
            }
        } else {
            Integer[] userids = noticeForm.getUserids();
            if (userids == null || userids.length == 0) {
                throw new WebException(WebEnum.ERROR_0021);
            }
            for (Integer userid : userids) {
                SysNoticeUser sysNoticeUser = new SysNoticeUser();
                sysNoticeUser.setNoticeid(id);
                sysNoticeUser.setUserid(userid);
                sysNoticeUserList.add(sysNoticeUser);
            }
        }
        if (!sysNoticeUserList.isEmpty() && sysNoticeUserService.saveBatch(sysNoticeUserList)) {
            sysNotice.setStatus(CommonConsts.NOTICE_STATUS_2);
            sysNoticeMapper.updateById(sysNotice);
            return RestResponse.success("发布成功");
        }
        return RestResponse.failure("发布失败");
    }

    @Override
    public List<SysNotice> selectUserNoticeByForm(SysUser user, SysNoticeForm noticeForm) {
        List<SysNotice> list = Lists.newArrayList();
        if (user != null && noticeForm != null) {
            noticeForm.setUserid(user.getId());
            list = sysNoticeMapper.selectUserNoticeByForm(noticeForm);
            if (list != null && !list.isEmpty()) {
                for (SysNotice sysNotice : list) {
                    SysDict sysDict = baseService.selectDictByCode(sysNotice.getType(),
                            CommonConsts.DICT_NOTICE_TYPE);
                    sysNotice.setTypename(sysDict != null ? sysDict.getName() : null);
                    sysNotice.setIcon(sysDict != null ? sysDict.getIcon() : null);
                }
            }
        }
        return list;
    }

}
