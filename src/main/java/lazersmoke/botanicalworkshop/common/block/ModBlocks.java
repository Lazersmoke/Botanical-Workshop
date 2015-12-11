package lazersmoke.botanicalworkshop.common.block;

import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.block.mana.BlockElvenPool;
import lazersmoke.botanicalworkshop.common.block.subtile.BotanicalWorkshopSignature;
import lazersmoke.botanicalworkshop.common.block.subtile.functional.SubTileExAquainas;
import lazersmoke.botanicalworkshop.common.block.subtile.functional.SubTileLogicalSound;
import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.block.tile.TileThaumicCore;
import lazersmoke.botanicalworkshop.common.block.tile.mana.TileElvenPool;
import lazersmoke.botanicalworkshop.common.block.tile.mana.lightning.TileLightningCore;
import lazersmoke.botanicalworkshop.common.block.tile.mana.lightning.TileLightningRod;
import lazersmoke.botanicalworkshop.common.block.tile.mana.lightning.TileThaumtanicalTransposer;
import lazersmoke.botanicalworkshop.common.lib.LibBlockNames;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.subtile.SubTileEntity;
import cpw.mods.fml.common.registry.GameRegistry;

public final class ModBlocks{

	public static Block elvenPool;
	public static Block gatewayCore;
	public static Block thaumicCore;
	public static Block weakGatewayCore;
	public static Block thaumtanicalTransposer;
	public static Block lightningRod;
	public static Block lightningCore;
	
	public static void init(){

		gatewayCore = new BlockGatewayCore();
		elvenPool = new BlockElvenPool();
		thaumicCore = new BlockThaumicCore();
		weakGatewayCore = new BlockWeakGatewayCore();
		thaumtanicalTransposer = new BlockThaumtanicalTransposer();
		lightningRod = new BlockLightningRod();
		lightningCore = new BlockLightningCore();
		
		initTileEntities();
	}

	public static void initTileEntities(){
		registerTile(TileElvenPool.class, LibBlockNames.ELVEN_POOL);
		registerTile(TileGatewayCore.class, LibBlockNames.GATEWAY_CORE);
		registerTile(TileThaumicCore.class, LibBlockNames.THAUMIC_CORE);
		registerTile(TileThaumtanicalTransposer.class, LibBlockNames.THAUMTANICAL_TRANSPOSER);
		registerTile(TileLightningRod.class, LibBlockNames.LIGHTNING_ROD);
		registerTile(TileLightningCore.class, LibBlockNames.LIGHTNING_CORE);
		
		registerSubTile(SubTileExAquainas.class, LibBlockNames.SUBTILE_EXAQUAINAS);
		registerSubTile(SubTileLogicalSound.class, LibBlockNames.SUBTILE_LOGICALSOUND);
	}

	private static void registerTile(Class<? extends TileEntity> clazz, String key){
		GameRegistry.registerTileEntity(clazz, LibResources.PREFIX_MOD + key);
	}

	private static void registerSubTile(Class<? extends SubTileEntity> clazz, String key){
		BotaniaAPI.registerSubTile(key, clazz);
		BotaniaAPI.registerSubTileSignature(clazz, new BotanicalWorkshopSignature(key));
		BotaniaAPI.addSubTileToCreativeMenu(key);
	}
}