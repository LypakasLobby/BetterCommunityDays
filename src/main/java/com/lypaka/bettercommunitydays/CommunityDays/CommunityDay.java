package com.lypaka.bettercommunitydays.CommunityDays;

import com.lypaka.bettercommunitydays.BetterCommunityDays;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonBuilder;

import java.util.List;
import java.util.Map;

public class CommunityDay {

    private final int index;
    private final String name;
    private boolean configured;
    private final List<String> biomeBlacklist;
    private final int[] endTime;
    private final int[] startTime;

    private final String guiDisplayName;
    private final List<String> guiLore;
    private final String guiRepresentationSpecies;

    private final Map<String, Double> abilities;
    private final String flag;
    private final String form;
    private final double ivBoost;
    private final int maxLevel;
    private final int minLevel;
    private final Map<String, Double> specialMoves;
    private final double shinyChance;
    private final double spawnChance;
    private final String species;
    private final int specialMoveAmount;
    private final Map<String, Double> specialTextures;
    private final Map<String, String> spawnManagerAreas;
    private final List<String> worldBlacklist;

    private boolean active;
    private final Pokemon pokemonSpecies;

    public CommunityDay (int index, String name, boolean configured, List<String> biomeBlacklist, int[] endTime, int[] startTime, String guiDisplayName, List<String> guiLore, String guiRepresentationSpecies,
                         Map<String, Double> abilities, String flag, String form, double ivBoost, int maxLevel, int minLevel, Map<String, Double> specialMoves, double shinyChance, double spawnChance, String species, int specialMoveAmount, Map<String, Double> specialTextures,
                         Map<String, String> spawnManagerAreas, List<String> worldBlacklist) {

        this.index = index;
        this.name = name;
        this.configured = configured;
        this.biomeBlacklist = biomeBlacklist;
        this.endTime = endTime;
        this.startTime = startTime;
        this.guiDisplayName = guiDisplayName;
        this.guiLore = guiLore;
        this.guiRepresentationSpecies = guiRepresentationSpecies;
        this.abilities = abilities;
        this.flag = flag;
        this.form = form;
        this.ivBoost = ivBoost;
        this.maxLevel = maxLevel;
        this.minLevel = minLevel;
        this.specialMoves = specialMoves;
        this.shinyChance = shinyChance;
        this.spawnChance = spawnChance;
        this.species = species;
        this.specialMoveAmount = specialMoveAmount;
        this.specialTextures = specialTextures;
        this.spawnManagerAreas = spawnManagerAreas;
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

    public void setConfigured (boolean configured) {

        this.configured = configured;
        updatedConfigured();

    }

    private void updatedConfigured() {

        BetterCommunityDays.communityDayManager.getConfigNode(this.index, "Event-Data", "Configured").setValue(this.configured);
        BetterCommunityDays.communityDayManager.save();

    }

    public List<String> getBiomeBlacklist() {

        return this.biomeBlacklist;

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

    public Map<String, Double> getAbilities() {

        return this.abilities;

    }

    public String getFlag() {

        return this.flag;

    }

    public String getForm() {

        return this.form;

    }

    public double getIVBoost() {

        return this.ivBoost;

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

    public double getSpawnChance() {

        return this.spawnChance;

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

    public Map<String, String> getSpawnManagerAreas() {

        return this.spawnManagerAreas;

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
