package com.yb.openandroidmvc.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;

import com.yb.openandroidmvc.R;
import com.yb.openandroidmvc.util.ToastUtil;

import butterknife.ButterKnife;

/**
 * 类说明：MVC架构模式的Dialog基类
 *
 * @author 裕博
 * Date: 2019/9/17
 * Time: 17:21
 */
public abstract class BaseDialog extends Dialog {

    public BaseDialog(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getContext(), getLayoutId(), null);
        ButterKnife.bind(this, view);
        setContentView(view);
        Window window = getWindow();
        //设置dialog进出动画
        assert window != null;
        window.setWindowAnimations(R.style.dialogWindowAnim);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int) (getContext().getResources().getDisplayMetrics().widthPixels * 0.85);
        window.setAttributes(params);
        setCanceledOnTouchOutside(true);
        init();
    }

    /**
     * 初始化方法
     */
    protected abstract void init();

    /**
     * 获取当前的布局文件
     */
    public abstract int getLayoutId();

    /**
     * 短Toast的显示方法
     *
     * @param msg 消息内容
     */
    public void showToastShort(String msg) {
        ToastUtil.showToastShort(getContext(), msg);
    }

    /**
     * 长Toast的显示方法
     */
    public void showToastLong(String msg) {
        ToastUtil.showToastLong(getContext(), msg);
    }
}
