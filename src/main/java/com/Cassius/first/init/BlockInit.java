package com.Cassius.first.init;

import com.Cassius.first.FirstMod;

import com.Cassius.first.objects.blocks.QuarryBlock;
import com.Cassius.first.objects.blocks.SpecalBlock;
import net.minecraft.block.Block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(FirstMod.MOD_ID)
@Mod.EventBusSubscriber(modid = FirstMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {
    public static final Block ruby_ore = null;
    public static final Block ruby_block = null;
    public static final Block specal_block = null;
    public static final Block quarry_block = null;


    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event){
        event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(1.5f, 1.0f).harvestTool(ToolType.PICKAXE).harvestLevel(1).sound(SoundType.METAL)).setRegistryName("ruby_ore"));
        event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(1.5f, 1.0f).harvestTool(ToolType.PICKAXE).harvestLevel(0).sound(SoundType.METAL)).setRegistryName("ruby_block"));
        event.getRegistry().register(new SpecalBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(0.2f,10.0f).harvestLevel(0).sound(SoundType.WOOD).lightValue(4).slipperiness(1.2f).speedFactor(0.7f).noDrops()).setRegistryName("specal_block"));
        event.getRegistry().register(new QuarryBlock(Block.Properties.create(Material.IRON)).setRegistryName("quarry_block"));
    }
    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> event){
        event.getRegistry().register(new BlockItem(ruby_ore, new Item.Properties().group(FirstMod.FirstItemGroup.instance)).setRegistryName("ruby_ore"));
        event.getRegistry().register(new BlockItem(ruby_block, new Item.Properties().group(FirstMod.FirstItemGroup.instance)).setRegistryName("ruby_block"));
        event.getRegistry().register(new BlockItem(specal_block,new Item.Properties().group(FirstMod.FirstItemGroup.instance)).setRegistryName("specal_block"));
        event.getRegistry().register(new BlockItem(quarry_block,new Item.Properties().group(FirstMod.FirstItemGroup.instance)).setRegistryName("quarry_block"));

    }
}
