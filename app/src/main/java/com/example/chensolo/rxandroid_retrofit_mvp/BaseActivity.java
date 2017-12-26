package com.example.chensolo.rxandroid_retrofit_mvp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/26.
 */

public class BaseActivity extends AppCompatActivity{
    @Bind(R.id.toolbar)
    Toolbar mToolBar;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    private boolean mAutoBindView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(layoutResID, false);
    }

    public void setContentView(int layoutResID, boolean hideBackButton) {
           super.setContentView(layoutResID);
        ButterKnife.bind(this);
        initToolBar(hideBackButton);
        mAutoBindView = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( mAutoBindView ){
            ButterKnife.unbind(this);
        }
        BaseApplication.getRefWatcher().watch(this);
    }

    @SuppressLint("RestrictedApi")
    private void initToolBar(boolean hideBackButton) {

        if ( mToolBar == null ){
            return;
        }
        setTitle("");

        setSupportActionBar(mToolBar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

       if (!hideBackButton ) {
           mToolBar.setNavigationIcon(R.mipmap.back);
           mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   finish();
               }
           });
       }
       }

  protected void setTitle(String title) {
       mTvTitle.setVisibility(View.VISIBLE);
       mTvTitle.setText(title);
    }

    protected void setTitleRes(int resID){
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText(resID);
    }
    protected void hideTitle(){
        mTvTitle.setVisibility(View.GONE);
    }
}

