package com.Cassius.first.init;

import com.Cassius.first.FirstMod;
import com.Cassius.first.world.dimension.RubyDimension;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DimensionInit {
    public static final DeferredRegister<ModDimension> MOD_DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, FirstMod.MOD_ID);
    public static final RegistryObject<ModDimension> RUBY_DIM = MOD_DIMENSIONS.register("ruby_dim" ,() -> new RubyDimension());
}
