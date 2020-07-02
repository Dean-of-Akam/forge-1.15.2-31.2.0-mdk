package com.Cassius.first.client.entity.render;

import com.Cassius.first.FirstMod;
import com.Cassius.first.client.entity.model.RubySheepEntityModel;
import com.Cassius.first.entities.RubySheepEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class EntityRender extends MobRenderer<RubySheepEntity, RubySheepEntityModel<RubySheepEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(FirstMod.MOD_ID, "textures/entity/ruby_entity.png");

    public EntityRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RubySheepEntityModel<RubySheepEntity>(), 0.5f);
    }


    @Override
    public ResourceLocation getEntityTexture(RubySheepEntity entity) {
        return TEXTURE;
    }
}
