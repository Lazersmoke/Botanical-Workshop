package lazersmoke.botanical.common.core.proxy;

import net.minecraft.world.World;

import org.apache.logging.log4j.Level;

import lazersmoke.botanical.common.core.handler.CommonTickHandler;
import lazersmoke.botanical.common.crafting.ModElvenTradeRecipes;
import lazersmoke.botanical.common.core.handler.ConfigHandler;
import lazersmoke.botanical.common.crafting.ModCraftingRecipes;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import lazersmoke.botanical.common.block.ModBlocks;
import lazersmoke.botanical.common.item.ModItems;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event){
		ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
		
		ModBlocks.init();
		ModItems.init();
		
		ModCraftingRecipes.init();
		ModElvenTradeRecipes.init();
	}
	
	public void init(FMLInitializationEvent event){
		FMLCommonHandler.instance().bus().register(new CommonTickHandler());
	}
	
	public void postInit(FMLPostInitializationEvent event){
		FMLLog.log(Level.INFO, "[BotanicalWorkshop] Botanical workshop dev Lazersmoke expresses his sincere gratitude to Vazkii");
	}
	
	public void serverStarting(FMLServerStartingEvent event) {
		//NO-OP
		
	}	
	public void serverStopping(FMLServerStoppingEvent event) {
		//NO-OP
	}

	public void sparkleFX(World worldObj, double d, int i, double e, float red, float green, float blue, float random, int j) {
		vazkii.botania.common.Botania.proxy.sparkleFX(worldObj, d, i, e, red, green, blue, random, j);
	}

	public void wispFX(World worldObj, double d, double e, double f, float g, float h, float i, float j, float k, float l) {
		vazkii.botania.common.Botania.proxy.wispFX(worldObj, d, e, f, g, h, i, j, k, l);
	}
}