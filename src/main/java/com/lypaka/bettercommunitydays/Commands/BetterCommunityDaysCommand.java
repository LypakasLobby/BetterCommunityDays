package com.lypaka.bettercommunitydays.Commands;

import com.lypaka.bettercommunitydays.BetterCommunityDays;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = BetterCommunityDays.MOD_ID)
public class BetterCommunityDaysCommand {

    public static final List<String> ALIASES = Arrays.asList("bettercommunitydays", "communitydays", "bcd", "commday");

    @SubscribeEvent
    public static void onCommandRegistration (RegisterCommandsEvent event) {

        new ReloadCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());

    }

}
