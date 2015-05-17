package lazersmoke.botanicalworkshop.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.block.mana.BlockElvenPool;
import lazersmoke.botanicalworkshop.common.block.tile.TileSuperGatewayCore;
import lazersmoke.botanicalworkshop.common.block.tile.mana.TileElvenPool;
import lazersmoke.botanicalworkshop.common.lib.LibBlockNames;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public final class ModBlocks {
	
	public static Block elvenPool;
	public static Block superGatewayCore;
	
	public static void init(){
		
		superGatewayCore = new BlockSuperGatewayCore();
		elvenPool = new BlockElvenPool();
	
		initTileEntities();
	}
	
	public static void initTileEntities(){
		registerTile(TileElvenPool.class, LibBlockNames.ELVEN_POOL);
		registerTile(TileSuperGatewayCore.class, LibBlockNames.SUPER_GATEWAY_CORE);
	}

	private static void registerTile(Class<? extends TileEntity> clazz, String key) {
		GameRegistry.registerTileEntity(clazz, LibResources.PREFIX_MOD + key);
	}
}
