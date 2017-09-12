package com.example.dellc.qq.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by dellc on 2017/9/12.
 */

public class ThreadUtils {
    public static final String TAG = "ThreadUtils";

    private static Executor sExecutor= Executors.newSingleThreadExecutor();

    /**
     * 创建一个绑定主线程的Looper的handler
     * 处理message或者runnable都会在主线程执行
     */
    private static Handler sHandler=new Handler(Looper.getMainLooper());

    /**
     * 在单线程的线程池中运行runnable
     * @param runnable
     */
    public static void runOnBackgroundThread(Runnable runnable){
        sExecutor.execute(runnable);
    }

    public static void runOnUiThread(Runnable runnable){
        sHandler.post(runnable);
    }

}
