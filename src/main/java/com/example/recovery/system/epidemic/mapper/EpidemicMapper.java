package com.example.recovery.system.epidemic.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.recovery.system.epidemic.entity.Epidemic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.recovery.system.product.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author msi
 * @since 2020-02-28
 */
@Mapper
public interface EpidemicMapper extends BaseMapper<Epidemic> {

    @Select("select * " +
            "from epidemic " +
            "where area_number = #{higherAreaNumber} " +
            "and date = #{date}")
    Epidemic findEpidemicByAreaNumber(Epidemic epidemic);

    /**
     * 条件构造器查询
     * @param wrapper
     * @return
     */
    @Select("select *, from epidemic ${ew.customSqlSegment}")
    List<Epidemic> getAll(@Param(Constants.WRAPPER) QueryWrapper wrapper);

    @Update("update epidemic set confirmed = confirmed + #{confirmed}, suspected = suspected + #{suspected}, " +
            "death = death + #{death}, cured = cured + #{cured} " +
            "where area_number = #{areaNumber} " +
            "and date = #{date} ")
    int update3(Epidemic epidemic);

    @Update("update epidemic set confirmed = confirmed + #{confirmed}, suspected = suspected + #{suspected}, " +
            "death = death + #{death}, cured = cured + #{cured} " +
            "where province = #{province} " +
            "and date = '0' ")
    int update2(Epidemic epidemic);

    @Insert("INSERT INTO epidemic VALUES (#{epidemicId}, #{province}, #{area_number}, #{higher_area_number}， " +
            "#{date}, #{confirmed}, #{suspected}, #{death}, #{cured})")
    @Options(useGeneratedKeys = true, keyColumn = "epidemicId", keyProperty = "epidemicId")
    int add(Epidemic epidemic);

    @Update("update epidemic set confirmed = #{confirmed}, " +
            "suspected = #{suspected}, death = #{death} ,cured = #{cured} " +
            "where epidemic_id = #{epidemicId} ")
    int update(Epidemic epidemic);

    @Delete("delete from epidemic where epidemic_id = #{epidemicId}")
    int delete(Integer epidemicId);

    @Select("select * " +
            "from epidemic " +
            "where epidemic_id = #{epidemicId}")
    Epidemic findEpidemic(@Param("epidemicId") Integer epidemicId);

    @Select("select epidemic_id, province, area_number, higher_area_number, date, confirmed, suspected, death, cured " +
            "from epidemic")
    List<Epidemic> findEpidemicList();
}
