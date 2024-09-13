package com.lypaka.bettercommunitydays.CommunityDays;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonBuilder;
import com.pixelmonmod.pixelmon.api.pokemon.species.Species;

import java.util.List;
import java.util.Map;

public class CommunityDay {

    private final String name;
    private final boolean configured;
    private final int[] endTime;
    private final int[] startTime;

    private final String guiDisplayName;
    private final List<String> guiLore;
    private final String guiRepresentationSpecies;

    private final String form;
    private final int maxLevel;
    private final int minLevel;
    private final Map<String, Double> specialMoves;
    private final double shinyChance;
    private final String species;
    private final int specialMoveAmount;
    private final Map<String, Double> specialTextures;
    private final List<String> worldBlacklist;

    private boolean active;
    private final Pokemon pokemonSpecies;

    public CommunityDay (String name, boolean configured, int[] endTime, int[] startTime, String guiDisplayName, List<String> guiLore, String guiRepresentationSpecies,
                         String form, int maxLevel, int minLevel, Map<String, Double> specialMoves, double shinyChance, String species, int specialMoveAmount, Map<String, Double> specialTextures,
                         List<String> worldBlacklist
                         ) {

        this.name = name;
        this.configured = configured;
        this.endTime = endTime;
        this.startTime = startTime;
        this.guiDisplayName = guiDisplayName;
        this.guiLore = guiLore;
        this.guiRepresentationSpecies = guiRepresentationSpecies;
        this.form = form;
        this.maxLevel = maxLevel;
        this.minLevel = minLevel;
        this.specialMoves = specialMoves;
        this.shinyChance = shinyChance;
        this.species = species;
        this.specialMoveAmount = specialMoveAmount;
        this.specialTextures = specialTextures;
        this.worldBlacklist = worldBlacklist;

        this.active = false;
        this.pokemonSpecies = PokemonBuilder.builder().species(this.species).build();

    }

    public String getName() {

        return this.name;

    }

    public boolean isConfigured() {

        return this.configured;

    }

    public int getEndYear() {

        return this.endTime[0];

    }

    public int getEndMonth() {

        return this.endTime[1];

    }

    public int getEndDay() {

        return this.endTime[2];

    }

    public int getEndHour() {

        return this.endTime[3];

    }

    public int getEndMinute() {

        return this.endTime[4];

    }

    public int getEndSecond() {

        return this.endTime[5];

    }

    public int getStartYear() {

        return this.startTime[0];

    }

    public int getStartMonth() {

        return this.startTime[1];

    }

    public int getStartDay() {

        return this.startTime[2];

    }

    public int getStartHour() {

        return this.startTime[3];

    }

    public int getStartMinute() {

        return this.startTime[4];

    }

    public int getStartSecond() {

        return this.startTime[5];

    }

    public String getGUIDisplayNam() {

        return this.guiDisplayName;

    }

    public List<String> getGUILore() {

        return this.guiLore;

    }

    public String getGUIRepresentationSpecies() {

        return this.guiRepresentationSpecies;

    }

    public String getForm() {

        return this.form;

    }

    public int getMaxLevel() {

        return this.maxLevel;

    }

    public int getMinLevel() {

        return this.minLevel;

    }

    public Map<String, Double> getSpecialMoves() {

        return this.specialMoves;

    }

    public double getShinyChance() {

        return this.shinyChance;

    }

    public String getSpecies() {

        return this.species;

    }

    public int getSpecialMoveAmount() {

        return this.specialMoveAmount;

    }

    public Map<String, Double> getSpecialTextures() {

        return this.specialTextures;

    }

    public List<String> getWorldBlacklist() {

        return this.worldBlacklist;

    }

    public boolean isActive() {

        return this.active;

    }

    public void setActive (boolean active) {

        this.active = active;

    }

    public int getPokemonDexNumber() {

        return this.pokemonSpecies.getSpecies().getDex();

    }

}
