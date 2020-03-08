package com.example.recovery.system.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.recovery.framework.entity.ResponseMap;
import com.example.recovery.framework.entity.StatusEnum;
import com.example.recovery.framework.util.CommUtil;
import com.example.recovery.framework.util.PageUtil;
import com.example.recovery.system.product.entity.Product;
import com.example.recovery.system.product.mapper.ProductMapper;
import com.example.recovery.system.product.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.recovery.system.sell.entity.ProductSell;
import com.example.recovery.system.sell.mapper.ProductSellMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author msi
 * @since 2020-02-21
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Resource
    ProductMapper productMapper;

    @Resource
    ProductSellMapper sellMapper;

    @Override
    @Transactional
    public Map add(Product product) {
        Product productByName = productMapper.findProductByName(product.getName());
        if (productByName == null || productByName.getProductId().equals(product.getProductId())) {
            if(productMapper.add(product) == 1) {
                ProductSell productSell = (ProductSell)product;
                sellMapper.add(productSell);
                return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
            }
            return  ResponseMap.factoryResult(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
        return  ResponseMap.factoryResult(StatusEnum.RET_INSERT_EXIST.getCode(),StatusEnum.RET_INSERT_EXIST.getData());
    }

    @Override
    @Transactional
    public Map delete(Integer productId) {
        if (productMapper.findProduct(productId) == null) {
            return  ResponseMap.factoryResult(StatusEnum.RET_NOT_DATA_FOUND.getCode(),StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        if(productMapper.delete(productId) == 1) {
            sellMapper.delete(productId);
            return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }else {
            return  ResponseMap.factoryResult(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
    }

    @Override
    public Map update(Product product) {
        Product oldProduct = productMapper.findProduct(product.getProductId());
        if (oldProduct == null || oldProduct.getProductId().equals(product.getProductId())) {
            if(productMapper.update(product) == 1) {
                ProductSell sell = sellMapper.findProduct(product.getProductId());
                sell.setName(product.getName());
                sellMapper.update(sell);
                return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
            }
            return  ResponseMap.factoryResult(StatusEnum.RET_UPDATE_FAIL.getCode(),StatusEnum.RET_UPDATE_FAIL.getData());
        }
        return  ResponseMap.factoryResult(StatusEnum.RET_INSERT_EXIST.getCode(),StatusEnum.RET_INSERT_EXIST.getData());
    }

    @Override
    public Map findById(Integer productId) {
        Product product = productMapper.findProduct(productId);
        if (product != null) {
            return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(), product);
        }
        return ResponseMap.factoryResult(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getData());
    }

    @Override
    public List<Product> findList() {
        return productMapper.findProductList();
    }

    @Override
    public List<Product> findAll(Map<String, String> queryMap) {
        QueryWrapper<Product> queryWrapper = PageUtil.getQueryWrapper(queryMap);
        return productMapper.getAll(queryWrapper);
    }

}
