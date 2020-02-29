package com.example.recovery.framework.util;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * @ClassName: CommUtil
 * @Description: 公共工具类，提供判断参数是否为空
 * @Author: MSI
 * @Date: 2018/12/23 16:48
 * @Vresion: 1.0.0
 **/
public class CommUtil {

    /**
     * @Author MSI
     * @Description 判断变量是否为空值,如果有空值则返回true，没有就返回false
     * @Date 2018/12/23 17:58
     * @Param [values]
     * @return boolean
     **/
    public static boolean isNullValue(Object...values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * @Author MSI
     * @Description 判断String 类型是否有空字符串，如果有就返回true，没有就返回false
     * @Content  调整算法判断顺序，修复bug
     * @Date 2019/1/9 18:49
     * @Param [values]
     * @return boolean 
     **/
    public static boolean isNullString(String...values) {
        // 1.字符串为空， 2.空字符串
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null || "".equals(values[i].trim()) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断str是否为数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str){
        return NumberUtils.isNumber(str);
    }

    /**
     * @Author MSI
     * @Description 分割ID字符串，产生ID集合
     * @Content: 1.对ID字符串进行分割，产生ID数组
     *            2.遍历数组，装进List集合，并返回
     * @Date 2019/3/7 11:40
     * @Param [ids] 多个ID组成的字符串，ID之间用 '，'隔开 ，如 1，2，3
     * @return java.util.List<java.lang.Long>
     **/
    public static List<Long> splitString(String ids) {
        List<Long> longList = new LinkedList<>();
        //1.按 "," 分割字符串，产生字符串数组
        String[] idArray = ids.split(",");
        //2.遍历字符串数组
        for (int i = 0; i < idArray.length; i++) {
            //2.1转换成Long类型，加入到Set集合中
            longList.add(Long.valueOf(idArray[i]));
        }
        //3.返回集合
        return longList;
    }

    /**
     * @Author MSI
     * @Description 字符串转换Date 方法，默认格式为 "yyyy-MM-dd HH:mm:ss"
     * @Content: TODO
     * @Date 2019/3/9 17:21
     * @Param [stringDate]
     * @return java.util.Date
     **/
    public static Date stringToDate(String stringDate,String template) {
        SimpleDateFormat formatter = new SimpleDateFormat(template);
        ParsePosition pos = new ParsePosition(0);
        Date date = formatter.parse(stringDate, pos);
        return date;
    }

    /**
     * @Author MSI
     * @Description Date 转换字符串方法，默认格式为 "yyyy-MM-dd HH:mm:ss"  简化
     * @Content: TODO
     * @Date 2019/3/9 17:20
     * @Param [date]
     * @return java.lang.String
     **/
    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = sdf.format(date);
        System.out.println("stringDate : " + stringDate);
        return stringDate;
    }

    public static String simplifyDateString(String dateString) {
        String simpilfyDate;
        simpilfyDate = dateString.substring(1,11) + " " + dateString.substring(12,dateString.length()-6);
        System.out.println("simpilfyDate = " + simpilfyDate);
        return simpilfyDate;
    }

    /**
     * @Author MSI
     * @Description 获得指定时间的前几个月的时间
     * @Content: TODO
     * @Date 2019/5/8 13:30
     * @Param [date, month] date：需要改变的日期字符串，month：回退的几个月
     * @return java.lang.String 返回处理过的时间字符串
     **/
    public static String getDateMonth(Date date,int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH,-month);
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
        String dateString = s.format(calendar.getTime());
        return dateString;

    }


    /**
     * 复制源对象和目标对象的属性值
     *
     */
    public static void copyAsm(Object source, Object target) {
        // 得到对象的Class
        Class<? extends Object> sourceClass = source.getClass();
        // 得到对象的Class
        Class<? extends Object> targetClass = target.getClass();

        MethodAccess sourceMethodAccess = MethodAccess.get(sourceClass);
        MethodAccess targetMethodAccess = MethodAccess.get(targetClass);
        String[] sourceMethods = sourceMethodAccess.getMethodNames();
        String[] targetMethods = targetMethodAccess.getMethodNames();

        for (int index = 0; index < sourceMethods.length; index++) {
            if (!"get".equalsIgnoreCase(sourceMethods[index].substring(0, 3))) {
                continue;
            }
            Object value = sourceMethodAccess.invoke(source, sourceMethods[index]);
            if (value == null) {
                continue;
            }

            if (value instanceof String) {
                String strValue = ((String) value).trim();
                if (strValue.isEmpty()) {
                    continue;
                }
            }

            /**属性名**/
            String name = sourceMethods[index].substring(3).replaceAll("_", "");
            String targetSetMethodName = findMethodByName(targetMethods, "set" + name);
            if (targetSetMethodName == null) {
                continue;
            }
            targetMethodAccess.invoke(target, targetSetMethodName, value);
        }
    }

    private static String findMethodByName(String[] methodNames, String name) {
        for (int index = 0; index < methodNames.length; index++) {
            String result = methodNames[index];
            if (result.replaceAll("_", "").toLowerCase().equalsIgnoreCase(name)) {
                return result;
            }
        }
        return null;
    }

    public static boolean objectEquals(Object objA, Object objB) {
        if (objA == null && objB == null) {
            return true;
        } else if (objA == null || objB == null) {
            return false;
        }
        if (!objA.getClass().isInstance(objB)) {
            return false;
        }
        return objA.equals(objB);
    }
}
