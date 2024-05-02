package com.entity.view;

import com.entity.CheweiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 车位信息
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email
 * @date 2021-05-05
 */
@TableName("chewei")
public class CheweiView extends CheweiEntity implements Serializable {
    private static final long serialVersionUID = 1L;



		//级联表 tingchequyu
			/**
			* 区域名称
			*/
			private String tingchequyuName;
			/**
			* 车位数量
			*/
			private Integer tingchequyuNumber;
			/**
			* 详情信息
			*/
			private String tingchequyuContent;
			@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
			@DateTimeFormat
			/**
			* 录入时间
			*/
			private Date insertTime;

	public CheweiView() {

	}

	public CheweiView(CheweiEntity cheweiEntity) {
		try {
			BeanUtils.copyProperties(this, cheweiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}















				//级联表的get和set tingchequyu
					/**
					* 获取： 区域名称
					*/
					public String getTingchequyuName() {
						return tingchequyuName;
					}
					/**
					* 设置： 区域名称
					*/
					public void setTingchequyuName(String tingchequyuName) {
						this.tingchequyuName = tingchequyuName;
					}
					/**
					* 获取： 车位数量
					*/
					public Integer getTingchequyuNumber() {
						return tingchequyuNumber;
					}
					/**
					* 设置： 车位数量
					*/
					public void setTingchequyuNumber(Integer tingchequyuNumber) {
						this.tingchequyuNumber = tingchequyuNumber;
					}
					/**
					* 获取： 详情信息
					*/
					public String getTingchequyuContent() {
						return tingchequyuContent;
					}
					/**
					* 设置： 详情信息
					*/
					public void setTingchequyuContent(String tingchequyuContent) {
						this.tingchequyuContent = tingchequyuContent;
					}
					/**
					* 获取： 录入时间
					*/
					public Date getInsertTime() {
						return insertTime;
					}
					/**
					* 设置： 录入时间
					*/
					public void setInsertTime(Date insertTime) {
						this.insertTime = insertTime;
					}






}
