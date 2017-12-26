package com.example.chensolo.rxandroid_retrofit_mvp;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.chensolo.rxandroid_retrofit_mvp.view.MainView;
import com.jakewharton.rxbinding.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import adapter.PlaceAdapter;
import adapter.WeatherDataAdapter;
import adapter.WeatherExtraAdapter;
import butterknife.Bind;
import model.Place;
import presenter.MainPresenter;
import presenter.MainPresenterImpl;
import rx.functions.Action1;
import service.response.WeatherResponse;

public class MainActivity extends BaseActivity implements MainView{
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.rv_place)
    RecyclerView mRvPlace;
    @Bind(R.id.rv_weather_data) RecyclerView mRvWeatherData;
    @Bind(R.id.recyvler_view) RecyclerView mRvWeatherExtra;
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;
    @Bind(R.id.tv_city)
    TextView mTvCity;
    @Bind(R.id.tv_pm25) TextView mTvPm25;
    @Bind(R.id.fab)
    FloatingActionButton mFloatingActionBar;
    @Bind(R.id.tv_empty_data) TextView mTvEmptyData;

    private MainPresenter mMainPresenter;
    private WeatherExtraAdapter mWeatherExteaAdapter;
    private WeatherDataAdapter mWeatherDataAdapter;
    private PlaceAdapter mPlaceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main,true);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.menu);
        ab.setDisplayHomeAsUpEnabled(true);

        initView();
        mMainPresenter = new MainPresenterImpl(this);
        mMainPresenter.onCreate();

        mMainPresenter.getPlaceAndWeatherData("北京");
        RxView.clicks(mFloatingActionBar).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                mMainPresenter.onRefresh();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         if ( Utils.ViewUtils.isFastClick()) return true;

         switch (item.getItemId()){
             case android.R.id.home:
                 mDrawerLayout.openDrawer(GravityCompat.START);
                 break;
             case R.id.weather:
                 mDrawerLayout.openDrawer(GravityCompat.END);
                 break;
                 default:
                     break;
         }
         return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ( mMainPresenter !=null ){
            mMainPresenter.onDestroy();
        }
    }

    private void initView() {
        mRvPlace.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        mRvPlace.setLayoutManager(staggeredGridLayoutManager);
        mPlaceAdapter = new PlaceAdapter();

        mRvPlace.setAdapter(mWeatherDataAdapter);



    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setupPlaceData(List<Place> placeList) {

    }

    @Override
    public void setupWeatherData(WeatherResponse response) {

    }
}
