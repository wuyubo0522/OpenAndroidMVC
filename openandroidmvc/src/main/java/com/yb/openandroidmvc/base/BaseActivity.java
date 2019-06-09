package com.yb.openandroidmvc.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.yb.openandroidmvc.util.ToastUtil;

import butterknife.ButterKnife;

/**
 * 类说明：Activity的基类
 *
 * @author 裕博
 * Date: 2019/6/9
 * Time: 15:04
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 视图绑定
        ButterKnife.bind(this);
        // 获取xml布局的ID
        getLayoutId();
        // 初始化数据
        initData();
        // 初始化界面
        initView();
    }

    /**
     * 获取xml布局
     *
     * @return xml的资源ID
     */
    public abstract int getLayoutId();

    /**
     * 初始化界面
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 跳转页面
     *
     * @param c 跳转的页面
     */
    protected void jumpActivity(Class c) {
        startActivity(new Intent(this, c));
    }

    /**
     * 跳转页面并刷新界面
     *
     * @param c 跳转的页面
     */
    protected void jumpActivityFinish(Class<? extends AppCompatActivity> c) {
        startActivity(new Intent(this, c));
        finish();
    }

    /**
     * 带参数跳转页面
     *
     * @param c      跳转的页面
     * @param bundle 参数的传递类，可以封装多个参数
     */
    protected void jumpActivityBundle(Class c, Bundle bundle) {
        Intent intent = new Intent(this, c);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 短Toast的显示方法
     *
     * @param msg 消息内容
     */
    public void showToastShort(String msg) {
        ToastUtil.showToastShort(this, msg);
    }

    /**
     * 长Toast的显示方法
     */
    public void showToastLong(String msg) {
        ToastUtil.showToastLong(this, msg);
    }

    /**
     * Activity设置状态栏字体颜色
     * 注意：本方法基于Android5.0以上的系统版本才能实现，模拟器无效。
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void changeStatusBarTextImgColor(boolean isBlack) {
        if (isBlack) {
            //设置状态栏黑色字体
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            //恢复状态栏白色字体
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }
}
