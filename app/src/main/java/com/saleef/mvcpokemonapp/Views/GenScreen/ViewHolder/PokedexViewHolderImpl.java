package com.saleef.mvcpokemonapp.Views.GenScreen.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.saleef.mvcpokemonapp.Commons.MvcSkeleton.BaseObservableImpl;
import com.saleef.mvcpokemonapp.ViewModel.Pokemon;
import com.saleef.mvcpokemonapp.R;

// ViewHolder for our pokeDex HomeScreen
public class PokedexViewHolderImpl extends BaseObservableImpl<PokeDexViewHolderMvc.Listener> implements PokeDexViewHolderMvc {

       private final ImageView mImageView;
       private final TextView  nameTxt;
       private final TextView dexEntryTxt;
       private TextView type;
       private TextView type2;
       private Pokemon mPokemon;
       private final RequestOptions mRequestOptions;
      public PokedexViewHolderImpl(LayoutInflater layoutInflater, ViewGroup viewGroup){
            setRootView(layoutInflater.inflate(R.layout.pokemon_viewholder,viewGroup,false));
            mImageView = findViewById(R.id.pokeImage);
            dexEntryTxt = findViewById(R.id.idTxt);
            nameTxt = findViewById(R.id.nameTxt);
            type = findViewById(R.id.typeTxt);
            type2 = findViewById(R.id.type2Txt);
          mRequestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).
                  error(R.drawable.ic_launcher_background);
            getRootView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Listener listener:getListenerTypes()){
                        listener.onPokemonClicked(mPokemon);
                    }
                }
            });
      }



    @Override
    public void bindPokemon(Pokemon pokemon) {
          mPokemon = pokemon;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) type.getLayoutParams();
          nameTxt.setText(pokemon.getName());
          dexEntryTxt.setText("#" + pokemon.getId());
          if (pokemon.getTypes().size()>1){
              type.setText(pokemon.getTypes().get(0).getPokemonTypeJson().getTypeName());
            type2.setText(pokemon.getTypes().get(1).getPokemonTypeJson().getTypeName());
            type2.setVisibility(View.VISIBLE);
          } else{
              layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
              type.setText(pokemon.getTypes().get(0).getPokemonTypeJson().getTypeName());
              type.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
              type.setLayoutParams(layoutParams);
              type2.setVisibility(View.GONE);
          }

        Glide.with(getContext()).
                load(pokemon.getImage()) // loads image based off a url
                .apply(mRequestOptions). // apply image based off options
                into(mImageView);
    }
}
