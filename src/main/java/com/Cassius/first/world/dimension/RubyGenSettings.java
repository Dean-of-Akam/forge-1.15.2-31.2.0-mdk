package com.Cassius.first.world.dimension;

import com.Cassius.first.init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.GenerationSettings;

public class RubyGenSettings extends GenerationSettings {
    public RubyGenSettings(){
        this.setDefaultBlock(BlockInit.ruby_block.getDefaultState());
    }



    public int getBiomeSize(){
        return 4;
    }
    public int getRiveSize(){
        return 4;
    }
    public int getBiomeId(){
        return -1;
    }

    @Override
    public int getBedrockRoofHeight() {
        return 0;
    }

}
