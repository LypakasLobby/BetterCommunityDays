package com.lypaka.bettercommunitydays;

import com.lypaka.lypakautils.ConfigurationLoaders.BasicConfigManager;
import com.lypaka.lypakautils.ConfigurationLoaders.ComplexConfigManager;
import com.lypaka.lypakautils.ConfigurationLoaders.ConfigUtils;
import net.minecraftforge.fml.common.Mod;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("bettercommunitydays")
public class BetterCommunityDays {

    public static final String MOD_ID = "bettercommunitydays";
    public static final String MOD_NAME = "BetterCommunityDays";
    public static final Logger logger = LogManager.getLogger(MOD_NAME);
    public static BasicConfigManager configManager;
    public static ComplexConfigManager communityDayManager;

    public BetterCommunityDays() throws ObjectMappingException {

        String[] files = new String[]{"bettercommunitydays.conf", "gui.conf"};
        Path dir = ConfigUtils.checkDir(Paths.get("./config/bettercommunitydays"));
        configManager = new BasicConfigManager(files, dir, BetterCommunityDays.class, MOD_NAME, MOD_ID, logger);
        configManager.init();
        ConfigGetters.load();
        communityDayManager = new ComplexConfigManager(ConfigGetters.communityDays, "days", "communityDayTemplate.conf", dir, BetterCommunityDays.class, MOD_NAME, MOD_ID, logger);
        communityDayManager.init();

    }

}
