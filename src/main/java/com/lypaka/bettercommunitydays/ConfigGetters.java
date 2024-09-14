package com.lypaka.bettercommunitydays;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.util.List;
import java.util.Map;

public class ConfigGetters {

    public static String broadcastEnd;
    public static String broadcastStart;
    public static List<String> communityDays;
    public static boolean glowing;
    public static String message;

    public static int guiRows;
    public static String guiTitle;
    public static Map<String, String> guiBorder;

    public static void load() throws ObjectMappingException {

        boolean needsSaving = false;
        if (!BetterCommunityDays.configManager.getConfigNode(0, "Chance").isVirtual()) {

            BetterCommunityDays.configManager.getConfigNode(0, "Chance").setValue(null);
            needsSaving = true;

        }
        broadcastStart = "&aA Community Day for %pokemon% has just activated!";
        broadcastEnd = "&aThe Community Day for %pokemon% has ended!";
        message = "&eA Community Day for %pokemon% is currently active!";
        if (BetterCommunityDays.configManager.getConfigNode(0, "Broadcast").isVirtual()) {

            if (!needsSaving) needsSaving = true;
            BetterCommunityDays.configManager.getConfigNode(0, "Broadcast-Start").setValue("&aA Community Day for %pokemon% has just activated!");
            BetterCommunityDays.configManager.getConfigNode(0, "Broadcast-Start").setComment("A broadcast that goes off to all online players when a Community Day activates");
            BetterCommunityDays.configManager.getConfigNode(0, "Broadcast-End").setValue("&aThe Community Day for %pokemon% has ended!");
            BetterCommunityDays.configManager.getConfigNode(0, "Broadcast-End").setComment("A broadcast that goes off to all online players when a Community Day deactivates");
            BetterCommunityDays.configManager.getConfigNode(0, "Message").setValue("&eA Community Day for %pokemon% is currently active!");
            BetterCommunityDays.configManager.getConfigNode(0, "Message").setComment("A message sent to players for every active Community Day when they log in");

        } else {

            broadcastStart = BetterCommunityDays.configManager.getConfigNode(0, "Broadcast-Start").getString();
            broadcastEnd = BetterCommunityDays.configManager.getConfigNode(0, "Broadcast-End").getString();
            message = BetterCommunityDays.configManager.getConfigNode(0, "Message").getString();

        }
        communityDays = BetterCommunityDays.configManager.getConfigNode(0, "Days").getList(TypeToken.of(String.class));
        glowing = BetterCommunityDays.configManager.getConfigNode(0, "Glowing").getBoolean();

        guiRows = BetterCommunityDays.configManager.getConfigNode(1, "General", "Rows").getInt();
        guiTitle = BetterCommunityDays.configManager.getConfigNode(1, "General", "Title").getString();
        guiBorder = BetterCommunityDays.configManager.getConfigNode(1, "Border").getValue(new TypeToken<Map<String, String>>() {});

        if (needsSaving) {

            BetterCommunityDays.configManager.save();

        }

    }

}
