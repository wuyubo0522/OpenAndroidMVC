package com.yb.openandroidmvc.util;

import android.text.TextUtils;

/**
 * 类说明：App开发中验证方面有关的工具类
 *
 * @author 裕博
 * Date: 2019/6/8
 * Time: 1:04
 */
public class ValidateUtil {
    /**
     * 验证手机格式，正则表达式
     * @param mobiles 手机号字符串
     * @return boolean
     */
    public static boolean isPhone(String mobiles) {
        String telRegex = "^1[3-9][0-9]\\d{8}$";
        return !TextUtils.isEmpty(mobiles) && mobiles.matches(telRegex);
    }
}
