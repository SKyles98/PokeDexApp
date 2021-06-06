package com.saleef.mvcpokemonapp.ViewModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.saleef.mvcpokemonapp.Networking.Model.AbilitiesSchema;
import com.saleef.mvcpokemonapp.Networking.Model.PokemonType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Model class of all the vars that make up a Pokemon
public class Pokemon implements Comparator<Pokemon>, Parcelable {

    private  int id;
    private String name;
    private  int height;
    private  int weight;
    private List<PokemonType> types;
    private String description;
    private  String image;
    private String color;
    private  List<AbilitiesSchema> abilities;
    private List<String> typeChange;
    private List<String> abilitiesChange;
    private List<String> abilitesUrls;

    public Pokemon(){

    }

    public Pokemon(int id, int height, int weight, List<PokemonType> types, String image, List<AbilitiesSchema> abilities,String name) {
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.types = types;
        this.image = image;
        this.abilities = abilities;
        this.name = name;
        typeChange = new ArrayList<>();
        abilitiesChange = new ArrayList<>();
        abilitesUrls = new ArrayList<>();
       setTypeChanges(types);
       setAbilitiesChanges(abilities);
       setAbilitesUrl(abilities);
    }


    private void setTypeChanges(List<PokemonType> types){
        for (PokemonType pokemonType:types){
            typeChange.add(pokemonType.getPokemonTypeJson().getTypeName());
        }
    }
    private void setAbilitiesChanges(List<AbilitiesSchema> abilities){
        for (AbilitiesSchema abilitiesSchema:abilities){
            abilitiesChange.add(abilitiesSchema.getAbilityJson().getAbilityName());
        }
    }
    private void setAbilitesUrl(List<AbilitiesSchema> abilities){
        for (AbilitiesSchema abilitiesSchema:abilities){
            abilitesUrls.add(abilitiesSchema.getAbilityJson().getAbilityUrl());
        }
    }

    protected Pokemon(Parcel in) {
        id = in.readInt();
        name = in.readString();
        height = in.readInt();
        weight = in.readInt();
        description = in.readString();
        image = in.readString();
        typeChange = new ArrayList<String>();
        color = in.readString();
        abilitiesChange = new ArrayList<String>();
        abilitesUrls = new ArrayList<String>();
         in.readList(typeChange,String.class.getClassLoader());
         in.readList(abilitiesChange,String.class.getClassLoader());
         in.readList(abilitesUrls,String.class.getClassLoader());
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonType> types) {
        this.types = types;
    }

    public List<AbilitiesSchema> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilitiesSchema> abilities) {
        this.abilities = abilities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getAbilitiesChange() {
        return abilitiesChange;
    }

    public List<String> getTypeChange() {
        return typeChange;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<String> getAbilitesUrls() {
        return abilitesUrls;
    }

    @Override
    public int compare(Pokemon o1, Pokemon o2) {
        return o1.getId()-o2.getId();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(height);
        dest.writeInt(weight);
        dest.writeString(description);
        dest.writeString(image);
        dest.writeList(typeChange);
        dest.writeList(abilitiesChange);
        dest.writeString(color);
        dest.writeList(abilitesUrls);
    }


}
