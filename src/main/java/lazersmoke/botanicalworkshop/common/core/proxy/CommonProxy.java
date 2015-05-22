package lazersmoke.botanicalworkshop.common.core.proxy;

import lazersmoke.botanicalworkshop.api.BotanicalWorkshopAPI;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.ModBlocks;
import lazersmoke.botanicalworkshop.common.core.handler.CommonTickHandler;
import lazersmoke.botanicalworkshop.common.core.handler.ConfigHandler;
import lazersmoke.botanicalworkshop.common.core.handler.InternalMethodHandler;
import lazersmoke.botanicalworkshop.common.crafting.ModCraftingRecipes;
import lazersmoke.botanicalworkshop.common.crafting.ModElvenTradeRecipes;
import lazersmoke.botanicalworkshop.common.crafting.ModGatewayRecipes;
import lazersmoke.botanicalworkshop.common.crafting.ModPetalRecipes;
import lazersmoke.botanicalworkshop.common.item.ModItems;
import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event){
		BotanicalWorkshopAPI.internalHandler = new InternalMethodHandler();
		
		ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
		
		ModBlocks.init();
		ModItems.init();
		
		ModCraftingRecipes.init();
		ModElvenTradeRecipes.init();
		ModGatewayRecipes.init();
		ModPetalRecipes.init();
		
		
		LexiconData.init();
	}
	
	public void init(FMLInitializationEvent event){
		FMLCommonHandler.instance().bus().register(new CommonTickHandler());
	}
	
	public void postInit(FMLPostInitializationEvent event){
		BotanicalWorkshop.logger.info("Botanical workshop dev Lazersmoke expresses his sincere gratitude to Vazkii");
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
	public void wispFX(World worldObj, double d, double e, double f, float g, float h, float i, float j, float k, float l, float m) {
		vazkii.botania.common.Botania.proxy.wispFX(worldObj, d, e, f, g, h, i, j, k, l, m);
	}
}