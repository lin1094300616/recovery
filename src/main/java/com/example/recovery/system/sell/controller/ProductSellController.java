package com.example.recovery.system.sell.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.recovery.framework.entity.ResponseMap;
import com.example.recovery.framework.entity.StatusEnum;
import com.example.recovery.framework.util.PageUtil;
import com.example.recovery.system.sell.entity.ProductSell;
import com.example.recovery.system.sell.service.IProductSellService;
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
 * @since 2020-02-29
 */
@RestController
@RequestMapping("/sell")
@CrossOrigin
public class ProductSellController {

    @Autowired
    IProductSellService sellService;

    @PutMapping()
    public Map updateProductSell(@RequestBody ProductSell product) {
        return sellService.update(product);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public Map getProductSellById(@PathVariable("productId") Integer productId) {
        return sellService.findById(productId);
    }

    @GetMapping("/list")
    public Map findProductSellList() {
        return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(), sellService.findList());
    }

    @PostMapping("/page")
    public Map query(@RequestBody Map<String,String> queryMap) {
        //获取分页信息，并从查询条件中去除
        Integer page = Integer.valueOf(queryMap.get("page"));
        Integer size = Integer.valueOf(queryMap.get("size"));
        queryMap.remove("page");
        queryMap.remove("size");

        Page<ProductSell> pageInfo = PageHelper.startPage(page,size);
        List<ProductSell> productList = sellService.findAll(queryMap);
        JSONObject result = PageUtil.pageBaseInfo(pageInfo);
        return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(), productList, result);
    }
}
