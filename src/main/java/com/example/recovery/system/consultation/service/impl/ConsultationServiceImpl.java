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

    List<ProductSell> productSellList;

    Float price = 0.0f;

    public void load(SortedSet<Prescription> prescriptionList) {
        List<Integer> idList = new ArrayList<>();
        for (Prescription prescription : prescriptionList) {
            idList.add(prescription.getProductId());
        }
        QueryWrapper<ProductSell> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id",idList);
        queryWrapper.orderByAsc("product_id");
        productSellList = sellMapper.getAll(queryWrapper);
    }

    @Override
    public Map check(Consultation consultation) {
        //校验药品是否充裕
        for (Prescription prescription : consultation.getPrescription()) {
            if (sellMapper.checkStock(
                    prescription.getProductId(),
                    prescription.getStock(),
                    prescription.getMinUnitStock()) <= 0) {
                return  ResponseMap.factoryResult(StatusEnum.Product_1001.getCode(),prescription.getName() + StatusEnum.Product_1001.getData());
            }
        }
        /**
         * 药品充裕，进行计算
         * sortList 获得购买量，
         * productList 获得价格，计算，累加
         */
        SortedSet<Prescription> sortedSet = consultation.getPrescription();
        load(sortedSet);
        int subscript = 0;
        for (Prescription prescription : sortedSet) {
            ProductSell productSell = productSellList.get(subscript);
            int stock = prescription.getStock();
            int unitStock = prescription.getMinUnitStock();
            price = price + (stock * productSell.getPrice() + unitStock * productSell.getMinPrice());
            consultation.setTotalPrice(price);
            subscript++;
        }
        return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),consultation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map add(Consultation consultation) {
        if(consultationMapper.add(consultation) == 1) {
            SortedSet<Prescription> prescriptionList = consultation.getPrescription();
            if (!prescriptionList.isEmpty()) {
                for (Prescription prescription : prescriptionList) {
                    prescription.setConsultationId(consultation.getConsultationId());
                    prescriptionMapper.add(prescription);
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
            SortedSet<Prescription> prescriptionList = consultation.getPrescription();
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
            return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(), consultation);
        }
        return ResponseMap.factoryResult(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getData());
    }

    @Override
    public List<Consultation> findList() {
        return consultationMapper.findConsultationList();
    }

    @Override
    public List<Consultation> findAll(Map<String, String> queryMap) {
        QueryWrapper<Product> queryWrapper = PageUtil.getQueryWrapper(queryMap);
        return consultationMapper.getAll(queryWrapper);
    }
}
