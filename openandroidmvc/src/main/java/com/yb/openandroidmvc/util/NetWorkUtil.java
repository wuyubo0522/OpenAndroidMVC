package com.yb.openandroidmvc.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.io.IOException;

/**
 * 类说明：网络检测工具类
 *
 * @author 裕博
 * Date: 2019/6/5
 * Time: 16:59
 */
public class NetWorkUtil {
    /**
     * 没有网络连接
     */
    public static final int NETWORN_NONE = 0;
    /**
     * wifi连接
     */
    public static final int NETWORN_WIFI = 1;
    /**
     * 手机网络数据连接类型2G
     */
    public static final int NETWORN_2G = 2;
    /**
     * 手机网络数据连接类型3G
     */
    public static final int NETWORN_3G = 3;
    /**
     * 手机网络数据连接类型4G
     */
    public static final int NETWORN_4G = 4;
    /**
     * 未知的手机网络
     */
    public static final int NETWORN_MOBILE = 5;

    /**
     * 获取网络服务类型
     *
     * @param context 上下文
     */
    public static int getNetworkType(Context context) {
        // 获取系统的网络服务器
        ConnectivityManager connectManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // 如果当前没有网络
        if (null == connectManager) {
            return NETWORN_NONE;
        }

        // 获取当前网络类型，如果为空，返回无网络
        NetworkInfo activeNetInfo = connectManager.getActiveNetworkInfo();
        if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
            return NETWORN_NONE;
        }

        // 判断当前网络连接是不是WIFI
        NetworkInfo wifiInfo = connectManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (null != wifiInfo) {
            NetworkInfo.State state = wifiInfo.getState();
            if (null != state) {
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                    return NETWORN_WIFI;
                }
            }
        }

        // 如果不是Wifi，则判断当前连接的运营商的哪种网络2G\3G\4G等
        NetworkInfo networkInfo = connectManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (null != networkInfo) {
            NetworkInfo.State state = networkInfo.getState();
            String strSubTypeName = networkInfo.getSubtypeName();
            if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                switch (activeNetInfo.getSubtype()) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        // 以上都是2G类型，NETWORK_TYPE_GPRS是联通2g，NETWORK_TYPE_CDMA是电信2g，
                        // NETWORK_TYPE_EDGE是移动2g
                        return NETWORN_2G;
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        // 以上是3G类型网络,NETWORK_TYPE_EVDO_A电信3G网络
                        return NETWORN_3G;
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        // 4G类型网络
                        return NETWORN_4G;
                    default:
                        //中国移动 联通 电信 三种3G制式
                        if (strSubTypeName.equalsIgnoreCase("TD-SCDMA") || strSubTypeName.equalsIgnoreCase("WCDMA") || strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            return NETWORN_3G;
                        } else {
                            return NETWORN_MOBILE;
                        }
                }
            }
        }
        return NETWORN_NONE;
    }

    /**
     * 判断网络是否连接(是否打开移动网络或者是连接wifi)
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: 不是
     */
    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 判断WIFI是否连接状态
     * <p>需添加权限 {@code <uses-permission android:name=”android.permission.ACCESS_NETWORK_STATE”/>}</p>
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: 不是
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }


    /**
     * 判断外网是否可用（常用于wifi已经连接但是无法访问外网的情况），耗时操作，不应该放在主线程
     */
    public static boolean isNetworkOnline() {
        try {
            // 除非百度挂了，否则用这个应该没问题
            String ip = "www.baidu.com";
            //ping3次
            Process ipProcess = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 打开网络设置界面
     * @param activity 界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }
}
