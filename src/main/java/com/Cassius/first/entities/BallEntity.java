package com.Cassius.first.entities;

import com.Cassius.first.FirstMod;
import com.Cassius.first.init.ItemTileInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BallEntity extends ProjectileItemEntity {
    public ItemStack stack = ItemStack.EMPTY;

    public BallEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public BallEntity(double x, double y, double z, World world, ItemStack stack){
        super(ItemTileInit.MOB_BALL.get(), x, y, z, world);
        this.stack = stack;
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

    }
}
