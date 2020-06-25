package com.Cassius.first;


import com.Cassius.first.init.BlockInit;
import com.Cassius.first.init.BlockInitNew;
import com.Cassius.first.init.ItemInitNew;
import com.Cassius.first.init.ModTileEntityTypes;
import com.Cassius.first.world.FirstOreGen;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




@Mod("firstmod")
@Mod.EventBusSubscriber(modid = FirstMod.MOD_ID , bus = Mod.EventBusSubscriber.Bus.MOD)
public class FirstMod
{

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "firstmod";
    public static FirstMod instance;

    public FirstMod() {
        final IEventBus modEventsBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventsBus.addListener(this::setup);

        modEventsBus.addListener(this::doClientStuff);
        ItemInitNew.ITEMS.register(modEventsBus);
        BlockInitNew.BLOCKS.register(modEventsBus);
        ModTileEntityTypes.TILE_ENTITY_TYPEs.register(modEventsBus);

        instance = this;

        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event){
        final IForgeRegistry<Item> registry = event.getRegistry();
        BlockInitNew.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().group(FirstItemGroup.instance);
            final BlockItem blockItem = new BlockItem(block,properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });

        LOGGER.debug("Registerd Items!");
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
