package com.example.gaodi.kaijiqidong;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by gaodi on 2017/3/19.
 */

public class WebViewActivity extends Activity implements View.OnClickListener {


    private WebView mWebView;
    private TextView tv_sroll;
    private Button bt_without_param,bt_with_param;
    private EditText et_native_input;
    private String str_native_input;

    private JsCallJavaInterface jsCallJavaMethod;
private WebViewActivity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_webview);
        context=this;
        setWebView();
    }

    @SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
    private void setWebView() {
        mWebView = (WebView) findViewById(R.id.webview);
        tv_sroll = (TextView) findViewById(R.id.tv_sroll);
        et_native_input = (EditText) findViewById(R.id.et_native_input);
        bt_without_param = (Button) findViewById(R.id.bt_without_param);
        bt_with_param = (Button) findViewById(R.id.bt_with_param);

        // JavaScript使能（如果加载的页面中有JS代码，则必须使能JS）
        WebSettings webSettings = mWebView.getSettings();
        // 启用javascript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        // 在WebView中打开连接（默认行为是使用浏览器，设置此项后都用WebView打开）
        mWebView.setWebViewClient(new WebViewClient());

        // 从assets目录下面的加载html
        mWebView.loadUrl("file:///android_asset/index.html");

        jsCallJavaMethod=new JsCallJavaInterface() {

            @Override
            @JavascriptInterface
            public void javaFunction() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        tv_sroll.setText(tv_sroll.getText() + "\nAndroid原生接口中方法被 js 调用（无参）");

                    }
                });

            }
        };
        mWebView.addJavascriptInterface(jsCallJavaMethod, "InterfaceTest");//传递接口实例化对象
        mWebView.addJavascriptInterface(this, "MyTest");//传递了该类的实例对象，以便于html中可以对象.方法的调用


        bt_without_param.setOnClickListener(this);
        bt_with_param.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_without_param:// 无参数调用js方法
                mWebView.loadUrl("javascript:jscallbynative()");
                break;
            case R.id.bt_with_param:// 调用js方法 并传递参数
                str_native_input= et_native_input.getText().toString();
                mWebView.loadUrl("javascript:jscallbynativewithparam('" + str_native_input + "'+" + "'  android原生传递参数给js 并调用了js的打印方法'" + ")");
                break;
            default:
                break;
        }
    }


    /**
     * 用于js调用的java方法（无参） （已废弃,接口实现类中实现）
     * Android4.2以上版本一定要在方法前加上@JavascriptInterface
     */
    @JavascriptInterface
    public void close() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.finish();
            }
        });
    }
    /**
     * 用于js调用的java方法（无参） （已废弃,接口实现类中实现）
     * Android4.2以上版本一定要在方法前加上@JavascriptInterface
     */
    @JavascriptInterface
    public void javaFunction() {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                tv_sroll.setText(tv_sroll.getText() + "\nAndroid原生方法被 js 调用（无参）");

            }
        });
    }

    /**
     * 用于js调用的java方法（有参）
     * Android4.2以上版本一定要在方法前加上@JavascriptInterface
     */
    @JavascriptInterface
    public void javaFunction(final String str) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                tv_sroll.setText(tv_sroll.getText() + "\nAndroid原生方法被 js 调用并传递参数：" + str);

            }
        });
    }


}
