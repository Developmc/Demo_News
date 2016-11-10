package com.clement.example.demo_news.base;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by clement on 16/11/9.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int getLayoutViewId();
    protected abstract void initVariables();
    protected abstract void initViews();
    protected abstract void initBehavior();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局
        int layoutId = getLayoutViewId();
        if(layoutId>0){
            setContentView(layoutId);
        }
        //using butter knife
        ButterKnife.bind(this);
        initVariables();
        initViews();
        initBehavior();
    }

    /**
     * @param fragment 将要显示的fragment
     * @param containerViewId fragment所在的容器
     */
    public void replaceFragment(BaseFragment fragment,int containerViewId){
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(containerViewId,fragment);
        transaction.commit();
    }
}
