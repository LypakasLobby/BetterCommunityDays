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
                int nowDay = now.getDayOfMonth();
                int nowMonth = now.getMonthValue();
                int nowHour = now.getHour();
                int nowMinute = now.getMinute();

                for (Map.Entry<String, CommunityDay> entry : CommunityDayHandler.communityDayMap.entrySet()) {

                    CommunityDay communityDay = entry.getValue();
                    int endDay = communityDay.getEndDay();
                    int endHour = communityDay.getEndHour();
                    int endMinute = communityDay.getEndMinute();
                    int endMonth = communityDay.getEndMonth();
                    int startDay = communityDay.getStartDay();
                    int startHour = communityDay.getStartHour();
                    int startMinute = communityDay.getStartMinute();
                    int startMonth = communityDay.getStartMonth();
                    if (!communityDay.isActive()) {

                        if (nowMonth >= startMonth && nowMonth <= endMonth) {

                            if (nowDay >= startDay && nowDay <= endDay) {

                                if (nowHour >= startHour && nowHour <= endHour) {

                                    if (nowMinute >= startMinute && nowMinute <= endMinute) {

                                        communityDay.setActive(true);
                                        BetterCommunityDays.logger.info("Activating Community Day: " + entry.getKey());
                                        CommunityDayHandler.activeCommunityDays.add(communityDay);

                                    }

                                }

                            }

                        }

                    } else {

                        if (nowMonth >= endMonth) {

                            if (nowDay >= endDay) {

                                if (nowHour >= endHour) {

                                    if (nowMinute >= endMinute) {

                                        communityDay.setActive(false);
                                        CommunityDayHandler.activeCommunityDays.removeIf(e -> e.getName().equalsIgnoreCase(entry.getKey()));
                                        BetterCommunityDays.logger.info("Deactivating Community Day: " + entry.getKey());

                                    }

                                }

                            }

                        }

                    }

                }

            }

        }, 0, 1000L);

    }

}
