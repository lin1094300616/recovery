package com.example.recovery.system.consultation.service;

import com.example.recovery.system.consultation.entity.Consultation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author msi
 * @since 2020-03-06
 */
public interface IConsultationService extends IService<Consultation> {

    Map check(Consultation consultation);

    Map add(Consultation consultation);

    Map delete(Integer consultationId);

    Map update(Consultation consultation);

    Map findById(Integer consultationId);

    List<Consultation> findList();

    List<Consultation> findAll(Map<String,String> queryMap);
}
