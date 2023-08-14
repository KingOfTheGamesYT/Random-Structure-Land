package com.devmaster.random_structure_land.configs;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ForgeConfigSpec;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

import java.util.Arrays;

public class RSLConfig {
    public static ForgeConfigSpec.IntValue StructureSpawnChance;

    public RSLConfig() {
    }

    public static void COMMON(ForgeConfigSpec.Builder builder) {
            builder.comment("Chance (out of 100) for a structure to spawn in a biome.");
            builder.push("Structure Spawn Chances");
            StructureSpawnChance = builder.defineInRange("Percentage", 5, 0, 100);
            builder.pop();
        }

        }




