package com.lypaka.bettercommunitydays.CommunityDays;

import com.lypaka.bettercommunitydays.BetterCommunityDays;
import com.pixelmonmod.pixelmon.api.pokemon.species.Species;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.api.spawning.SpawnInfo;
import com.pixelmonmod.pixelmon.api.spawning.SpawnSet;
import com.pixelmonmod.pixelmon.api.spawning.archetypes.entities.pokemon.SpawnInfoPokemon;
import com.pixelmonmod.pixelmon.spawning.PixelmonSpawning;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonBiomesMap {

    public static Map<String, List<Integer>> pokemonBiomesMap = new HashMap<>();

    public static void load() {

        List<SpawnSet> list = PixelmonSpawning.standard;
        list.addAll(PixelmonSpawning.legendaries);
        for (Species species : PixelmonSpecies.getAll()) {

            for (SpawnSet set : list) {

                for (SpawnInfo spawnInfo : set.spawnInfos) {

                    if (spawnInfo instanceof SpawnInfoPokemon) {

                        SpawnInfoPokemon sip = (SpawnInfoPokemon) spawnInfo;
                        if (sip.getSpecies() != null && species != null && sip.getSpecies().getDex() == species.getDex() && sip.getSpecies().equals(species)) {

                            for (ResourceLocation rl : sip.condition.biomes) {

                                List<Integer> dexNumbers = new ArrayList<>();
                                if (pokemonBiomesMap.containsKey(rl.getNamespace())) {

                                    dexNumbers = pokemonBiomesMap.get(rl.getNamespace());

                                }
                                if (!dexNumbers.contains(species.getDex())) {

                                    dexNumbers.add(species.getDex());

                                }
                                pokemonBiomesMap.put(rl.getNamespace(), dexNumbers);

                            }

                        }

                    }

                }

            }

        }

        BetterCommunityDays.logger.info("Finished loading the really big Pokemon<->Biomes map!");

    }

}
