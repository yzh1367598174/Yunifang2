package com.example.administrator.yunifang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.yunifang.Fragment.A;
import com.example.administrator.yunifang.Fragment.B;
import com.example.administrator.yunifang.Fragment.C;
import com.example.administrator.yunifang.Fragment.D;

import java.util.ArrayList;
import java.util.List;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout frame;
    private RadioButton butt1;
    private RadioButton butt2;
    private RadioButton butt3;
    private RadioButton butt4;
    List<Fragment> fragmentList = new ArrayList<>();
    private RadioGroup rediogroup;
    private A a;
    private B b;
    private C c;
    private D d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        frame = (FrameLayout) findViewById(R.id.frame);
        butt1 = (RadioButton) findViewById(R.id.butt1);
        butt2 = (RadioButton) findViewById(R.id.butt2);
        butt3 = (RadioButton) findViewById(R.id.butt3);
        butt4 = (RadioButton) findViewById(R.id.butt4);
        rediogroup = (RadioGroup) findViewById(R.id.rediogroup);
        fragmentList.add(new A());
        fragmentList.add(new B());
        fragmentList.add(new C());
        fragmentList.add(new D());
        butt1.setOnClickListener(this);
        butt2.setOnClickListener(this);
        butt3.setOnClickListener(this);
        butt4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hinal(transaction);
        switch (v.getId()) {
            case R.id.butt1:
                if (a == null) {
                    a = new A();
                    transaction.add(R.id.frame, a);
                }else{
                    transaction.show(a);
                }
                break;
            case R.id.butt2:
                if (b == null) {
                    b = new B();
                    transaction.add(R.id.frame, b);
                }else {
                    transaction.show(b);
                }
                break;
            case R.id.butt3:
                if(c==null){
                    c = new C();
                    transaction.add(R.id.frame,c);
                }else{
                    transaction.show(c);
                }

                break;
            case R.id.butt4:
                if(d==null){
                    d = new D();
                    transaction.add(R.id.frame,d);
                }else{
                    transaction.show(d);
                }

                break;
        }
    }

    private void hinal(FragmentTransaction transaction) {
        if (a != null) {
            transaction.hide(a);
        }
        if (b != null) {
            transaction.hide(b);
        }
        if (c != null) {
            transaction.hide(c);
        }
        if (d != null) {
            transaction.hide(d);
        }
    }
}
