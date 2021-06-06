package com.saleef.mvcpokemonapp.Networking.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Other {

    @SerializedName("official-artwork")
    @Expose
    private OfficialArtwork mOfficialArtwork;


    public OfficialArtwork getOfficialArtwork() {
        return mOfficialArtwork;
    }


}
