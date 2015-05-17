package lazersmoke.botanicalworkshop.api;

import java.util.ArrayList;
import java.util.List;

import lazersmoke.botanicalworkshop.api.recipe.RecipeSuperGateway;
import net.minecraft.item.ItemStack;

public class BotanicalWorkshopAPI{

	public static List<RecipeSuperGateway> superGatewayRecipes = new ArrayList<RecipeSuperGateway>();
	
	/**
	 * Registers a Super Gateway recipe (throw some items in a Super Gateway).
	 * @param output The ItemStack to return.
	 * @param inputs The items required, must be List<ItemStack>
	 * @return The recipe created.
	 */
	public static RecipeSuperGateway registerSuperGatewayRecipe(ItemStack output, ItemStack catalyst, ItemStack... inputs) {
		RecipeSuperGateway recipe = new RecipeSuperGateway(output, catalyst, inputs);
		superGatewayRecipes.add(recipe);
		return recipe;
	}
	
}