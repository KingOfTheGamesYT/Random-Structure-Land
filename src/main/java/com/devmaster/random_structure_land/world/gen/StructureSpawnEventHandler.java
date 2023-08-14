package com.devmaster.random_structure_land.world.gen;


import com.devmaster.random_structure_land.configs.RSLConfig;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;
import java.util.Random;


public class StructureSpawnEventHandler {

    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        // Remove all structures from the biome
        event.getGeneration().getStructures().clear();

        Random seededRandom = new Random(event.getName().hashCode());

        // Randomly decide if a structure should be added to the biome
        for (Map.Entry<RegistryKey<StructureFeature<?, ?>>, StructureFeature<?, ?>> structure : WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE.getEntries()) {
            if (isStructureEligible(structure) && seededRandom.nextInt(100) < RSLConfig.StructureSpawnChance.get()) {  // 5% as an example
                event.getGeneration().getStructures().add(() -> structure.getValue());
            }
        }
    }

    private static boolean isStructureEligible(Map.Entry<RegistryKey<StructureFeature<?, ?>>, StructureFeature<?, ?>> structure) {
        // Place any filtering logic here
        return true;
    }
}
