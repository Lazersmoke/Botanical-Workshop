package lazersmoke.botanicalworkshop.client.core.handler;

import java.awt.Color;

import lazersmoke.botanicalworkshop.client.lib.LibResources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import vazkii.botania.client.core.helper.RenderHelper;

public class HUDHandler {

	public static final ResourceLocation lightningBar = new ResourceLocation(LibResources.GUI_LIGHTNING_HUD);
	
	public static void drawSimpleLightningHUD(int color, int lightning, int power, int buffer, int overflow, String name, ScaledResolution res) {
		//LightningRenderer.renderLightningBolt(null, null, 7, res, 2.0F, new Random());
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Minecraft mc = Minecraft.getMinecraft();
		int x = res.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(name) / 2;
		int y = res.getScaledHeight() / 2 + 10;

		mc.fontRenderer.drawStringWithShadow(name, x, y, color);

		x = res.getScaledWidth() / 2 - 64;
		y += 8;

		renderLightningBar(x, y, color, lightning < 0 ? 0.5F : 1F, lightning, power, buffer, overflow);

		if(lightning < 0) {
			String text = "UNKNOWN";
			x = res.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(text) / 2;
			y -= 1;
			mc.fontRenderer.drawString(text, x, y, color);
		}

		GL11.glDisable(GL11.GL_BLEND);
	}
	
	public static void renderLightningBar(int x, int y, int color, float alpha, int lightning, int power, int buffer, int overflow) {
		Minecraft mc = Minecraft.getMinecraft();

		GL11.glColor4f(1F, 1F, 1F, alpha);
		mc.renderEngine.bindTexture(lightningBar);
		RenderHelper.drawTexturedModalRect(x, y, 1, 0, 0, 128, 16);

		int powerPercentage = Math.max(0, (int) ((double) power / (double) overflow * 100));
		int bufferPercentage = Math.max(0, (int) ((double) buffer / (double) overflow * 100));
		int lightningPercentage = Math.max(0, (int) ((double) lightning / (double) overflow * 100));

		if(lightningPercentage == 0 && lightning > 0)
			lightningPercentage = 1;

		RenderHelper.drawTexturedModalRect(x + 2 + (int) Math.floor(124 * powerPercentage / 100), y + 2, 2, 0, 18, 6, 12);
		RenderHelper.drawTexturedModalRect(x + 2 + (int) Math.floor(124 * bufferPercentage / 100), y + 2, 2, 0, 18, 6, 12);
		RenderHelper.drawTexturedModalRect(x + 2 + (int) Math.floor(124 * 100 / 100), y + 2, 2, 0, 18, 6, 12);

		if(lightningPercentage >= powerPercentage){
			Color color_ = new Color(0.0F, 1.0F, 0.0F);
			GL11.glColor4ub((byte) color_.getRed(), (byte) color_.getGreen(),(byte) color_.getBlue(), (byte) (255F * alpha));
			
			RenderHelper.drawTexturedModalRect(x + 6, y + 2, 3, 0, 32, (int) Math.floor(124 * powerPercentage / 100) - 2, 12);
			if(lightningPercentage >= bufferPercentage){
				color_ = new Color(0.0F, 0.0F, 1.0F);
				GL11.glColor4ub((byte) color_.getRed(), (byte) color_.getGreen(),(byte) color_.getBlue(), (byte) (255F * alpha));
				
				RenderHelper.drawTexturedModalRect(x + 6 + (int) Math.floor(124 * powerPercentage / 100), y + 2, 3, 0, 32, (int) Math.floor(124 * bufferPercentage / 100) - (int) Math.floor(124 * powerPercentage / 100) - 2, 12);
				
				color_ = new Color(1.0F, 0.0F, 0.0F);
				GL11.glColor4ub((byte) color_.getRed(), (byte) color_.getGreen(),(byte) color_.getBlue(), (byte) (255F * alpha));
				
				RenderHelper.drawTexturedModalRect(x + 6 + (int) Math.floor(124 * bufferPercentage / 100), y + 2, 3, 0, 32, (int) Math.floor(124 * lightningPercentage / 100) - (int) Math.floor(124 * bufferPercentage / 100), 12);
				
			}else{
				color_ = new Color(0.0F, 0.0F, 1.0F);
				GL11.glColor4ub((byte) color_.getRed(), (byte) color_.getGreen(),(byte) color_.getBlue(), (byte) (255F * alpha));
				
				RenderHelper.drawTexturedModalRect(x + 6 + (int) Math.floor(124 * powerPercentage / 100), y + 2, 3, 0, 32, (int) Math.floor(124 * lightningPercentage / 100) - (int) Math.floor(124 * powerPercentage / 100), 12);
			}
		}else{
			Color color_ = new Color(0.0F, 1.0F, 0.0F);
			GL11.glColor4ub((byte) color_.getRed(), (byte) color_.getGreen(),(byte) color_.getBlue(), (byte) (255F * alpha));
			
			RenderHelper.drawTexturedModalRect(x + 6, y + 2, 3, 0, 32, (int) Math.floor(124 * lightningPercentage / 100), 12);
		}
	}
}
