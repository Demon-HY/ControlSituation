package com.control.situation.utils.datetime;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间相关操作类
 *
 * Created by demon on 2017/7/1 0001.
 */
public class DateUtils {

    private static Logger log = LogManager.getLogger(com.control.situation.utils.datetime.DateUtils.class);

    private DateUtils() { }

    /**  一秒=1000毫秒 */
    public static final long SECOND = 1000L;
    public final static long MONUTE = 60 * 1000;// 1分钟
    public final static long HOUR = 60 * MONUTE;// 1小时
    public final static long DAY = 24 * HOUR;// 1天
    public final static long MONTH = 31 * DAY;// 月
    public final static long year = 12 * MONTH;// 年

    public final static String DATE_SEQUENCE = "yyyyMMddHHmmssSSS";
    /** 通用的格式 */
    public static final String GENERAL_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /** 截止到分钟格式 */
    public static final String MINT_PATTERN = "yyyy-MM-dd HH:mm";
    /** 截止到小时格式 */
    public static final String HOUR_PATTERN = "yyyy-MM-dd HH";
    /** 截止到天格式 */
    public static final String DAY_PATTERN = "yyyy-MM-dd";
    /** 只返回时间格式 */
    public static final String TIME_PATTERN = "HH:mm:ss";
    /** 天数差 */
    public static final int DIFF_DAY = 1;
    /** 小时差 */
    public static final int DIFF_HOUR = 2;
    /** 分钟差 */
    public static final int DIFF_MINUTE = 3;
    /** 秒数差 */
    public static final int DIFF_SECOND = 4;
    /** 分钟毫秒数 */
    public static final long MINT_MILLIS = 60 * SECOND;
    /** 小时毫秒数 */
    public static final long HOUR_MILLIS = 60 * MINT_MILLIS;
    /** 天毫秒数 */
    public static final long DAY_MILLIS = 24 * HOUR_MILLIS;

    /**
     * 获取时间序列，格式：
     * @return yyyyMMddHHmmssSSS
     */
    public static String getDateSequence() {
        return new SimpleDateFormat(DATE_SEQUENCE).format(new Date());
    }

    /**
     * 将 Date 类型时间转成 unix 时间戳
     *
     * @param date java.util.Date
     * @return unix 时间戳
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * 将 unix 时间戳格式化
     *
     * @param time
     *            unix 时间戳
     * @return 时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static String longTimeToString(long time) {
        return longTimeToString(time, GENERAL_PATTERN);
    }

    /**
     * 将 unix 时间戳格式化成指定格式
     *
     * @param time
     *            unix 时间戳
     * @param format
     *            常用类型如下 <blockquote> <br>
     *            yyyy-MM-dd HH:mm:ss <br>
     *            yyyy-MM-dd <br>
     *            HH:mm:ss <br>
     *            HH:mm:ss yyyy-MM-dd <br>
     *            MM-dd-yyyy HH:mm:ss </blockquote>
     * @return 格式化时间
     */
    public static String longTimeToString(long time, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        return formatter.format(time);
    }

    /**
     * 将 Date 时间格式化成指定格式
     *
     * @param date
     *            java.util.Date
     */
    public static String dateToString(Date date) {
        return dateToString(date, GENERAL_PATTERN);
    }

    /**
     * 将 Date 时间格式化成指定格式
     *
     * @param date
     *            java.util.Date
     * @param format
     *            常用类型如下 <blockquote> <br>
     *            yyyy-MM-dd HH:mm:ss <br>
     *            yyyy-MM-dd <br>
     *            HH:mm:ss <br>
     *            HH:mm:ss yyyy-MM-dd <br>
     *            MM-dd-yyyy HH:mm:ss </blockquote>
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        return formatter.format(date);
    }

    /**
     * 获取当前系统时间
     * @return java.util.Date
     */
    public static Date getCurrentTimeDate() {
        return new Date();
    }

    /**
     * 获取当前系统时间
     *
     * @return unix 时间戳
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取时间距离当前时间的描述
     * @param date java.util.Date
     * @return
     */
    public static String getTimeFormatText(Long date) {
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date;
        long r;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > MONTH) {
            r = (diff / MONTH);
            return r + "个月前";
        }
        if (diff > DAY) {
            r = (diff / DAY);
            return r + "天前";
        }
        if (diff > HOUR) {
            r = (diff / HOUR);
            return r + "个小时前";
        }
        if (diff > MONUTE) {
            r = (diff / MONUTE);
            return r + "分钟前";
        }
        return "刚刚";
    }

