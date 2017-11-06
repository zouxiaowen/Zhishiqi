package wen.xiao.com.zhishiqi;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static net.lucode.hackware.magicindicator.buildins.UIUtil.dip2px;

/**
 * Created by Administrator on 2017/11/6.
 */

public class Activity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        String[] mTitles = new String[]{"语文", "英语","数学"};
        ArrayList<ViewPagerFragment> mViewPagerFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            mViewPagerFragments.add(ViewPagerFragment.newInstance(mTitles[i]));
        }
        viewPagerAdapter.setTitles(mTitles);
        viewPagerAdapter.setFragments(mViewPagerFragments);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                TabUtils.setIndicator(tabLayout,30,30,0);   //  最后一个参数很重要 表示距离字体的距离
            }
        });
//        3、此方法是通过反射获取tablayout私有属性进修改属性值，如果app启动混淆 会报NoSuchFieldException；
//        解决方案：在proguard-rules.pro混淆文件中添加-keep class Android.support.design.widget.TabLayout{*;}
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {

        Class tabLayout = tabs.getClass();

        Field tabStrip = null;

        try {

            tabStrip = tabLayout.getDeclaredField("mTabStrip");

        } catch (NoSuchFieldException e) {

            e.printStackTrace();

        }

        tabStrip.setAccessible(true);

        LinearLayout llTab = null;

        try {

            llTab = (LinearLayout) tabStrip.get(tabs);

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());

        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {

            View child = llTab.getChildAt(i);

            child.setPadding(0, 0, 0, 0);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);

            params.leftMargin = left;

            params.rightMargin = right;

            child.setLayoutParams(params);

            child.invalidate();

        }

    }


}
