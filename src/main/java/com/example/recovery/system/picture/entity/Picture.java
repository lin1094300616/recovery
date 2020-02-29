package com.example.recovery.system.picture.entity;

/**
 * <p>
 * 
 * </p>
 *
 * @author msi
 * @since 2020-01-05
 */
public class Picture {

    /**
     * 图片ID
     */
    private Long pictureId;

    /**
     * 模块类别
     */
    private String type;

    /**
     * 关联实体ID
     */
    private Integer entityId;

    /**
     * 保存base64 字符串
     */
    private String pictureBase64;

    /**
     * 保存文件存储路径
     */
    private String path;

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getPictureBase64() {
        return pictureBase64;
    }

    public void setPictureBase64(String pictureBase64) {
        this.pictureBase64 = pictureBase64;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
