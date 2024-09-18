package com.lypaka.bettercommunitydays.Listeners.SpawnManagerListeners;

import com.lypaka.areamanager.Areas.Area;
import com.lypaka.bettercommunitydays.API.CommunityDaySpawnEvent;
import com.lypaka.bettercommunitydays.CommunityDays.CommunityDay;
import com.lypaka.bettercommunitydays.CommunityDays.CommunityDayHandler;
import com.lypaka.lypakautils.WorldStuff.WorldMap;
import com.lypaka.spawnmanager.API.AreaSurfSpawnEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.Map;

public class SurfSpawnEvent {

    @SubscribeEvent
    public void onSurfSpawn (AreaSurfSpawnEvent event) {

        ServerPlayerEntity player = event.getPlayer();
        Pokemon pokemon = event.getPokemon();
        String worldName = WorldMap.getWorldName(player);
        String biome = player.level.getBiome(player.blockPosition()).getRegistryName().toString();
        List<CommunityDay> communityDays = CommunityDayHandler.getCommunityDaysFromBiome(biome, worldName);

        if (communityDays.size() == 0) return;

        CommunityDay day;
        if (communityDays.size() > 1) {

            day = RandomHelper.getRandomElementFromList(communityDays);

        } else {

            day = communityDays.get(0);

        }

        Area area = event.getArea();
        for (Map.Entry<String, String> entry : day.getSpawnManagerAreas().entrySet()) {

            if (entry.getKey().equalsIgnoreCase(area.getName())) {

                String spawners = entry.getValue();
                if (spawners.contains("surf") || spawners.contains("surfing")) {

                    Pokemon communityDaySpawn = CommunityDayHandler.buildCommunityDaySpawn(day);
                    CommunityDaySpawnEvent cdse = new CommunityDaySpawnEvent(player, communityDaySpawn, pokemon);
                    MinecraftForge.EVENT_BUS.post(cdse);
                    if (!cdse.isCanceled()) {

                        Pokemon spawn = cdse.getCommunityDaySpawn();
                        spawn.getPersistentData().putBoolean("CommunityDaySpawn", true);
                        spawn.getPersistentData().putString("FromCommunityDay", day.getName());
                        event.setPokemon(spawn);
                        break;

                    }

                }

            }

        }

    }

}
