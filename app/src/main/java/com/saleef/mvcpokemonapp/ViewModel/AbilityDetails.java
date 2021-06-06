package com.saleef.mvcpokemonapp.ViewModel;

// Model class for our ability Details
public class AbilityDetails {




    private final String commonAbilityDescription;
    private final String shortenedAbilityDescription;
    private final String flavortextAbilityDescription;
    private final String name;

    public AbilityDetails(String commonAbilityDescription, String shortenedAbilityDescription, String flavortextAbilityDescription, String name) {
        this.commonAbilityDescription = commonAbilityDescription;
        this.shortenedAbilityDescription = shortenedAbilityDescription;
        this.flavortextAbilityDescription = flavortextAbilityDescription;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCommonAbilityDescription() {
        return commonAbilityDescription;
    }

    public String getFlavortextAbilityDescription() {
        return flavortextAbilityDescription;
    }

    public String getShortenedAbilityDescription() {
        return shortenedAbilityDescription;
    }


}
