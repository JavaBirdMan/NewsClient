package utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by FBI on 2015/12/18.
 */
public class SharePrefencesUtils {

    public static SharedPreferences config;

    public static void saveBoolean(Context context,String key,boolean defautlValue){
        if (config==null){
            config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }config.edit().putBoolean(key,defautlValue).commit();
    }


    public static boolean getBoolean(Context context,String key,boolean defaultValue){
        if (config==null){
             config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return config.getBoolean(key,defaultValue);
    }


    public static void saveString(Context context,String key,String defautlValue){
        if (config==null){
            config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }config.edit().putString(key, defautlValue).commit();
    }


    public static String getString(Context context,String key,String defaultValue){
        if (config==null){
            config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return config.getString(key, defaultValue);
    }


}


