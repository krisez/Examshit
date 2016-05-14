package com.redrock.my.smusic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.redrock.my.smusic.SongOfBangdan.BandMain;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private FragmentPagerAdapter adapter;
    private List<Fragment> fragmentList;

    private LinearLayout tabEurope;
    private LinearLayout tabChina;
    private LinearLayout tabHk;
    private LinearLayout tabHot;

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);

        initView();
        initTabEvents();
        setPagerSelect(0);
    }

    //TAB点击事件
    private void initTabEvents() {
        tabEurope.setOnClickListener(this);
        tabChina.setOnClickListener(this);
        tabHk.setOnClickListener(this);
        tabHot.setOnClickListener(this);
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        tabEurope = (LinearLayout) findViewById(R.id.europe_tab);
        tabChina = (LinearLayout) findViewById(R.id.china_tab);
        tabHk = (LinearLayout) findViewById(R.id.hk_tab);
        tabHot = (LinearLayout) findViewById(R.id.hotSong_tab);

        imageView1 = (ImageView) findViewById(R.id.tab_img1);
        imageView2 = (ImageView) findViewById(R.id.tab_img2);
        imageView3 = (ImageView) findViewById(R.id.tab_img3);
        imageView4 = (ImageView) findViewById(R.id.tab_img4);

        fragmentList = new ArrayList<>();
        Fragment mTab1 = new BandMain(3);
        Fragment mTab2 = new BandMain(5);
        Fragment mTab3 = new BandMain(6);
        Fragment mTab4 = new BandMain(26);
        fragmentList.add(mTab1);
        fragmentList.add(mTab2);
        fragmentList.add(mTab3);
        fragmentList.add(mTab4);
        //ViewPagerAdapter 设置
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        viewPager.setAdapter(adapter);
        //监听ViewPager的改变
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetImg();
                setTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        resetImg();
        switch (v.getId()){
            case R.id.europe_tab:
                setPagerSelect(0);
                break;
            case R.id.china_tab:
                setPagerSelect(1);
                break;
            case R.id.hk_tab:
                setPagerSelect(2);
                break;
            case R.id.hotSong_tab:
                setPagerSelect(3);
                break;
        }
    }

    private void setTab(int position) {
        resetImg();
        switch (position) {
            case 0:
                imageView1.setVisibility(View.VISIBLE);
                break;
            case 1:
                imageView2.setVisibility(View.VISIBLE);
                break;
            case 2:
                imageView3.setVisibility(View.VISIBLE);
                break;
            case 3:
                imageView4.setVisibility(View.VISIBLE);
                break;
        }
    }

    //初始化下划线的显示
    private void resetImg() {
        imageView1.setVisibility(View.INVISIBLE);
        imageView2.setVisibility(View.INVISIBLE);
        imageView3.setVisibility(View.INVISIBLE);
        imageView4.setVisibility(View.INVISIBLE);
    }

    //选择显示的View
    private void setPagerSelect(int i) {
        setTab(i);
        viewPager.setCurrentItem(i);
    }


}
