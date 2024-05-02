package com.entity.view;

import com.entity.YuyueEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 车位预约
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email
 * @date 2021-05-05
 */
@TableName("yuyue")
public class YuyueView extends YuyueEntity implements Serializable {
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

		//级联表 yonghu
			/**
			* 教师姓名
			*/
			private String yonghuName;
			/**
			* 性别
			*/
			private Integer sexTypes;
				/**
				* 性别的值
				*/
				private String sexValue;
			/**
			* 身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 手机号
			*/
			private String yonghuPhone;
			/**
			* 邮箱
			*/
			private String yonghuEmail;
			/**
			* 专业班级
			*/
			private String yonghuBanjiZhuanye;
			/**
			* 照片
			*/
			private String yonghuPhoto;

	public YuyueView() {

	}

	public YuyueView(YuyueEntity yuyueEntity) {
		try {
			BeanUtils.copyProperties(this, yuyueEntity);
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


				//级联表的get和set yonghu
					/**
					* 获取： 教师姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 教师姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 性别
					*/
					public Integer getSexTypes() {
						return sexTypes;
					}
					/**
					* 设置： 性别
					*/
					public void setSexTypes(Integer sexTypes) {
						this.sexTypes = sexTypes;
					}


						/**
						* 获取： 性别的值
						*/
						public String getSexValue() {
							return sexValue;
						}
						/**
						* 设置： 性别的值
						*/
						public void setSexValue(String sexValue) {
							this.sexValue = sexValue;
						}
					/**
					* 获取： 身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}
					/**
					* 获取： 专业班级
					*/
					public String getYonghuBanjiZhuanye() {
						return yonghuBanjiZhuanye;
					}
					/**
					* 设置： 专业班级
					*/
					public void setYonghuBanjiZhuanye(String yonghuBanjiZhuanye) {
						this.yonghuBanjiZhuanye = yonghuBanjiZhuanye;
					}
					/**
					* 获取： 照片
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 照片
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}







}
