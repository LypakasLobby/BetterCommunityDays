package com.lypaka.bettercommunitydays;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.util.List;
import java.util.Map;

public class ConfigGetters {

    public static double spawnChance;
    public static List<String> communityDays;
    public static boolean glowing;

    public static int guiRows;
    public static String guiTitle;
    public static Map<String, String> guiBorder;

    public static void load() throws ObjectMappingException {

        spawnChance = BetterCommunityDays.configManager.getConfigNode(0, "Chance").getDouble();
        communityDays = BetterCommunityDays.configManager.getConfigNode(0, "Days").getList(TypeToken.of(String.class));
        glowing = BetterCommunityDays.configManager.getConfigNode(0, "Glowing").getBoolean();

        guiRows = BetterCommunityDays.configManager.getConfigNode(1, "General", "Rows").getInt();
        guiTitle = BetterCommunityDays.configManager.getConfigNode(1, "General", "Title").getString();
        guiBorder = BetterCommunityDays.configManager.getConfigNode(1, "Border").getValue(new TypeToken<Map<String, String>>() {});

    }

}
