package com.xixiwan.platform.sys.dto;

import com.xixiwan.platform.sys.entity.SysMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Node extends SysMenu {

	private static final long serialVersionUID = 1L;

	/**
	 * 为给定的树节点显示的文本值，通常在节点图标的右侧。
	 */
	private String text;

	/**
	 * 在给定节点上显示的图标，通常在文本的左边。
	 */
	private String icon;

	/**
	 * 选中时在给定节点上显示的图标，通常位于文本的左侧。
	 */
	private String selectedIcon;

	/**
	 * 在给定节点上使用的前景色覆盖全局颜色选项。
	 */
	private String color;

	/**
	 * 在给定节点上使用的背景颜色覆盖全局颜色选项。
	 */
	private String backColor;

	/**
	 * 与全局enableLinks选项结合使用，在给定节点上指定锚标记URL。
	 */
	private String href;

	/**
	 * 树中的节点是否可选。False表示节点应该充当扩展标题，不会触发选择事件。
	 */
	private Boolean selectable = Boolean.TRUE;;

	/**
	 * 描述节点的初始状态。
	 */
	private NodeState state;

	/**
	 * 与global showTags选项一起使用，向每个节点的右侧添加额外信息;使用引导徽章
	 */
	private String[] tags;

	/**
	 * nodes
	 */
	private List<Node> nodes;

}
