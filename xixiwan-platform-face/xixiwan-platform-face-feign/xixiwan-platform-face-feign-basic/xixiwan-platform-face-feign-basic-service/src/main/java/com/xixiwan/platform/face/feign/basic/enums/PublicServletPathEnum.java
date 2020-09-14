package com.xixiwan.platform.face.feign.basic.enums;

import com.google.common.collect.Lists;

import java.util.List;

public enum PublicServletPathEnum {

    ERROR("/error"), ACTUATOR("/actuator/**");

    private String servletPath;

    private PublicServletPathEnum(String servletPath) {
        this.servletPath = servletPath;
    }

    public String getServletPath() {
        return servletPath;
    }

    public static List<String> isList() {
        List<String> list = Lists.newArrayList();
        for (PublicServletPathEnum publicServletPathEnum : PublicServletPathEnum.values()) {
            list.add(publicServletPathEnum.getServletPath());
        }
        return list;
    }

    public static String[] isArray() {
        return isList().stream().toArray(String[]::new);
    }

}
