package com.example.gaodi.kaijiqidong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.gaodi.kaijiqidong.view.LoginActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现

        //SDKInitializer.initialize(getApplicationContext());
        SharedPreferences diyi = getSharedPreferences("diyi", 0);

        setContentView(R.layout.activity_main);
      /*  try
        {
            MainActivity.this.clearWallpaper();
        } catch (IOException e)
        {
            Toast.makeText(getApplicationContext(),"清空桌面背景失败，请稍后再试",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        InputStream myImagInputStream = imageView.getResources().openRawResource(R.drawable.mydesttop);
        try
        {
            MainActivity.this.setWallpaper(myImagInputStream);
            Toast.makeText(getApplicationContext(),"恭喜你设置成功！！",Toast.LENGTH_LONG).show();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            Toast.makeText(getApplicationContext(),"设置桌面图标失败，请稍后再试",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }*/
        Resources res=getResources();
        BitmapDrawable bmpDraw=(BitmapDrawable)res.getDrawable(R.drawable.zmm);
        Bitmap bmp=bmpDraw.getBitmap();

        try{
            setWallpaper(bmp);

        }catch(IOException e) {

            e.printStackTrace();

        }
        Glide.with(this);
    }
    public void show(View view){

        Intent intent=new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
    }
    public void showWeb(View view){

        Intent intent=new Intent();
        intent.setClass(this, WebViewActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
