package lazersmoke.botanicalworkshop.common.crafting.recipe;

import lazersmoke.botanicalworkshop.api.shifted.IShiftedArmorUpgrade;
import lazersmoke.botanicalworkshop.common.item.equipment.armor.shifted.ItemShiftedArmor;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class ShiftedArmorUpgradeRecipe implements IRecipe{

	@Override
	public boolean matches(InventoryCrafting inventory, World world){
		boolean foundUpgrade = false;
		boolean foundArmor = false;

		for(int i = 0; i < inventory.getSizeInventory(); i++){
			ItemStack stack = inventory.getStackInSlot(i);
			if(stack != null){
				if(foundUpgrade && foundArmor)
					return false;
				if(stack.getItem() instanceof IShiftedArmorUpgrade
						&& !foundUpgrade)
					foundUpgrade = true;
				else if(!foundArmor){
					if(stack.getItem() instanceof ItemShiftedArmor)
						foundArmor = true;
					else
						return false;
				}
			}
		}

		return foundUpgrade && foundArmor;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventory){
		ItemStack armorStack = null;
		ItemStack upgradeStack = null;

		for(int i = 0; i < inventory.getSizeInventory(); i++){
			ItemStack stack = inventory.getStackInSlot(i);
			if(stack != null){
				if(stack.getItem() instanceof ItemShiftedArmor
						&& armorStack == null)
					armorStack = stack;
				else
					upgradeStack = stack;
			}
		}
		ItemStack copy = armorStack.copy();
		ItemNBTHelper.setBoolean(copy, ItemShiftedArmor.TAG_UPGRADE_BASE
				+ ( (IShiftedArmorUpgrade) upgradeStack.getItem() ).getKey(),
				true);
		return copy;
	}

	@Override
	public int getRecipeSize(){
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput(){
		return null;
	}
}