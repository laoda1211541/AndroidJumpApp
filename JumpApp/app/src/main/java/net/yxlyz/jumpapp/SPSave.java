package net.yxlyz.jumpapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;
/**
 * @Author Saraph1nes
 * @Date 2020/5/21 14:30
 * @Version 1.0
 */
public class SPSave {
    //保存信息
    public static boolean savaUserInfo(Context context, String number, String password) {
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        //文件位于data/data/<packagename>/share_prefs文件夹中
        //写入的时候需要Editor
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("uname",number);
        edit.putString("pwd",password);
        edit.commit();
        return  true;
    }

    //获取信息
    public static Map<String,String> getUserInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        //取值的时候不需要Editor
        String number = sp.getString("uname",null);
        String password = sp.getString("pwd",null);
        Map<String,String> userMap = new HashMap<String,String>();
        userMap.put("uname",number);
        userMap.put("pwd",password);
        return userMap;
    }
}
