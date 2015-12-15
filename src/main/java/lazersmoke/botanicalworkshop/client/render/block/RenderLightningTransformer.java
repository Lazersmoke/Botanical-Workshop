package lazersmoke.botanicalworkshop.client.render.block;

import lazersmoke.botanicalworkshop.client.lib.LibRenderIDs;
import lazersmoke.botanicalworkshop.client.render.tile.RenderTileLightningTransformer;
import lazersmoke.botanicalworkshop.common.block.tile.lightning.TileLightningTransformer;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderLightningTransformer implements ISimpleBlockRenderingHandler{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer){
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5F, -0.6F, -0.5F);
		GL11.glScalef(0.9F, 0.9F, 0.9F);
		RenderTileLightningTransformer.forceMeta = metadata;
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileLightningTransformer(), 0.0D, 0.0D, 0.0D, 0.0F);
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer){
		return false;
	}

	@Override
	public int getRenderId(){
		return LibRenderIDs.idTransformer;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId){
		return true;
	}
}