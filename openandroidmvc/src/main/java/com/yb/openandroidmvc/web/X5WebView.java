package com.yb.openandroidmvc.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * 类说明：基于腾讯X5内核封装一个WebView
 *
 * @author 裕博
 * Date: 2019/6/8
 * Time: 20:41
 */
public class X5WebView extends WebView {
    /**
     * 防止应用加载网页的时候调起系统浏览器
     */
    private WebViewClient client = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            webView.loadUrl(url);
            return true;
        }
    };

    public X5WebView(Context context) {
        super(context);
        // 设置背景颜色
        setBackgroundColor(85621);
    }

    public X5WebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.setWebViewClient(client);
        this.getView().setClickable(true);
        initWebViewSettings();
    }

    /**
     * 初始化WebView设置
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void initWebViewSettings() {
        WebSettings webSettings = this.getSettings();
        // 允许加载JS
        webSettings.setJavaScriptEnabled(true);
        //
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //
        webSettings.setAllowFileAccess(true);
        //
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //
        webSettings.setSupportZoom(true);
        //
        webSettings.setBuiltInZoomControls(true);
        //
        webSettings.setUseWideViewPort(true);
        //
        webSettings.setSupportMultipleWindows(true);
        // cache缓存
        webSettings.setAppCacheEnabled(true);
        // DOM缓存
        webSettings.setDomStorageEnabled(true);
        //
        webSettings.setGeolocationEnabled(true);
        //
        webSettings.setAppCacheMaxSize(Long.MAX_VALUE);
        //
        webSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        //
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 自适应屏幕，超出宽度的时候，会缩小适应屏幕
        webSettings.setLoadWithOverviewMode(true);

        webSettings.setDomStorageEnabled(true);

        webSettings.setDatabaseEnabled(true);
        //保存表单数据 默认true
        webSettings.setSaveFormData(false);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAllowFileAccess(true);
    }
}
