package com.devmaster.random_structure_land.misc;

import com.devmaster.random_structure_land.configs.BaseConfig;
import com.devmaster.random_structure_land.world.gen.RegistryHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("random_structure_land")
public class Random_Structure_Land {

    public static final Logger LOGGER = LogManager.getLogger("Random Structure Land");
    public static final String MOD_ID = "random_structure_land";

    public Random_Structure_Land() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BaseConfig.COMMON, "random_structure_land-common.toml");
        RegistryHandler.STRUCTURE_MODIFIER_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.register(this);
    }
}



