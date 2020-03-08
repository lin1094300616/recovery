package com.example.recovery.system.patient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.recovery.framework.entity.ResponseMap;
import com.example.recovery.framework.entity.StatusEnum;
import com.example.recovery.framework.util.PageUtil;
import com.example.recovery.system.consultation.entity.Consultation;
import com.example.recovery.system.consultation.mapper.ConsultationMapper;
import com.example.recovery.system.consultation.service.IConsultationService;
import com.example.recovery.system.patient.entity.Patient;
import com.example.recovery.system.patient.mapper.PatientMapper;
import com.example.recovery.system.patient.service.IPatientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.recovery.system.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author msi
 * @since 2020-03-07
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements IPatientService {

    @Resource
    PatientMapper patientMapper;

    @Autowired
    IConsultationService consultationService;

    @Override
    public Map add(Patient patient) {
        if (patientMapper.add(patient) == 1) {
            return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),patient);
        }
        return  ResponseMap.factoryResult(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
    }

    /**
     * 删除病人后查询出该所有就诊情况，循环删除所有就诊情况
     * @param patientId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map delete(Integer patientId) {
        if (patientMapper.findPatient(patientId) == null) {
            return  ResponseMap.factoryResult(StatusEnum.RET_NOT_DATA_FOUND.getCode(),StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        if(patientMapper.delete(patientId) == 1) {
            Map<String,String> map = new HashMap<>(2);
            map.put("patient_id",patientId.toString());
            List<Consultation> deleteList = consultationService.findAll(map);
            if (!deleteList.isEmpty()) {
                for (Consultation consultation : deleteList) {
                    consultationService.delete(consultation.getConsultationId());
                }
            }
            return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }else {
            return  ResponseMap.factoryResult(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
    }

    @Override
    public Map update(Patient patient) {
        if (patientMapper.update(patient) == 1) {
            return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(), patient);
        }
        return ResponseMap.factoryResult(StatusEnum.RET_UPDATE_FAIL.getCode(), StatusEnum.RET_UPDATE_FAIL.getData());
    }

    @Override
    public Map findById(Integer patientId) {
        Patient patient = patientMapper.findPatient(patientId);
        if (patient != null) {
            return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(), patient);
        }
        return ResponseMap.factoryResult(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getData());
    }

    @Override
    public List<Patient> findList() {
        return patientMapper.findPatientList();
    }

    @Override
    public List<Patient> findAll(Map<String, String> queryMap) {
        QueryWrapper<Product> queryWrapper = PageUtil.getQueryWrapper(queryMap);
        return patientMapper.getAll(queryWrapper);
    }
}
