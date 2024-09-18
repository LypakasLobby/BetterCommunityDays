package com.lypaka.bettercommunitydays.Listeners;

import com.lypaka.bettercommunitydays.API.CommunityDayCaptureEvent;
import com.lypaka.bettercommunitydays.CommunityDays.CommunityDay;
import com.lypaka.bettercommunitydays.CommunityDays.CommunityDayHandler;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.stats.BattleStatsType;
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

    @SubscribeEvent
    public void onCaught (CaptureEvent.SuccessfulCapture event) {

        Pokemon pokemon = event.getPokemon().getPokemon();
        if (pokemon.getPersistentData().contains("FromCommunityDay")) {

            String communityDayName = pokemon.getPersistentData().getString("FromCommunityDay");
            CommunityDay communityDay = CommunityDayHandler.communityDayMap.get(communityDayName);
            if (communityDay.getIVBoost() > 0) {

                double boost = communityDay.getIVBoost();
                int hpIV = pokemon.getIVs().getArray()[0];
                int atkIV = pokemon.getIVs().getArray()[1];
                int defIV = pokemon.getIVs().getArray()[2];
                int spAtkIV = pokemon.getIVs().getArray()[3];
                int spDefIV = pokemon.getIVs().getArray()[4];
                int spdIV = pokemon.getIVs().getArray()[5];

                int updatedHP = (int) Math.min(31, (hpIV * boost) + hpIV);
                int updatedAtk = (int) Math.min(31, (atkIV * boost) + atkIV);
                int updatedDef = (int) Math.min(31, (defIV * boost) + defIV);
                int updatedSpAtk = (int) Math.min(31, (spAtkIV * boost) + spAtkIV);
                int updatedSpDef = (int) Math.min(31, (spDefIV * boost) + spDefIV);
                int updatedSpd = (int) Math.min(31, (spdIV * boost) + spdIV);

                int[] newIVs = new int[]{updatedHP, updatedAtk, updatedDef, updatedSpAtk, updatedSpDef, updatedSpd};
                pokemon.getIVs().fillFromArray(newIVs);

            }

        }

    }

}
