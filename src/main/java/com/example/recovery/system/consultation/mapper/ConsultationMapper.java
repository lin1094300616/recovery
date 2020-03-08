package com.example.recovery.system.consultation.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.recovery.system.consultation.entity.Consultation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author msi
 * @since 2020-03-06
 */
@Mapper
public interface ConsultationMapper extends BaseMapper<Consultation> {

    @Delete("delete from consultation where patient_id = #{patientId}")
    int deleteByPatient(Integer patientId);

    /**
     * 条件构造器查询
     * @param wrapper
     * @return
     */
    @Select("select * from consultation ${ew.customSqlSegment}")
    List<Consultation> getAll(@Param(Constants.WRAPPER) QueryWrapper wrapper);

    @Select("select * " +
            "from consultation " +
            "where name = #{name}")
    Consultation findConsultationByName(@Param("name") String name);

    @Insert("INSERT INTO `consultation` VALUES (#{consultationId}, #{disease}, #{symptoms}, #{treatment}, " +
            "#{totalPrice}, #{patientId} )")
    @Options(useGeneratedKeys = true, keyColumn = "consultationId", keyProperty = "consultationId")
    int add(Consultation consultation);

    @Update("update consultation set disease = #{disease},symptoms = #{symptoms}, " +
            "treatment = #{treatment}, total_price = #{totalPrice}, patient_id = #{patient_id} " +
            "where consultation_id = #{consultationId} ")
    int update(Consultation consultation);

    @Delete("delete from consultation where consultation_id = #{consultationId}")
    int delete(Integer consultationId);

    @Select("select *" +
            "from consultation " +
            "where consultation_id = #{consultationId}")
    Consultation findConsultation(@Param("consultationId") Integer consultationId);

    @Select("select *" +
            "from consultation ")
    List<Consultation> findConsultationList();
}
