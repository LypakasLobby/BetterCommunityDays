package com.lypaka.bettercommunitydays.Commands;

import com.lypaka.bettercommunitydays.CommunityDays.CommunityDayHandler;
import com.lypaka.bettercommunitydays.GUIs.CommunityDayMenu;
import com.lypaka.lypakautils.FancyText;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

public class MenuCommand {

    public MenuCommand (CommandDispatcher<CommandSource> dispatcher) {

        for (String a : BetterCommunityDaysCommand.ALIASES) {

            dispatcher.register(
                    Commands.literal(a)
                            .then(
                                    Commands.literal("menu")
                                            .executes(c -> {

                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                    if (CommunityDayHandler.activeCommunityDays.size() == 0) {

                                                        player.sendMessage(FancyText.getFormattedText("&cNo active Community Days to show!"), player.getUUID());
                                                        return 0;

                                                    } else {

                                                        CommunityDayMenu.openMenu(player);

                                                    }

                                                }

                                                return 1;

                                            })
                            )
                            .executes(c -> {

                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                    if (CommunityDayHandler.activeCommunityDays.size() == 0) {

                                        player.sendMessage(FancyText.getFormattedText("&cNo active Community Days to show!"), player.getUUID());
                                        return 0;

                                    } else {

                                        CommunityDayMenu.openMenu(player);

                                    }

                                }

                                return 1;

                            })
            );

        }

    }

}
