package com.Cassius.first.world.dimension;

import com.Cassius.first.init.BiomeInit;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

import java.util.Random;
import java.util.Set;

public class RubyBiomeProvider extends BiomeProvider {
    private Random random;

    public RubyBiomeProvider() {
        super(biomeList);
        random = new Random();
    }

    private static final Set<Biome> biomeList = ImmutableSet.of(BiomeInit.RUBY_BIOME.get());

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return BiomeInit.RUBY_BIOME.get();
    }
}
