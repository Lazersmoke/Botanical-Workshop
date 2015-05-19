package lazersmoke.botanicalworkshop.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.block.mana.BlockElvenPool;
import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.block.tile.TileThaumicCore;
import lazersmoke.botanicalworkshop.common.block.tile.mana.TileElvenPool;
import lazersmoke.botanicalworkshop.common.lib.LibBlockNames;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public final class ModBlocks {
	
	public static Block elvenPool;
	public static Block gatewayCore;
	public static Block thaumicCore;
	
	public static void init(){
		
		gatewayCore = new BlockGatewayCore();
		elvenPool = new BlockElvenPool();
		thaumicCore = new BlockThaumicCore();
	
		initTileEntities();
	}
	
	public static void initTileEntities(){
		registerTile(TileElvenPool.class, LibBlockNames.ELVEN_POOL);
		registerTile(TileGatewayCore.class, LibBlockNames.GATEWAY_CORE);
		registerTile(TileThaumicCore.class, LibBlockNames.THAUMIC_CORE);
	}

	private static void registerTile(Class<? extends TileEntity> clazz, String key) {
		GameRegistry.registerTileEntity(clazz, LibResources.PREFIX_MOD + key);
	}
}
