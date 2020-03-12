package com.example.recovery.system.consultation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.recovery.framework.entity.ResponseMap;
import com.example.recovery.framework.entity.StatusEnum;
import com.example.recovery.framework.util.PageUtil;
import com.example.recovery.system.consultation.entity.Consultation;
import com.example.recovery.system.consultation.mapper.ConsultationMapper;
import com.example.recovery.system.consultation.service.IConsultationService;
import com.example.recovery.system.prescription.entity.Prescription;
import com.example.recovery.system.prescription.mapper.PrescriptionMapper;
import com.example.recovery.system.product.entity.Product;
import com.example.recovery.system.sell.entity.ProductSell;
import com.example.recovery.system.sell.mapper.ProductSellMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author msi  规格
 * @since 2020-03-06
 */
@Service
public class ConsultationServiceImpl extends ServiceImpl<ConsultationMapper, Consultation> implements IConsultationService {

    @Resource
    ConsultationMapper consultationMapper;

    @Resource
    PrescriptionMapper prescriptionMapper;

    @Resource
    ProductSellMapper sellMapper;

    Float price = 0.0f;

    @Override
    public Map check(Consultation consultation) {
        //校验药品是否充裕
        for (Prescription prescription : consultation.getPrescription()) {
            ProductSell product = sellMapper.findProduct(prescription.getProductId());

            //如果盒装存量等于盒装购买量 并且 最小单位存量小于购买最小单位购买量
            if ((product.getStock().equals(prescription.getStock())) &&
                    (product.getMinUnitStock() < prescription.getMinUnitStock())) {
                return ResponseMap.factoryResult(StatusEnum.Product_1001.getCode(), prescription.getName() + StatusEnum.Product_1001.getData());
            } else if (product.getStock() < prescription.getStock()) {
                //如果盒装存量小于盒装购买量
                return ResponseMap.factoryResult(StatusEnum.Product_1001.getCode(), prescription.getName() + StatusEnum.Product_1001.getData());
            }
            int stock = prescription.getStock();
            int unitStock = prescription.getMinUnitStock();
            price = price + (stock * product.getPrice() + unitStock * product.getMinPrice());
        }
        consultation.setTotalPrice(price);
        return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),consultation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map add(Consultation consultation) {
        if(consultationMapper.add(consultation) == 1) {
            List<Prescription> prescriptionList =  consultation.getPrescription();
            if (!prescriptionList.isEmpty()) {
                for (Prescription prescription : prescriptionList) {
                    prescription.setConsultationId(consultation.getConsultationId());
                    prescriptionMapper.add(prescription);
                    sellMapper.decreaseStock(prescription.getStock(), prescription.getMinUnitStock(), prescription.getProductId());
                }
            }
            return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }
        return  ResponseMap.factoryResult(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map delete(Integer consultationId) {
        if (consultationMapper.findConsultation(consultationId) == null) {
            return  ResponseMap.factoryResult(StatusEnum.RET_NOT_DATA_FOUND.getCode(),StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        if(consultationMapper.delete(consultationId) == 1) {
            prescriptionMapper.delete(consultationId);
            return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }else {
            return  ResponseMap.factoryResult(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
    }

    @Override
    public Map update(Consultation consultation) {
        if(consultationMapper.update(consultation) == 1) {
            List<Prescription> prescriptionList = consultation.getPrescription();
            if (!prescriptionList.isEmpty()) {
                for (Prescription prescription : prescriptionList) {
                    prescriptionMapper.update(prescription);
                }
            }
            return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }
        return  ResponseMap.factoryResult(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
    }

    @Override
    public Map findById(Integer consultationId) {
        Consultation consultation = consultationMapper.findConsultation(consultationId);
        if (consultation != null) {
            Map<String, String> queryMap = new HashMap<>(2);
            queryMap.put("consultation_id",consultationId.toString());
            QueryWrapper<Product> queryWrapper = PageUtil.getQueryWrapper(queryMap);
            List<Prescription> prescriptionList =  prescriptionMapper.getAll(queryWrapper);
            consultation.setPrescription(prescriptionList);
            return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(), consultation);
        }
        return ResponseMap.factoryResult(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getData());
    }

    @Override
    public List<Consultation> findList() {
        List<Prescription> prescriptionByConsultationId = prescriptionMapper.findPrescriptionByConsultationId(1);
        System.out.println("prescriptionByConsultationId = " + prescriptionByConsultationId);
        return consultationMapper.findConsultationList();
    }

    @Override
    public List<Consultation> findAll(Map<String, String> queryMap) {
        QueryWrapper<Product> queryWrapper = PageUtil.getQueryWrapper(queryMap);
        return consultationMapper.getAll(queryWrapper);
    }
}
