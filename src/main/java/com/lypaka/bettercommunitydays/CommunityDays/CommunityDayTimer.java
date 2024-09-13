package com.lypaka.bettercommunitydays.CommunityDays;

import com.lypaka.bettercommunitydays.BetterCommunityDays;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class CommunityDayTimer {

    public static void startTimer() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                LocalDateTime now = LocalDateTime.now();
                //LocalDateTime.of(int year, int month, int dayOfMonth, int hour, int minute, int second)

                for (Map.Entry<String, CommunityDay> entry : CommunityDayHandler.communityDayMap.entrySet()) {

                    CommunityDay communityDay = entry.getValue();
                    int endYear = communityDay.getEndYear();
                    int endMonth = communityDay.getEndMonth();
                    int endDay = communityDay.getEndDay();
                    int endHour = communityDay.getEndHour();
                    int endMinute = communityDay.getEndMinute();
                    int endSecond = communityDay.getEndSecond();
                    int startYear = communityDay.getStartYear();
                    int startMonth = communityDay.getStartMonth();
                    int startDay = communityDay.getStartDay();
                    int startHour = communityDay.getStartHour();
                    int startMinute = communityDay.getStartMinute();
                    int startSecond = communityDay.getStartSecond();

                    LocalDateTime start = LocalDateTime.of(startYear, startMonth, startDay, startHour, startMinute, startSecond);
                    LocalDateTime end = LocalDateTime.of(endYear, endMonth, endDay, endHour, endMinute, endSecond);
                    if (communityDay.isConfigured()) {

                        if (!communityDay.isActive()) {

                            if (now.isAfter(start) && now.isBefore(end)) {

                                communityDay.setActive(true);
                                BetterCommunityDays.logger.info("Activating Community Day: " + entry.getKey());
                                CommunityDayHandler.activeCommunityDays.add(communityDay);

                            }

                        } else {

                            if (now.isAfter(end)) {

                                communityDay.setActive(false);
                                CommunityDayHandler.activeCommunityDays.removeIf(e -> e.getName().equalsIgnoreCase(entry.getKey()));
                                BetterCommunityDays.logger.info("Deactivating Community Day: " + entry.getKey());

                            }

                        }

                    } else {

                        if (communityDay.isActive()) {

                            communityDay.setActive(false);
                            CommunityDayHandler.activeCommunityDays.removeIf(e -> e.getName().equalsIgnoreCase(entry.getKey()));
                            BetterCommunityDays.logger.info("Deactivating Community Day: " + entry.getKey());

                        }

                    }

                }

            }

        }, 0, 1000L);

    }

}
