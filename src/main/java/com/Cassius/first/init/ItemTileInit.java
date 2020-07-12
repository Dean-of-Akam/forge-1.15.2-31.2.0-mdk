package com.Cassius.first.init;


import com.Cassius.first.FirstMod;
import com.Cassius.first.entities.BallEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;


import net.minecraftforge.fml.RegistryObject;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;




public class ItemTileInit {

    public static final DeferredRegister<EntityType<?>> EDR = new DeferredRegister<>(ForgeRegistries.ENTITIES,FirstMod.MOD_ID);
    public static final RegistryObject<EntityType<BallEntity>> MOB_BALL = EDR.register("mod_ball", () -> EntityType.Builder.<BallEntity>create(BallEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(.6f, .6f).build("mob_ball"));



}
