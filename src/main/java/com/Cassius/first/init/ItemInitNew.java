package com.Cassius.first.init;

import com.Cassius.first.FirstMod;
import com.Cassius.first.objects.items.RubyStaffItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.Cassius.first.objects.items.MobBall;

public class ItemInitNew {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, FirstMod.MOD_ID);

    public static final RegistryObject<Item> DEF_ITEM = ITEMS.register("def_item",() -> new RubyStaffItem(ItemInit.ModItemTier.RUBY,1,15.0f,new Item.Properties().group(FirstMod.FirstItemGroup.instance)));
    public static final RegistryObject<Item> SEED_ITEM = ITEMS.register("seed_item", () ->new BlockItem(BlockInitNew.RUBY_CROP.get(), new Item.Properties().group(FirstMod.FirstItemGroup.instance)));
    public static final RegistryObject<Item> MOB_BALL = ITEMS.register("mob_ball", () -> new MobBall(new Item.Properties().group(FirstMod.FirstItemGroup.instance)));

}
