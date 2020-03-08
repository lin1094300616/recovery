package com.example.recovery.system.sell.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.recovery.system.sell.entity.ProductSell;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author msi
 * @since 2020-02-29
 */
@Mapper
public interface ProductSellMapper extends BaseMapper<ProductSell> {

    @Select("select COUNT(1) FROM product_sell " +
            "WHERE product_id = #{productId} " +
            "and (stock > #{stock} OR (stock = #{stock} and min_unit_stock >= #{minStock}))")
    int checkStock(@Param("productId")Integer productId, @Param("stock")Integer stock, @Param("minStock")Integer minStock);

    /**
     * 条件构造器查询
     * @param wrapper
     * @return
     */
    @Select("select * from product_sell ${ew.customSqlSegment}")
    List<ProductSell> getAll(@Param(Constants.WRAPPER) QueryWrapper wrapper);

    @Select("select * " +
            "from product_sell " +
            "where name = #{name}")
    ProductSell findProductByname(@Param("name") String name);

    @Insert("INSERT INTO `product_sell` VALUES (#{productId}, #{name}, #{purchasePrice}, #{price}, #{stock}, " +
            "#{unit}, #{minPrice}, #{minUnitStock}, #{minUnit}, #{stockDate}, #{purchaser}, " +
            "#{purchaserPhone} )")
    @Options(useGeneratedKeys = true, keyColumn = "productId", keyProperty = "productId")
    int add(ProductSell productSell);

    @Update("update product_sell set name = #{name}, purchase_price = #{purchasePrice}, price = #{price}, " +
            "stock = #{stock}, unit = #{unit}, min_price = #{minPrice}, min_unit_stock = #{minUnitStock}, " +
            "min_unit = #{minUnit},stock_date = #{stockDate}, purchaser = #{purchaser}, " +
            "purchaser_phone = #{purchaserPhone} " +
            "where product_id = #{productId} ")
    int update(ProductSell productSell);

    @Delete("delete from product_sell where product_id = #{productId}")
    int delete(Integer productId);

    @Select("select * " +
            "from product_sell " +
            "where product_id = #{productId}")
    ProductSell findProduct(@Param("productId") Integer productId);

    @Select("select * " +
            "from product_sell ")
    List<ProductSell> findProductList();


}
