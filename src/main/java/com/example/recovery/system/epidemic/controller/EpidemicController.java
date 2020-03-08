package com.example.recovery.system.epidemic.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.recovery.framework.entity.ResponseMap;
import com.example.recovery.framework.entity.StatusEnum;
import com.example.recovery.framework.util.CommUtil;
import com.example.recovery.framework.util.PageUtil;
import com.example.recovery.system.epidemic.entity.Epidemic;
import com.example.recovery.system.epidemic.service.IEpidemicService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author msi
 * @since 2020-02-28
 */
@RestController
@RequestMapping("/epidemic")
@CrossOrigin
public class EpidemicController {

    @Autowired
    IEpidemicService epidemicService;

    @PostMapping
    public Map postEpidemic(@RequestBody Epidemic epidemic) {
        if (CommUtil.isNullValue(epidemic.getAreaNumber(),epidemic.getHigherAreaNumber())) {
            return ResponseMap.factoryResult(StatusEnum.SYSTEM_ERROR_9002.getCode(),StatusEnum.SYSTEM_ERROR_9002.getData());
        }
        Map<String,String> queryMap = new HashMap<>(2);
        queryMap.put("date",epidemic.getDate());
        queryMap.put("province",epidemic.getProvince());
        List<Epidemic> epidemicByDate = epidemicService.findAll(queryMap);
        if (!epidemicByDate.isEmpty()) {
            return ResponseMap.factoryResult(StatusEnum.RET_INSERT_EXIST.getCode(),StatusEnum.RET_INSERT_EXIST.getData());
        }
        if (epidemic.getDate().compareTo(CommUtil.dateToString(new Date())) > 0) {
            return ResponseMap.factoryResult(StatusEnum.SYSTEM_ERROR_9007.getCode(),StatusEnum.SYSTEM_ERROR_9007.getData());
        }
        return epidemicService.add(epidemic);
    }

    @PutMapping()
    public Map updateEpidemic(@RequestBody Epidemic epidemic) {
        return epidemicService.update(epidemic);
    }

    @RequestMapping(value = "/{epidemicId}", method = RequestMethod.GET)
    public Map getEpidemicById(@PathVariable("epidemicId") Integer epidemicId) {
        return epidemicService.findEpidemic(epidemicId);
    }

    @PostMapping("/page")
    public Map query(@RequestBody Map<String,String> queryMap) {
        //获取分页信息，并从查询条件中去除
        Integer page = Integer.valueOf(queryMap.get("page"));
        Integer size = Integer.valueOf(queryMap.get("size"));
        queryMap.remove("page");
        queryMap.remove("size");

        Page<Epidemic> pageInfo = PageHelper.startPage(page,size);
        List<Epidemic> epidemics = epidemicService.findAll(queryMap);
        JSONObject result = PageUtil.pageBaseInfo(pageInfo);
        return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(), epidemics, result);
    }
}
