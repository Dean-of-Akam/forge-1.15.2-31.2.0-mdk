package com.Cassius.first.init;

import com.Cassius.first.FirstMod;
import com.Cassius.first.tileentity.QuarryTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPEs = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, FirstMod.MOD_ID);
    public static final RegistryObject<TileEntityType<QuarryTileEntity>> QUARRY = TILE_ENTITY_TYPEs.register("quarry",() -> TileEntityType.Builder.create(QuarryTileEntity::new,BlockInit.quarry_block).build(null));
}
