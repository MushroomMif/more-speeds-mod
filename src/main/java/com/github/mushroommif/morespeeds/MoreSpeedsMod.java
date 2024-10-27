package com.github.mushroommif.morespeeds;

import com.github.mushroommif.fabricapi.ConfigUtil;
import net.fabricmc.api.ModInitializer;

public class MoreSpeedsMod implements ModInitializer {

    public static Config config;

    @Override
    public void onInitialize() {
        config = ConfigUtil.loadOrCreateConfig(
                Config.class, "more-speeds", Config::createDefault
        );
        config.validate();
    }
}
