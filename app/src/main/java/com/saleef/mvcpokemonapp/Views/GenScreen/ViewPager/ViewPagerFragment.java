package com.saleef.mvcpokemonapp.Views.GenScreen.ViewPager;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saleef.mvcpokemonapp.Commons.Bases.BaseFragment;

//Fragment that holds our viewPager
public class ViewPagerFragment extends BaseFragment {



    private ViewPagerViewImpl mViewPagerView;

    public ViewPagerFragment() {
        // Required empty public constructor
    }


    public static ViewPagerFragment newInstance() {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mViewPagerView = getControllerCompositionRoot().getViewMvcFactory().getViewPagerViewImpl(container);
        return mViewPagerView.getRootView();
    }
}