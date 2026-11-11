package com.devmaster.random_structure_land.world.gen;

import com.devmaster.random_structure_land.configs.RSLConfig;
import com.devmaster.random_structure_land.misc.Random_Structure_Land;

import com.mojang.serialization.Codec;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;

import net.minecraftforge.common.world.ModifiableStructureInfo;
import net.minecraftforge.common.world.StructureModifier;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Mod.EventBusSubscriber(modid = Random_Structure_Land.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public record StructureSpawnEventHandler() implements StructureModifier {

    private static final AtomicInteger RANDOMIZED = new AtomicInteger();

    @Override
    public void modify(Holder<Structure> structure, Phase phase, ModifiableStructureInfo.StructureInfo.Builder builder) {
        if (phase != Phase.MODIFY) {
            return;
        }

        Optional<ResourceKey<Structure>> key = structure.unwrapKey();
        if (key.isEmpty()) {
            return;
        }
        ResourceLocation structureId = key.get().location();
        if (!isStructureEligible(structureId)) {
            return;
        }

        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null) {
            Random_Structure_Land.LOGGER.error("No server available while randomizing {}; leaving it unchanged.", structureId);
            return;
        }

        Registry<Biome> biomeRegistry = server.registryAccess().registryOrThrow(Registries.BIOME);
        int chance = RSLConfig.StructureSpawnChance.get();

        List<Holder<Biome>> allowed = biomeRegistry.holders()
                .filter(biome -> isAllowedIn(biome.key().location(), structureId, chance))
                .collect(Collectors.toList());

        builder.getStructureSettings().setBiomes(HolderSet.direct(allowed));
        RANDOMIZED.incrementAndGet();
        Random_Structure_Land.LOGGER.debug("{} may now generate in {} biome(s)", structureId, allowed.size());
    }

    @Override
    public Codec<? extends StructureModifier> codec() {
        return RegistryHandler.RANDOMIZE_STRUCTURES.get();
    }

    private static boolean isStructureEligible(ResourceLocation structureId) {
        return true;
    }

    //Deterministic "random" roll for one biome/structure pair. SplittableRandom mixes the seed well.
    static boolean isAllowedIn(ResourceLocation biomeId, ResourceLocation structureId, int percent) {
        long seed = ((long) biomeId.hashCode() << 32) ^ (structureId.hashCode() & 0xFFFFFFFFL);
        return new SplittableRandom(seed).nextInt(100) < percent;
    }
}
