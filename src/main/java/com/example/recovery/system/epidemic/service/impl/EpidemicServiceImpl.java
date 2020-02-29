package com.example.recovery.system.epidemic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.recovery.framework.entity.ResponseMap;
import com.example.recovery.framework.entity.StatusEnum;
import com.example.recovery.framework.util.PageUtil;
import com.example.recovery.system.epidemic.entity.Epidemic;
import com.example.recovery.system.epidemic.mapper.EpidemicMapper;
import com.example.recovery.system.epidemic.service.IEpidemicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author msi
 * @since 2020-02-28
 */
@Service
public class EpidemicServiceImpl extends ServiceImpl<EpidemicMapper, Epidemic> implements IEpidemicService {

    @Resource
    EpidemicMapper epidemicMapper;

    @Override
    @Transactional
    public Map add(Epidemic epidemic) {
        if(epidemicMapper.add(epidemic) == 1) {
            epidemicMapper.update2(epidemic);
            return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }else {
            return  ResponseMap.factoryResult(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
    }

    @Override
    @Transactional
    public Map update(Epidemic epidemic) {
        Epidemic oldEpidemic = epidemicMapper.findEpidemic(epidemic.getEpidemicId());
        if (epidemicMapper.update(epidemic) == 1) {
            epidemic.setConfirmed(epidemic.getConfirmed() - oldEpidemic.getConfirmed());
            epidemic.setSuspected(epidemic.getSuspected() - oldEpidemic.getSuspected());
            epidemic.setDeath(epidemic.getDeath() - oldEpidemic.getDeath());
            epidemic.setCured(epidemic.getCured() - oldEpidemic.getCured());
            if (epidemicMapper.update2(epidemic) == 1) {
                return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
            }
        }
        return  ResponseMap.factoryResult(StatusEnum.RET_UPDATE_FAIL.getCode(),StatusEnum.RET_UPDATE_FAIL.getData());
    }

    @Override
    public Map delete(Integer epidemicId) {
        return null;
    }

    @Override
    public Map findEpidemic(Integer epidemicId) {
        Epidemic epidemic = epidemicMapper.findEpidemic(epidemicId);
        if (epidemic != null) {
            return ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(), epidemic);
        }
        return ResponseMap.factoryResult(StatusEnum.RET_NOT_DATA_FOUND.getCode(), StatusEnum.RET_NOT_DATA_FOUND.getData());
    }

    @Override
    public List<Epidemic> findEpidemicList() {
        return epidemicMapper.findEpidemicList();
    }

    @Override
    public List<Epidemic> findAll(Map<String,String> queryMap) {
        QueryWrapper<Epidemic> queryWrapper = PageUtil.getQueryWrapper(queryMap);
        return epidemicMapper.getAll(queryWrapper);
    }
}
