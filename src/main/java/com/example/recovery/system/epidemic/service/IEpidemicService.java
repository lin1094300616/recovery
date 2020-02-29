package com.example.recovery.system.epidemic.service;

import com.example.recovery.system.epidemic.entity.Epidemic;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author msi
 * @since 2020-02-28
 */
public interface IEpidemicService extends IService<Epidemic> {

    Map add(Epidemic epidemic);

    Map update(Epidemic epidemic);

    Map delete(Integer epidemicId);

    Map findEpidemic(Integer epidemicId);

    List<Epidemic> findEpidemicList();

    List<Epidemic> findAll(Map<String,String> queryMap);
}
