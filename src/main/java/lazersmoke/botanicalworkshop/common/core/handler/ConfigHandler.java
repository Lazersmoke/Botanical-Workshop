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
		String desc = "Set this to false to disable the crafting of Shifted Armor.";
		LibConfigs.CRAFT_SHIFTED_ARMOR = loadPropBool("crafting.gateway.shiftedArmor", desc, true);;

		// Table Crafting
		desc = "Set this to false to disable the crafting of Elven Mana Pools.";
		LibConfigs.CRAFT_ELVEN_POOL = loadPropBool("crafting.table.elvenPool", desc, true);

		desc = "Set this to false to disable the crafting of Simple Catalysts.";
		LibConfigs.CRAFT_SIMPLE_CATALYST = loadPropBool("crafting.table.simpleCatalyst", desc, true);

		desc = "Set this to false to disable the crafting of Empty Catalysts. Not reccommended";
		LibConfigs.CRAFT_EMPTY_CATALYST = loadPropBool("crafting.table.emptyCatalyst", desc, true);

		desc = "Set this to false to disable the crafting of Elven Crystals";
		LibConfigs.CRAFT_EMPTY_CATALYST = loadPropBool("crafting.table.elvenCrystal", desc, true);

		desc = "Set this to false to disable the crafting of Weak Gateway Cores";
		LibConfigs.CRAFT_WEAK_GATEWAY_CORE = loadPropBool("crafting.table.weakGatewayCore", desc, true);

		desc = "Set this to false to disable the crafting of Shifted Armor.";
		LibConfigs.CRAFT_SHIFTED_ARMOR = loadPropBool("crafting.table.shiftedArmor", desc, true);

		desc = "Set this to false to disable the crafting of Shifted Hops Upgrades.";
		LibConfigs.CRAFT_SHIFTED_HOPS_UPGRADE = loadPropBool("crafting.table.shiftedHopsUpgrade", desc, true);

		desc = "Set this to false to disable the crafting of Shifted Phase Upgrades.";
		LibConfigs.CRAFT_SHIFTED_PHASE_UPGRADE = loadPropBool("crafting.table.shiftedPhaseUpgrade", desc, true);

		desc = "Set this to false to disable the crafting of Shifted Revealing Upgrades.";
		LibConfigs.CRAFT_SHIFTED_REVEALING_UPGRADE = loadPropBool("crafting.table.shiftedRevealingUpgrade", desc, true);

		desc = "Set this to false to disable the crafting of Voltmeters.";
		LibConfigs.CRAFT_VOLTMETER = loadPropBool("crafting.table.voltmeter", desc, true);

		desc = "Set this to false to disable the crafting of Lightning Rods.";
		LibConfigs.CRAFT_LIGHTNING_ROD = loadPropBool("crafting.table.lightningRod", desc, true);

		desc = "Set this to false to disable the crafting of Shifted Hypervelocity Skewers.";
		LibConfigs.CRAFT_SHIFTED_HYPERVELOCITY_SKEWER = loadPropBool("crafting.table.shiftedHypervelocitySkewer", desc, true);

		desc = "Set this to false to disable the crafting of Lightning Cells.";
		LibConfigs.CRAFT_LIGHTNING_CELL = loadPropBool("crafting.table.lightningCell", desc, true);

		desc = "Set this to false to disable the crafting of Worldshaper Staves.";
		LibConfigs.CRAFT_WORLDSHAPER_STAFF = loadPropBool("crafting.table.worldshaperStaff", desc, true);

		// Elven Portal Craftinghis to false to disable the crafting of Elven Mana Pools using alfheim portals.";
		LibConfigs.PORTAL_CRAFT_ELVEN_POOL = loadPropBool("crafting.portal.elvenPool", desc, true);

		desc = "Set this to false to disable the crafting of Elven Crystals using alfheim portals.";
		LibConfigs.PORTAL_CRAFT_ELVEN_CRYSTAL = loadPropBool("crafting.portal.elvenCrystal", desc, true);

		desc = "Set this to false to disable the crafting of Elven Knotting using alfheim portals.";
		LibConfigs.PORTAL_CRAFT_ELVEN_KNOTTING = loadPropBool("crafting.portal.elvenKnotting", desc, true);

		desc = "Set this to false to disable the crafting of Gateway Cores using alfheim portals.";
		LibConfigs.PORTAL_CRAFT_GATEWAY_CORE = loadPropBool("crafting.portal.gatewayCore", desc, true);

		// Gateway Crafting
		desc = "Set this to false to disable the crafting of Mana Binding Crystals using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_MANA_BINDING_CRYSTAL = loadPropBool("crafting.gateway.manaBindingCrystal", desc, true);

		desc = "Set this to false to disable the crafting of Blood Binding Crystals using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_BLOOD_BINDING_CRYSTAL = loadPropBool("crafting.gateway.bloodBindingCrystal", desc, true);

		desc = "Set this to false to disable the crafting of Thaumic Binding Crystals using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_THAUMIC_BINDING_CRYSTAL = loadPropBool("crafting.gateway.thaumicBindingCrystal", desc, true);

		desc = "Set this to false to disable the crafting of Lightning Binding Crystals using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_LIGHTNING_BINDING_CRYSTAL = loadPropBool("crafting.gateway.lightningBindingCrystal", desc, true);

		desc = "Set this to false to disable the crafting of Mana Catalysts using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_MANA_CATALYST = loadPropBool("crafting.gateway.manaCatalyst", desc, true);

		desc = "Set this to false to disable the crafting of Thaumic Cores using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_THAUMIC_CORE = loadPropBool("crafting.gateway.thaumicCore", desc, true);

		desc = "Set this to false to disable the crafting of Thaumic Catalyst using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_THAUMIC_CATALYST = loadPropBool("crafting.gateway.thaumicCatalyst", desc, true);

		desc = "Set this to false to disable the crafting of Shifted Matter using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_SHIFTED_MATTER = loadPropBool("crafting.gateway.shiftedMatter", desc, true);

		desc = "Set this to false to disable the crafting of Shifted Armor using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_SHIFTED_ARMOR = loadPropBool("crafting.gateway.shiftedArmor", desc, true);

		desc = "Set this to false to disable the crafting of Shifted Hops Upgrades using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_SHIFTED_HOPS_UPGRADE = loadPropBool("crafting.gateway.shiftedHopsUpgrade", desc, true);

		desc = "Set this to false to disable the crafting of Shifted Phase Upgrades using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_SHIFTED_PHASE_UPGRADE = loadPropBool("crafting.gateway.shiftedPhaseUpgrade", desc, true);

		desc = "Set this to false to disable the crafting of Shifted Revealing Upgrades using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_SHIFTED_REVEALING_UPGRADE = loadPropBool("crafting.gateway.shiftedRevealingUpgrade", desc, true);

		desc = "Set this to false to disable the crafting of Scrap Metal using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_SCRAP_METAL = loadPropBool("crafting.gateway.scrapMetal", desc, true);

		desc = "Set this to false to disable the crafting of Elven Crystal using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_ELVEN_CRYSTAL = loadPropBool("crafting.gateway.elvenCrystal", desc, true);

		desc = "Set this to false to disable the crafting of Elven Knotting using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_ELVEN_KNOTTING = loadPropBool("crafting.gateway.elvenKnotting", desc, true);

		desc = "Set this to false to disable the crafting of Lightning Cores using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_LIGHTNING_CORE = loadPropBool("crafting.gateway.lightningCore", desc, true);

		desc = "Set this to false to disable the crafting of Lightning Consuming Catalysts using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_LIGHTNING_CONSUMING_CATALYST = loadPropBool("crafting.gateway.lightningConsumingCatalyst", desc, true);

		desc = "Set this to false to disable the crafting of Lightning Generation Catalysts using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_LIGHTNING_GENERATION_CATALYST = loadPropBool("crafting.gateway.lightningGenerationCatalyst", desc, true);

		desc = "Set this to false to disable the crafting of Thaumtanical Transposers using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_THAUMTANICAL_TRANSPOSER = loadPropBool("crafting.gateway.thaumtanicalTransposer", desc, true);

		desc = "Set this to false to disable the crafting of Lightning Transformers using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_LIGHTNING_TRANSFORMER = loadPropBool("crafting.gateway.lightningTransformer", desc, true);

		desc = "Set this to false to disable the crafting of Lightning Capacitors using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_LIGHTNING_CAPACITOR = loadPropBool("crafting.gateway.lightningCapacitor", desc, true);

		desc = "Set this to false to disable the crafting of vanilla recipes and recipes from mods loaded before Botanical Workshop using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_VANILLA_RECIPES = loadPropBool("crafting.gateway.vanillaRecipes", desc, true);

		desc = "Set this to false to disable the crafting of Lightning Furni using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_LIGHTNING_FURNACE = loadPropBool("crafting.gateway.lightningFurnace", desc, true);

		desc = "Set this to false to disable the crafting of Shifted Hypervelocity Skewer using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_SHIFTED_HYPERVELOCITY_SKEWER = loadPropBool("crafting.gateway.shiftedHypervelocitySkewer", desc, true);

		desc = "Set this to false to disable the crafting of Crafting Automation Catalyst using the Gateway.";
		LibConfigs.GATEWAY_CRAFT_CRAFTING_AUTOMATION_CATALYST = loadPropBool("crafting.gateway.craftingAutomationCatalyst", desc, true);

		// Gameplay Settings
		desc = "Set this to change the mana cost of opening a Gateway.";
		LibConfigs.GATEWAY_OPENING_MANA_COST = loadPropInt("gameplay.gateway.openingManaCost", desc, 1000000);

		desc = "Set this to change the mana cost of keeping a gateway open per tick.";
		LibConfigs.GATEWAY_TICK_MANA_COST = loadPropInt("gameplay.gateway.perTickManaCost", desc, 10);

		desc = "Set this to false to disable the songs of the flowers.";
		LibConfigs.TONAL_FLORA = loadPropBool("gameplay.flowers.tonalFlora", desc, true);

		desc = "Cost per tick of getting fire resist from shifted upgrade.";
		LibConfigs.SHIFTED_FIRE_IMMUNITY_COST = loadPropInt("gameplay.shifted.fireImmune", desc, 1000);

		desc = "Cost per tick of becoming invisible from shifted upgrade.";
		LibConfigs.SHIFTED_PHASE_COST = loadPropInt("gameplay.shifted.phase", desc, 100);

		desc = "Cost per tick of seeing aura nodes etc from shifted upgrade.";
		LibConfigs.SHIFTED_REVEALING_COST = loadPropInt("gameplay.shifted.revealing", desc, 10);

		desc = "Cost per tick of getting mad hops from shifted upgrade.";
		LibConfigs.SHIFTED_HOPS_COST = loadPropInt("gameplay.shifted.hops", desc, 10);

		desc = "Cost of teleposing a block with worldshaper staff.";
		LibConfigs.WORLDSHAPER_TELEPOSE_COST = loadPropInt("gameplay.shifted.worldshaper.telepose", desc, 100);

		desc = "Cost of repairing a point of damage on a shifted item.";
		LibConfigs.SHIFTED_REPAIR_COST = loadPropInt("gameplay.shifted.repair", desc, 10);

		desc = "Conversion rate from lightning to mana.";
		LibConfigs.LIGHTNING_MANA_RATE = loadPropInt("gameplay.lightning.conversionrate", desc, 10);

		// Performance Settings
		desc = "Set this to change the particle density of Botanical Workshop effects. Default is 1";
		LibConfigs.PARTICLE_DENSITY = loadPropInt("performance.particles.density", desc, 1);

		if(config.hasChanged())
			config.save();
	}

	public static void loadPostInit(){
		if(config.hasChanged())
			config.save();
	}

	public static int loadPropInt(String propName, String desc, int default_){
		final Property prop = config.get(Configuration.CATEGORY_GENERAL, propName, default_, desc);
		return prop.getInt(default_);
	}

	public static double loadPropDouble(String propName, String desc, double default_){
		final Property prop = config.get(Configuration.CATEGORY_GENERAL, propName, default_, desc);
		return prop.getDouble(default_);
	}

	public static boolean loadPropBool(String propName, String desc, boolean default_){
		final Property prop = config.get(Configuration.CATEGORY_GENERAL, propName, default_, desc);
		return prop.getBoolean(default_);
	}

	public static class ChangeListener{

		@SubscribeEvent
		public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs){
			if(eventArgs.modID.equals("BotanicalWorkshop"))
				load();
		}

	}
}