package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.TingcheEntity;
import java.util.Map;

/**
 * 停车信息 服务类
 * @author 
 * @since 2021-05-05
 */
public interface TingcheService extends IService<TingcheEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}