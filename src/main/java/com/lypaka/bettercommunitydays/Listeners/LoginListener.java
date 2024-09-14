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

@Mod.EventBusSubscriber(modid = BetterCommunityDays.MOD_ID)
public class LoginListener {

    @SubscribeEvent
    public static void onJoin (PlayerEvent.PlayerLoggedInEvent event) {

        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        for (CommunityDay day : CommunityDayHandler.activeCommunityDays) {

            player.sendMessage(FancyText.getFormattedText(ConfigGetters.message.replace("%pokemon%", day.getSpecies())), player.getUUID());

        }

    }

}
