package com.example.recovery.system.picture.service;

import com.example.recovery.system.picture.entity.Picture;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author msi
 * @since 2020-01-05
 */
public interface IPictureService  {

    List<Picture> findAllByEntityId(Integer entityId);

    Map deleteByEntityId(Integer entityId);

    Map add(Picture picture);

    Map delete(Integer pictureId);

    Picture findById(Integer pictureId);

    List<Picture> findPictureList();
}
