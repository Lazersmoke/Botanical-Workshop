package lazersmoke.botanicalworkshop.api.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lazersmoke.botanicalworkshop.common.item.ModItems;
import net.minecraft.item.ItemStack;

public class RecipeGatewayTransmutation {

	ItemStack output;
	List<ItemStack> inputs;
	ItemStack catalyst;

	public RecipeGatewayTransmutation(ItemStack output, ItemStack catalyst, ItemStack... inputs) {
		this.output = output;
		this.catalyst = catalyst;
		this.inputs = Arrays.asList(inputs);
	}
	
	public RecipeGatewayTransmutation(String defaultrecipe){
		if(defaultrecipe == "default"){
			this.output = new ItemStack(ModItems.botanicalResource, 1, 1);
			this.catalyst = new ItemStack(ModItems.botanicalResource, 1, 1);
			this.inputs = Arrays.asList(new ItemStack(ModItems.botanicalResource, 1, 1));
		}
	}

	public boolean matches(List<ItemStack> stacks, boolean noGhost) {
		List<ItemStack> remainingInputs = new ArrayList<ItemStack>(inputs);
		List<ItemStack> toKill = new ArrayList<ItemStack>();
		for(ItemStack stack : stacks)
			for(ItemStack input : inputs)
				if(simpleAreStacksEqual(stack, input)){
					remainingInputs.remove(input);
					if(noGhost)
						toKill.add(stack);
				}
		if(noGhost)
			for(ItemStack kill : toKill)
				stacks.remove(kill);
		return remainingInputs.isEmpty();
	}

	boolean simpleAreStacksEqual(ItemStack stack, ItemStack stack2) {
		return stack.getItem() == stack2.getItem() && stack.getItemDamage() == stack2.getItemDamage();
	}

	public List<ItemStack> getInputs() {
		return new ArrayList<ItemStack>(inputs);
	}

	public ItemStack getOutput() {
		return output == null ? new ItemStack(ModItems.botanicalResource, 1, 0) : output;
	}

	public ItemStack getCatalyst() {
		return catalyst == null ? new ItemStack(ModItems.botanicalResource, 1, 0) : catalyst;
	}

}