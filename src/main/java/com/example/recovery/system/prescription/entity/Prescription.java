package com.example.recovery.system.prescription.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *  药方
 * </p>
 *
 * @author msi
 * @since 2020-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Prescription implements Serializable,Comparable<Prescription> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "prescription_id", type = IdType.AUTO)
    private Integer prescriptionId;

    /**
     * 就诊信息编号
     */
    private Integer consultationId;

    /**
     * 药品编号
     */
    private Integer productId;

    /**
     * 药品名
     */
    private String name;

    /**
     * 用量
     */
    private Integer stock;

    /**
     * 最小销售单位用量
     */
    private Integer minUnitStock;


    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Integer getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Integer consultationId) {
        this.consultationId = consultationId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getMinUnitStock() {
        return minUnitStock;
    }

    public void setMinUnitStock(Integer minUnitStock) {
        this.minUnitStock = minUnitStock;
    }

    @Override
    public int compareTo(Prescription o) {

            if(this.productId>o.productId){
                return 1 ;
            }else if(this.productId<o.productId){
                return -1 ;
            }else{
                return 0 ;
            }

    }

//    @Override
//    public int compareTo(Prescription prescription1, Prescription prescription2) {
//        return prescription1.getProductId().compareTo(prescription2.getProductId());
//    }
}
