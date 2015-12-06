package lazersmoke.botanicalworkshop.api.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.logging.log4j.Level;

import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.item.ModItems;
import net.minecraft.item.ItemStack;

public class RecipeGatewayTransmutation{

	ItemStack output;
	List<ItemStack> inputs;
	ItemStack catalyst;

	public RecipeGatewayTransmutation(ItemStack output, ItemStack catalyst,
			ItemStack ... inputs){
		this.output = output;
		this.catalyst = catalyst;
		this.inputs = reduceStacks(Arrays.asList(inputs));
	}

	public RecipeGatewayTransmutation(String defaultrecipe){
		if(defaultrecipe == "default"){
			this.output = new ItemStack(ModItems.botanicalResource, 1, 1);
			this.catalyst = new ItemStack(ModItems.botanicalResource, 1, 1);
			this.inputs = Arrays.asList(new ItemStack(
					ModItems.botanicalResource, 1, 1));
		}
	}

	public boolean matches(List<ItemStack> stacks, boolean noGhost){
		List<ItemStack> remainingInputs = new CopyOnWriteArrayList<ItemStack>(reduceStacks(inputs));
		List<ItemStack> toKill = new ArrayList<ItemStack>();
		List<ItemStack> reducedStacks = reduceStacks(stacks);
		for(ItemStack stack : reducedStacks)
			for(ItemStack input : remainingInputs)
				if(simpleAreStacksEqual(stack, input)){
					remainingInputs.remove(input);
					if(noGhost)
						toKill.add(stack);
					break;
				}
		if(noGhost)
			for(ItemStack curr : toKill)
				reducedStacks.remove(curr);
		stacks.clear();
		stacks.addAll(reducedStacks);
		return remainingInputs.isEmpty();
	}

	boolean simpleAreStacksEqual(ItemStack stack, ItemStack stack2){
		return stack.getItem() == stack2.getItem() && stack.getItemDamage() == stack2.getItemDamage();
	}
	List<ItemStack> reduceStacks(List<ItemStack> toReduce){
		List<ItemStack> reduced = new ArrayList<ItemStack>();
		for(ItemStack curr : toReduce){
			if(curr.stackSize == 1)
				reduced.add(curr.copy());
			else
				for(int i=0; i<curr.stackSize; i++){
					ItemStack newCurr = curr.copy();
					newCurr.stackSize = 1;
					reduced.add(newCurr);
				}
		}
		return reduced;
	}
	public List<ItemStack> getInputs(){
		return new ArrayList<ItemStack>(inputs);
	}

	public ItemStack getOutput(){
		return output == null ? new ItemStack(ModItems.botanicalResource, 1, 0) : output;
	}

	public ItemStack getCatalyst(){
		return catalyst == null ? new ItemStack(ModItems.botanicalResource, 1, 0) : catalyst;
	}

}