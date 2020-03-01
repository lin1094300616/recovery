package com.example.recovery.system.sell.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.recovery.framework.entity.ResponseMap;
import com.example.recovery.framework.entity.StatusEnum;
import com.example.recovery.framework.util.PageUtil;
import com.example.recovery.system.product.service.IProductService;
import com.example.recovery.system.sell.entity.ProductSell;
import com.example.recovery.system.sell.mapper.ProductSellMapper;
import com.example.recovery.system.sell.service.IProductSellService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author msi
 * @since 2020-02-29
 */
@Service
public class ProductSellServiceImpl extends ServiceImpl<ProductSellMapper, ProductSell> implements IProductSellService {

    @Autowired
    IProductService productService;

    @Resource
    ProductSellMapper sellMapper;

//    @Override
//    public Map add(ProductSell productSell) {
//        ProductSell productByName = sellMapper.findProductByname(productSell.getName());
//
//        if (productByName == null || productByName.getProductId().equals(productSell.getProductId())) {
//            if(sellMapper.add(productSell) == 1) {
//                return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
//            }
//            return  ResponseMap.factoryResult(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
//        }
//        return  ResponseMap.factoryResult(StatusEnum.RET_INSERT_EXIST.getCode(),StatusEnum.RET_INSERT_EXIST.getData());
//    }
//
//    @Override
//    public Map delete(Integer productId) {
//        return productService.delete(productId);
//    }

    @Override
    public Map update(ProductSell productSell) {
        ProductSell sellByName = sellMapper.findProductByname(productSell.getName());
        if (sellByName == null || sellByName.getProductId().equals(productSell.getProductId())) {
            if(sellMapper.update(productSell) == 1) {
                return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
            }
            return  ResponseMap.factoryResult(StatusEnum.RET_UPDATE_FAIL.getCode(),StatusEnum.RET_UPDATE_FAIL.getData());
        }
        return  ResponseMap.factoryResult(StatusEnum.RET_INSERT_EXIST.getCode(),StatusEnum.RET_INSERT_EXIST.getData());
    }

    @Override
    public Map findById(Integer productId) {
        ProductSell product = sellMapper.findProduct(productId);
        if (product != null) {
            return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(), product);
        }
        return ResponseMap.factoryResult(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getData());
    }

    @Override
    public List<ProductSell> findList() {
        return sellMapper.findProductList();
    }

    @Override
    public List<ProductSell> findAll(Map<String, String> queryMap) {
        QueryWrapper<ProductSell> queryWrapper = PageUtil.getQueryWrapper(queryMap);
        return sellMapper.getAll(queryWrapper);
    }
}
