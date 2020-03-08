package com.example.recovery.system.patient.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author msi
 * @since 2020-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "patient_id", type = IdType.AUTO)
    private Integer patientId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 体温
     */
    private BigDecimal temperature;

    /**
     * 是否出现疑似症状，0未出现； 1已出现
     */
    private String suspected;

    /**
     * 是否经过疫区 0未经过； 1经过
     */
    private String epidemicArea;


    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public String getSuspected() {
        return suspected;
    }

    public void setSuspected(String suspected) {
        this.suspected = suspected;
    }

    public String getEpidemicArea() {
        return epidemicArea;
    }

    public void setEpidemicArea(String epidemicArea) {
        this.epidemicArea = epidemicArea;
    }
}
