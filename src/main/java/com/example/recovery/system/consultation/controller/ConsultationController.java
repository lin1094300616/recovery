package com.example.recovery.system.consultation.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.recovery.framework.entity.ResponseMap;
import com.example.recovery.framework.entity.StatusEnum;
import com.example.recovery.framework.util.CommUtil;
import com.example.recovery.framework.util.PageUtil;
import com.example.recovery.system.consultation.entity.Consultation;
import com.example.recovery.system.consultation.service.IConsultationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author msi
 * @since 2020-03-06
 */
@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    @Autowired
    IConsultationService consultationService;

    @PostMapping("/check")
    public Map check(@RequestBody Consultation consultation){
        if (CommUtil.isNullValue(consultation.getPatientId())) {
            return ResponseMap.factoryResult(StatusEnum.SYSTEM_ERROR_9002.getCode(), StatusEnum.SYSTEM_ERROR_9002.getData());
        }
        return consultationService.check(consultation);
    }
    
    @PostMapping
    public Map postConsultation(@RequestBody Consultation consultation) {
        return consultationService.add(consultation);
    }

    @PutMapping()
    public Map updateConsultation(@RequestBody Consultation consultation) {
        return consultationService.update(consultation);
    }

    @DeleteMapping(value = "/{consultationId}")
    public Map delete(@PathVariable(value = "consultationId")Integer consultationId) {
        return consultationService.delete(consultationId);
    }

    @RequestMapping(value = "/{consultationId}", method = RequestMethod.GET)
    public Map getConsultationById(@PathVariable("consultationId") Integer consultationId) {
        return consultationService.findById(consultationId);
    }

    @GetMapping("/list")
    public Map findConsultationList() {
        return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(), consultationService.findList());
    }

    @PostMapping("/page")
    public Map query(@RequestBody Map<String,String> queryMap) {
        //获取分页信息，并从查询条件中去除
        Integer page = Integer.valueOf(queryMap.get("page"));
        Integer size = Integer.valueOf(queryMap.get("size"));
        queryMap.remove("page");
        queryMap.remove("size");

        Page<Consultation> pageInfo = PageHelper.startPage(page,size);
        List<Consultation> consultationList = consultationService.findAll(queryMap);
        JSONObject result = PageUtil.pageBaseInfo(pageInfo);
        return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(), consultationList, result);
    }
}
