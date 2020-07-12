package com.Cassius.first;


import com.Cassius.first.entities.BallEntity;
import com.Cassius.first.init.*;
import com.Cassius.first.objects.blocks.RubyCrop;
import com.Cassius.first.world.gen.FirstOreGen;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;


@Mod("firstmod")
@Mod.EventBusSubscriber(modid = FirstMod.MOD_ID , bus = Mod.EventBusSubscriber.Bus.MOD)
public class FirstMod
{

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "firstmod";
    public static FirstMod instance;
    public static final ResourceLocation RUBY_DIM_TYPE = new ResourceLocation(MOD_ID,"ruby");

    public FirstMod() {
        final IEventBus modEventsBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventsBus.addListener(this::setup);

        modEventsBus.addListener(this::doClientStuff);

        ItemInitNew.ITEMS.register(modEventsBus);
        ItemTileInit.EDR.register(modEventsBus);
        BlockInitNew.BLOCKS.register(modEventsBus);
        ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventsBus);
        ModContainerType.CONTAINER_TYPES.register(modEventsBus);
        BiomeInit.BIOMES.register(modEventsBus);
        DimensionInit.MOD_DIMENSIONS.register(modEventsBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventsBus);
        SoundInit.SOUNDS.register(modEventsBus);
        instance = this;

        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event){
        final IForgeRegistry<Item> registry = event.getRegistry();
        BlockInitNew.BLOCKS.getEntries().stream().filter(block -> !(block.get() instanceof RubyCrop)).map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().group(FirstItemGroup.instance);
            final BlockItem blockItem = new BlockItem(block,properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });

        LOGGER.debug("Registered Items!");
    }
    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event){
        BiomeInit.registerBiomes();
    }


    @SubscribeEvent
    public static void onRegisterItemEntity(final FMLCommonSetupEvent event){
        RenderingRegistry.registerEntityRenderingHandler(ItemTileInit.MOB_BALL.get(), render -> new SpriteRenderer<>(render, Minecraft.getInstance().getItemRenderer()));
        DispenserBlock.registerDispenseBehavior(ItemInitNew.MOB_BALL.get(), new ProjectileDispenseBehavior() {
            @Nonnull
            @Override
            protected IProjectile getProjectileEntity(@Nonnull World worldIn,@Nonnull IPosition position,@Nonnull ItemStack stackIn) {
                ItemStack newStack = stackIn.copy();
                newStack.setCount(1);
                return new BallEntity(position.getX(), position.getY(), position.getZ(), worldIn, newStack);
            }
        });
    }




    private void setup(final FMLCommonSetupEvent event)
    {
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }
    @SubscribeEvent
    public void onSeverStarting(FMLServerStartingEvent event){

    }
    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event){
        FirstOreGen.generateOre();
    }

    public static class FirstItemGroup extends ItemGroup{
        public static FirstItemGroup instance = new FirstItemGroup(ItemGroup.GROUPS.length, "First");
        private FirstItemGroup(int index, String label){
            super(index, label);
        }
        @Override
        public ItemStack createIcon(){
            return new ItemStack(BlockInit.ruby_block);
        }
    }


}
