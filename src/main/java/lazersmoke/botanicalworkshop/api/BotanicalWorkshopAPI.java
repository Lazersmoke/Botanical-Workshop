package lazersmoke.botanicalworkshop.api;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import lazersmoke.botanicalworkshop.api.recipe.RecipeGatewayTransmutation;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class BotanicalWorkshopAPI{

	public static List<RecipeGatewayTransmutation> gatewayRecipes = new ArrayList<RecipeGatewayTransmutation>();
	public static Set<String> subtilesForCreativeMenu = new LinkedHashSet<String>();
	public static ArmorMaterial shiftedArmorMaterial = EnumHelper.addArmorMaterial("SHIFTED", 16, new int[] { 2, 6, 5, 2 }, 18);
		
	/**
	 * Registers a Gateway recipe (throw some items in a Gateway).
	 * @param output The ItemStack to return.
	 * @param inputs The items required, must be List<ItemStack>
	 * @return The recipe created.
	 */
	public static RecipeGatewayTransmutation registerGatewayRecipe(ItemStack output, ItemStack catalyst, ItemStack... inputs) {
		RecipeGatewayTransmutation recipe = new RecipeGatewayTransmutation(output, catalyst, inputs);
		gatewayRecipes.add(recipe);
		return recipe;
	}
}