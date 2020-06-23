package com.Cassius.first.events;

import com.Cassius.first.FirstMod;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FirstMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TestJumpEvent {
    @SubscribeEvent
    public static void testJumpEvent(PlayerXpEvent.LevelChange event){
        FirstMod.LOGGER.info("Fired!!!!!");
        PlayerEntity playerEntity = event.getPlayer();
        World world = playerEntity.getEntityWorld();
        Block diamondBlock = Blocks.DIAMOND_BLOCK;
        world.setBlockState(playerEntity.getPosition().down(), diamondBlock.getDefaultState());
        playerEntity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 6000, 5));
        playerEntity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 6000, 5));
        playerEntity.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 6000, 5));



    }
}
