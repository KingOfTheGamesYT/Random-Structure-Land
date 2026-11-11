package com.devmaster.random_structure_land.configs;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class BaseConfig {
    private static final Builder COMMON_BUILDER = new Builder();
    public static final ForgeConfigSpec COMMON;

    public BaseConfig() {
    }

    static {
        RSLConfig.COMMON(COMMON_BUILDER);
        COMMON = COMMON_BUILDER.build();
    }
}
