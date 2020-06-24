package com.Cassius.first.tileentity;


import com.Cassius.first.init.ModTileEntityTypes;
import com.Cassius.first.util.helpers.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;


public class QuarryTileEntity extends TileEntity implements ITickableTileEntity {

    public int x,y,z,tick;
    boolean initialized = false;


    public QuarryTileEntity(final TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
    public QuarryTileEntity(){
        this(ModTileEntityTypes.QUARRY.get());
    }

    @Override
    public void tick() {
        if (!initialized) init();
        tick++;
        if (tick ==40 ){
            tick = 0;
            if(y>2) execute();
        }
    }



    private void init() {
        initialized = true;
        x = this.pos.getX()-1;
        y = this.pos.getY()-1;
        z = this.pos.getZ()-1;
        tick = 0;
    }
    private void execute() {
        int index = 0;
        Block[] blocksRemoved = new Block[9];
        for (int x = 0 ;x<3;x++){
            for (int z = 0;z<3;z++){
                BlockPos pos2Break = new BlockPos(this.x+x,this.y,this.z+z);
                    blocksRemoved[index] = this.world.getBlockState(pos2Break).getBlock();
                    destroyBlock(pos2Break,true,null);
                    index++;
            }
        }
        this.y--;
    }

    private Boolean destroyBlock(BlockPos pos, boolean dropBlock, @Nullable Entity entity) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.isAir(world, pos)) return false;
        else {
            IFluidState iFluidState = world.getFluidState(pos);
            world.playEvent(2001,pos,Block.getStateId(blockState));
            if(dropBlock){
                TileEntity tileEntity = blockState.hasTileEntity() ? world.getTileEntity(pos) : null;
                Block.spawnDrops(blockState,world,this.pos.add(0,1.5,0),tileEntity,entity, ItemStack.EMPTY);

            }
            return world.setBlockState(pos,iFluidState.getBlockState(),3);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("initvalues", NBTHelper.toNBT(this));
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        CompoundNBT initvalues = compound.getCompound("initvalues");
        if (initvalues != null){
            this.x = initvalues.getInt("x");
            this.y = initvalues.getInt("y");
            this.z = initvalues.getInt("z");
            this.tick = 0;
            initialized = true;
            return;
        }
        init();
    }
}
