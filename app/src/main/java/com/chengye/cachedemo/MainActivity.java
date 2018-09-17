package com.chengye.cachedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * lruCache和DiskLruCache
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getExternalCacheDir().getPath();
    }


}
