package com.lypaka.bettercommunitydays.Listeners;

import com.lypaka.bettercommunitydays.API.CommunityDayCaptureEvent;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CaptureListener {

    @SubscribeEvent
    public void onCapture (CaptureEvent.StartCapture event) {

        ServerPlayerEntity player = event.getPlayer();
        Pokemon pokemon = event.getPokemon().getPokemon();
        int catchrate = event.getCaptureValues().getCatchRate();
        if (pokemon.getPersistentData().contains("CommunityDaySpawn")) {

            CommunityDayCaptureEvent cdce = new CommunityDayCaptureEvent(player, pokemon, catchrate);
            MinecraftForge.EVENT_BUS.post(cdce);
            event.getCaptureValues().setCatchRate(cdce.getCatchrate());

        }

    }

}
