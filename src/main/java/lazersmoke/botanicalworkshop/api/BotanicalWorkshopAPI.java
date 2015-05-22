package lazersmoke.botanicalworkshop.api;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import lazersmoke.botanicalworkshop.api.recipe.RecipeGateway;
import net.minecraft.item.ItemStack;

public class BotanicalWorkshopAPI{

	public static List<RecipeGateway> gatewayRecipes = new ArrayList<RecipeGateway>();
	public static Set<String> subtilesForCreativeMenu = new LinkedHashSet<String>();
		
	/**
	 * Registers a Gateway recipe (throw some items in a Gateway).
	 * @param output The ItemStack to return.
	 * @param inputs The items required, must be List<ItemStack>
	 * @return The recipe created.
	 */
	public static RecipeGateway registerGatewayRecipe(ItemStack output, ItemStack catalyst, ItemStack... inputs) {
		RecipeGateway recipe = new RecipeGateway(output, catalyst, inputs);
		gatewayRecipes.add(recipe);
		return recipe;
	}
}