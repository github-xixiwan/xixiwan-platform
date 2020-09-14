package com.xixiwan.platform.sys.dto;

import lombok.Data;

@Data
public class NodeState {

	/**
	 * 是否选中节点，用复选框样式的符号表示。
	 */
	private Boolean checked = Boolean.FALSE;

	/**
	 * 是否禁用节点(不可选择、可扩展或可检查)。
	 */
	private Boolean disabled = Boolean.FALSE;

	/**
	 * 一个节点是否扩展，即打开。优先于全局选项级别。
	 */
	private Boolean expanded = Boolean.FALSE;

	/**
	 * 是否选择节点。
	 */
	private Boolean selected = Boolean.FALSE;

}
