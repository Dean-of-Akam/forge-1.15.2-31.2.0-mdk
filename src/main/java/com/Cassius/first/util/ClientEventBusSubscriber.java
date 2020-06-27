package com.Cassius.first.util;

import com.Cassius.first.FirstMod;
import com.Cassius.first.client.gui.RubyChestScreen;
import com.Cassius.first.init.ModContainerType;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = FirstMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    @SubscribeEvent
    public static void clientSetUp(FMLClientSetupEvent event){
        ScreenManager.registerFactory(ModContainerType.RUBY_CHEST.get(), RubyChestScreen::new);
    }
}
