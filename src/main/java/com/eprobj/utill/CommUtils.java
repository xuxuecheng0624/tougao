package com.eprobj.utill;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * Created by xxc on 2019/9/10
 */
public class CommUtils {

    public static final String format = "yyyy-MM-dd HH:mm:ss";

    public static String getTimeStr() {
        Calendar cal = Calendar.getInstance();
        return new SimpleDateFormat(format).format(cal.getTime());
    }
    /**
     * 时间戳转换成时间
     * @param date 传入的时间戳
     * @return 返回格式化时间
     */
    public static String timeStampToTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 处理时间，返回访问间隔分钟数
     *
     * @return
     */
    public static int getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return (int) (min + (day * 24 + hour) * 60);
    }

    /**
     * 删除所有的HTML标签
     *
     * @param source 需要进行除HTML的文本
     * @return
     */
    public static String deleteAllHTMLTag(String source) {

        if(source == null) {
            return "";
        }

        String s = source;
        /** 删除普通标签  */
        s = s.replaceAll("<(S*?)[^>]*>.*?|<.*? />", "");
        /** 删除转义字符 */
        s = s.replaceAll("&.{2,6}?;", "");
        return s;
    }
}