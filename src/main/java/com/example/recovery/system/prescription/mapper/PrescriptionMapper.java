package com.example.recovery.system.prescription.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.recovery.system.prescription.entity.Prescription;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 药方接口 就诊与药品关联表
 * </p>
 *
 * @author msi
 * @since 2020-03-07
 */
@Mapper
public interface PrescriptionMapper extends BaseMapper<Prescription> {

    /**
     * 条件构造器查询
     * @param wrapper
     * @return
     */
    @Select("select * from prescription ${ew.customSqlSegment}")
    List<Prescription> getAll(@Param(Constants.WRAPPER) QueryWrapper wrapper);

    @Select("select * " +
            "from prescription " +
            "where consultation_id = #{consultationId}")
    List<Prescription> findPrescriptionByConsultationId(Integer consultationId);

    @Insert("INSERT INTO `prescription` VALUES (#{prescriptionId}, #{consultationId}, #{productId}, #{name}, " +
            "#{stock}, #{minUnitStock} )")
    @Options(useGeneratedKeys = true, keyColumn = "prescriptionId", keyProperty = "prescriptionId")
    int add(Prescription prescription);

    @Update("update prescription set name = #{name}, stock = #{stock}, min_unit_stock = #{minUnitStock} " +
            "where prescription_id = #{prescriptionId} ")
    int update(Prescription prescription);

    @Delete("delete from prescription where prescription_id = #{prescriptionId}")
    int delete(Integer prescriptionId);

    @Select("select *" +
            "from prescription " +
            "where prescription_id = #{prescriptionId}")
    Prescription findPrescription(@Param("prescriptionId") Integer prescriptionId);

    @Select("select *" +
            "from prescription ")
    List<Prescription> findPrescriptionList();
}
