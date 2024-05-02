package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
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

import com.entity.TingcheEntity;

import com.service.TingcheService;
import com.entity.view.TingcheView;
import com.service.TingchequyuService;
import com.entity.TingchequyuEntity;
import com.service.YonghuService;
import com.entity.YonghuEntity;

import com.utils.PageUtils;
import com.utils.R;

/**
 * 停车信息
 * 后端接口
 * @author
 * @email
 * @date 2021-05-05
*/
@RestController
@Controller
@RequestMapping("/tingche")
public class TingcheController {
    private static final Logger logger = LoggerFactory.getLogger(TingcheController.class);

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
        PageUtils page = tingcheService.queryPage(params);

        //字典表数据转换
        List<TingcheView> list =(List<TingcheView>)page.getList();
        for(TingcheView c:list){
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
        TingcheEntity tingche = tingcheService.selectById(id);
        if(tingche !=null){
            //entity转view
            TingcheView view = new TingcheView();
            BeanUtils.copyProperties( tingche , view );//把实体数据重构到view中

            //级联表
            TingchequyuEntity tingchequyu = tingchequyuService.selectById(tingche.getTingchequyuId());
            if(tingchequyu != null){
                BeanUtils.copyProperties( tingchequyu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setTingchequyuId(tingchequyu.getId());
            }
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(tingche.getYonghuId());
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
    public R save(@RequestBody TingcheEntity tingche, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,tingche:{}",this.getClass().getName(),tingche.toString());
        Wrapper<TingcheEntity> queryWrapper = new EntityWrapper<TingcheEntity>()
            .eq("yonghu_id", tingche.getYonghuId())
            .eq("tingchequyu_id", tingche.getTingchequyuId())
            .eq("tingche_paihao", tingche.getTingchePaihao())
            .eq("tingche_chexing", tingche.getTingcheChexing())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TingcheEntity tingcheEntity = tingcheService.selectOne(queryWrapper);
        if(tingcheEntity==null){
            tingche.setInsertTime(new Date());
            tingche.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      tingche.set
        //  }
            tingcheService.insert(tingche);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TingcheEntity tingche, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,tingche:{}",this.getClass().getName(),tingche.toString());
        //根据字段查询是否有相同数据
        Wrapper<TingcheEntity> queryWrapper = new EntityWrapper<TingcheEntity>()
            .notIn("id",tingche.getId())
            .andNew()
            .eq("yonghu_id", tingche.getYonghuId())
            .eq("tingchequyu_id", tingche.getTingchequyuId())
            .eq("tingche_paihao", tingche.getTingchePaihao())
            .eq("tingche_chexing", tingche.getTingcheChexing())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TingcheEntity tingcheEntity = tingcheService.selectOne(queryWrapper);
        if(tingcheEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      tingche.set
            //  }
            tingcheService.updateById(tingche);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<TingcheEntity> tingcheEntities = tingcheService.selectBatchIds(Arrays.asList(ids));
        if(tingcheEntities.size() <= 0){
            return R.error();
        }
        Boolean yanzheng = true;
        for (TingcheEntity t:tingcheEntities) {
            if(yanzheng){
                TingchequyuEntity tingchequyu = tingchequyuService.selectById(t.getTingchequyuId());
                if(tingchequyu == null){
                    return R.error();
                }
                if(t.getLixiaoTime() != null){
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
        tingcheService.deleteBatchIds(Arrays.asList(ids));
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
        PageUtils page = tingcheService.queryPage(params);

        //字典表数据转换
        List<TingcheView> list =(List<TingcheView>)page.getList();
        for(TingcheView c:list){
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
        TingcheEntity tingche = tingcheService.selectById(id);
            if(tingche !=null){
                //entity转view
        TingcheView view = new TingcheView();
                BeanUtils.copyProperties( tingche , view );//把实体数据重构到view中

                //级联表
                    TingchequyuEntity tingchequyu = tingchequyuService.selectById(tingche.getTingchequyuId());
                if(tingchequyu != null){
                    BeanUtils.copyProperties( tingchequyu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setTingchequyuId(tingchequyu.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(tingche.getYonghuId());
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
    public R add(@RequestBody TingcheEntity tingche, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,tingche:{}",this.getClass().getName(),tingche.toString());
        Wrapper<TingcheEntity> queryWrapper = new EntityWrapper<TingcheEntity>()
            .eq("yonghu_id", tingche.getYonghuId())
            .eq("tingchequyu_id", tingche.getTingchequyuId())
            .eq("tingche_paihao", tingche.getTingchePaihao())
            .eq("tingche_chexing", tingche.getTingcheChexing())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
    TingcheEntity tingcheEntity = tingcheService.selectOne(queryWrapper);
        if(tingcheEntity==null){
            tingche.setInsertTime(new Date());
            tingche.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      tingche.set
        //  }
        tingcheService.insert(tingche);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }





}

