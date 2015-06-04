package lazersmoke.botanicalworkshop.common;

import lazersmoke.botanicalworkshop.common.core.proxy.CommonProxy;
import lazersmoke.botanicalworkshop.common.lib.LibMisc;
import lazersmoke.botanicalworkshop.common.quakemovement.QuakeClientPlayer;
import lazersmoke.botanicalworkshop.common.quakemovement.QuakeConfig;
import lazersmoke.botanicalworkshop.common.quakemovement.QuakeServerPlayer;
import net.minecraft.creativetab.CreativeTabs;

import org.apache.logging.log4j.Logger;

import api.player.client.ClientPlayerAPI;
import api.player.server.ServerPlayerAPI;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = LibMisc.MOD_ID, name = LibMisc.MOD_NAME, version = LibMisc.VERSION, dependencies = LibMisc.DEPENDENCIES)
public class BotanicalWorkshop{
	
	public static Logger logger;
	
	public static boolean botaniaLoaded = false;
	public static boolean thaumcraftLoaded = false;	
	public static boolean bloodMagicLoaded = false;
	
	@Instance(LibMisc.MOD_ID)
	public static BotanicalWorkshop instance;
	
	@SidedProxy(serverSide = LibMisc.PROXY_COMMON, clientSide = LibMisc.PROXY_CLIENT)
	public static CommonProxy proxy;

	public static CreativeTabs creativeTab;

	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		
		logger = event.getModLog();
		
		botaniaLoaded = Loader.isModLoaded("Botania");		
		thaumcraftLoaded = Loader.isModLoaded("Thaumcraft");
		bloodMagicLoaded = Loader.isModLoaded("AWWayofTime"); // Psh, noob
		
		QuakeConfig.init(event.getSuggestedConfigurationFile());
		
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
		ServerPlayerAPI.register(LibMisc.MOD_ID, QuakeServerPlayer.class);

		if (event.getSide() == Side.CLIENT)
			ClientPlayerAPI.register(LibMisc.MOD_ID, QuakeClientPlayer.class);
		
		proxy.postInit(event);
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event){
		proxy.serverStarting(event);
	}
	
	@EventHandler
	public void serverStopping(FMLServerStoppingEvent event){
		proxy.serverStopping(event);
	}
}