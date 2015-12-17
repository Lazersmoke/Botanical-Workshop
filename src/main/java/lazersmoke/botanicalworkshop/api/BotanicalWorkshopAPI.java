package lazersmoke.botanicalworkshop.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lazersmoke.botanicalworkshop.api.recipe.RecipeGatewayTransmutation;
import lazersmoke.botanicalworkshop.api.shifted.IShiftedArmorUpgrade;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class BotanicalWorkshopAPI{

	public static List<RecipeGatewayTransmutation> gatewayRecipes = new ArrayList<RecipeGatewayTransmutation>();
	public static Map<String, IShiftedArmorUpgrade> shiftedUpgrades = new HashMap<String, IShiftedArmorUpgrade>();
	public static Set<String> subtilesForCreativeMenu = new LinkedHashSet<String>();

	public static ArmorMaterial shiftedArmorMaterial = EnumHelper.addArmorMaterial("SHIFTED", 16, new int[] {2, 6, 5, 2}, 18);
	public static ToolMaterial shiftedToolMaterial = EnumHelper.addToolMaterial("SHIFTED", 3, 300, 6.2F, 0F, 20);

	/**
	 * Registers a Gateway recipe (throw some items in a Gateway).
	 *
	 * @param output
	 * The ItemStack to return.
	 * @param catalyst
	 * The catalyst required to craft this recipe
	 * @param inputs
	 * The items required
	 * @return The recipe created.
	 */
	public static RecipeGatewayTransmutation registerGatewayRecipe(ItemStack output, ItemStack catalyst, ItemStack... inputs){
		final RecipeGatewayTransmutation recipe = new RecipeGatewayTransmutation(output, catalyst, inputs);
		gatewayRecipes.add(recipe);
		return recipe;
	}

	/**
	 * Registers a Gateway recipe (throw some items in a Gateway).
	 *
	 * @param output
	 * The ItemStack to return.
	 * @param catalyst
	 * The catalyst required to craft this recipe
	 * @param inputs
	 * The items required, must be List<ItemStack>
	 * @return The recipe created.
	 */
	public static RecipeGatewayTransmutation registerGatewayRecipe(ItemStack output, ItemStack catalyst, List<ItemStack> inputs){
		inputs.removeAll(Collections.singleton(null));// Remove nulls
		return registerGatewayRecipe(output, catalyst, inputs.toArray(new ItemStack[inputs.size()]));
	}

	/**
	 * Registers a shifted armor upgrade item.
	 *
	 * @param upgrade
	 * The upgrade to register.
	 * @return The upgrade registered.
	 */
	public static IShiftedArmorUpgrade registerShiftedArmorUpgrade(IShiftedArmorUpgrade upgrade){
		shiftedUpgrades.put(upgrade.getKey(), upgrade);
		return upgrade;
	}
}