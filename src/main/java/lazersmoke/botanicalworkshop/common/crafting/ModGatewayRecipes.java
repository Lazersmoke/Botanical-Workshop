package lazersmoke.botanicalworkshop.common.crafting;

import lazersmoke.botanicalworkshop.api.BotanicalWorkshopAPI;
import lazersmoke.botanicalworkshop.api.recipe.RecipeGateway;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.item.ModItems;
import lazersmoke.botanicalworkshop.common.lib.LibConfigs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModGatewayRecipes{
	
	public static RecipeGateway manaBindingCrystal;
	public static RecipeGateway bloodBindingCrystal;
	public static RecipeGateway thaumicBindingCrystal;
	public static RecipeGateway mechanicalBindingCrystal;
	
	public static void init(){
		if(LibConfigs.GATEWAY_CRAFT_MANA_BINDING_CRYSTAL)
			manaBindingCrystal = BotanicalWorkshopAPI.registerGatewayRecipe(
					new ItemStack(ModItems.bindingCrystal, 1, 0), //Mana Binding Crystal
					new ItemStack(ModItems.simpleCatalyst), //Simple Catalyst
					new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 1, 1), //mana diamond
					new ItemStack(Items.nether_star), //Nether Star
					new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 1, 2));//mana pearl
		
		if(LibConfigs.GATEWAY_CRAFT_BLOOD_BINDING_CRYSTAL && BotanicalWorkshop.bloodMagicLoaded)
			bloodBindingCrystal = BotanicalWorkshopAPI.registerGatewayRecipe(
					new ItemStack(ModItems.bindingCrystal, 1, 1), //Blood Binding Crystal
					new ItemStack(ModItems.simpleCatalyst), 
					new ItemStack((Item) Item.itemRegistry.getObject("AWWayofTime:apprenticeBloodOrb")), //Apprentice Blood Orb
					new ItemStack((Item) Item.itemRegistry.getObject("AWWayofTime:bucketLife"))); //Bucket of Life Essence
		if(LibConfigs.GATEWAY_CRAFT_THAUMIC_BINDING_CRYSTAL && BotanicalWorkshop.thaumcraftLoaded)
			thaumicBindingCrystal = BotanicalWorkshopAPI.registerGatewayRecipe(
					new ItemStack(ModItems.bindingCrystal, 1, 2), //Thaumic Binding Crystal
					new ItemStack(ModItems.simpleCatalyst), 
					new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:ItemThaumometer")), //Thaumometer
					new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:ItemResource"), 1, 2), //Thaumium Ingot
					new ItemStack((Item) Item.itemRegistry.getObject("Thaumcraft:ItemResource"), 1, 14)); //Salis Mundus
		/*if(LibConfigs.GATEWAY_CRAFT_MECHANICAL_BINDING_CRYSTAL)
			mechanicalBindingCrystal = BotanicalWorkshopAPI.registerGatewayRecipe(
					new ItemStack(ModItems.bindingCrystal, 1, 3), //Mechanical Binding Crystal
					new ItemStack(ModItems.simpleCatalyst), //Simple Catalyst
					new ItemStack())*/
	}
}