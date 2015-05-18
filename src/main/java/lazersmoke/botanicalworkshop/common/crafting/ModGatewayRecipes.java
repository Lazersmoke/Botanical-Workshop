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
	
	public static void init(){
		if(LibConfigs.GATEWAY_CRAFT_MANA_BINDING_CRYSTAL)//Mana Diamond, Mana Pearl, Nether Star (you only need one ever)
			manaBindingCrystal = BotanicalWorkshopAPI.registerGatewayRecipe(new ItemStack(ModItems.bindingCrystal, 1, 0), new ItemStack(ModItems.simpleCatalyst), new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 1, 1)/*mana diamond*/, new ItemStack(Items.nether_star), new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 1, 2)/*mana pearl*/);
		if(LibConfigs.GATEWAY_CRAFT_BLOOD_BINDING_CRYSTAL && BotanicalWorkshop.bloodMagicLoaded){
			Item orb = (Item) Item.itemRegistry.getObject("AWWayofTime:apprenticeBloodOrb");
			Item bucket = (Item) Item.itemRegistry.getObject("AWWayofTime:bucketLife");
			bloodBindingCrystal = BotanicalWorkshopAPI.registerGatewayRecipe(new ItemStack(ModItems.bindingCrystal, 1, 1), new ItemStack(ModItems.simpleCatalyst), new ItemStack(orb), new ItemStack(bucket));
		}
		if(LibConfigs.GATEWAY_CRAFT_THAUMIC_BINDING_CRYSTAL && BotanicalWorkshop.thaumcraftLoaded){
			Item thaummeter = (Item) Item.itemRegistry.getObject("Thaumcraft:ItemThaumometer");
			Item resource = (Item) Item.itemRegistry.getObject("Thaumcraft:ItemResource");
			thaumicBindingCrystal = BotanicalWorkshopAPI.registerGatewayRecipe(new ItemStack(ModItems.bindingCrystal, 1, 2), new ItemStack(ModItems.simpleCatalyst), new ItemStack(thaummeter), new ItemStack(resource, 1, 2)/*thaumium ingot*/, new ItemStack(resource, 1, 14)/*salis mundus*/);
		}
	}
}