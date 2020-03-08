package com.example.recovery.system.patient.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.recovery.system.patient.entity.Patient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author msi
 * @since 2020-03-07
 */
@Mapper
public interface PatientMapper extends BaseMapper<Patient> {

    /**
     * 条件构造器查询
     * @param wrapper
     * @return
     */
    @Select("select * from patient ${ew.customSqlSegment}")
    List<Patient> getAll(@Param(Constants.WRAPPER) QueryWrapper wrapper);

    @Select("select * " +
            "from patient " +
            "where name = #{name}")
    Patient findPatientByName(@Param("name") String name);

    @Insert("INSERT INTO patient VALUES (#{patientId}, #{name}, #{sex}, #{age}, " +
            "#{nativePlace}, #{temperature}, #{suspected}, #{epidemicArea} )")
    @Options(useGeneratedKeys = true, keyColumn = "patientId", keyProperty = "patientId")
    int add(Patient patient);

    @Update("update patient set name = #{name},sex = #{sex} ,age = #{age},native_place = #{nativePlace}, " +
            "temperature = #{temperature}, suspected = #{suspected}, epidemic_area = #{epidemicArea} " +
            "where patient_id = #{patientId} ")
    int update(Patient patient);

    @Delete("delete from patient where patient_id = #{patientId}")
    int delete(Integer patientId);

    @Select("select *" +
            "from patient " +
            "where patient_id = #{patientId}")
    Patient findPatient(@Param("patientId") Integer patientId);

    @Select("select *" +
            "from patient ")
    List<Patient> findPatientList();
}
