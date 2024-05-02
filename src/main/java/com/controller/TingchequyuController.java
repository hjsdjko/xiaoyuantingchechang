package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

import com.entity.YuyueEntity;
import com.service.YuyueService;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.TingchequyuEntity;

import com.service.TingchequyuService;
import com.entity.view.TingchequyuView;

import com.utils.PageUtils;
import com.utils.R;

/**
 * 停车区域
 * 后端接口
 * @author
 * @email
 * @date 2021-05-05
*/
@RestController
@Controller
@RequestMapping("/tingchequyu")
public class TingchequyuController {
    private static final Logger logger = LoggerFactory.getLogger(TingchequyuController.class);

    @Autowired
    private TingchequyuService tingchequyuService;


    @Autowired
    private YuyueService yuyueService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
     
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isNotEmpty(role) && "教师".equals(role)){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
        params.put("orderBy","id");
        PageUtils page = tingchequyuService.queryPage(params);

        //字典表数据转换
        List<TingchequyuView> list =(List<TingchequyuView>)page.getList();
        for(TingchequyuView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TingchequyuEntity tingchequyu = tingchequyuService.selectById(id);
        if(tingchequyu !=null){
            //entity转view
            TingchequyuView view = new TingchequyuView();
            BeanUtils.copyProperties( tingchequyu , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody TingchequyuEntity tingchequyu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,tingchequyu:{}",this.getClass().getName(),tingchequyu.toString());
        Wrapper<TingchequyuEntity> queryWrapper = new EntityWrapper<TingchequyuEntity>()
            .eq("tingchequyu_name", tingchequyu.getTingchequyuName())
            .eq("tingchequyu_number", tingchequyu.getTingchequyuNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TingchequyuEntity tingchequyuEntity = tingchequyuService.selectOne(queryWrapper);
        if(tingchequyuEntity==null){
            tingchequyu.setInsertTime(new Date());
            tingchequyu.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      tingchequyu.set
        //  }
            tingchequyuService.insert(tingchequyu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TingchequyuEntity tingchequyu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,tingchequyu:{}",this.getClass().getName(),tingchequyu.toString());
        //根据字段查询是否有相同数据
        Wrapper<TingchequyuEntity> queryWrapper = new EntityWrapper<TingchequyuEntity>()
            .notIn("id",tingchequyu.getId())
            .andNew()
            .eq("tingchequyu_name", tingchequyu.getTingchequyuName())
            .eq("tingchequyu_number", tingchequyu.getTingchequyuNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TingchequyuEntity tingchequyuEntity = tingchequyuService.selectOne(queryWrapper);
        if(tingchequyuEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      tingchequyu.set
            //  }
            tingchequyuService.updateById(tingchequyu);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/yuyue")
    public R yuyue(Integer ids, HttpServletRequest request){
        TingchequyuEntity tingchequyu = tingchequyuService.selectById(ids);
        if(tingchequyu == null){
            return R.error();
        }
        tingchequyu.setTingchequyuNumber(tingchequyu.getTingchequyuNumber()-1);
        YuyueEntity yuyue = new YuyueEntity();
        yuyue.setCreateTime(new Date());
        yuyue.setInsertTime(new Date());
        yuyue.setTingchequyuId(ids);
        yuyue.setYuyueTypes(3);//未
        yuyue.setYonghuId((Integer) request.getSession().getAttribute("userId"));
        Wrapper<YuyueEntity> queryWrapper = new EntityWrapper<YuyueEntity>()
                .eq("yonghu_id", yuyue.getYonghuId())
                .eq("tingchequyu_id", yuyue.getTingchequyuId())
                .eq("yuyue_types", yuyue.getYuyueTypes())
                ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YuyueEntity yuyueEntity = yuyueService.selectOne(queryWrapper);
        if(yuyueEntity != null){
            return R.error("你已经预约过这个区域的车位了");
        }
        boolean insert = yuyueService.insert(yuyue);
        if(insert){
            tingchequyuService.updateById(tingchequyu);
            return R.ok();
        }
        return R.error();
    }


    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        tingchequyuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }



    /**
    * 前端列表
    */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isNotEmpty(role) && "教师".equals(role)){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = tingchequyuService.queryPage(params);

        //字典表数据转换
        List<TingchequyuView> list =(List<TingchequyuView>)page.getList();
        for(TingchequyuView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TingchequyuEntity tingchequyu = tingchequyuService.selectById(id);
            if(tingchequyu !=null){
                //entity转view
        TingchequyuView view = new TingchequyuView();
                BeanUtils.copyProperties( tingchequyu , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody TingchequyuEntity tingchequyu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,tingchequyu:{}",this.getClass().getName(),tingchequyu.toString());
        Wrapper<TingchequyuEntity> queryWrapper = new EntityWrapper<TingchequyuEntity>()
            .eq("tingchequyu_name", tingchequyu.getTingchequyuName())
            .eq("tingchequyu_number", tingchequyu.getTingchequyuNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
    TingchequyuEntity tingchequyuEntity = tingchequyuService.selectOne(queryWrapper);
        if(tingchequyuEntity==null){
            tingchequyu.setInsertTime(new Date());
            tingchequyu.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      tingchequyu.set
        //  }
        tingchequyuService.insert(tingchequyu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }





}

