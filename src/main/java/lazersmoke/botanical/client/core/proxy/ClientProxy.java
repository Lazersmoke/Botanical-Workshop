package lazersmoke.botanical.client.core.proxy;

import lazersmoke.botanical.client.lib.LibRenderIDs;
import lazersmoke.botanical.client.render.block.RenderElvenPool;
import lazersmoke.botanical.client.render.tile.RenderTileElvenPool;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import lazersmoke.botanical.common.block.tile.mana.TileElvenPool;
import lazersmoke.botanical.common.core.proxy.CommonProxy;

public class ClientProxy extends CommonProxy{
	@Override
	public void init(FMLInitializationEvent event){
		super.init(event);
		initRenderers();
	}

	private void initRenderers() {
		LibRenderIDs.idPool = RenderingRegistry.getNextAvailableRenderId();
		
		RenderingRegistry.registerBlockHandler(new RenderElvenPool());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileElvenPool.class, new RenderTileElvenPool());
	}
}