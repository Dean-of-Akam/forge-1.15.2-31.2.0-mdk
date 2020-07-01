package com.Cassius.first.world.feature;

import com.Cassius.first.init.BlockInitNew;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;
import java.util.Random;


public class RubyTree extends Tree {

    public static final TreeFeatureConfig RUBY_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInitNew.RUBY_LOG.get().getDefaultState()), new SimpleBlockStateProvider(BlockInitNew.RUBY_LEAVES.get().getDefaultState()), new BlobFoliagePlacer(3, 0))).baseHeight(13).heightRandA(5).foliageHeight(9).ignoreVines().setSapling((IPlantable) BlockInitNew.RUBY_SAPLING.get()).build();


    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean b) {
        return Feature.NORMAL_TREE.withConfiguration(RUBY_TREE_CONFIG);
    }
}
