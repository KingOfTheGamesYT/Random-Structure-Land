package com.devmaster.random_structure_land.misc;

import com.devmaster.random_structure_land.configs.BaseConfig;
import com.devmaster.random_structure_land.world.gen.StructureSpawnEventHandler;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("random_structure_land")
public class Random_Structure_Land {
    public static ServerWorld serverWorldCache;

    public static final Logger LOGGER = LogManager.getLogger("Random Structure Land");
    public static final String MOD_ID = "random_structure_land";

    public Random_Structure_Land() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BaseConfig.COMMON, "random_structure_land-common.toml");
        BaseConfig.loadConfig(BaseConfig.COMMON, FMLPaths.CONFIGDIR.get().resolve("random_structure_land-common.toml").toString());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(StructureSpawnEventHandler.class);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }
    private void doClientStuff(final FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {
        if (!event.getWorld().isRemote()) { // Ensure it's the server world
            serverWorldCache = (ServerWorld) event.getWorld();
        }
    }
}



