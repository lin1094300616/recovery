package com.example.recovery.system.product.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.recovery.system.product.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author msi
 * @since 2020-02-21
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 条件构造器查询
     * @param wrapper
     * @return
     */
    @Select("select * from product ${ew.customSqlSegment}")
    List<Product> getAll(@Param(Constants.WRAPPER) QueryWrapper wrapper);

    @Select("select * " +
            "from product " +
            "where name = #{name}")
    Product findProductByName(@Param("name") String name);

    @Insert("INSERT INTO `product` VALUES (#{productId}, #{name}, #{component}, #{description}, " +
            "#{pharmacologicalAction}, #{indications}, #{dosageAdministration}, #{contraindications}, " +
            "#{precautions}, #{pack}, #{storage}, #{otc}, #{type} )")
    @Options(useGeneratedKeys = true, keyColumn = "productId", keyProperty = "productId")
    int add(Product product);

    @Update("update product set name = #{name},component = #{component}," +
            "description = #{description}, pharmacological_action = #{pharmacologicalAction}, " +
            "indications = #{indications}, dosage_administration = #{dosageAdministration}, " +
            "contraindications = #{contraindications}, precautions = #{precautions}, " +
            "pack = #{pack}, storage = #{storage}, otc = #{otc}, type = #{type} " +
            "where product_id = #{productId} ")
    int update(Product product);

    @Delete("delete from product where product_id = #{productId}")
    int delete(Integer productId);

    @Select("select product_id, name, component, description, pharmacological_action, indications, " +
            "dosage_administration, contraindications, precautions, pack, storage, otc, type " +
            "from product " +
            "where product_id = #{productId}")
    Product findProduct(@Param("productId") Integer productId);

    @Select("select product_id, name, component, description, pharmacological_action, indications, " +
            "dosage_administration, contraindications, precautions, pack, storage, otc, type " +
            "from product ")
    List<Product> findProductList();



}
