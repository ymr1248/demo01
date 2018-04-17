package com.yongxing.activity.demo01.service;

import android.util.Log;


/**
 * Created by Administrator on 2018-01-30.
 */

public class HttpUtil {

    public static String getURl(final String string){
//        "http://192.168.0.107:8080/monitoring/spaceUseList"
        final String[] json = new String[1];
        Log.d("YMR", "1WWWWWWWWWWWWWWWWWWWWWWWWonCreate: "+json[0]);
        new Thread() {
            public void run() {
                Log.d("YMR", "2WWWWWWWWWWWWWWWWWWWWWWWWonCreate: "+json[0]);
                {
                    try {
                     json[0] = HtmlService.getHtml(string);
                        Log.d("YMR", "3WWWWWWWWWWWWWWWWWWWWWWWWonCreate: "+json[0]);
                    } catch (Exception e) {
                        Log.d("YMR", "4WWWWWWWWWWWWWWWWWWWWWWWWonCreate: "+e);
                        e.printStackTrace();
                    }

                }
            }

        }.start();
        return   json[0];
    }
}
