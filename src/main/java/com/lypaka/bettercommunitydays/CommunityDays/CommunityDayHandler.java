package com.lypaka.bettercommunitydays.CommunityDays;

import com.google.common.reflect.TypeToken;
import com.lypaka.bettercommunitydays.BetterCommunityDays;
import com.lypaka.bettercommunitydays.ConfigGetters;
import com.lypaka.lypakautils.MiscHandlers.PixelmonHelpers;
import com.pixelmonmod.api.Flags;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonBuilder;
import com.pixelmonmod.pixelmon.api.pokemon.ability.AbilityRegistry;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.battles.attacks.ImmutableAttack;
import com.pixelmonmod.pixelmon.client.gui.starter.ChooseStarterScreen;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommunityDayHandler {

    public static Map<String, CommunityDay> communityDayMap;
    public static Map<String, CommunityDay> activeCommunityDays = new HashMap<>();

    public static List<CommunityDay> getCommunityDaysFromBiome (String biome, String worldName) {

        List<CommunityDay> days = new ArrayList<>();
        if (!PokemonBiomesMap.pokemonBiomesMap.containsKey(biome)) return days;

        List<Integer> dexNums = PokemonBiomesMap.pokemonBiomesMap.get(biome);
        for (Map.Entry<String, CommunityDay> entry : activeCommunityDays.entrySet()) {

            CommunityDay day = entry.getValue();
            if (!day.getWorldBlacklist().contains(worldName)) {

                if (dexNums.contains(day.getPokemonDexNumber())) {

                    if (!days.contains(day)) days.add(day);

                }

            }

        }

        return days;

    }

    public static Pokemon buildCommunityDaySpawn (CommunityDay communityDay) {

        String species = communityDay.getSpecies();
        String form = communityDay.getForm();

        Pokemon pokemon = PixelmonHelpers.fixPokemonIVsAndGender(PokemonBuilder.builder().species(species).build());
        if (!form.equalsIgnoreCase("default")) pokemon.setForm(form);

        int level = RandomHelper.getRandomNumberBetween(communityDay.getMinLevel(), communityDay.getMaxLevel());
        pokemon.setLevel(level);
        pokemon.setLevelNum(level);
        List<ImmutableAttack> moves = new ArrayList<>(pokemon.getForm().getMoves().getMovesUpToLevel(level));
        ImmutableAttack a1 = RandomHelper.getRandomElementFromList(moves);
        moves.remove(a1);
        ImmutableAttack a2 = null;
        if (moves.size() > 0) {

            a2 = RandomHelper.getRandomElementFromList(moves);
            moves.remove(a2);

        }
        ImmutableAttack a3 = null;
        if (moves.size() > 0) {

            a3 = RandomHelper.getRandomElementFromList(moves);
            moves.remove(a3);

        }
        ImmutableAttack a4 = null;
        if (moves.size() > 0) {

            a4 = RandomHelper.getRandomElementFromList(moves);

        }
        pokemon.getMoveset().set(0, new Attack(a1.getAttackName()));
        pokemon.getMoveset().set(1, a2 == null ? null : new Attack(a2.getAttackName()));
        pokemon.getMoveset().set(2, a3 == null ? null : new Attack(a3.getAttackName()));
        pokemon.getMoveset().set(3, a4 == null ? null : new Attack(a4.getAttackName()));
        Attack attack1 = pokemon.getMoveset().get(0);
        Attack attack2 = pokemon.getMoveset().get(1);
        Attack attack3 = pokemon.getMoveset().get(2);
        Attack attack4 = pokemon.getMoveset().get(3);

        int specialMoveAmount = communityDay.getSpecialMoveAmount();
        int specialMoves = 0;
        List<String> addedMoves = new ArrayList<>();
        if (communityDay.getSpecialMoves().size() > 0) {

            for (Map.Entry<String, Double> entry : communityDay.getSpecialMoves().entrySet()) {

                if (specialMoves == specialMoveAmount) break;
                String move = entry.getKey();
                double chance = entry.getValue();
                if (RandomHelper.getRandomChance(chance)) {

                    if (!addedMoves.contains(move)) {

                        addedMoves.add(move);
                        specialMoves++;
                        if (attack1 == null) {

                            pokemon.getMoveset().set(0, new Attack(move));

                        } else if (attack2 == null) {

                            pokemon.getMoveset().set(1, new Attack(move));

                        } else if (attack3 == null) {

                            pokemon.getMoveset().set(2, new Attack(move));

                        } else if (attack4 == null) {

                            pokemon.getMoveset().set(3, new Attack(move));

                        } else {

                            pokemon.getMoveset().set(0, new Attack((move)));

                        }

                    }

                }

            }

        }
        pokemon.setShiny(RandomHelper.getRandomChance(communityDay.getShinyChance()));
        if (communityDay.getSpecialTextures().size() > 0) {

            for (Map.Entry<String, Double> entry : communityDay.getSpecialTextures().entrySet()) {

                if (RandomHelper.getRandomChance(entry.getValue())) {

                    pokemon.setPalette(entry.getKey());
                    break;

                }

            }

        }
        if (!communityDay.getAbilities().isEmpty()) {

            for (Map.Entry<String, Double> entry : communityDay.getAbilities().entrySet()) {

                String ability = entry.getKey();
                double chance = entry.getValue();
                if (RandomHelper.getRandomChance(chance)) {

                    pokemon.setAbility(AbilityRegistry.getAbility(ability));
                    break;

                }

            }

        }

        if (!communityDay.getFlag().equalsIgnoreCase("")) {

            pokemon.addFlag(communityDay.getFlag());

        }

        return pokemon;

    }

    public static void loadCommunityDays() throws ObjectMappingException {

        activeCommunityDays = new HashMap<>();
        communityDayMap = new HashMap<>();
        boolean needsSaving = false;
        for (int i = 0; i < ConfigGetters.communityDays.size(); i++) {

            String name = ConfigGetters.communityDays.get(i).replace(".conf", "");
            boolean configured = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "Configured").getBoolean();
            String[] end = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "End-Time").getString().split(", ");
            int[] endTime = new int[end.length];
            for (int e = 0; e < end.length; e++) {

                endTime[e] = Integer.parseInt(end[e]);

            }
            String[] start;// = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "Start-Time").getString().split(", ");
            if (!BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "Start-Time").isVirtual()) {

                start = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "Start-Time").getString().split(", ");
                String string = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "Start-Time").getString();
                BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "Start-Time").setValue(null);
                BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "Begin-Time").setValue(string);
                needsSaving = true;

            } else {

                start = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "Begin-Time").getString().split(", ");

            }
            int[] startTime = new int[start.length];
            for (int s = 0; s < start.length; s++) {

                startTime[s] = Integer.parseInt(start[s]);

            }

            String guiDisplayName = BetterCommunityDays.communityDayManager.getConfigNode(i, "GUI-Data", "Display-Name").getString();
            List<String> guiLore = BetterCommunityDays.communityDayManager.getConfigNode(i, "GUI-Data", "Lore").getList(TypeToken.of(String.class));
            String guiRepresentationSpecies = BetterCommunityDays.communityDayManager.getConfigNode(i, "GUI-Data", "Representation-Species").getString();

            Map<String, Double> abilities = new HashMap<>();
            if (BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Abilities").isVirtual()) {

                BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Abilities").setValue(abilities);
                if (!needsSaving) needsSaving = true;

            } else {

                abilities = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Abilities").getValue(new TypeToken<Map<String, Double>>() {});

            }
            String flag = "";
            if (BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Flag").isVirtual()) {

                BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Flag").setComment("Allows for setting a mark/flag on the Pokemon. To not set one, leave as \"\"");
                BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Flag").setValue("");
                if (!needsSaving) needsSaving = true;

            } else {

                flag = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Flag").getString();

            }
            String form = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Form").getString();
            double ivBoost = 0;
            if (BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "IV-Boost-Percentage").isVirtual()) {

                BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "IV-Boost-Percentage").setValue(ivBoost);
                if (!needsSaving) needsSaving = true;

            } else {

                ivBoost = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "IV-Boost-Percentage").getDouble();

            }
            int maxLevel = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Max-Level").getInt();
            int minLevel = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Min-Level").getInt();
            Map<String, Double> specialMoves = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Moves").getValue(new TypeToken<Map<String, Double>>() {});
            double shinyChance = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Shiny-Chance").getDouble();
            double spawnChance = 0.60;
            if (BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Spawn-Chance").isVirtual()) {

                BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Spawn-Chance").setComment("Sets the spawn chance for the Pokemon, i.e. the chance that the Community Day spawn overwrites the default spawn from Pixelmon");
                BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Spawn-Chance").setValue(0.60);
                BetterCommunityDays.communityDayManager.save();

            } else {

                spawnChance = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Spawn-Chance").getDouble();

            }
            String species = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Species").getString();
            int specialMoveAmount = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Special-Move-Amount").getInt();
            Map<String, Double> specialTextures = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Textures").getValue(new TypeToken<Map<String, Double>>() {});
            List<String> worldBlacklist = BetterCommunityDays.communityDayManager.getConfigNode(i, "World-Blacklist").getList(TypeToken.of(String.class));

            CommunityDay communityDay = new CommunityDay(i, name, configured, endTime, startTime, guiDisplayName, guiLore, guiRepresentationSpecies, abilities,
                    flag, form, ivBoost, maxLevel, minLevel, specialMoves, shinyChance, spawnChance, species, specialMoveAmount, specialTextures, worldBlacklist);

            communityDayMap.put(name, communityDay);
            BetterCommunityDays.logger.info("Successfully created and loaded Community Day: " + name);

        }
        if (needsSaving) {

            BetterCommunityDays.communityDayManager.save();

        }

    }

}
