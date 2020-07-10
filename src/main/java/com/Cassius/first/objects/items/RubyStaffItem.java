package com.Cassius.first.objects.items;
import com.Cassius.first.init.BlockInit;
import com.Cassius.first.util.helpers.KeyboardHelper;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;

public class RubyStaffItem extends SwordItem {


    public RubyStaffItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (KeyboardHelper.isHoldingShift()){
            tooltip.add(new StringTextComponent("Wula!"));
        }else {

            tooltip.add(new StringTextComponent("Hold shift for more info!"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 10));
        playerIn.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 600, 10));
        worldIn.createExplosion(playerIn,
                playerIn.getPosX(),
                playerIn.getPosY(),
                playerIn.getPosZ(),
                10.0f,
                Explosion.Mode.BREAK
        );
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World worldIn = attacker.getEntityWorld();

        //test the side
        if(!(worldIn instanceof ServerWorld)) return super.hitEntity(stack, target, attacker);

        //cast World to ServerWorld
        ServerWorld world = (ServerWorld) worldIn;

        attacker.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 10));
        attacker.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 600, 10));
        BlockPos blockPos = target.getPosition();

        LightningBoltEntity lightningBoltEntity = new LightningBoltEntity(worldIn, blockPos.getX(), blockPos.getY(), blockPos.getZ(), false);
        world.addLightningBolt(lightningBoltEntity);

        return super.hitEntity(stack, target, attacker);
    }
    

    @Override
    public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        if (!(worldIn instanceof ServerWorld)) return;

        ServerWorld world = (ServerWorld)worldIn;
        LightningBoltEntity lightningBoltEntity = new LightningBoltEntity(world, playerIn.getPosX()+3,playerIn.getPosY(), playerIn.getPosZ()+3,false);
        world.addLightningBolt(lightningBoltEntity);
        super.onCreated(stack, worldIn, playerIn);
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return 600;
    }
}
