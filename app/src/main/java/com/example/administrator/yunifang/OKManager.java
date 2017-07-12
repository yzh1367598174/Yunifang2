package com.example.administrator.yunifang;

import android.os.Handler;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by apple on 2017/6/9.
 * 使用场景：给所有的Activity或者Fragment等其他需要的类调用
 * 实现要求： 1、必须保证对象的唯一性
 * 2、保证网络返回的数据在主线程中
 * 1、定义变量
 * 2、创建静态的单例方法
 */

public class OKManager {

    private OkHttpClient mClient;

    private volatile  static OKManager sManager;//防止多个线程同时访问.比synchronized轻

    private volatile static OKManager manager;



    private static Handler mHandler;
    private Reader reader;

    ///////////////////////////////////////使用构造方法,完成初始化////////////////////////
    public OKManager() {
        mClient = new OkHttpClient();
        mHandler = new Handler();
    }
    ///////////////////////////////////////使用单例模式,通过获取的方式拿到对象/////////////
    public static OKManager getInstance(){
        if (sManager == null) {
            sManager=new OKManager();
        }
        return sManager;
    }

    interface Func4 {
        void onResponse(Bean bean);
    }

    /**
     * 返回响应的结果是bean对象
     *
     * @param url
     * @param callBack
     */
    public void getBeanMethod(String url, final Func4 callBack) {
        final Request request = new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                System.out.println("网络错误" + e.toString());
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    ResponseBody body = response.body();
                    reader = body.charStream();
                    String res = null;
                    BufferedReader bf = new BufferedReader(reader);
                    StringBuffer sb = new StringBuffer();
                    while ((res = bf.readLine()) != null) {
                        sb.append(res);
                    }

                    onSuccessJsonToBean(new String(sb.toString().getBytes(), "UTF-8"), callBack);
                }
            }
        });
    }

    /**
     * 返回的Bean对象到Func4接口中
     *
     * @param string
     * @param callBack
     */
    private void onSuccessJsonToBean(final String string, final Func4 callBack) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Bean bean = gson.fromJson(string, Bean.class);
                callBack.onResponse(bean);
            }
        });
    }


}
