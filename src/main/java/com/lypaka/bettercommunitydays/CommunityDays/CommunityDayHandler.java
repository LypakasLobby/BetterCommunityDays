package com.lypaka.bettercommunitydays.CommunityDays;

import com.google.common.reflect.TypeToken;
import com.lypaka.bettercommunitydays.BetterCommunityDays;
import com.lypaka.bettercommunitydays.ConfigGetters;
import com.lypaka.lypakautils.MiscHandlers.PixelmonHelpers;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonBuilder;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.battles.attacks.ImmutableAttack;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommunityDayHandler {

    public static Map<String, CommunityDay> communityDayMap;
    public static List<CommunityDay> activeCommunityDays = new ArrayList<>();

    public static List<CommunityDay> getCommunityDaysFromBiome (String biome) {

        List<CommunityDay> days = new ArrayList<>();
        if (!PokemonBiomesMap.pokemonBiomesMap.containsKey(biome)) return days;

        List<Integer> dexNums = PokemonBiomesMap.pokemonBiomesMap.get(biome);
        for (CommunityDay day : activeCommunityDays) {

            if (dexNums.contains(day.getPokemonDexNumber())) {

                if (!days.contains(day)) days.add(day);

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

        return pokemon;

    }

    public static void loadCommunityDays() throws ObjectMappingException {

        communityDayMap = new HashMap<>();
        for (int i = 0; i < ConfigGetters.communityDays.size(); i++) {

            String name = ConfigGetters.communityDays.get(i).replace(".conf", "");
            int endDay = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "End-Day").getInt();
            int endHour = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "End-Hour").getInt();
            int endMinute = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "End-Minute").getInt();
            int endMonth = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "End-Month").getInt();
            int startDay = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "Start-Day").getInt();
            int startHour = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "Start-Hour").getInt();
            int startMinute = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "Start-Minute").getInt();
            int startMonth = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "Start-Month").getInt();

            String guiDisplayName = BetterCommunityDays.communityDayManager.getConfigNode(i, "GUI-Data", "Display-Name").getString();
            List<String> guiLore = BetterCommunityDays.communityDayManager.getConfigNode(i, "GUI-Data", "Lore").getList(TypeToken.of(String.class));
            String guiRepresentationSpecies = BetterCommunityDays.communityDayManager.getConfigNode(i, "GUI-Data", "Representation-Species").getString();

            String form = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Form").getString();
            int maxLevel = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Max-Level").getInt();
            int minLevel = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Min-Level").getInt();
            Map<String, Double> specialMoves = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Moves").getValue(new TypeToken<Map<String, Double>>() {});
            double shinyChance = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Shiny-Chance").getDouble();
            String species = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Species").getString();
            int specialMoveAmount = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Special-Move-Amount").getInt();
            Map<String, Double> specialTextures = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Textures").getValue(new TypeToken<Map<String, Double>>() {});

            CommunityDay communityDay = new CommunityDay(name, endDay, endHour, endMinute, endMonth, startDay, startHour, startMinute, startMonth, guiDisplayName, guiLore, guiRepresentationSpecies,
                    form, maxLevel, minLevel, specialMoves, shinyChance, species, specialMoveAmount, specialTextures);

            communityDayMap.put(name, communityDay);
            BetterCommunityDays.logger.info("Successfully created and loaded Community Day: " + name);

        }

    }

}
