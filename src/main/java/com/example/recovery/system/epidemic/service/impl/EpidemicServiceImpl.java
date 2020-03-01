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

    static  Epidemic cumulativeEpidemic = new Epidemic();

    /**
     * 添加—— 上级地区修改（累加相应的值）
     * 修改—— 修改的值和原值做比较，修改上级地区
     *
     * 上级行政地区递归方法，判断当前地区上级地区是否存在（是否为0）
     * 存在则调用递归修改
     */
    @Transactional(rollbackFor=Exception.class)
    public boolean updateArea(Epidemic epidemic) {
        try{
            if (epidemic.getHigherAreaNumber() == 0){
                epidemic.setDate("0");
                epidemicMapper.update3(epidemic);
                return true;
            }
            Epidemic updateEpidemic = epidemicMapper.findEpidemicByAreaNumber(epidemic);
            updateEpidemic.setConfirmed(cumulativeEpidemic.getConfirmed());
            updateEpidemic.setSuspected(cumulativeEpidemic.getSuspected());
            updateEpidemic.setDeath(cumulativeEpidemic.getDeath());
            updateEpidemic.setCured(cumulativeEpidemic.getCured());
            epidemicMapper.update3(updateEpidemic);
            return updateArea(updateEpidemic);
        }catch (Exception e) {
            System.out.println("e = " + e);
        }
        return false;
    }

    /**
     * 计算出每一项需要 相加/减少的人数
     * @param oldEpidemic
     * @param newEpidemic
     * @return
     */
//    public void getDifference(Epidemic oldEpidemic, Epidemic newEpidemic) {
//        cumulativeEpidemic.setConfirmed(newEpidemic.getConfirmed() - oldEpidemic.getConfirmed());
//        cumulativeEpidemic.setSuspected(newEpidemic.getSuspected() - oldEpidemic.getSuspected());
//        cumulativeEpidemic.setDeath(newEpidemic.getDeath() - oldEpidemic.getDeath());
//        cumulativeEpidemic.setCured(newEpidemic.getCured() - oldEpidemic.getCured());
//    }

    @Override
    @Transactional
    public Map add(Epidemic epidemic) {
        if((epidemicMapper.add(epidemic) == 1) && (epidemic.getHigherAreaNumber() != 0)) {
            //添加疫情的情况下，直接使用当前值进行递归调用
            updateArea(epidemic);
            return  ResponseMap.factoryResult(StatusEnum.RESPONSE_OK.getCode(),StatusEnum.RESPONSE_OK.getData());
        }else {
            return  ResponseMap.factoryResult(StatusEnum.RET_INSERT_FAIL.getCode(),StatusEnum.RET_INSERT_FAIL.getData());
        }
    }

    @Override
    @Transactional
    public Map update(Epidemic epidemic) {
        Epidemic oldEpidemic = epidemicMapper.findEpidemic(epidemic.getEpidemicId());
        //直接修改最高级行政地区，不循环调用
        if ((epidemicMapper.update(epidemic) == 1) && (epidemic.getHigherAreaNumber() != 0)) {
            //抽出方法，计算每项需要相加的值，return epidemic；
            cumulativeEpidemic.setConfirmed(epidemic.getConfirmed() - oldEpidemic.getConfirmed());
            cumulativeEpidemic.setSuspected(epidemic.getSuspected() - oldEpidemic.getSuspected());
            cumulativeEpidemic.setDeath(epidemic.getDeath() - oldEpidemic.getDeath());
            cumulativeEpidemic.setCured(epidemic.getCured() - oldEpidemic.getCured());
            //调用递归修改，循环修改到最上级行政地区
            if (updateArea(epidemic)) {
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
