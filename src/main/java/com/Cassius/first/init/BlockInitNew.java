package com.Cassius.first.init;

import com.Cassius.first.FirstMod;
import com.Cassius.first.objects.blocks.ModPressurePlateBlock;
import com.Cassius.first.objects.blocks.ModWoodButtonBLock;


import com.Cassius.first.objects.blocks.RubyChestBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInitNew {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, FirstMod.MOD_ID);

    public static final RegistryObject<Block> DEF_BlOCK = BLOCKS.register("def_block",() ->new Block(Block.Properties.create(Material.IRON).sound(SoundType.BAMBOO)));

    public static final RegistryObject<Block> CREEPY_STAIRS = BLOCKS.register("creepy_stairs",() -> new StairsBlock(() ->BlockInit.ruby_block.getDefaultState(),Block.Properties.create(Material.WOOD)));
    public static final RegistryObject<Block> CREEPY_FENCE = BLOCKS.register("creepy_fence",() -> new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.PURPLE_TERRACOTTA)));
    public static final RegistryObject<Block> CREEPY_BUTTON = BLOCKS.register("creepy_button",() -> new ModWoodButtonBLock(Block.Properties.create(Material.WOOD,MaterialColor.PURPLE_TERRACOTTA)));
    public static final RegistryObject<Block> CREEPY_PRESSUREPLATE = BLOCKS.register("creepy_pressure_plate",() -> new ModPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,Block.Properties.create(Material.WOOD, MaterialColor.PURPLE_TERRACOTTA)));

    public static final RegistryObject<Block> RUBY_CHEST = BLOCKS.register("ruby_chest",() -> new RubyChestBlock(Block.Properties.from(BlockInitNew.DEF_BlOCK.get())));
}
