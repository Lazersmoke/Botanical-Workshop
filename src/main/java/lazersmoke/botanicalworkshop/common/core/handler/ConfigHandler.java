package lazersmoke.botanicalworkshop.common.core.handler;

import java.io.File;

import lazersmoke.botanicalworkshop.common.lib.LibConfigs;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public final class ConfigHandler {

	public static Configuration config;

	public static void loadConfig(File configFile) {
		config = new Configuration(configFile);

		config.load();
		load();

		FMLCommonHandler.instance().bus().register(new ChangeListener());
	}

	public static void load() {
		String desc;

		desc = "Set this to false to disable the crafting of Elven Mana Pools.";
		LibConfigs.CRAFT_ELVEN_POOL = loadPropBool("crafting.table.elvenPool", desc, true);
		
		desc = "Set this to false to disable the crafting of Elven Mana Pools using alfheim portals.";
		LibConfigs.PORTAL_CRAFT_ELVEN_POOL = loadPropBool("crafting.portal.elvenPool", desc, true);

		if(config.hasChanged())
			config.save();
	}

	public static void loadPostInit() {
		if(config.hasChanged())
			config.save();
	}

	public static int loadPropInt(String propName, String desc, int default_) {
		Property prop = config.get(Configuration.CATEGORY_GENERAL, propName, default_);
		prop.comment = desc;
		return prop.getInt(default_);
	}

	public static double loadPropDouble(String propName, String desc, double default_) {
		Property prop = config.get(Configuration.CATEGORY_GENERAL, propName, default_);
		prop.comment = desc;
		return prop.getDouble(default_);
	}

	public static boolean loadPropBool(String propName, String desc, boolean default_) {
		Property prop = config.get(Configuration.CATEGORY_GENERAL, propName, default_);
		prop.comment = desc;
		return prop.getBoolean(default_);
	}

	public static class ChangeListener {

		@SubscribeEvent
		public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
			if(eventArgs.modID.equals("BotanicalWorkshop"))
				load();
		}

	}
}