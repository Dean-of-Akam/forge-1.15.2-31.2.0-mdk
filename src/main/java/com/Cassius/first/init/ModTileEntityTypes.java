package com.Cassius.first.init;

import com.Cassius.first.FirstMod;
import com.Cassius.first.tileentity.QuarryTileEntity;
import com.Cassius.first.tileentity.RubyChestTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, FirstMod.MOD_ID);
    public static final RegistryObject<TileEntityType<QuarryTileEntity>> QUARRY = TILE_ENTITY_TYPES.register("quarry",() -> TileEntityType.Builder.create(QuarryTileEntity::new,BlockInit.quarry_block).build(null));
    public static final RegistryObject<TileEntityType<RubyChestTileEntity>> RUBY_CHEST = TILE_ENTITY_TYPES.register("ruby_chest",() -> TileEntityType.Builder.create(RubyChestTileEntity::new,BlockInitNew.RUBY_CHEST.get()).build(null));
}

