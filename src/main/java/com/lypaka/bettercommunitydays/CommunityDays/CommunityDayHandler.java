package com.lypaka.bettercommunitydays.CommunityDays;

import com.google.common.reflect.TypeToken;
import com.lypaka.bettercommunitydays.BetterCommunityDays;
import com.lypaka.bettercommunitydays.ConfigGetters;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommunityDayHandler {

    public static Map<String, CommunityDay> communityDayMap;

    public static void loadCommunityDays() throws ObjectMappingException {

        communityDayMap = new HashMap<>();
        for (int i = 0; i < ConfigGetters.communityDays.size(); i++) {

            int endDay = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "End-Day").getInt();
            int endHour = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "End-Hour").getInt();
            int endMinute = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "End-Minute").getInt();
            int endMonth = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "End-Month").getInt();
            int startDay = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "Start-Day").getInt();
            int startHour = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "Start-Hour").getInt();
            int startMinute = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "Start-Minute").getInt();
            int startMonth = BetterCommunityDays.communityDayManager.getConfigNode(i, "Event-Data", "Start-Month").getInt();

            String guiDisplayName = BetterCommunityDays.communityDayManager.getConfigNode(i, "GUI-Data", "Display-Name").getString();
            List<String> guiLore = BetterCommunityDays.communityDayManager.getConfigNode(i, "GUI-Data", "Lore").getList(TypeToken.of(String.class));
            String guiRepresentationSpecies = BetterCommunityDays.communityDayManager.getConfigNode(i, "GUI-Data", "Representation-Species").getString();

            String form = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Form").getString();
            int maxLevel = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Max-Level").getInt();
            int minLevel = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Min-Level").getInt();
            Map<String, Double> specialMoves = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Moves").getValue(new TypeToken<Map<String, Double>>() {});
            double shinyChance = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Shiny-Chance").getDouble();
            String species = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Species").getString();
            int specialMoveAmount = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Special-Move-Amount").getInt();
            Map<String, Double> specialTextures = BetterCommunityDays.communityDayManager.getConfigNode(i, "Pokemon-Data", "Textures").getValue(new TypeToken<Map<String, Double>>() {});

            CommunityDay communityDay = new CommunityDay(endDay, endHour, endMinute, endMonth, startDay, startHour, startMinute, startMonth, guiDisplayName, guiLore, guiRepresentationSpecies,
                    form, maxLevel, minLevel, specialMoves, shinyChance, species, specialMoveAmount, specialTextures);

            String name = ConfigGetters.communityDays.get(i).replace(".conf", "");
            communityDayMap.put(name, communityDay);
            BetterCommunityDays.logger.info("Successfully created and loaded Community Day: " + name);

        }

    }

}
