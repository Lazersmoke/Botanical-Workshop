package lazersmoke.botanicalworkshop.common.core.proxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.ModBlocks;
import lazersmoke.botanicalworkshop.common.core.handler.ConfigHandler;
import lazersmoke.botanicalworkshop.common.crafting.ModCraftingRecipes;
import lazersmoke.botanicalworkshop.common.crafting.ModElvenTradeRecipes;
import lazersmoke.botanicalworkshop.common.crafting.ModGatewayRecipes;
import lazersmoke.botanicalworkshop.common.crafting.ModPetalRecipes;
import lazersmoke.botanicalworkshop.common.item.ModItems;
import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event){
		
		BotanicalWorkshop.creativeTab = new CreativeTabs("Botanical Workshop") {
			@Override
			public Item getTabIconItem() {
				return ModItems.simpleCatalyst;
			}
		};
		
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
	}
	
	public void postInit(FMLPostInitializationEvent event){
		BotanicalWorkshop.logger.info("Botanical workshop dev Lazersmoke expresses his sincere gratitude to Vazkii and SoundLogic");
	}
	
	public void serverStarting(FMLServerStartingEvent event) {
		//NO-OP
		
	}	
	public void serverStopping(FMLServerStoppingEvent event) {
		//NO-OP
	}
}