package com.supets.pet.pagenav.viewholder;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.supets.pet.pagenav.adapter.GridViewAdapter;
import com.supets.pet.pagenav.adapter.ViewPagerAdapter;
import com.supets.pet.pagenav.model.Model;
import com.supets.pet.pagnav.R;

import java.util.ArrayList;
import java.util.List;

public class PageNavCardHolder {

    private ViewPager mPager;
    private LinearLayout mLlDot;
    private GridViewAdapter gridViewAdapter;

    private String[] titles = {"美食", "电影", "酒店住宿", "休闲娱乐", "外卖", "自助餐", "KTV", "机票/火车票", "周边游", "美甲美睫",
            "火锅", "生日蛋糕", "甜品饮品", "水上乐园", "汽车服务", "美发", "丽人", "景点", "足疗按摩", "运动健身", "健身", "超市", "买菜",
            "今日新单", "小吃快餐", "面膜", "洗浴/汗蒸", "母婴亲子", "生活服务", "婚纱摄影", "学习培训", "家装", "结婚", "全部分配"};
    private List<View> mPagerList;
    private List<Model> mDatas;

    private int pageCount;//总页数
    private int pageSize = 10;//每一页的个数
    private int curIndex = 0;//当前显示的事第几页

    public static int posSelect = 0;

    private Activity activity;
    private LayoutInflater inflater;

    public PageNavCardHolder(final Activity activity) {

        this.activity = activity;
        inflater = LayoutInflater.from(activity);

        posSelect = 0;
        mPager = (ViewPager) activity.findViewById(R.id.viewpager);
        mLlDot = (LinearLayout) activity.findViewById(R.id.ll_dot);
        initDatas();

        //总页数=总数/每页的个数，取整
        pageCount = (int) Math.ceil(mDatas.size() * 1.0 / pageSize);

        mPagerList = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            //每个页面都是inflate出的一个新实例
            GridView gridView = (GridView) inflater.inflate(R.layout.gridview, null);
            gridViewAdapter = new GridViewAdapter(activity, mDatas, i, pageSize);
            gridView.setAdapter(gridViewAdapter);
            mPagerList.add(gridView);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    int pos = position + curIndex * pageSize;
                    posSelect = pos;

                    mPager.setAdapter(new ViewPagerAdapter(mPagerList));
                    mPager.setCurrentItem(curIndex);

                    Toast.makeText(activity, mDatas.get(pos).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        //设置viewpageAdapter
        mPager.setAdapter(new ViewPagerAdapter(mPagerList));
        //设置小圆点
        setOvalLayout();

    }

    private void setOvalLayout() {
        for (int i = 0; i < pageCount; i++) {
            mLlDot.addView(inflater.inflate(R.layout.dot, null));
        }
        //默认显示第一页
        mLlDot.getChildAt(0).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_selected);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //取消选中
                mLlDot.getChildAt(curIndex).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_normal);
                //选中
                mLlDot.getChildAt(position).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_selected);

                curIndex = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
            int imageId = activity.getResources().getIdentifier("ic_category_" + i, "drawable", activity.getPackageName());
            mDatas.add(new Model(titles[i], imageId));
        }
        Log.i("数据源", "---->" + mDatas.size());
        Log.i("数据源", "---->" + mDatas.toString());
    }
}
