package com.example.recovery.system.sell.service;

import com.example.recovery.system.sell.entity.ProductSell;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author msi
 * @since 2020-02-29
 */
public interface IProductSellService extends IService<ProductSell> {

//    Map add(ProductSell productSell);
//
//    Map delete(Integer productId);

    Map update(ProductSell productSell);

    Map findById(Integer productId);

    List<ProductSell> findList();

    List<ProductSell> findAll(Map<String,String> queryMap);
}
