package lazersmoke.botanical.client.render.block;

import lazersmoke.botanical.client.render.tile.RenderTileElvenPool;
import lazersmoke.botanical.common.block.tile.mana.TileElvenPool;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

import org.lwjgl.opengl.GL11;

import vazkii.botania.client.render.block.RenderPool;

public class RenderElvenPool extends RenderPool{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		RenderTileElvenPool.forceMeta = metadata;
		//RenderTileElvenPool.forceMana = RenderTileElvenPool.forceMana | metadata == 1;
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileElvenPool(), 0.0D, 0.0D, 0.0D, 0.0F);
		GL11.glPopMatrix();
	}
}