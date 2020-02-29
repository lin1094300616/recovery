package com.example.recovery.system.product.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.recovery.framework.entity.ResponseMap;
import com.example.recovery.framework.entity.StatusEnum;
import com.example.recovery.framework.util.CommUtil;
import com.example.recovery.framework.util.PageUtil;
import com.example.recovery.system.product.entity.Product;
import com.example.recovery.system.product.entity.Product;
import com.example.recovery.system.product.service.IProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author msi
 * @since 2020-02-21
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @PostMapping
    public Map postProduct(@RequestBody Product product) {
        if (CommUtil.isNullString(product.getName())) {
            return ResponseMap.factoryResult(StatusEnum.SYSTEM_ERROR_9002.getCode(),StatusEnum.SYSTEM_ERROR_9002.getData());
        }
        return productService.add(product);
    }

    @PutMapping()
    public Map updateProduct(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping(value = "/{productId}")
    public Map delete(@PathVariable(value = "productId")Integer productId) {
        return productService.delete(productId);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public Map getProductById(@PathVariable("productId") Integer productId) {
        return productService.findById(productId);
    }

    @GetMapping("/page")
    public Map query(@RequestBody Map<String,String> queryMap) {
        //获取分页信息，并从查询条件中去除
        Integer page = Integer.valueOf(queryMap.get("page"));
        Integer size = Integer.valueOf(queryMap.get("size"));
        queryMap.remove("page");
        queryMap.remove("size");

        Page<Product> pageInfo = PageHelper.startPage(page,size);
        List<Product> productList = productService.findAll(queryMap);
        JSONObject result = PageUtil.pageBaseInfo(pageInfo);
        return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(), productList, result);
    }
}
