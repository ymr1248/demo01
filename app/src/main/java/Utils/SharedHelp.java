package Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/20 0020.
 */
public class SharedHelp {


    private Context mContext;

    public SharedHelp() {
    }

    public SharedHelp(Context mContext) {
        this.mContext = mContext;
    }




    //定义一个保存数据的方法
    public void saveclock(String clockName, String StartTime, String clockCountent, String remindMode, int shockNum ) {
        SharedPreferences sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("clockName", clockName);
        editor.putString("clockCountent", clockCountent);
        editor.putInt("shockNum", 0);
        editor.putString("remindMode", remindMode);
        editor.putString("StartTime", StartTime);
        editor.commit();
        Toast.makeText(mContext, "信息已写入SharedPreference中", Toast.LENGTH_SHORT).show();
    }
    public void save(String StartTime, String clockName ) {
        SharedPreferences sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(StartTime, clockName);

        editor.commit();
        Toast.makeText(mContext, "信息已写入SharedPreference中", Toast.LENGTH_SHORT).show();
    }
    //定义一个读取SP文件的方法
    public Map<String, String> read(String startTime) {
        Map<String, String> data = new HashMap<String, String>();
        SharedPreferences sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
        Log.i("Helen", "read: "+sp);
        data.put("startTime", sp.getString(startTime, ""));
        return data;
    }
}
