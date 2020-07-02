package com.Cassius.first.init;

import com.Cassius.first.FirstMod;
import com.Cassius.first.entities.RubySheepEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, FirstMod.MOD_ID);
    public static final RegistryObject<EntityType<RubySheepEntity>> RUBY_SHEEP_ENTITY = ENTITY_TYPES.register("ruby_sheep", () -> EntityType.Builder.<RubySheepEntity>create(RubySheepEntity::new, EntityClassification.CREATURE).size(0.9f, 1.3f).build(new ResourceLocation(FirstMod.MOD_ID, "ruby_sheep").toString()));


}

