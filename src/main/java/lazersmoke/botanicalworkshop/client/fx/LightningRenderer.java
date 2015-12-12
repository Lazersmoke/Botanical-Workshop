package lazersmoke.botanicalworkshop.client.fx;

import java.util.Random;

import lazersmoke.botanicalworkshop.client.lib.LibResources;

import org.lwjgl.opengl.GL11;

import vazkii.botania.common.core.helper.MathHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.ResourceLocation;

public class LightningRenderer {
	public static final ResourceLocation theTexture = new ResourceLocation(LibResources.WHITE_TEXTURE);
	
	public static void renderLightningBolt(ChunkCoordinates start, ChunkCoordinates end, int segments, ScaledResolution res, float thickness, Random rand){
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		//float distanceTotal = MathHelper.pointDistanceSpace(start.posX, start.posY, start.posZ, end.posX, end.posY, end.posZ);
		
		int originX = res.getScaledWidth() / 2;
		int originY = res.getScaledHeight() / 2;
		
		int endX = 0;
		int endY = res.getScaledHeight();
		
		int totalX = endX - originX;
		int totalY = endY - originY;
		
		Minecraft mc = Minecraft.getMinecraft();

		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.renderEngine.bindTexture(theTexture);
		
		int u = 0;
		int v = 0;
		
		Tessellator tessellator = Tessellator.instance;
		
		double currXOffset = originX;
		double currYOffset = originY;
		
		tessellator.startDrawingQuads();
		for(int i = 0; i < segments; i++){
			tessellator.addVertex(currXOffset, currYOffset - (thickness / 2), 0);//Top of line, start
			tessellator.addVertex(currXOffset, currYOffset + (thickness / 2), 0);//Bottom of line, start
			tessellator.addVertex(currXOffset + (totalX / segments), currYOffset - (thickness / 2) + (totalY / segments), 0);//Top of line, start
			tessellator.addVertex(currXOffset + (totalX / segments), currYOffset + (thickness / 2) + (totalY / segments), 0);//Top of line, end
			currXOffset += totalX / segments;
			currYOffset += (totalY / segments);
		}
		tessellator.draw();
	}
}
