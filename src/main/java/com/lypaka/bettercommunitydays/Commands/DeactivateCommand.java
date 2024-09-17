package com.lypaka.bettercommunitydays.Commands;

import com.lypaka.bettercommunitydays.CommunityDays.CommunityDay;
import com.lypaka.bettercommunitydays.CommunityDays.CommunityDayHandler;
import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.PermissionHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.entity.player.ServerPlayerEntity;

public class DeactivateCommand {

    public DeactivateCommand (CommandDispatcher<CommandSource> dispatcher) {

        for (String a : BetterCommunityDaysCommand.ALIASES) {

            dispatcher.register(
                    Commands.literal(a)
                            .then(
                                    Commands.literal("deactivate")
                                            .then(
                                                    Commands.argument("communityday", StringArgumentType.word())
                                                            .suggests(
                                                                    (context, builder) -> ISuggestionProvider.suggest(CommunityDayHandler.activeCommunityDays.keySet(), builder)
                                                            )
                                                            .executes(c -> {

                                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                                    if (!PermissionHandler.hasPermission(player, "bettercommunitydays.command.admin")) {

                                                                        player.sendMessage(FancyText.getFormattedText("&cYou don't have permission to use this command!"), player.getUUID());
                                                                        return 0;

                                                                    }

                                                                }

                                                                String target = StringArgumentType.getString(c, "communityday");
                                                                CommunityDay communityDay = CommunityDayHandler.activeCommunityDays.get(target);
                                                                communityDay.setConfigured(false);
                                                                c.getSource().sendSuccess(FancyText.getFormattedText("&aSuccessfully queued Community Day: " + target + " for deactivation on the next iteration!"), true);
                                                                return 1;

                                                            })
                                            )
                            )
            );

        }

    }

}
