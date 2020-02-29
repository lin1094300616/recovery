package com.example.recovery;

import com.example.recovery.framework.util.CommUtil;
import com.example.recovery.system.epidemic.entity.Epidemic;
import com.example.recovery.system.epidemic.service.IEpidemicService;
import com.example.recovery.system.product.entity.Product;
import com.example.recovery.system.product.service.IProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecoveryApplicationTests {

    @Autowired
    IEpidemicService epidemicService;

    @Test
    public void contextLoads() {

//        List<Product> productList = productService.findAll();
//        System.out.println("productList = " + productList.get(0));

//        Map<String,String> queryMap = new HashMap<>(2);
//        queryMap.put("date","2020-02-28");
//        List<Epidemic> epidemicByDate = epidemicService.findAll(queryMap);
//        System.out.println("epidemicByDate = " + epidemicByDate.get(0));

        String test = "name like";
        System.out.println(test.endsWith("like"));
        test = "namelike";
        System.out.println(test.endsWith("like"));
        test = "name.like";
        System.out.println(test.endsWith("like"));
    }

}
