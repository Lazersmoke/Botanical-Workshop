package lazersmoke.botanical.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import lazersmoke.botanical.client.lib.LibResources;
import lazersmoke.botanical.common.block.mana.BlockElvenPool;
import lazersmoke.botanical.common.block.tile.mana.TileElvenPool;
import lazersmoke.botanical.common.lib.LibBlockNames;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public final class ModBlocks {
	
	public static Block elvenPool;
	
	public static void init(){
		
		elvenPool = new BlockElvenPool();
	
		initTileEntities();
	}
	
	public static void initTileEntities(){
		registerTile(TileElvenPool.class, LibBlockNames.ELVEN_POOL);
	}

	private static void registerTile(Class<? extends TileEntity> clazz, String key) {
		GameRegistry.registerTileEntity(clazz, LibResources.PREFIX_MOD + key);
	}
}
