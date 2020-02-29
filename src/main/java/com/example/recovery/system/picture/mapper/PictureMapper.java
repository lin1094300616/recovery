package com.example.recovery.system.picture.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.recovery.system.picture.entity.Picture;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author msi
 * @since 2020-01-05
 */
@Mapper
public interface PictureMapper extends BaseMapper<Picture> {

    /**根据实体Id查询图片集合**/
    @Select("select picture_id, type, entity_id, picture_base64, path " +
            "from picture " +
            "where entity_id = #{entity_id}")
    List<Picture> findAllByEntityId(@Param("entity_id") Integer entityId);

    /**根据实体Id删除图片集合**/
    @Delete("delete from picture where entity_id = #{entity_id}")
    int deleteByEntityId(Integer entityId);

    @Insert("INSERT INTO `picture` VALUES (#{pictureId}, #{type}, #{entityId}, #{pictureBase64}, #{path})")
    @Options(useGeneratedKeys = true, keyColumn = "pictureId", keyProperty = "pictureId")
    int add(Picture picture);

    @Delete("delete from picture where picture_id = #{pictureId}")
    int delete(Integer pictureId);

    @Select("select picture_id, type, entity_id, picture_base64, path" +
            "from picture " +
            "where picture_id = #{pictureId}")
    Picture findById(@Param("pictureId") Integer pictureId);

    @Select("select picture_id, type, entity_id, picture_base64, path from picture")
    List<Picture> findList();
}
