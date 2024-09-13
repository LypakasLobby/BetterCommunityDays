package com.lypaka.bettercommunitydays.CommunityDays;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonBuilder;
import com.pixelmonmod.pixelmon.api.pokemon.species.Species;

import java.util.List;
import java.util.Map;

public class CommunityDay {

    private final String name;
    private final int endDay;
    private final int endHour;
    private final int endMinute;
    private final int endMonth;
    private final int startDay;
    private final int startHour;
    private final int startMinute;
    private final int startMonth;

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

    public CommunityDay (String name, int endDay, int endHour, int endMinute, int endMonth, int startDay, int startHour, int startMinute, int startMonth,
                         String guiDisplayName, List<String> guiLore, String guiRepresentationSpecies,
                         String form, int maxLevel, int minLevel, Map<String, Double> specialMoves, double shinyChance, String species, int specialMoveAmount, Map<String, Double> specialTextures,
                         List<String> worldBlacklist
                         ) {

        this.name = name;
        this.endDay = endDay;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.endMonth = endMonth;
        this.startDay = startDay;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.startMonth = startMonth;
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

    public int getEndDay() {

        return this.endDay;

    }

    public int getEndHour() {

        return this.endHour;

    }

    public int getEndMinute() {

        return this.endMinute;

    }

    public int getEndMonth() {

        return this.endMonth;

    }

    public int getStartDay() {

        return this.startDay;

    }

    public int getStartHour() {

        return this.startHour;

    }

    public int getStartMinute() {

        return this.startMinute;

    }

    public int getStartMonth() {

        return this.startMonth;

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
