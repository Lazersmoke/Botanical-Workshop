package lazersmoke.botanicalworkshop.api.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class RecipeSuperGateway {

	ItemStack output;
	List<ItemStack> inputs;

	public RecipeSuperGateway(ItemStack output, List<ItemStack> inputs) {
		this.output = output;
		this.inputs = inputs;
	}

	public boolean matches(List<ItemStack> stacks, boolean remove) {
		List<ItemStack> remainingInputs = new ArrayList<ItemStack>(inputs);
		for(ItemStack stack : stacks)
			for(ItemStack input : remainingInputs)
				if(simpleAreStacksEqual(stack, input))
					remainingInputs.remove(input);
		return remainingInputs.isEmpty();
	}

	boolean simpleAreStacksEqual(ItemStack stack, ItemStack stack2) {
		return stack.getItem() == stack2.getItem() && stack.getItemDamage() == stack2.getItemDamage();
	}

	public List<ItemStack> getInputs() {
		return new ArrayList<ItemStack>(inputs);
	}

	public ItemStack getOutput() {
		return output;
	}

}