package com.lypaka.bettercommunitydays.Listeners;

import com.lypaka.bettercommunitydays.BetterCommunityDays;
import com.lypaka.bettercommunitydays.CommunityDays.CommunityDay;
import com.lypaka.bettercommunitydays.CommunityDays.CommunityDayHandler;
import com.lypaka.bettercommunitydays.ConfigGetters;
import com.lypaka.lypakautils.FancyText;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = BetterCommunityDays.MOD_ID)
public class LoginListener {

    @SubscribeEvent
    public static void onJoin (PlayerEvent.PlayerLoggedInEvent event) {

        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        for (Map.Entry<String, CommunityDay> entry : CommunityDayHandler.activeCommunityDays.entrySet()) {

            player.sendMessage(FancyText.getFormattedText(ConfigGetters.message.replace("%pokemon%", entry.getValue().getSpecies())), player.getUUID());

        }

    }

}
