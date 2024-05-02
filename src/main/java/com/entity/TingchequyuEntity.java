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
 * 停车区域
 *
 * @author 
 * @email
 * @date 2021-05-05
 */
@TableName("tingchequyu")
public class TingchequyuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public TingchequyuEntity() {

	}

	public TingchequyuEntity(T t) {
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
     * 区域名称
     */
    @TableField(value = "tingchequyu_name")

    private String tingchequyuName;


    /**
     * 车位数量
     */
    @TableField(value = "tingchequyu_number")

    private Integer tingchequyuNumber;


    /**
     * 详情信息
     */
    @TableField(value = "tingchequyu_content")

    private String tingchequyuContent;


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
	 * 设置：区域名称
	 */
    public String getTingchequyuName() {
        return tingchequyuName;
    }


    /**
	 * 获取：区域名称
	 */

    public void setTingchequyuName(String tingchequyuName) {
        this.tingchequyuName = tingchequyuName;
    }
    /**
	 * 设置：车位数量
	 */
    public Integer getTingchequyuNumber() {
        return tingchequyuNumber;
    }


    /**
	 * 获取：车位数量
	 */

    public void setTingchequyuNumber(Integer tingchequyuNumber) {
        this.tingchequyuNumber = tingchequyuNumber;
    }
    /**
	 * 设置：详情信息
	 */
    public String getTingchequyuContent() {
        return tingchequyuContent;
    }


    /**
	 * 获取：详情信息
	 */

    public void setTingchequyuContent(String tingchequyuContent) {
        this.tingchequyuContent = tingchequyuContent;
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
        return "Tingchequyu{" +
            "id=" + id +
            ", tingchequyuName=" + tingchequyuName +
            ", tingchequyuNumber=" + tingchequyuNumber +
            ", tingchequyuContent=" + tingchequyuContent +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
