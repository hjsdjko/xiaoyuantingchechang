package com.dao;

import com.entity.TingchequyuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TingchequyuView;

/**
 * 停车区域 Dao 接口
 *
 * @author 
 * @since 2021-05-05
 */
public interface TingchequyuDao extends BaseMapper<TingchequyuEntity> {

   List<TingchequyuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
