package com.Cassius.first.objects.items;

import com.Cassius.first.entities.BallEntity;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import javax.annotation.Nullable;
import java.util.List;

public class MobBall extends Item {
    public static final String KEY ="entity_holder";
    private static boolean glowing = false;
    public MobBall(Properties properties){
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        PlayerEntity playerEntity = context.getPlayer();
        if (playerEntity == null) return ActionResultType.FAIL;
        Hand hand = Hand.MAIN_HAND;
        ItemStack itemStack = playerEntity.getHeldItemMainhand();
        if (playerEntity.getEntityWorld().isRemote || !containEntity(itemStack)) return ActionResultType.FAIL;
        Entity entity = getEntityFromStack(itemStack, playerEntity.world, true);
        this.glowing = false;
        BlockPos blockPos = context.getPos();
        entity.setPositionAndRotation(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, 0, 0);
        itemStack.setTag(null);
        playerEntity.setHeldItem(hand, itemStack);
        playerEntity.world.addEntity(entity);
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return this.glowing;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {
        if (target.getEntityWorld().isRemote || target instanceof PlayerEntity || containEntity(stack))  return false;
        EntityType<?> entityID = target.getType();
        ItemStack itemStack = stack.copy();
        CompoundNBT nbt = getNBTFromEntity(target);
        ItemStack newStack = itemStack.split(1);
        newStack.getOrCreateTag().put(KEY, nbt);
        playerIn.swingArm(hand);
        playerIn.setHeldItem(hand, itemStack);
        if (!playerIn.addItemStackToInventory(newStack)){
            ItemEntity itemEntity = new ItemEntity(playerIn.world, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), newStack);
            playerIn.world.addEntity(itemEntity);
        }
        target.remove();
        this.glowing = true;
        playerIn.getCooldownTracker().setCooldown(this, 5);
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {


        if (containEntity(stack)){
            if (!getID(stack).isEmpty()){
                String s0 = "entity." + getID(stack);
                String s1 = s0.replace(":",".");
                tooltip.add(new StringTextComponent(I18n.format(s1)));
                tooltip.add(new StringTextComponent("Health: "+ stack.getTag().getDouble("Health")));
            }
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        if (!containEntity(stack))
            return new TranslationTextComponent(super.getTranslationKey(stack)+ ".name");
        String s0 = "entity."+getID(stack);
        String s1 = s0.replace(":", ".");
        return new TranslationTextComponent(I18n.format(super.getTranslationKey(stack) + ".name") + ": "+I18n.format(s1));
    }

    public BallEntity createBall(World worldIn, LivingEntity shooter, ItemStack stack){
        ItemStack newStack = stack.copy();
        newStack.setCount(1);
        return new BallEntity(shooter.getPosX(), shooter.getPosY() + 1.25, shooter.getPosZ(), worldIn ,newStack);
    }

    //helper
    public static boolean containEntity(@Nullable ItemStack itemStack){
        return itemStack.hasTag() && itemStack.getTag().contains(KEY);
    }
    public static String getID(ItemStack stack){
        return getID(stack.getOrCreateTag());
    }
    public static String getID(CompoundNBT nbt){
        return nbt.getString("entity");
    }
    public static Entity getEntityFromNBT(CompoundNBT nbt, World world, boolean withInfo){
        Entity entity = Registry.ENTITY_TYPE.getOrDefault(new ResourceLocation(nbt.getString("entity"))).create(world);
        if (withInfo) entity.read(nbt);
        return entity;
    }
    public static Entity getEntityFromStack(ItemStack stack, World world, boolean withInfo){
        return getEntityFromNBT(stack.getOrCreateTag().getCompound(KEY), world, withInfo);
    }
    public static CompoundNBT getNBTFromEntity(Entity entity){
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("entity", entity.getType().getRegistryName().toString());
        nbt.putString("id", EntityType.getKey(entity.getType()).toString());
        entity.writeUnlessPassenger(nbt);
        return nbt;
    }
}
