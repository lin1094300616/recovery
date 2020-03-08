package com.example.recovery.system.patient.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.recovery.framework.entity.ResponseMap;
import com.example.recovery.framework.entity.StatusEnum;
import com.example.recovery.framework.util.NumberUtil;
import com.example.recovery.framework.util.PageUtil;
import com.example.recovery.system.patient.entity.Patient;
import com.example.recovery.system.patient.service.IPatientService;
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
 * @since 2020-03-07
 */
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    IPatientService patientService;

    @PostMapping
    public Map postPatient(@RequestBody Patient patient) {
        if (!NumberUtil.isPositiveDecimal(patient.getTemperature().toString())) {
            return ResponseMap.factoryResult(StatusEnum.SYSTEM_ERROR_9005.getCode(), StatusEnum.SYSTEM_ERROR_9005.getData());
        }
        return patientService.add(patient);
    }

    @PutMapping()
    public Map updatePatient(@RequestBody Patient patient) {
        return patientService.update(patient);
    }

    @DeleteMapping(value = "/{patientId}")
    public Map delete(@PathVariable(value = "patientId")Integer patientId) {
        return patientService.delete(patientId);
    }

    @RequestMapping(value = "/{patientId}", method = RequestMethod.GET)
    public Map getPatientById(@PathVariable("patientId") Integer patientId) {
        return patientService.findById(patientId);
    }

    @GetMapping("/list")
    public Map findPatientList() {
        return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(), patientService.findList());
    }

    @PostMapping("/page")
    public Map query(@RequestBody Map<String,String> queryMap) {
        //获取分页信息，并从查询条件中去除
        Integer page = Integer.valueOf(queryMap.get("page"));
        Integer size = Integer.valueOf(queryMap.get("size"));
        queryMap.remove("page");
        queryMap.remove("size");

        Page<Patient> pageInfo = PageHelper.startPage(page,size);
        List<Patient> patientList = patientService.findAll(queryMap);
        JSONObject result = PageUtil.pageBaseInfo(pageInfo);
        return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(), patientList, result);
    }
}
