package com.Cassius.first;


import com.Cassius.first.init.BlockInit;
import com.Cassius.first.world.FirstOreGen;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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

        instance = this;

        MinecraftForge.EVENT_BUS.register(this);
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
