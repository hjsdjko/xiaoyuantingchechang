package com.entity.model;

import com.entity.TingcheEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 停车信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-05-05
 */
public class TingcheModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 停车教师
     */
    private Integer yonghuId;


    /**
     * 停车区域
     */
    private Integer tingchequyuId;


    /**
     * 车牌号
     */
    private String tingchePhone;


    /**
     * 车型
     */
    private String tingchePhoto;


    /**
     * 停车时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date tingcheTime;


    /**
     * 离校时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date lixiaoTime;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：停车教师
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：停车教师
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：停车区域
	 */
    public Integer getTingchequyuId() {
        return tingchequyuId;
    }


    /**
	 * 设置：停车区域
	 */
    public void setTingchequyuId(Integer tingchequyuId) {
        this.tingchequyuId = tingchequyuId;
    }
    /**
	 * 获取：车牌号
	 */
    public String getTingchePhone() {
        return tingchePhone;
    }


    /**
	 * 设置：车牌号
	 */
    public void setTingchePhone(String tingchePhone) {
        this.tingchePhone = tingchePhone;
    }
    /**
	 * 获取：车型
	 */
    public String getTingchePhoto() {
        return tingchePhoto;
    }


    /**
	 * 设置：车型
	 */
    public void setTingchePhoto(String tingchePhoto) {
        this.tingchePhoto = tingchePhoto;
    }
    /**
	 * 获取：停车时间
	 */
    public Date getTingcheTime() {
        return tingcheTime;
    }


    /**
	 * 设置：停车时间
	 */
    public void setTingcheTime(Date tingcheTime) {
        this.tingcheTime = tingcheTime;
    }
    /**
	 * 获取：离校时间
	 */
    public Date getLixiaoTime() {
        return lixiaoTime;
    }


    /**
	 * 设置：离校时间
	 */
    public void setLixiaoTime(Date lixiaoTime) {
        this.lixiaoTime = lixiaoTime;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：录入时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
