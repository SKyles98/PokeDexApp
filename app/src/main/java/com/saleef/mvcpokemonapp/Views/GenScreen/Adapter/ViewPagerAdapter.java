package com.saleef.mvcpokemonapp.Views.GenScreen.Adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.saleef.mvcpokemonapp.Views.GenScreen.PokedexGenFragment;
// Adapter for our our viewPager
public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
      return PokedexGenFragment.newInstance(position + 1);

    }

    @Override
    public int getItemCount() {
        return 8;
    }
}
