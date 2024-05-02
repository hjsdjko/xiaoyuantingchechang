package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 车位信息
 *
 * @author 
 * @email
 * @date 2021-05-05
 */
@TableName("chewei")
public class CheweiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public CheweiEntity() {

	}

	public CheweiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 车位编号
     */
    @TableField(value = "chewei_bianhao")

    private String cheweiBianhao;


    /**
     * 车位名称
     */
    @TableField(value = "chewei_name")

    private String cheweiName;


    /**
     * 所属区域
     */
    @TableField(value = "tingchequyu_id")

    private Integer tingchequyuId;


    /**
     * 详情信息
     */
    @TableField(value = "chewei_content")

    private String cheweiContent;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：车位编号
	 */
    public String getCheweiBianhao() {
        return cheweiBianhao;
    }


    /**
	 * 获取：车位编号
	 */

    public void setCheweiBianhao(String cheweiBianhao) {
        this.cheweiBianhao = cheweiBianhao;
    }
    /**
	 * 设置：车位名称
	 */
    public String getCheweiName() {
        return cheweiName;
    }


    /**
	 * 获取：车位名称
	 */

    public void setCheweiName(String cheweiName) {
        this.cheweiName = cheweiName;
    }
    /**
	 * 设置：所属区域
	 */
    public Integer getTingchequyuId() {
        return tingchequyuId;
    }


    /**
	 * 获取：所属区域
	 */

    public void setTingchequyuId(Integer tingchequyuId) {
        this.tingchequyuId = tingchequyuId;
    }
    /**
	 * 设置：详情信息
	 */
    public String getCheweiContent() {
        return cheweiContent;
    }


    /**
	 * 获取：详情信息
	 */

    public void setCheweiContent(String cheweiContent) {
        this.cheweiContent = cheweiContent;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Chewei{" +
            "id=" + id +
            ", cheweiBianhao=" + cheweiBianhao +
            ", cheweiName=" + cheweiName +
            ", tingchequyuId=" + tingchequyuId +
            ", cheweiContent=" + cheweiContent +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
