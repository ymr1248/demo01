package Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具类
 * 
 * @author
 *
 */
public class DateUtil {
	/**
	 * 获取当前的年
	 * 
	 * @return
	 */
	public static String getYear() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		final Date d = new Date();
		c.setTime(d);
		return c.get(Calendar.YEAR) + "";
	}

	/**
	 * 获取当前的月
	 * 
	 * @return
	 */
	public static String getMonth() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		final Date d = new Date();
		c.setTime(d);
		return (c.get(Calendar.MONTH) + 1) < 10 ? "0" + (c.get(Calendar.MONTH) + 1) : "" + (c.get(Calendar.MONTH) + 1);
	}

	/**
	 * 获取当前的日
	 * 
	 * @return
	 */
	public static String getDay() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		final Date d = new Date();
		c.setTime(d);
		return c.get(Calendar.DATE) < 10 ? "0" + c.get(Calendar.DATE) : "" + c.get(Calendar.DATE);
	}

	/**
	 * 获取当前的小时
	 * 
	 * @return
	 */
	public static String getHour() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		final Date d = new Date();
		c.setTime(d);
		return c.get(Calendar.HOUR_OF_DAY) < 10 ? "0" + c.get(Calendar.HOUR_OF_DAY) : "" + c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取当前的分
	 * 
	 * @return
	 */
	public static String getMinute() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		final Date d = new Date();
		c.setTime(d);
		return c.get(Calendar.MINUTE) < 10 ? "0" + c.get(Calendar.MINUTE) : "" + c.get(Calendar.MINUTE);
	}

	/**
	 * 获取当前的秒
	 * 
	 * @return
	 */
	public static String getSeconds() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		final Date d = new Date();
		c.setTime(d);
		return c.get(Calendar.SECOND) < 10 ? "0" + c.get(Calendar.SECOND) : "" + c.get(Calendar.SECOND);
	}

	/**
	 * 获取中文星期
	 * 
	 * @return
	 */
	public static String getWeek() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		final Date d = new Date();
		c.setTime(d);
		String ret = "星期";
		switch (c.get(Calendar.DAY_OF_WEEK) - 1) {
		case 0: {
			ret += "日";
			break;
		}
		case 1: {
			ret += "一";
			break;
		}
		case 2: {
			ret += "二";
			break;
		}
		case 3: {
			ret += "三";
			break;
		}
		case 4: {
			ret += "四";
			break;
		}
		case 5: {
			ret += "五";
			break;
		}
		case 6: {
			ret += "六";
			break;
		}
		}
		return ret;
	}

	/**
	 * 获取当前时间
	 * 
	 * YYYY-MM-DD HH:MM:SS
	 * 
	 * @return
	 */
	public static String getCurDate() {
		return getYear() + "-" + getMonth() + "-" + getDay() + " " + getHour() + ":" + getMinute() + ":" + getSeconds();
	}

	/**
	 * 获取当前时间
	 * 
	 * YYYY-MM-DD HH:MM
	 * 
	 * @return
	 */
	public static String getCurDateYMDHM() {
		return getYear() + "-" + getMonth() + "-" + getDay() + " " + getHour() + ":" + getMinute();
	}

	/**
	 * 获取当前日期
	 * 
	 * YYYY-MM-DD
	 * 
	 * @return
	 */
	public static String getCurDateYMD() {
		return getYear() + "-" + getMonth() + "-" + getDay();
	}

	/**
	 * 获取当前时间
	 * 
	 * HH:MM:SS
	 * 
	 * @return
	 */
	public static String getCurDateHMS() {
		return getHour() + ":" + getMinute() + ":" + getSeconds();
	}

	/**
	 * 判断时间为白天还是晚上
	 * 
	 * @param time
	 *            HH:mi/HH:mi:ss
	 * @return 晚上=true,白天=false
	 */
	public static boolean isDayOrNight(String time) {
		try {
			String strHour = time.substring(0, time.indexOf(":"));
			int hour = Integer.parseInt(strHour);
			if ((hour >= 0 && hour < 6) || (hour >= 18 && hour < 24)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	/**
	 * 计算两个日期之间相差多少分钟
	 * 
	 * @param startDate
	 *            日期格式:yyyy-mm-dd hh:mi:ss 或者yyyy-mm-dd hh:mi
	 * @param endDate
	 *            日期格式:yyyy-mm-dd hh:mi:ss 或者yyyy-mm-dd hh:mi
	 * @return N分钟
	 */
	public static long getDateDiff(String startDate, String endDate) {
		Calendar cal1 = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		cal1.set(Calendar.YEAR, Integer.parseInt(startDate.substring(0, 4)));
		cal1.set(Calendar.MONTH, Integer.parseInt(startDate.substring(5, 7)));
		cal1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(startDate.substring(8, 10)));
		cal1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startDate.substring(11, 13)));
		cal1.set(Calendar.MINUTE, Integer.parseInt(startDate.substring(14, 16)));

		Calendar cal2 = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		cal2.set(Calendar.YEAR, Integer.parseInt(endDate.substring(0, 4)));
		cal2.set(Calendar.MONTH, Integer.parseInt(endDate.substring(5, 7)));
		cal2.set(Calendar.DAY_OF_MONTH, Integer.parseInt(endDate.substring(8, 10)));
		cal2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endDate.substring(11, 13)));
		cal2.set(Calendar.MINUTE, Integer.parseInt(endDate.substring(14, 16)));

		long result = (cal2.getTimeInMillis() - cal1.getTimeInMillis()) / (60 * 1000);
		return result;
	}

	/**
	 * 获取某个日期时间增加N分钟后的日期时间
	 * 
	 * @param date
	 *            日期格式:yyyy-mm-dd hh:mi:ss 或者yyyy-mm-dd hh:mi
	 * @param minute
	 *            分钟
	 * @return yyyy-mm-dd hh:mi:ss
	 */
	public static String getDateAddMinuts(String date, int minute) {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		c.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
		c.set(Calendar.MONTH, Integer.parseInt(date.substring(5, 7)));
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(8, 10)));
		c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date.substring(11, 13)));
		c.set(Calendar.MINUTE, Integer.parseInt(date.substring(14, 16)));
		c.set(Calendar.SECOND, 0);

		c.add(Calendar.MINUTE, minute);

		int yyyy = c.get(Calendar.YEAR);
		int mm = c.get(Calendar.MONTH);
		int dd = c.get(Calendar.DAY_OF_MONTH);
		int hh = c.get(Calendar.HOUR_OF_DAY);
		int mi = c.get(Calendar.MINUTE);
		int ss = c.get(Calendar.SECOND);

		String result = yyyy + "-" + (mm < 10 ? ("0" + mm) : ("" + mm)) + "-" + (dd < 10 ? ("0" + dd) : ("" + dd)) + " "
				+ (hh < 10 ? ("0" + hh) : ("" + hh)) + ":" + (mi < 10 ? ("0" + mi) : ("" + mi)) + ":"
				+ (ss < 10 ? ("0" + ss) : ("" + ss));
		return result;
	}
}
