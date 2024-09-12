package com.lypaka.bettercommunitydays;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.util.List;

public class ConfigGetters {

    public static List<String> communityDays;

    public static void load() throws ObjectMappingException {

        communityDays = BetterCommunityDays.configManager.getConfigNode(0, "Days").getList(TypeToken.of(String.class));

    }

}
