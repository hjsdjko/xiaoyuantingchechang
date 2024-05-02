package com.entity.view;

import com.entity.TingchequyuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 停车区域
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email
 * @date 2021-05-05
 */
@TableName("tingchequyu")
public class TingchequyuView extends TingchequyuEntity implements Serializable {
    private static final long serialVersionUID = 1L;



	public TingchequyuView() {

	}

	public TingchequyuView(TingchequyuEntity tingchequyuEntity) {
		try {
			BeanUtils.copyProperties(this, tingchequyuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}













}
