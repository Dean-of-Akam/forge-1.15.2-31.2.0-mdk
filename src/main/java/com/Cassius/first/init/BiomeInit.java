package com.Cassius.first.init;

import com.Cassius.first.FirstMod;

import com.Cassius.first.world.biomes.RubyBiome;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {
    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, FirstMod.MOD_ID);

    public static final RegistryObject<Biome> RUBY_BIOME = BIOMES.register("ruby_biome", () -> new RubyBiome(new Biome.Builder().precipitation(Biome.RainType.SNOW).scale(1.2f).waterColor(16724639).waterFogColor(16762304).temperature(0.5f).surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), BlockInit.ruby_block.getDefaultState(), Blocks.DIAMOND_ORE.getDefaultState())).category(Biome.Category.PLAINS).downfall(0.5f).depth(0.12f).parent(null)));
    public static void registerBiomes(){
        registerBiome(RUBY_BIOME.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.OVERWORLD);
    }
    private static void registerBiome(Biome biome , BiomeDictionary.Type... types){
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome, 100));//在世界范围内生成
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addSpawnBiome(biome);
    }
}
