package com.entity.model;

import com.entity.TingchequyuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 停车区域
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-05-05
 */
public class TingchequyuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


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
	 * 获取：区域名称
	 */
    public String getTingchequyuName() {
        return tingchequyuName;
    }


    /**
	 * 设置：区域名称
	 */
    public void setTingchequyuName(String tingchequyuName) {
        this.tingchequyuName = tingchequyuName;
    }
    /**
	 * 获取：车位数量
	 */
    public Integer getTingchequyuNumber() {
        return tingchequyuNumber;
    }


    /**
	 * 设置：车位数量
	 */
    public void setTingchequyuNumber(Integer tingchequyuNumber) {
        this.tingchequyuNumber = tingchequyuNumber;
    }
    /**
	 * 获取：详情信息
	 */
    public String getTingchequyuContent() {
        return tingchequyuContent;
    }


    /**
	 * 设置：详情信息
	 */
    public void setTingchequyuContent(String tingchequyuContent) {
        this.tingchequyuContent = tingchequyuContent;
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
