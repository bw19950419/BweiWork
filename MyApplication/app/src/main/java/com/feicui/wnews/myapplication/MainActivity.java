package com.feicui.wnews.myapplication;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import static com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.TOUCHMODE_MARGIN;

public class MainActivity extends FragmentActivity {
    private FragmentTransaction fragmentTransaction;
    private FragmentNews fragment;
    private FragmentJunshi fragmentJunshi;
    private RadioButton radioButtonzy;
    private RadioButton radioButtonjs;
    private RadioGroup radioGroup;
    private ListView listView;
    private RelativeLayout titleView;
    private ViewPager mViewPager;
    private SxAdapter sxadapter;
    private ImageView cursor;
    //动画图片宽度
    private int bmpW;
    //动画图片偏移量
    private int offset = 0;
    private int position_one;
    private int position_two;
    //存放Fragment
    private ArrayList<Fragment> fragmentArrayList;
    private FragmentManager fragmentManager;
    //当前页卡编号
    private int currIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        initview();

        //侧滑页面
        initSlidingmenu();
        //右侧DrawerLayout
        initDrawerLayout();
        //初始化动画
        InitImageView();
        //初始化Fragment
        InitFragment();
        //页面切换
        InitViewPager();
        //添加点击事件
        radioButtonzy.setOnClickListener(new MyOnClickListener(0));
        radioButtonjs.setOnClickListener(new MyOnClickListener(1));

    }

    /**
     * 初始化页卡内容区
     */
    private void InitViewPager() {

        mViewPager = (ViewPager) findViewById(R.id.vPager);
        mViewPager.setAdapter(new MFragmentPagerAdapter(fragmentManager, fragmentArrayList));

        //让ViewPager缓存2个页面
        mViewPager.setOffscreenPageLimit(2);

        //设置默认打开第一页
        mViewPager.setCurrentItem(0);

        //将顶部文字恢复默认值
        resetTextViewTextColor();
        radioButtonzy.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));

        //设置viewpager页面滑动监听事件
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Animation animation = null ;
                switch (position){

                    //当前为页卡1
                    case 0:
                        //从页卡1跳转转到页卡2
                        if(currIndex == 1){
                            animation = new TranslateAnimation(position_one, 0, 0, 0);
                            resetTextViewTextColor();
                            radioButtonzy.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                        }else if(currIndex == 2){//从页卡1跳转转到页卡3
                            animation = new TranslateAnimation(position_two, 0, 0, 0);
                            resetTextViewTextColor();
                            radioButtonzy.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                        }
                        break;

                    //当前为页卡2
                    case 1:
                        //从页卡1跳转转到页卡2
                        if (currIndex == 0) {
                            animation = new TranslateAnimation(offset, position_one, 0, 0);
                            resetTextViewTextColor();
                            radioButtonjs.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                        } else if (currIndex == 2) { //从页卡1跳转转到页卡2
                            animation = new TranslateAnimation(position_two, position_one, 0, 0);
                            resetTextViewTextColor();
                            radioButtonzy.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                        }
                        break;

                    //当前为页卡3

                }
                currIndex = position;

                animation.setFillAfter(true);// true:图片停在动画结束位置
                animation.setDuration(300);
                cursor.startAnimation(animation);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void InitFragment() {
        fragmentArrayList = new ArrayList<Fragment>();
        fragmentArrayList.add(new FragmentNews());
        fragmentArrayList.add(new FragmentJunshi());
       fragmentManager=  getSupportFragmentManager();

    }
    /**
     * 将顶部文字恢复默认值
     */
    private void resetTextViewTextColor(){

        radioButtonzy.setTextColor(getResources().getColor(R.color.main_top_tab_color));
        radioButtonjs.setTextColor(getResources().getColor(R.color.main_top_tab_color));

    }

    /**
     * 设置动画图片宽度
     * @param mWidth
     */
    private void setBmpW(ImageView imageView,int mWidth){
        ViewGroup.LayoutParams para;
        para = imageView.getLayoutParams();
        para.width = mWidth;
        imageView.setLayoutParams(para);
    }
    /**
     * 头标点击监听
     * @author weizhi
     * @version 1.0
     */
    public class MyOnClickListener implements View.OnClickListener{
        private int index = 0 ;
        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mViewPager.setCurrentItem(index);
        }
    }

    /**
     * 初始化动画
     */
    private void InitImageView() {
        cursor = (ImageView) findViewById(R.id.cursor);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        // 获取分辨率宽度
        int screenW = dm.widthPixels;

        bmpW = (screenW/2);

        //设置动画图片宽度
        setBmpW(cursor, bmpW);
        offset = 0;

        //动画图片偏移量赋值
        position_one = (int) (screenW / 2.0);
        position_two = position_one ;
    }

    private void initDrawerLayout() {
        DrawerLayout drawerlayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        LinearLayout llRight = (LinearLayout) findViewById(R.id.ll_right);
        ViewGroup.LayoutParams layoutParams = (DrawerLayout.LayoutParams) llRight.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels * 1 / 2;
        llRight.setLayoutParams(layoutParams);
    }


    /**
     * 添加头部尾部
     */
    private void initSlidingmenu() {
        SlidingMenu slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(slidingMenu.LEFT);
        slidingMenu.setMenu(R.layout.sliding_menu);
        slidingMenu.setTouchModeAbove(TOUCHMODE_MARGIN);
        slidingMenu.setBehindWidth(300);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setBehindScrollScale(0.9F);
        //添加头部
        listView = (ListView) findViewById(R.id.list_sliding_menu);
        LayoutInflater inflater = LayoutInflater.from(this);
        titleView = (RelativeLayout) inflater.inflate(R.layout.head_item, null);
        listView.addHeaderView(titleView);
        List list = new ArrayList();
        for (int i = 0; i < 5; i++) {
            list.add("你好");
        }

        TitleAdapter titleAdapter = new TitleAdapter(this, list);
        listView.setAdapter(titleAdapter);
    }

    //初始化控件
    private void initview() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButtonzy = (RadioButton) findViewById(R.id.zy_redio_button);
        radioButtonjs = (RadioButton) findViewById(R.id.js_radio_button);

    }



}

