package com.lypaka.bettercommunitydays.Listeners;

import com.lypaka.bettercommunitydays.BetterCommunityDays;
import com.lypaka.bettercommunitydays.CommunityDays.CommunityDayHandler;
import com.lypaka.bettercommunitydays.CommunityDays.PokemonBiomesMap;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

@Mod.EventBusSubscriber(modid = BetterCommunityDays.MOD_ID)
public class ServerStartedListener {

    @SubscribeEvent
    public static void onServerStarted (FMLServerStartedEvent event) throws ObjectMappingException {

        PokemonBiomesMap.load();
        CommunityDayHandler.loadCommunityDays();

    }

}
