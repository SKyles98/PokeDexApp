package com.saleef.mvcpokemonapp.Views.GenScreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saleef.mvcpokemonapp.Commons.MvcSkeleton.BaseObservableImpl;
import com.saleef.mvcpokemonapp.ViewModel.Pokemon;
import com.saleef.mvcpokemonapp.R;
import com.saleef.mvcpokemonapp.Commons.ViewFactory.ViewMvcFactory;
import com.saleef.mvcpokemonapp.Views.GenScreen.Adapter.PokedexRecyclerAdapter;

// This View class loaded when the user clicks on a tab
import java.util.List;

public class PokemonGenViewImpl extends BaseObservableImpl<PokemonGenViewMvc.Listener> implements PokemonGenViewMvc, PokedexRecyclerAdapter.Listener {

    private final ProgressBar mProgressBar;
    private final PokedexRecyclerAdapter mPokedexRecyclerAdapter;
    private final RecyclerView mRecyclerView;



    public PokemonGenViewImpl(LayoutInflater layoutInflater, ViewGroup viewGroup){
        setRootView(layoutInflater.inflate(R.layout.fragment_pokedex_gen,viewGroup,false));
        mProgressBar = findViewById(R.id.progressBar);
        mRecyclerView = findViewById(R.id.pokedexRecycler);
        mPokedexRecyclerAdapter = new PokedexRecyclerAdapter(this,new ViewMvcFactory(layoutInflater,null));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mPokedexRecyclerAdapter);
    }








    @Override
    public void bindPokemon(List<Pokemon> list) {
            mPokedexRecyclerAdapter.bindPokemon(list);
    }

    @Override
    public void showProgressIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
       mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPokemonClicked(Pokemon pokemon) {
              for (Listener listener:getListenerTypes()){
                  listener.onPokemonClicked(pokemon);
              }
    }
}
