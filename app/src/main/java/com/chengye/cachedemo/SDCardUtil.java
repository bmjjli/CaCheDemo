package com.chengye.cachedemo;

import android.content.Context;
import android.os.Environment;

import java.io.File;


public class SDCardUtil {

    public static boolean isSDCardMounted() {
        return Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED) || !Environment.isExternalStorageRemovable();
    }


    public static String getDiskLruCaChePath(Context context, String fileName) {

        String SDCachePath = isSDCardMounted()
                ? context.getExternalCacheDir().getPath()// /sdcard/Android/data/<application package>/cache
                : context.getCacheDir().getPath()// /data/data/<application package>/cache
                ;

        return SDCachePath + File.separator + fileName;
    }

}
