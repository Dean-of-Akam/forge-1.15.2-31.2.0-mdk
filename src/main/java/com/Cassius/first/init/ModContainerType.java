package com.Cassius.first.init;

import com.Cassius.first.FirstMod;
import com.Cassius.first.container.RubyChestContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerType {

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, FirstMod.MOD_ID);
    public static final RegistryObject<ContainerType<RubyChestContainer>> RUBY_CHEST = CONTAINER_TYPES.register("ruby_chest",() -> IForgeContainerType.create(RubyChestContainer::new));
}
