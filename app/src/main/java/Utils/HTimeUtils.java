package Utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HTimeUtils {

    //毫秒转秒
    public static String long2String(long time){

        //毫秒转秒
        int sec = (int) time / 1000 ;
        int min = sec / 60 ;	//分钟
        sec = sec % 60 ;		//秒
        if(min < 10){	//分钟补0
            if(sec < 10){	//秒补0
                return "0"+min+":0"+sec;
            }else{
                return "0"+min+":"+sec;
            }
        }else{
            if(sec < 10){	//秒补0
                return min+":0"+sec;
            }else{
                return min+":"+sec;
            }
        }

    }
    public static String StringTolong(String time){
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        long  millionSeconds=0;
        Date date = null;
        Calendar c = Calendar.getInstance();
        try {
           millionSeconds = format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        millionSeconds=millionSeconds-1000;
        c.setTimeInMillis(millionSeconds);
        date = c.getTime();
      String st = format.format(date);
        //毫秒转秒
        int sec = (int) 1 / 1000 ;
        int min = sec / 60 ;	//分钟
        sec = sec % 60 ;		//秒
        if(min < 10){	//分钟补0
            if(sec < 10){	//秒补0
//                return "0"+min+":0"+sec;
            }else{
//                return "0"+min+":"+sec;
            }
        }else{
            if(sec < 10){	//秒补0
//                return min+":0"+sec;
            }else{
//                return min+":"+sec;
            }
        }
return st;
    }

    /**
     * 返回当前时间的格式为 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(System.currentTimeMillis());
    }


}
