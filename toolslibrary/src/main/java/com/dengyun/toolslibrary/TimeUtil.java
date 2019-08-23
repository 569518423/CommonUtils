package com.dengyun.toolslibrary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author bingyi
 * @description desc
 * @date 2019/8/23 9:53.
 */
public class TimeUtil {
    /**
     * 获取网络时间
     */
    public static Long getWebsiteDatetime() {
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));

        return stringToLongTime(dff.format(new Date()));
    }

    /**
     * 把String类型的事件转换为毫秒值 "yyyy-MM-dd HH:mm:ss"
     */
    public static Long stringToLongTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long millionSeconds = 0;//毫秒
        try {
            return millionSeconds = sdf.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 把毫秒值 转化为"yyyy-MM-dd HH:mm:ss"
     */
    public static String longTimeToStringDate(long time) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(date);


    }
    /**
     * 把毫秒值 转化为"yyyy/MM/dd"" 优惠券显示使用
     */
    public static String longTimeToLimitDate(long time) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date).substring(2,10);

    }

    /**
     * 若是当天转化为hh:mm   若不是当天把毫秒值 转化为"yyyy-MM-dd HH:mm"
     */
    public static String longTimeToStringDateAuto(long time) {
        SimpleDateFormat sdf = null;
        if (longTimeToStringDate(time).substring(0, 11).equals(longTimeToStringDate(getWebsiteDatetime()).substring(0, 11))) {
            sdf = new SimpleDateFormat("HH:mm");
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
        Date date = new Date(time);

        return sdf.format(date);
    }

    /**
     * 把Long类型的毫秒值转换为
     *
     * @param counttime HH时mm分ss秒
     * @return
     */
    public static String longToStringTime(long counttime) {
        long hours = counttime / (1000 * 60 * 60);
        long minutes = (counttime - hours * (1000 * 60 * 60)) / (1000 * 60);
        long second = (counttime - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / (1000);
        String hour = hours >= 10 ? hours + "" : "0" + hours;
        String minute = minutes >= 10 ? minutes + "" : "0" + minutes;
        String secon = second >= 10 ? second + "" : "0" + second;

        return hour + ":" + minute + ":" + secon;

    }

    /**
     * 把Long类型的毫秒值转换为
     *
     * @param counttime dd天 HH时mm分ss秒
     * @return
     */
    public static String longToStringTimeWithDay(long counttime) {
        if (counttime <= 0) {
            return "";
        }
        counttime = counttime/1000;
        int days = (int) (counttime / (  60 * 60 * 24));
        long hours = (counttime - days * ( 60 * 60 * 24)) / ( 60 * 60);
        long minutes = ((counttime - days * (  60 * 60 * 24)) - hours * (  60 * 60)) / ( 60);
        long second = ((counttime - days * (  60 * 60 * 24)) - hours * (  60 * 60) - minutes * (  60)) ;
        String day = days >= 10 ? days + "" : "0" + days;
        String hour = hours >= 10 ? hours + "" : "0" + hours;
        String minute = minutes >= 10 ? minutes + "" : "0" + minutes;
        String secon = second >= 10 ? second + "" : "0" + second;

        return day + "天" + hour + "小时" + minute + "分" + secon + "秒";

    }

    /**
     * 从Long类型的毫秒值转化成天数
     *
     * @return
     */
    public static int getDayFromTime(long counttime) {
        long hours = counttime / (1000 * 60 * 60);
        String hour = hours >= 10 ? hours + "" : "0" + hours;
        return (int) (Integer.parseInt(hour) / 24);
    }

    /**
     * 用来防止1s内同时点击两次
     *
     * @param context
     * @return
     */
    static long mLastClickTime = 0;

    public static boolean isDouClick() {
        long nowTime = System.currentTimeMillis();
        if (nowTime - mLastClickTime < 1000) {
            mLastClickTime = nowTime;
            return true;
        } else {
            mLastClickTime = nowTime;
            return false;
        }
    }
}
