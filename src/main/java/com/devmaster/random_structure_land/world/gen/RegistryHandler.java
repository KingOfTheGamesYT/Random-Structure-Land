package com.devmaster.random_structure_land.world.gen;

import com.devmaster.random_structure_land.misc.Random_Structure_Land;

import com.mojang.serialization.Codec;

import net.minecraftforge.common.world.StructureModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryHandler {
    public static final DeferredRegister<Codec<? extends StructureModifier>> STRUCTURE_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.STRUCTURE_MODIFIER_SERIALIZERS, Random_Structure_Land.MOD_ID);
    public static final RegistryObject<Codec<StructureSpawnEventHandler>> RANDOMIZE_STRUCTURES = STRUCTURE_MODIFIER_SERIALIZERS.register("randomize_structures", () -> Codec.unit(StructureSpawnEventHandler::new));
}
