package lazersmoke.botanicalworkshop.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelLightningRod extends ModelBase{
	// fields
	ModelRenderer Base;
	ModelRenderer SecondLayer;
	ModelRenderer ThirdLayer;
	ModelRenderer Spike;

	public ModelLightningRod(){
		textureWidth = 64;
		textureHeight = 32;

		Base = new ModelRenderer(this, 0, 0);
		Base.addBox(-4F, 20F, -4F, 8, 4, 8);
		Base.setRotationPoint(0F, 0F, 0F);
		Base.setTextureSize(64, 32);
		Base.mirror = true;
		SecondLayer = new ModelRenderer(this, 0, 12);
		SecondLayer.addBox(-3F, 16F, -3F, 6, 4, 6);
		SecondLayer.setRotationPoint(0F, 0F, 0F);
		SecondLayer.setTextureSize(64, 32);
		SecondLayer.mirror = true;
		ThirdLayer = new ModelRenderer(this, 0, 22);
		ThirdLayer.addBox(-2F, 12F, -2F, 4, 4, 4);
		ThirdLayer.setRotationPoint(0F, 0F, 0F);
		ThirdLayer.setTextureSize(64, 32);
		ThirdLayer.mirror = true;
		Spike = new ModelRenderer(this, 24, 12);
		Spike.addBox(-1F, 8F, -1F, 2, 4, 2);
		Spike.setRotationPoint(0F, 0F, 0F);
		Spike.setTextureSize(64, 32);
		Spike.mirror = true;
	}

	public void render(){
		float f5 = 1F / 16F;
		Base.render(f5);
		SecondLayer.render(f5);
		ThirdLayer.render(f5);
		Spike.render(f5);
	}
}
