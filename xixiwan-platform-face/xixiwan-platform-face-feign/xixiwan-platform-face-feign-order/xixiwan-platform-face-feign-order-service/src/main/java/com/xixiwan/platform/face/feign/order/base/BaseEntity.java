package com.xixiwan.platform.face.feign.order.base;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseEntity<T extends Model<T>> extends Model<T> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private Long id;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
