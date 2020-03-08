package com.example.recovery.system.patient.service;

import com.example.recovery.system.patient.entity.Patient;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author msi
 * @since 2020-03-07
 */
public interface IPatientService extends IService<Patient> {

    Map add(Patient patient);

    Map delete(Integer patientId);

    Map update(Patient patient);

    Map findById(Integer patientId);

    List<Patient> findList();

    List<Patient> findAll(Map<String,String> queryMap);
}
