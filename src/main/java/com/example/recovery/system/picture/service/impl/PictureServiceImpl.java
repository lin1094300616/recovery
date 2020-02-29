package com.example.recovery.system.picture.service.impl;

import com.example.recovery.framework.entity.ResponseMap;
import com.example.recovery.framework.entity.StatusEnum;
import com.example.recovery.framework.util.FileUtil;
import com.example.recovery.system.picture.entity.Picture;
import com.example.recovery.system.picture.mapper.PictureMapper;
import com.example.recovery.system.picture.service.IPictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author msi
 * @since 2020-01-05
 */
@Service
public class PictureServiceImpl  implements IPictureService {

    @Resource
    PictureMapper pictureMapper;
    
    @Override
    public List<Picture> findAllByEntityId(Integer entityId) {
        return pictureMapper.findAllByEntityId(entityId);
    }

    @Override
    public Map deleteByEntityId(Integer entityId) {
        if (pictureMapper.deleteByEntityId(entityId) > 0) {
            return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }
        return  ResponseMap.factoryResult(StatusEnum.RET_DELETE_FAIL.getCode(),StatusEnum.RET_DELETE_FAIL.getData());
    }

    @Override
    public Map add(Picture picture) {
        if(pictureMapper.add(picture) == 1) {
            return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }
        return  ResponseMap.factoryResult(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
    }


    @Override
    public Map delete(Integer pictureId) {
        Picture picture = pictureMapper.findById(pictureId);
        if (picture == null) {
            return  ResponseMap.factoryResult(StatusEnum.RET_NOT_DATA_FOUND.getCode(),StatusEnum.RET_NOT_DATA_FOUND.getData());
        }
        if(pictureMapper.delete(pictureId) == 1) {
            FileUtil.deleteDir(new File(picture.getPath()));
            return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }
        return  ResponseMap.factoryResult(StatusEnum.RET_DELETE_FAIL.getCode(),StatusEnum.RET_DELETE_FAIL.getData());
    }

    @Override
    public Picture findById(Integer pictureId) {
        return pictureMapper.findById(pictureId);
    }

    @Override
    public List<Picture> findPictureList() {
        return pictureMapper.findList();
    }
}
