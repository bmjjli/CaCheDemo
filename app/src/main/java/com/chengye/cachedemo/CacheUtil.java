package com.chengye.cachedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2018/9/17.
 */

public class CacheUtil {

    private static final long SDCARD_CACHE_MEMORY_MAX = 10 * 1024 * 1024;

    private LruCache<String, Bitmap> mLruCache;
    private DiskLruCache mDiskLruCache;

    private Context mAppContext;

    private CacheUtil() {
        initLruCache();
        initDiskLruCache();
    }


    private void initLruCache() {
        // 获取应用程序最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;

        // 设置图片缓存大小为程序最大可用内存的1/8
        mLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };

    }

    private void initDiskLruCache() {

        File file = new File(SDCardUtil.getDiskLruCaChePath(mAppContext, "image"));
        int appVersion = AppUtil.getAppVersion(mAppContext);

        //jvm似乎无法优化try catch代码块中的代码
        try {
            mDiskLruCache = DiskLruCache.open(file, appVersion, 1, SDCARD_CACHE_MEMORY_MAX);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public CacheUtil getInstance(Context context) {
        mAppContext = context.getApplicationContext();
        return CacheUtilInstance.SCacheUtil;
    }

    private static class CacheUtilInstance {
         final static CacheUtil SCacheUtil = new CacheUtil();
    }

}
