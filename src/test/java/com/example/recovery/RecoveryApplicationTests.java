package com.example.recovery;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.recovery.framework.util.CommUtil;
import com.example.recovery.framework.util.PageUtil;
import com.example.recovery.system.consultation.service.IConsultationService;
import com.example.recovery.system.consultation.service.impl.ConsultationServiceImpl;
import com.example.recovery.system.epidemic.entity.Epidemic;
import com.example.recovery.system.epidemic.service.IEpidemicService;
import com.example.recovery.system.prescription.entity.Prescription;
import com.example.recovery.system.product.entity.Product;
import com.example.recovery.system.product.service.IProductService;
import com.example.recovery.system.sell.entity.ProductSell;
import com.example.recovery.system.sell.service.IProductSellService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecoveryApplicationTests {

    @Autowired
    ConsultationServiceImpl service;

    @Test
    public void contextLoads() {

//        List<Product> productList = productService.findAll();
//        System.out.println("productList = " + productList.get(0));

//        Map<String,String> queryMap = new HashMap<>(2);
//        queryMap.put("date","2020-02-28");
//        List<Epidemic> epidemicByDate = epidemicService.findAll(queryMap);
//        System.out.println("epidemicByDate = " + epidemicByDate.get(0));
        Map<String, String> queryMap = new HashMap<>();
        QueryWrapper queryWrapper = PageUtil.getQueryWrapper(queryMap);
    }

}
