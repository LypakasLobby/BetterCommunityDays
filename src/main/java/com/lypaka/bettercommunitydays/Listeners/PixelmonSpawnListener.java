package com.lypaka.bettercommunitydays.Listeners;

import com.lypaka.bettercommunitydays.API.CommunityDaySpawnEvent;
import com.lypaka.bettercommunitydays.CommunityDays.CommunityDay;
import com.lypaka.bettercommunitydays.CommunityDays.CommunityDayHandler;
import com.lypaka.bettercommunitydays.ConfigGetters;
import com.lypaka.lypakautils.WorldStuff.WorldMap;
import com.pixelmonmod.pixelmon.api.events.spawning.SpawnEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import com.pixelmonmod.pixelmon.spawning.PlayerTrackingSpawner;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class PixelmonSpawnListener {

    @SubscribeEvent
    public void onSpawn (SpawnEvent event) {

        if (CommunityDayHandler.activeCommunityDays.size() == 0) return;
        if (!RandomHelper.getRandomChance(ConfigGetters.spawnChance)) return;

        if (event.action.getOrCreateEntity() instanceof PixelmonEntity) {

            if (event.spawner instanceof PlayerTrackingSpawner) {

                PixelmonEntity originalPixelmon = (PixelmonEntity) event.action.getOrCreateEntity();
                ServerPlayerEntity player = ((PlayerTrackingSpawner) event.spawner).getTrackedPlayer();
                if (player == null) return;
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

                int x = event.action.spawnLocation.location.pos.getX();
                int y = event.action.spawnLocation.location.pos.getY();
                int z = event.action.spawnLocation.location.pos.getZ();

                event.setCanceled(true);

                Pokemon pokemon = CommunityDayHandler.buildCommunityDaySpawn(day);
                CommunityDaySpawnEvent cdse = new CommunityDaySpawnEvent(player, pokemon, originalPixelmon.getPokemon());
                MinecraftForge.EVENT_BUS.post(cdse);
                if (!cdse.isCanceled()) {

                    cdse.getCommunityDaySpawn().getPersistentData().putBoolean("CommunityDaySpawn", true);
                    PixelmonEntity spawnedPixelmon = cdse.getCommunityDaySpawn().getOrSpawnPixelmon(player.level, x, y, z);
                    spawnedPixelmon.setPos(x, y, z);
                    spawnedPixelmon.setSpawnLocation(spawnedPixelmon.getDefaultSpawnLocation());
                    if (ConfigGetters.glowing) {

                        spawnedPixelmon.setGlowing(true);

                    }

                }

            }

        }

    }

}
