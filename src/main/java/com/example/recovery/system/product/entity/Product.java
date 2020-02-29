package com.example.recovery.system.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.example.recovery.system.sell.entity.ProductSell;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author msi
 * @since 2020-02-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Product extends ProductSell implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "product_id", type = IdType.AUTO)
    private Integer productId;

    /**
     * 名称
     */
    private String name;

    /**
     * 成分
     */
    private String component;

    /**
     * 性状
     */
    private String description;

    /**
     * 药理作用
     */
    private String pharmacologicalAction;

    /**
     * 功能主治
     */
    private String indications;

    /**
     * 用法用量
     */
    private String dosageAdministration;

    /**
     * 禁忌
     */
    private String contraindications;

    /**
     * 注意事项
     */
    private String precautions;

    /**
     * 包装
     */
    private String pack;

    /**
     * 贮存
     */
    private String storage;

    /**
     * 是否处方药
     */
    private String otc;

    /**
     * 类型：1药品， 2保健品， 3医疗器械
     */
    private String type;

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

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPharmacologicalAction() {
        return pharmacologicalAction;
    }

    public void setPharmacologicalAction(String pharmacologicalAction) {
        this.pharmacologicalAction = pharmacologicalAction;
    }

    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }

    public String getDosageAdministration() {
        return dosageAdministration;
    }

    public void setDosageAdministration(String dosageAdministration) {
        this.dosageAdministration = dosageAdministration;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public String getPrecautions() {
        return precautions;
    }

    public void setPrecautions(String precautions) {
        this.precautions = precautions;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getOtc() {
        return otc;
    }

    public void setOtc(String otc) {
        this.otc = otc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
