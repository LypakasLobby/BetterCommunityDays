package com.lypaka.bettercommunitydays.Listeners;

import com.lypaka.bettercommunitydays.BetterCommunityDays;
import com.lypaka.bettercommunitydays.CommunityDays.CommunityDayHandler;
import com.lypaka.bettercommunitydays.CommunityDays.CommunityDayTimer;
import com.lypaka.bettercommunitydays.CommunityDays.PokemonBiomesMap;
import com.lypaka.bettercommunitydays.Listeners.SpawnManagerListeners.*;
import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

@Mod.EventBusSubscriber(modid = BetterCommunityDays.MOD_ID)
public class ServerStartedListener {

    @SubscribeEvent
    public static void onServerStarted (FMLServerStartedEvent event) throws ObjectMappingException {

        Pixelmon.EVENT_BUS.register(new PixelmonSpawnListener());
        Pixelmon.EVENT_BUS.register(new CaptureListener());

        if (ModList.get().isLoaded("spawnmanager")) {

            MinecraftForge.EVENT_BUS.register(new FishSpawnEvent());
            MinecraftForge.EVENT_BUS.register(new GrassSpawnEvent());
            MinecraftForge.EVENT_BUS.register(new HeadbuttSpawnEvent());
            MinecraftForge.EVENT_BUS.register(new NaturalSpawnEvent());
            MinecraftForge.EVENT_BUS.register(new RockSmashSpawnEvent());
            MinecraftForge.EVENT_BUS.register(new SurfSpawnEvent());

        }

        PokemonBiomesMap.load();
        CommunityDayHandler.loadCommunityDays();
        CommunityDayTimer.startTimer();

    }

}
