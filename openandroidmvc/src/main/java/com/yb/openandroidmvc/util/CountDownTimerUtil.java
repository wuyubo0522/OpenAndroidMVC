package com.yb.openandroidmvc.util;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * 类说明：倒计时工具类，发送验证码会使用到。
 * 目前是文字类型的，会拓展
 *
 * @author: 裕博
 * Date: 2019/6/8
 * Time: 1:06
 */
public class CountDownTimerUtil extends CountDownTimer {

    /**
     * 验证文字显示
     */
    private TextView textView;

    /**
     * 文字色号
     */
    private String colorNumber;

    public CountDownTimerUtil(long millisInFuture, long countDownInterval, TextView textView, String colorNumber) {
        super(millisInFuture, countDownInterval);
        this.textView = textView;
        this.colorNumber = colorNumber;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onTick(long millisUntilFinished) {
        //设置不可点击
        textView.setClickable(false);
        //设置倒计时时间
        textView.setText(millisUntilFinished / 1000 + "(s)");
        //改变文字颜色
        textView.setTextColor(Color.parseColor("#FFFFFF"));

    }

    @Override
    public void onFinish() {
        textView.setText("发送");
        //改变文字颜色
        textView.setTextColor(Color.parseColor(colorNumber));
        //重新获得点击
        textView.setClickable(true);
    }
}
