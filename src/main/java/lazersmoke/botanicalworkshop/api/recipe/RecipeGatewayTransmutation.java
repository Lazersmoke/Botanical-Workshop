package lazersmoke.botanicalworkshop.api.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import lazersmoke.botanicalworkshop.common.item.ModItems;
import net.minecraft.item.ItemStack;
/**
 * Gateway Transumtation Recipe.
 * @author Sam
 *
 */
public class RecipeGatewayTransmutation{
	private ItemStack output;
	private List<ItemStack> inputs;
	private ItemStack catalyst;
	
	/**
	 * Creates a gateway transumatation recipe
	 * 
	 * @param output
	 * Output ItemStack
	 * @param catalyst
	 * Catalyst ItemStack (not consumed)
	 * @param inputs
	 * List of input ItemStack's
	 */
	public RecipeGatewayTransmutation(ItemStack output, ItemStack catalyst, ItemStack ... inputs){
		this.output = output;
		this.catalyst = catalyst;
		this.inputs = reduceStacks(Arrays.asList(inputs));
	}
	/**
	 * Creates a predefined, named gateway transumatation recipe
	 * 
	 * @param name
	 * The recipe to retrieve
	 */
	public RecipeGatewayTransmutation(String name){
		if(name == "default"){
			this.output = new ItemStack(ModItems.botanicalResource, 1, 1);
			this.catalyst = new ItemStack(ModItems.botanicalResource, 1, 1);
			this.inputs = Arrays.asList(new ItemStack(ModItems.botanicalResource, 1, 1));
		}
	}
	/**
	 * Checks if given ItemStacks's match any gateway recipe
	 * @param stacks
	 * Stacks to check
	 * @param noGhost
	 * If true, removes items used to satisfy recipe
	 * @return
	 * Does it match a recipe?
	 */
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
	//Getters
	/**
	 * Returns the list of input ItemStacks
	 * @return
	 * List of input ItemStacks
	 */
	public List<ItemStack> getInputs(){
		return new ArrayList<ItemStack>(inputs);
	}
	/**
	 * Returns the output ItemStack
	 * @return
	 * Output ItemStack
	 */
	public ItemStack getOutput(){
		return output == null ? new ItemStack(ModItems.botanicalResource, 1, 0) : output;
	}
	/**
	 * Returns the catalyst ItemStack
	 * @return
	 * Catalyst ItemStack
	 */
	public ItemStack getCatalyst(){
		return catalyst == null ? new ItemStack(ModItems.botanicalResource, 1, 0) : catalyst;
	}
	
	//Helper Methods
	private boolean simpleAreStacksEqual(ItemStack stack, ItemStack stack2){
		return stack.getItem() == stack2.getItem() && stack.getItemDamage() == stack2.getItemDamage();
	}
	
	private List<ItemStack> reduceStacks(List<ItemStack> toReduce){
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
}