package com.saleef.mvcpokemonapp.Views.GenScreen.ViewPager;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.saleef.mvcpokemonapp.Commons.MvcSkeleton.BaseViewImpl;
import com.saleef.mvcpokemonapp.R;
import com.saleef.mvcpokemonapp.Views.GenScreen.Adapter.ViewPagerAdapter;

// ViewClass that instantiates our tabs and handles user viewPager transactions
public class ViewPagerViewImpl extends BaseViewImpl {


    private final TabLayout mTabLayout;
    private final ViewPager2 mViewPager2;
    private final ViewPagerAdapter mViewPagerAdapter;

    public ViewPagerViewImpl(LayoutInflater inflater, ViewGroup viewGroup,ViewPagerAdapter viewPagerAdapter){
        setRootView(inflater.inflate(R.layout.pager_layout,viewGroup,false));
        mTabLayout = findViewById(R.id.poketabs);
        mViewPager2 = findViewById(R.id.genPager);
        mViewPagerAdapter = viewPagerAdapter;
        mViewPager2.setAdapter(mViewPagerAdapter);

        for (int i = 1;i<=8;i++){
            mTabLayout.addTab(mTabLayout.newTab().setText("Gen" + Integer.toString(i)));
        }
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // When tab is chaged get the current tab load the corresponding data
                mViewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mTabLayout.selectTab(mTabLayout.getTabAt(position));
            }
        });
    }

}
