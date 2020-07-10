package com.Cassius.first.init;

import com.Cassius.first.FirstMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {
    public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, FirstMod.MOD_ID);
    public static final RegistryObject<SoundEvent> AMBIENT = SOUNDS.register("entity.ruby_sheep_entity.ambient", () -> new SoundEvent(new ResourceLocation(FirstMod.MOD_ID,"entity.ruby_sheep_entity.ambient")));
}
