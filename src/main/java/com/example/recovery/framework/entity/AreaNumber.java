package com.example.recovery.framework.entity;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

/**
 * Class {@code Object} is .
 *
 * @author MSI
 * @version 1.0
 */
public class AreaNumber {

    /**
     * 行政地区对应地区编码
     * 数据量较小采用Map 存放；若数据量较大（全国、涉及区县）可将行政地区信息配置在数据库表中
     */
    public final static Map AREA_NUMBER_MAP = new HashMap();
    static {
        AREA_NUMBER_MAP.put("350000","福建省");
        AREA_NUMBER_MAP.put("350100","福州市");
        AREA_NUMBER_MAP.put("350200","厦门市");
        AREA_NUMBER_MAP.put("350300","莆田市");
        AREA_NUMBER_MAP.put("350400","三明市");
        AREA_NUMBER_MAP.put("350500","泉州市");
        AREA_NUMBER_MAP.put("350600","漳州市");
        AREA_NUMBER_MAP.put("350700","南平市");
        AREA_NUMBER_MAP.put("350800","龙岩市");
        AREA_NUMBER_MAP.put("350900","宁德市");
    }

    public final static Map HIGHER_AREA_NUMBER_MAP = new HashMap();
    static {
        HIGHER_AREA_NUMBER_MAP.put("350000",0);
        HIGHER_AREA_NUMBER_MAP.put("350100",350000);
        HIGHER_AREA_NUMBER_MAP.put("350200",350000);
        HIGHER_AREA_NUMBER_MAP.put("350300",350000);
        HIGHER_AREA_NUMBER_MAP.put("350400",350000);
        HIGHER_AREA_NUMBER_MAP.put("350500",350000);
        HIGHER_AREA_NUMBER_MAP.put("350600",350000);
        HIGHER_AREA_NUMBER_MAP.put("350700",350000);
        HIGHER_AREA_NUMBER_MAP.put("350800",350000);
        HIGHER_AREA_NUMBER_MAP.put("350900",350000);
    }


}
