package com.saleef.mvcpokemonapp.Commons.DependencyInjection;


import com.saleef.mvcpokemonapp.Constants.Constant;
import com.saleef.mvcpokemonapp.Networking.API.PokemonApi;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Our homemade dependency Injection to inject a global instance of our url
public class CompositionRoot {

         private Retrofit mRetrofit;



         private Retrofit getRetrofit(){
             if (mRetrofit==null){
                 mRetrofit = new Retrofit.Builder().baseUrl(Constant.POKEMON_URL).addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                         .addConverterFactory(GsonConverterFactory.create()).build();
             }
             return mRetrofit;
         }

         public PokemonApi getPokemonApi(){
             return getRetrofit().create(PokemonApi.class);
         }

}
