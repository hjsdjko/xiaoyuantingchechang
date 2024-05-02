package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

import com.entity.TingcheEntity;
import com.service.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;

import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.YuyueEntity;

import com.entity.view.YuyueView;
import com.entity.TingchequyuEntity;
import com.entity.YonghuEntity;

import com.utils.PageUtils;
import com.utils.R;

/**
 * 车位预约
 * 后端接口
 * @author
 * @email
 * @date 2021-05-05
*/
@RestController
@Controller
@RequestMapping("/yuyue")
public class YuyueController {
    private static final Logger logger = LoggerFactory.getLogger(YuyueController.class);

    @Autowired
    private YuyueService yuyueService;

    @Autowired
    private TingcheService tingcheService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;


    //级联表service
    @Autowired
    private TingchequyuService tingchequyuService;
    @Autowired
    private YonghuService yonghuService;


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
        PageUtils page = yuyueService.queryPage(params);

        //字典表数据转换
        List<YuyueView> list =(List<YuyueView>)page.getList();
        for(YuyueView c:list){
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
        YuyueEntity yuyue = yuyueService.selectById(id);
        if(yuyue !=null){
            //entity转view
            YuyueView view = new YuyueView();
            BeanUtils.copyProperties( yuyue , view );//把实体数据重构到view中

            //级联表
            TingchequyuEntity tingchequyu = tingchequyuService.selectById(yuyue.getTingchequyuId());
            if(tingchequyu != null){
                BeanUtils.copyProperties( tingchequyu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setTingchequyuId(tingchequyu.getId());
            }
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(yuyue.getYonghuId());
            if(yonghu != null){
                BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setYonghuId(yonghu.getId());
            }
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
    public R save(@RequestBody YuyueEntity yuyue, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yuyue:{}",this.getClass().getName(),yuyue.toString());
        Wrapper<YuyueEntity> queryWrapper = new EntityWrapper<YuyueEntity>()
            .eq("yonghu_id", yuyue.getYonghuId())
            .eq("tingchequyu_id", yuyue.getTingchequyuId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YuyueEntity yuyueEntity = yuyueService.selectOne(queryWrapper);
        if(yuyueEntity==null){
            yuyue.setInsertTime(new Date());
            yuyue.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      yuyue.set
        //  }
            yuyueService.insert(yuyue);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YuyueEntity yuyue, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,yuyue:{}",this.getClass().getName(),yuyue.toString());
        //根据字段查询是否有相同数据
        Wrapper<YuyueEntity> queryWrapper = new EntityWrapper<YuyueEntity>()
            .notIn("id",yuyue.getId())
            .andNew()
            .eq("yonghu_id", yuyue.getYonghuId())
            .eq("tingchequyu_id", yuyue.getTingchequyuId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YuyueEntity yuyueEntity = yuyueService.selectOne(queryWrapper);
        if(yuyueEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      yuyue.set
            //  }
            yuyueService.updateById(yuyue);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/ruxiao")
    public R ruxiao(Integer ids){
        YuyueEntity yuyueEntity = yuyueService.selectById(ids);
        if(yuyueEntity == null){
            return R.error();
        }
        yuyueEntity.setYuyueTypes(1);
        boolean b = yuyueService.updateById(yuyueEntity);
        if(b){
            TingcheEntity tingche = new TingcheEntity();
            tingche.setCreateTime(new Date());
            tingche.setInsertTime(new Date());
            tingche.setTingchequyuId(yuyueEntity.getTingchequyuId());
            tingche.setYonghuId(yuyueEntity.getYonghuId());
            tingche.setTingcheTime(new Date());
            boolean insert = tingcheService.insert(tingche);
            if(insert){
                return R.ok();
            }else{
                return R.error();
            }
        }
        return R.error();
    }


    /**
    * 删除
    */
    @RequestMapping("/quxiao")
    public R quxiao(Integer ids){
        YuyueEntity yuyueEntity = yuyueService.selectById(ids);
        if(yuyueEntity == null){
            return R.error();
        }
        yuyueEntity.setYuyueTypes(2);
        boolean b = yuyueService.updateById(yuyueEntity);
        if(b){
            TingchequyuEntity tingchequyuEntity = tingchequyuService.selectById(yuyueEntity.getTingchequyuId());
            tingchequyuEntity.setTingchequyuNumber(tingchequyuEntity.getTingchequyuNumber()+1);
            boolean b1 = tingchequyuService.updateById(tingchequyuEntity);
            if(b1){
                return R.ok();
            }else{
                return R.error();
            }
        }
        return R.error();
    }
    /**
    * 离校
    */
    @RequestMapping("/lixiao")
    public R lixiao(Integer ids){
        TingcheEntity tingche = tingcheService.selectById(ids);
        if(tingche == null){
           return R.error();
        }
        if(StringUtils.isBlank(tingche.getTingchePaihao()) || StringUtils.isBlank(tingche.getTingcheChexing())){
            return R.error("数据不全面，不能离校");
        }
        tingche.setLixiaoTime(new Date());
        boolean b = tingcheService.updateById(tingche);
        if(b){
            TingchequyuEntity tingchequyu = tingchequyuService.selectById(tingche.getTingchequyuId());
            if(tingchequyu == null){
                return R.error();
            }
            tingchequyu.setTingchequyuNumber(tingchequyu.getTingchequyuNumber()+1);
            boolean b1 = tingchequyuService.updateById(tingchequyu);
            if(b1){
                return R.ok();
            }else{
                return R.error();
            }
        }

        return R.error();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<YuyueEntity> yuyueEntities = yuyueService.selectBatchIds(Arrays.asList(ids));
        if(yuyueEntities.size() <= 0){
            return R.error();
        }
        Boolean yanzheng = true;
        for (YuyueEntity t:yuyueEntities) {
            if(yanzheng){
                TingchequyuEntity tingchequyu = tingchequyuService.selectById(t.getTingchequyuId());
                if(tingchequyu == null){
                    return R.error();
                }
                if(t.getYuyueTypes() == 3){
                    tingchequyu.setTingchequyuNumber(tingchequyu.getTingchequyuNumber()+1);
                    boolean b = tingchequyuService.updateById(tingchequyu);
                    yanzheng = b;
                }
                if(!yanzheng){
                    return R.error();
                }
            }else{
                return R.error();
            }
        }
        yuyueService.deleteBatchIds(Arrays.asList(ids));
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
        PageUtils page = yuyueService.queryPage(params);

        //字典表数据转换
        List<YuyueView> list =(List<YuyueView>)page.getList();
        for(YuyueView c:list){
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
        YuyueEntity yuyue = yuyueService.selectById(id);
            if(yuyue !=null){
                //entity转view
        YuyueView view = new YuyueView();
                BeanUtils.copyProperties( yuyue , view );//把实体数据重构到view中

                //级联表
                    TingchequyuEntity tingchequyu = tingchequyuService.selectById(yuyue.getTingchequyuId());
                if(tingchequyu != null){
                    BeanUtils.copyProperties( tingchequyu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setTingchequyuId(tingchequyu.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(yuyue.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody YuyueEntity yuyue, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yuyue:{}",this.getClass().getName(),yuyue.toString());
        Wrapper<YuyueEntity> queryWrapper = new EntityWrapper<YuyueEntity>()
            .eq("yonghu_id", yuyue.getYonghuId())
            .eq("tingchequyu_id", yuyue.getTingchequyuId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
    YuyueEntity yuyueEntity = yuyueService.selectOne(queryWrapper);
        if(yuyueEntity==null){
            yuyue.setInsertTime(new Date());
            yuyue.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      yuyue.set
        //  }
        yuyueService.insert(yuyue);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }





}

