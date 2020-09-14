package com.xixiwan.platform.enums;

import org.apache.commons.lang3.StringUtils;

public enum RangesTypeEnum {
	CREATETIME("1", "createtime"), MODIFYTIME("2", "modifytime");

	private String index;
	private String name;

	private RangesTypeEnum(String index, String name) {
		this.index = index;
		this.name = name;
	}

	public String getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public static String getNameByIndex(String index) {
		if (StringUtils.isBlank(index)) {
			return "";
		}
		for (RangesTypeEnum enums : RangesTypeEnum.values()) {
			if (index.equals(enums.getIndex())) {
				return enums.getName();
			}
		}
		return "";
	}

}
