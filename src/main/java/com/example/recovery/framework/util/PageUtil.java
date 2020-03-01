package com.example.recovery.framework.util;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.recovery.system.epidemic.entity.Epidemic;
import com.github.pagehelper.Page;

import java.util.Map;

/**
 * @ClassName: PageUtil
 * @Description: 分页工具类
 * @Author: MSI
 * @Date: 2019/1/7 15:31
 * @Vresion: 1.0.0
 **/
public class PageUtil {

    /**
     * 构建查询条件
     * @param queryMap
     * @return
     */
    public static QueryWrapper getQueryWrapper(Map<String, String> queryMap) {
        //构建Mybatis 条件构造器
        QueryWrapper<Epidemic> queryWrapper = new QueryWrapper<>();
        //遍历查询条件Map， page和size 在controller 层已经去除，剩下的都是查询条件
        for(Map.Entry<String, String> entry : queryMap.entrySet()){
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();

            //如果key 后缀有Like 则表示该字段模糊查询
            if (mapKey.endsWith("_like")) {
                //写入之前先去除后缀的_like
                queryWrapper.like(mapKey.substring(0,mapKey.length()-5),mapValue);
            }else if ((mapValue == null) || (mapValue.equals(""))) {
                //如果不是模糊查询，且value 为null 或 "", 不添加查询条件
                continue;
            }else {
                //无Like 且value 不为null 或"" ,则表示该字段精确查询
                queryWrapper.eq(mapKey,mapValue);
            }
        }
        return queryWrapper;
    }


    public static JSONObject pageInfo(Page page) {
        JSONObject result = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        //封装分页信息

        jsonObject.put("total",page.getTotal()); //总条目数
        jsonObject.put("pageNum",page.getPageSize()); //本页条数
        jsonObject.put("currentPage",page.getPageNum()); //页码

        result.put("total",jsonObject); //写入分页信息
        result.put("data",page.getResult()); //写入内容
        return result;
    }

    public static JSONObject pageBaseInfo(Page page) {

        JSONObject info = new JSONObject();
        //封装分页信息

        info.put("total",page.getTotal()); //总条目数
        info.put("pageNum",page.getPageSize()); //本页条数
        info.put("currentPage",page.getPageNum()); //页码
        return info;
    }

}