//    /**
//     * 将时间戳转换成当天0点
//     * @param timestamp unix 时间戳
//     * @return timestamp
//     */
//    public static long getDayBegin(long timestamp) {
//        String format = "yyyy-MM-DD";
//        String toDayString = new SimpleDateFormat(format).format(new Date(timestamp));
//        Date toDay;
//        try {
//            toDay = parseDate(toDayString, format);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return toDay.getTime();
//    }

    /**
     * 计算两个时间差
     *
     * @param diffType
     *            1:day 2:hour 3:minute 4:second
     *
     *            start or end time string format @see GENERAL_PATTERN yyyy-MM-dd HH:mm:ss
     *
     * @param start
     * @param end
     * @return long
     */
    public static long diffDate(int diffType, String start, String end) {
        if (DIFF_DAY == diffType)
            return doDiff(start, end, "yyyy-MM-dd", DAY_MILLIS);
        else if (DIFF_HOUR == diffType)
            return doDiff(start, end, "yyyy-MM-dd HH", HOUR_MILLIS);
        else if (DIFF_MINUTE == diffType)
            return doDiff(start, end, "yyyy-MM-dd HH:mm", MINT_MILLIS);
        else if (DIFF_SECOND == diffType)
            return doDiff(start, end, GENERAL_PATTERN, SECOND);
        return 0;
    }

    /**
     * 两个时间的秒数差
     *
     * @param start
     * @param end
     * @return long
     */
    private static long doDiff(String start, String end, String pattern, long millis) {
        Date startDate = null;
        Date endDate = null;
        try {
            SimpleDateFormat noopFormat = new SimpleDateFormat();
            noopFormat.applyPattern(pattern);
            startDate = noopFormat.parse(start);
            endDate = noopFormat.parse(end);
        } catch (ParseException e) {
            log.error("diff date for diff calc error", e);
            throw new RuntimeException(e);
        }
        return (endDate.getTime() - startDate.getTime()) / millis;
    }

    /**
     * 根据生日计算年龄
     *
     * @param birthDay
     * @return
     * @throws Exception
     */
    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }

        return age;
    }

    //获取指定年月的总天数
    public static int getLastDay(int year, int month) {
        int day = 1;
        Calendar cal = Calendar.getInstance();
        cal.set(year,month - 1,day);
        int last = cal.getActualMaximum(Calendar.DATE);
        return last;
    }

    //获取指定年月的日期
    public static List<String> getDatesByMonth(int year, int month){
        List<String> list=new ArrayList<String>();
        String yyyy=year+"";
        String mm=month+"";
        String dd;
        if(month<10){
            mm="0"+month;
        }
        int num=getLastDay(year, month);
        for(int i=1;i<=num;i++){
            if(i<10){
                dd="0"+i;
            }else{
                dd=i+"";
            }
            list.add(yyyy+"-"+mm+"-"+dd);
        }
        return list;
    }

    /**
     * 检验时间格式
     * @param str
     * @param format "yyyy/MM/dd HH:mm:ss" "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static boolean isValidDate(String str, String format) {
        boolean convertSuccess=true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(str);
        } catch (ParseException e) {
            convertSuccess=false;
        }
        return convertSuccess;
    }

    /**
     * 获取一周前的日期
     */
    public static String getWeekdayBeforeDate() {
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Calendar c = Calendar.getInstance();
//
//		//过去七天
//		c.setTime(new Date());
//		c.add(Calendar.DATE, - 7);
//		Date d = c.getTime();
//		return format.format(d);
        return getBeforeDate(Calendar.DATE, -7);
    }

    /**
     * 获取一月前的日期
     */
    public static String getOneMonthBeforeDate() {
        return getBeforeDate(Calendar.MONTH, -1);
    }

    /**
     * 获取三月前的日期
     */
    public static String getThreeMonthBeforeDate() {
        return getBeforeDate(Calendar.MONTH, -3);
    }

    /**
     * 获取六月前的日期
     */
    public static String getSixMonthBeforeDate() {
        return getBeforeDate(Calendar.MONTH, -6);
    }

    /**
     * 获取一年前的日期
     */
    public static String getOneYearBeforeDate() {
        return getBeforeDate(Calendar.YEAR, -1);
    }

    /**
     * 获取当前日期往前的天数
     * @param dateType 日期类型，用 Calendar
     * @param amount 往前的时间
     * @return
     */
    private static String getBeforeDate(int dateType, int amount) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();

        c.setTime(new Date());
        c.add(dateType, amount);
        Date m = c.getTime();
        return format.format(m);
    }

    /**
     * 获取当前日期往后的天数
     * @param dateType 日期类型，用 Calendar
     * @param amount 往前的时间
     * @return
     */
    public static Date getAfterDate(Date time, int dateType, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(dateType, amount);
        return c.getTime();
    }

    /**
     * 测试
     */
    static class Tester {
        public static void main(String[] args) {
        }
    }
}
