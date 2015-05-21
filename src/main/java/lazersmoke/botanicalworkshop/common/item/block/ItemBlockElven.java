package lazersmoke.botanicalworkshop.common.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.recipe.IElvenItem;

public class ItemBlockElven extends ItemBlockMod implements IElvenItem {

	public ItemBlockElven(Block block) {
		super(block);
	}

	@Override
	public boolean isElvenItem(ItemStack stack) {
		return ((IElvenItem) field_150939_a).isElvenItem(stack);
	}

}