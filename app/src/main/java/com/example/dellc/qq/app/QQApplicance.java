package com.example.dellc.qq.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.dellc.qq.BuildConfig;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import java.util.Iterator;
import java.util.List;

/**
 * Created by dellc on 2017/9/11.
 */

public class QQApplicance extends Application {
    public static final String TAG="QQApplicance";

    /*
    * app有多少个进程，onCreate方法执行多少次
    */
    @Override
    public void onCreate() {
        super.onCreate();
        initEaseMob();
    }

    /*
     * 初始化环信SDK
     */
    private void initEaseMob() {
        int pid = android.os.Process.myPid();//获取当前进程(Process)的id
        String processAppName = getAppName(pid);//获取到当前进程的名
        //默认：进程的app进程名是包名

// 如果APP启用了远程的service，此application:onCreate会被调用2次
// 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
// 默认的APP会在以包名为默认的process name下运行，
// 如果查到的process name不是APP的process name就立即返回

        if (processAppName == null ||!processAppName.equalsIgnoreCase(getPackageName())) {
            Log.e(TAG, "enter the service process!");

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }

        EMOptions options = new EMOptions();
// 当接受好友请求时，默认添加
        options.setAcceptInvitationAlways(true);
//初始化 只在默认进程初始化一次
        EMClient.getInstance().init(getApplicationContext(), options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        if (BuildConfig.DEBUG) {
            EMClient.getInstance().setDebugMode(true);
        }
    }


/*
获取getAppNamed方法app进程名字
*/
    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();//获取到正在运行的app进程
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        //
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }
}
