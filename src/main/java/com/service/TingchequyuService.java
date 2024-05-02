package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.TingchequyuEntity;
import java.util.Map;

/**
 * 停车区域 服务类
 * @author 
 * @since 2021-05-05
 */
public interface TingchequyuService extends IService<TingchequyuEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}