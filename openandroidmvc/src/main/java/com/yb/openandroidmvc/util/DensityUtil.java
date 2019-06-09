package com.yb.openandroidmvc.util;

import android.content.res.Resources;

/**
 * 类说明：密度转换工具类
 *
 * @author 裕博
 * Date: 2019/6/8
 * Time: 1:03
 */
public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @param dpValue 虚拟像素
     * @return 像素
     */
    public static int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     * @param pxValue 像素
     * @return 虚拟像素
     */
    public static float px2dp(int pxValue) {
        return (pxValue / Resources.getSystem().getDisplayMetrics().density);
    }
}