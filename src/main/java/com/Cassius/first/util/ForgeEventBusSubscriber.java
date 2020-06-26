package com.Cassius.first.util;

import com.Cassius.first.FirstMod;
import com.Cassius.first.init.DimensionInit;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FirstMod.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventBusSubscriber {
    @SubscribeEvent
    public static void registerDimensions(final RegisterDimensionsEvent event){
        if (DimensionType.byName(FirstMod.RUBY_DIM_TYPE) == null){
            DimensionManager.registerDimension(FirstMod.RUBY_DIM_TYPE, DimensionInit.RUBY_DIM.get(),null,true);
            FirstMod.LOGGER.info("Dimension registered!");
        }

    }
}
