package lazersmoke.botanicalworkshop.client.core.proxy;

import lazersmoke.botanicalworkshop.client.lib.LibRenderIDs;
import lazersmoke.botanicalworkshop.client.render.block.RenderElvenPool;
import lazersmoke.botanicalworkshop.client.render.block.RenderLightningRod;
import lazersmoke.botanicalworkshop.client.render.tile.RenderTileElvenPool;
import lazersmoke.botanicalworkshop.client.render.tile.RenderTileLightningRod;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import lazersmoke.botanicalworkshop.common.block.tile.mana.TileElvenPool;
import lazersmoke.botanicalworkshop.common.block.tile.mana.lightning.TileLightningRod;
import lazersmoke.botanicalworkshop.common.core.proxy.CommonProxy;

public class ClientProxy extends CommonProxy{

	@Override
	public void init(FMLInitializationEvent event){
		super.init(event);
		initRenderers();
	}

	private void initRenderers(){
		LibRenderIDs.idPool = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderElvenPool());
		ClientRegistry.bindTileEntitySpecialRenderer(TileElvenPool.class, new RenderTileElvenPool());
		
		LibRenderIDs.idRod = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderLightningRod());
		ClientRegistry.bindTileEntitySpecialRenderer(TileLightningRod.class, new RenderTileLightningRod());
	}
}