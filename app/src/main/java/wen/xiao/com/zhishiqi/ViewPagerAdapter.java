package wen.xiao.com.zhishiqi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/6.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private String[] titles;
    private ArrayList<ViewPagerFragment> viewPagerFragments;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public void setFragments(ArrayList<ViewPagerFragment> viewPagerFragments) {
        this.viewPagerFragments = viewPagerFragments;
    }
    @Override
    public Fragment getItem(int position) {
        return viewPagerFragments.get(position);
    }

    @Override
    public int getCount() {
        return viewPagerFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
