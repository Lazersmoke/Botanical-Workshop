package lazersmoke.botanicalworkshop.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelLightningTransformer extends ModelBase{
	// fields
	ModelRenderer Base;
	ModelRenderer PillarOne;
	ModelRenderer PillarTwo;
	ModelRenderer PillarThree;
	ModelRenderer PillarFour;
	ModelRenderer Top;
	ModelRenderer CenterCube;
	ModelRenderer SpikeBottom;
	ModelRenderer SpikeTop;

	public ModelLightningTransformer(){
		textureWidth = 128;
		textureHeight = 64;

		Base = new ModelRenderer(this, 0, 0);
		Base.addBox(-7F, 22F, -7F, 14, 2, 14);
		Base.setRotationPoint(0F, 0F, 0F);
		Base.setTextureSize(128, 64);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
		PillarOne = new ModelRenderer(this, 16, 32);
		PillarOne.addBox(-7F, 12F, 5F, 2, 10, 2);
		PillarOne.setRotationPoint(0F, 0F, 0F);
		PillarOne.setTextureSize(128, 64);
		PillarOne.mirror = true;
		setRotation(PillarOne, 0F, 0F, 0F);
		PillarTwo = new ModelRenderer(this, 8, 32);
		PillarTwo.addBox(5F, 12F, 5F, 2, 10, 2);
		PillarTwo.setRotationPoint(0F, 0F, 0F);
		PillarTwo.setTextureSize(128, 64);
		PillarTwo.mirror = true;
		setRotation(PillarTwo, 0F, 0F, 0F);
		PillarThree = new ModelRenderer(this, 0, 32);
		PillarThree.addBox(5F, 12F, -7F, 2, 10, 2);
		PillarThree.setRotationPoint(0F, 0F, 0F);
		PillarThree.setTextureSize(128, 64);
		PillarThree.mirror = true;
		setRotation(PillarThree, 0F, 0F, 0F);
		PillarFour = new ModelRenderer(this, 24, 32);
		PillarFour.addBox(-7F, 12F, -7F, 2, 10, 2);
		PillarFour.setRotationPoint(0F, 0F, 0F);
		PillarFour.setTextureSize(128, 64);
		PillarFour.mirror = true;
		setRotation(PillarFour, 0F, 0F, 0F);
		Top = new ModelRenderer(this, 0, 16);
		Top.addBox(-7F, 10F, -7F, 14, 2, 14);
		Top.setRotationPoint(0F, 0F, 0F);
		Top.setTextureSize(128, 64);
		Top.mirror = true;
		setRotation(Top, 0F, 0F, 0F);
		CenterCube = new ModelRenderer(this, 32, 32);
		CenterCube.addBox(0F, 0F, 0F, 6, 6, 6);
		CenterCube.setRotationPoint(-4.5F, 13F, -1.5F);
		CenterCube.setTextureSize(128, 64);
		CenterCube.mirror = true;
		setRotation(CenterCube, 0.7853982F, 0.7853982F, 0.7853982F);
		SpikeTop = new ModelRenderer(this, 56, 0);
		SpikeTop.addBox(-4F, 8F, -4F, 8, 1, 8);
		SpikeTop.setRotationPoint(0F, 0F, 0F);
		SpikeTop.setTextureSize(64, 32);
		SpikeTop.mirror = true;
		setRotation(SpikeTop, 0F, 0F, 0F);
		SpikeBottom = new ModelRenderer(this, 56, 9);
		SpikeBottom.addBox(-5F, 9F, -5F, 10, 1, 10);
		SpikeBottom.setRotationPoint(0F, 0F, 0F);
		SpikeBottom.setTextureSize(64, 32);
		SpikeBottom.mirror = true;
		setRotation(SpikeBottom, 0F, 0F, 0F);
	}

	public void render(){
		float f5 = 1F / 16F;
		Base.render(f5);
		PillarOne.render(f5);
		PillarTwo.render(f5);
		PillarThree.render(f5);
		PillarFour.render(f5);
		Top.render(f5);
		CenterCube.render(f5);
		SpikeTop.render(f5);
		SpikeBottom.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
