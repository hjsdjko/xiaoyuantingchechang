package com.entity.vo;

import com.entity.TingcheEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 停车信息
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email
 * @date 2021-05-05
 */
@TableName("tingche")
public class TingcheVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 停车教师
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 停车区域
     */

    @TableField(value = "tingchequyu_id")
    private Integer tingchequyuId;


    /**
     * 车牌号
     */

    @TableField(value = "tingche_paihao")
    private String tingchePaihao;


    /**
     * 车型
     */

    @TableField(value = "tingche_chexing")
    private String tingcheChexing;


    /**
     * 停车时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "tingche_time")
    private Date tingcheTime;


    /**
     * 离校时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "lixiao_time")
    private Date lixiaoTime;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
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
	 * 设置：停车教师
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：停车教师
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：停车区域
	 */
    public Integer getTingchequyuId() {
        return tingchequyuId;
    }


    /**
	 * 获取：停车区域
	 */

    public void setTingchequyuId(Integer tingchequyuId) {
        this.tingchequyuId = tingchequyuId;
    }
    /**
	 * 设置：车牌号
	 */
    public String getTingchePaihao() {
        return tingchePaihao;
    }


    /**
	 * 获取：车牌号
	 */

    public void setTingchePaihao(String tingchePaihao) {
        this.tingchePaihao = tingchePaihao;
    }
    /**
	 * 设置：车型
	 */
    public String getTingcheChexing() {
        return tingcheChexing;
    }


    /**
	 * 获取：车型
	 */

    public void setTingcheChexing(String tingcheChexing) {
        this.tingcheChexing = tingcheChexing;
    }
    /**
	 * 设置：停车时间
	 */
    public Date getTingcheTime() {
        return tingcheTime;
    }


    /**
	 * 获取：停车时间
	 */

    public void setTingcheTime(Date tingcheTime) {
        this.tingcheTime = tingcheTime;
    }
    /**
	 * 设置：离校时间
	 */
    public Date getLixiaoTime() {
        return lixiaoTime;
    }


    /**
	 * 获取：离校时间
	 */

    public void setLixiaoTime(Date lixiaoTime) {
        this.lixiaoTime = lixiaoTime;
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

}
