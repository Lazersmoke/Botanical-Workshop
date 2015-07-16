package lazersmoke.botanicalworkshop.client.render.block;

// This class is %90 Vazkii
import lazersmoke.botanicalworkshop.client.lib.LibRenderIDs;
import lazersmoke.botanicalworkshop.client.render.tile.RenderTileElvenPool;
import lazersmoke.botanicalworkshop.common.block.tile.mana.TileElvenPool;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderElvenPool implements ISimpleBlockRenderingHandler{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer){
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		RenderTileElvenPool.forceMeta = metadata;
		TileEntityRendererDispatcher.instance.renderTileEntityAt(
				new TileElvenPool(), 0.0D, 0.0D, 0.0D, 0.0F);
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer){
		return false;
	}

	@Override
	public int getRenderId(){
		return LibRenderIDs.idPool;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId){
		return true;
	}
}