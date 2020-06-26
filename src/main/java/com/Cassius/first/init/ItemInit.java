package com.Cassius.first.init;

import com.Cassius.first.FirstMod;
import com.Cassius.first.objects.items.RubyStaffItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = FirstMod.MOD_ID, bus = Bus.MOD)
@ObjectHolder(FirstMod.MOD_ID)
public class ItemInit {

    public static Item ruby = null;
    public static Item ruby_apple = null;
    public static Item stone_stick = null;
    public static Item ruby_staff = null;

    //Tools
    public static Item ruby_sword = null;
    public static Item ruby_pickaxe = null;
    public static Item ruby_shovel = null;
    public static Item ruby_axe = null;
    public static Item ruby_hoe = null;

    //Armor
    public static final Item ruby_helmet = null;
    public static final Item ruby_chestplate = null;
    public static final Item ruby_leggings = null;
    public static final Item ruby_boots = null;



    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event){
        event.getRegistry().register(new Item(new Item.Properties().group(FirstMod.FirstItemGroup.instance)).setRegistryName("ruby"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("stone_stick"));
        event.getRegistry().register(new Item(new Item.Properties().group(FirstMod.FirstItemGroup.instance).food(new Food.Builder().hunger(6).saturation(1.2f).effect(new EffectInstance(Effects.ABSORPTION, 6000, 5) , 1.0f).effect(new EffectInstance(Effects.FIRE_RESISTANCE, 6000, 5), 1.0f).build())).setRegistryName("ruby_apple"));
        event.getRegistry().register(new RubyStaffItem(ModItemTier.RUBY,-15,15.0f, new Item.Properties().group(FirstMod.FirstItemGroup.instance)).setRegistryName("ruby_staff"));

        //Tools
        event.getRegistry().register(new SwordItem(ModItemTier.RUBY, 1000, 15.0f, new Item.Properties().group(FirstMod.FirstItemGroup.instance)).setRegistryName("ruby_sword"));
        event.getRegistry().register(new PickaxeItem(ModItemTier.RUBY, 15, 15.0f, new Item.Properties().group(FirstMod.FirstItemGroup.instance)).setRegistryName("ruby_pickaxe"));
        event.getRegistry().register(new ShovelItem(ModItemTier.RUBY, 15, 15.0f, new Item.Properties().group(FirstMod.FirstItemGroup.instance)).setRegistryName("ruby_shovel"));
        event.getRegistry().register(new AxeItem(ModItemTier.RUBY, 15, 15.0f, new Item.Properties().group(FirstMod.FirstItemGroup.instance)).setRegistryName("ruby_axe"));
        event.getRegistry().register(new HoeItem(ModItemTier.RUBY, 15.0f, new Item.Properties().group(FirstMod.FirstItemGroup.instance)).setRegistryName("ruby_hoe"));

        //Armor
        event.getRegistry().register(new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.HEAD ,new Item.Properties().group(FirstMod.FirstItemGroup.instance)).setRegistryName("ruby_helmet"));
        event.getRegistry().register(new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.CHEST ,new Item.Properties().group(FirstMod.FirstItemGroup.instance)).setRegistryName("ruby_chestplate"));
        event.getRegistry().register(new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.LEGS ,new Item.Properties().group(FirstMod.FirstItemGroup.instance)).setRegistryName("ruby_leggings"));
        event.getRegistry().register(new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.FEET ,new Item.Properties().group(FirstMod.FirstItemGroup.instance)).setRegistryName("ruby_boots"));


    }

    public enum ModItemTier implements IItemTier{
        RUBY(4, 3600, 20.0f, 15.0f, 250, () -> {
            return Ingredient.fromItems(ItemInit.ruby);
        });
        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final LazyValue<Ingredient> repairMaterial;


        private ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial){
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairMaterial = new LazyValue<>(repairMaterial);
        }

        @Override
        public int getMaxUses() {
            return this.maxUses;
        }

        @Override
        public float getEfficiency() {
            return this.efficiency;
        }

        @Override
        public float getAttackDamage() {
            return this.attackDamage;
        }

        @Override
        public int getHarvestLevel() {
            return this.harvestLevel;
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return this.repairMaterial.getValue();
        }
    }
    public enum ModArmorMaterial implements IArmorMaterial{
        RUBY(FirstMod.MOD_ID + ":ruby", 5 ,new int[] {7, 9, 11, 7},420, SoundEvents.ENTITY_BEE_DEATH,6.9f,() -> {
            return Ingredient.fromItems(ItemInit.ruby);
        });
        private static final int[] MAX_DAMAGE_ARRAY = new int[] {16,16,16,16};
        private final String name ;
        private final int maxDamageFactor;
        private final int[] damageReductionAmountArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final float toughness;
        private final LazyValue<Ingredient> repairMaterial;


    private ModArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn, Supplier<Ingredient> repairMaterialIn) {
            this.name = nameIn;
            this.maxDamageFactor = maxDamageFactorIn;
            this.damageReductionAmountArray = damageReductionAmountArrayIn;
            this.enchantability = enchantabilityIn;
            this.soundEvent = soundEventIn;
            this.toughness = toughnessIn;
            this.repairMaterial = new LazyValue<>(repairMaterialIn);

        }

        @Override
        public int getDurability(EquipmentSlotType slotIn) {
            return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {
            return this.damageReductionAmountArray[slotIn.getIndex()];
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return this.soundEvent;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return this.repairMaterial.getValue();
        }

        @OnlyIn(Dist.CLIENT)
        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public float getToughness() {
            return this.toughness;
        }
    }
}
