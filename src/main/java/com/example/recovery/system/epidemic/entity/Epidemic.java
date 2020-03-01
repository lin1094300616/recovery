package com.example.recovery.system.epidemic.entity;

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
 * @since 2020-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Epidemic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 疫情ID
     */
    private Integer epidemicId;
    /**
     * 地区行政编码
     */
    private Integer areaNumber;
    /**
     * 上级地区行政编码
     */
    private Integer higherAreaNumber;
    /**
     * 省份
     */
    private String province;
    /**
     * 日期
     */
    private String date;

    /**
     * 确诊
     */
    private Integer confirmed = 0;

    /**
     * 疑似
     */
    private Integer suspected = 0;

    /**
     * 死亡
     */
    private Integer death = 0;

    /**
     * 治愈
     */
    private Integer cured = 0;

    public Integer getEpidemicId() {
        return epidemicId;
    }

    public Integer getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(Integer areaNumber) {
        this.areaNumber = areaNumber;
    }

    public Integer getHigherAreaNumber() {
        return higherAreaNumber;
    }

    public void setHigherAreaNumber(Integer higherAreaNumber) {
        this.higherAreaNumber = higherAreaNumber;
    }

    public void setEpidemicId(Integer epidemicId) {
        this.epidemicId = epidemicId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getSuspected() {
        return suspected;
    }

    public void setSuspected(Integer suspected) {
        this.suspected = suspected;
    }

    public Integer getDeath() {
        return death;
    }

    public void setDeath(Integer death) {
        this.death = death;
    }

    public Integer getCured() {
        return cured;
    }

    public void setCured(Integer cured) {
        this.cured = cured;
    }
}
