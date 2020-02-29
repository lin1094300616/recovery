package com.example.recovery.system.product.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.example.recovery.system.epidemic.entity.Epidemic;
import com.example.recovery.system.product.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author msi
 * @since 2020-02-21
 */
public interface IProductService extends IService<Product> {

    Map add(Product product);

    Map delete(Integer productId);

    Map update(Product product);

    Map findById(Integer productId);

    List<Product> findList();

    List<Product> findAll(Map<String,String> queryMap);
}
