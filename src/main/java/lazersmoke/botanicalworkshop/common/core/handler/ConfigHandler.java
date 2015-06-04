package lazersmoke.botanicalworkshop.common.core.handler;

import java.io.File;

import lazersmoke.botanicalworkshop.common.lib.LibConfigs;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public final class ConfigHandler{

	public static Configuration config;

	public static void loadConfig(File configFile){
		config = new Configuration(configFile);

		config.load();
		load();

		FMLCommonHandler.instance().bus().register(new ChangeListener());
	}

	public static void load(){
		String desc;

		// Table Crafting
		desc = "Set this to false to disable the crafting of Elven Mana Pools.";
		LibConfigs.CRAFT_ELVEN_POOL = loadPropBool("crafting.table.elvenPool",
		        desc, true);

		desc = "Set this to false to disable the crafting of Simple Catalysts.";
		LibConfigs.CRAFT_SIMPLE_CATALYST = loadPropBool(
		        "crafting.table.simpleCatalyst", desc, true);

		desc = "Set this to false to disable the crafting of Empty Catalysts. Not reccommended";
		LibConfigs.CRAFT_EMPTY_CATALYST = loadPropBool(
		        "crafting.table.emptyCatalyst", desc, true);

		desc = "Set this to false to disable the crafting of Elven Crystals";
		LibConfigs.CRAFT_EMPTY_CATALYST = loadPropBool(
		        "crafting.table.elvenCrystal", desc, true);

		desc = "Set this to false to disable the crafting of Weak Gateway Cores";
		LibConfigs.CRAFT_WEAK_GATEWAY_CORE = loadPropBool(
		        "crafting.table.weakGatewayCore", desc, true);

		// Elven Portal Crafting
		desc = "Set this to false to disable the crafting of Elven Mana Pools using alfheim portals.";
		LibConfigs.PORTAL_CRAFT_ELVEN_POOL = loadPropBool(
		        "crafting.portal.elvenPool", desc, true);

		desc = "Set this to false to disable the crafting of Elven Crystals using alfheim portals.";
		LibConfigs.PORTAL_CRAFT_ELVEN_POOL = loadPropBool(
		        "crafting.portal.elvenCrystal", desc, true);

		desc = "Set this to false to disable the crafting of Gateway Cores using alfheim portals.";
		LibConfigs.PORTAL_CRAFT_GATEWAY_CORE = loadPropBool(
		        "crafting.portal.gatewayCore", desc, true);

		// Gateway Crafting
		desc = "Set this to false to disable the crafting of Mana Binding Crystals using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_MANA_BINDING_CRYSTAL = loadPropBool(
		        "crafting.gateway.manaBindingCrystal", desc, true);

		desc = "Set this to false to disable the crafting of Blood Binding Crystals using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_BLOOD_BINDING_CRYSTAL = loadPropBool(
		        "crafting.gateway.bloodBindingCrystal", desc, true);

		desc = "Set this to false to disable the crafting of Thaumic Binding Crystals using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_THAUMIC_BINDING_CRYSTAL = loadPropBool(
		        "crafting.gateway.thaumicBindingCrystal", desc, true);

		desc = "Set this to false to disable the crafting of Mechanical Binding Crystals using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_MECHANICAL_BINDING_CRYSTAL = loadPropBool(
		        "crafting.gateway.mechanicalBindingCrystal", desc, true);

		desc = "Set this to false to disable the crafting of Mana Catalysts using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_MANA_CATALYST = loadPropBool(
		        "crafting.gateway.manaCatalyst", desc, true);

		desc = "Set this to false to disable the crafting of Thaumic Cores using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_THAUMIC_CORE = loadPropBool(
		        "crafting.gateway.thaumicCore", desc, true);

		desc = "Set this to false to disable the crafting of Thaumic Catalyst using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_THAUMIC_CATALYST = loadPropBool(
		        "crafting.gateway.thaumicCatalyst", desc, true);

		// Gameplay Settings
		desc = "Set this to change the mana cost of opening a Gateway. More than 8 million will disable opening gateways, but open gateways will remain open.";
		LibConfigs.GATEWAY_OPENING_MANA_COST = loadPropInt(
		        "gameplay.gateway.openingManaCost", desc, 1000000);

		desc = "Set this to change the mana cost of keeping a gateway open per tick.";
		LibConfigs.GATEWAY_TICK_MANA_COST = loadPropInt(
		        "gameplay.gateway.perTickManaCost", desc, 1000);

		desc = "Set this to false to disable the songs of the flowers.";
		LibConfigs.TONAL_FLORA = loadPropBool("gameplay.flowers.tonalFlora",
		        desc, true);

		// Performance Settings
		desc = "Set this to change the particle density of Botanical Workshop effects. Default is 1";
		LibConfigs.PARTICLE_DENSITY = loadPropInt(
		        "performance.particles.density", desc, 1);

		if(config.hasChanged())
			config.save();
	}

	public static void loadPostInit(){
		if(config.hasChanged())
			config.save();
	}

	public static int loadPropInt(String propName, String desc, int default_){
		Property prop = config.get(Configuration.CATEGORY_GENERAL, propName,
		        default_);
		prop.comment = desc;
		return prop.getInt(default_);
	}

	public static double loadPropDouble(String propName, String desc,
	        double default_){
		Property prop = config.get(Configuration.CATEGORY_GENERAL, propName,
		        default_);
		prop.comment = desc;
		return prop.getDouble(default_);
	}

	public static boolean loadPropBool(String propName, String desc,
	        boolean default_){
		Property prop = config.get(Configuration.CATEGORY_GENERAL, propName,
		        default_);
		prop.comment = desc;
		return prop.getBoolean(default_);
	}

	public static class ChangeListener{

		@SubscribeEvent
		public void onConfigChanged(
		        ConfigChangedEvent.OnConfigChangedEvent eventArgs){
			if(eventArgs.modID.equals("BotanicalWorkshop"))
				load();
		}

	}
}