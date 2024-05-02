package com.service.impl;

import com.utils.StringUtil;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;

import com.dao.TingcheDao;
import com.entity.TingcheEntity;
import com.service.TingcheService;
import com.entity.view.TingcheView;

/**
 * 停车信息 服务实现类
 * @author 
 * @since 2021-05-05
 */
@Service("tingcheService")
@Transactional
public class TingcheServiceImpl extends ServiceImpl<TingcheDao, TingcheEntity> implements TingcheService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<TingcheView> page =new Query<TingcheView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
