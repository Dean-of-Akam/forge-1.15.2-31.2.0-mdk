package com.Cassius.first.init;

import com.Cassius.first.FirstMod;
import com.Cassius.first.objects.blocks.SpecalBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInitNew {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, FirstMod.MOD_ID);
    public static final RegistryObject<Block> DEF_BlOCK = BLOCKS.register("def_block",() ->new Block(Block.Properties.create(Material.IRON).sound(SoundType.BAMBOO)));
}
