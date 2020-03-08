package com.example.recovery.system.consultation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import com.example.recovery.system.prescription.entity.Prescription;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author msi
 * @since 2020-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Consultation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 就诊ID
     */
    @TableId(value = "consultation_id", type = IdType.AUTO)
    private Integer consultationId;

    /**
     * 疾病名称
     */
    private String disease;

    /**
     * 症状
     */
    private String symptoms;

    /**
     * 治疗方法
     */
    private String treatment;

    /**
     * 关联病人ID
     */
    private Integer patientId;

    /**
     * 总价
     */
    private float TotalPrice;

    /**
     * 药方
     */
    private SortedSet<Prescription> prescription;

    public Integer getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Integer consultationId) {
        this.consultationId = consultationId;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public float getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        TotalPrice = totalPrice;
    }

    public SortedSet<Prescription> getPrescription() {
        return prescription;
    }

    public void setPrescription(SortedSet<Prescription> prescription) {
        this.prescription = prescription;
    }
}
