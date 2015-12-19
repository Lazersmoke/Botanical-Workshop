package lazersmoke.botanicalworkshop.api.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import lazersmoke.botanicalworkshop.common.item.ModItems;
import net.minecraft.item.ItemStack;

/**
 * Gateway Transumtation Recipe.
 *
 * @author Lazersmoke
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
	public RecipeGatewayTransmutation(ItemStack output, ItemStack catalyst, ItemStack... inputs){
		this.output = output;
		this.catalyst = catalyst;
		final List<ItemStack> localToReduce = new ArrayList<ItemStack>(Arrays.asList(inputs));
		localToReduce.removeAll(Collections.singleton(null));// Remove nulls
		this.inputs = Arrays.asList(inputs);// Don't reduce inputs or it shows up seperate in lexicon; compress them so they show nicely!
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
	 *
	 * @param stacks
	 * Stacks to check
	 * @param comsumeInputs
	 * If true, removes items used to satisfy recipe
	 * @return 0 means no match, 1 means partial match, 2 means full match
	 */
	public int matches(List<ItemStack> stacks, boolean comsumeInputs){
		final List<ItemStack> remainingInputs = new CopyOnWriteArrayList<ItemStack>(reduceStacks(inputs));
		final List<ItemStack> toKill = new ArrayList<ItemStack>();
		final List<ItemStack> reducedStacks = reduceStacks(stacks);

		for(final ItemStack stack : reducedStacks)
			for(final ItemStack input : remainingInputs)
				if(simpleAreStacksEqual(stack, input)){
					remainingInputs.remove(input);
					if(comsumeInputs)
						toKill.add(stack);
					break;
				}
		if(comsumeInputs)
			for(final ItemStack curr : toKill){
				reducedStacks.remove(curr);
				stacks.clear();
				stacks.addAll(reducedStacks);
			}
		return remainingInputs.isEmpty() ? (inputs.size() == stacks.size() ? 2 : 1) : 0;
	}

	// Getters
	/**
	 * Returns the list of input ItemStacks
	 *
	 * @return List of input ItemStacks
	 */
	public List<ItemStack> getInputs(){
		return new ArrayList<ItemStack>(inputs);
	}

	/**
	 * Returns the output ItemStack
	 *
	 * @return Output ItemStack
	 */
	public ItemStack getOutput(){
		return output == null ? new ItemStack(ModItems.botanicalResource, 1, 0) : output.copy();
	}

	/**
	 * Returns the catalyst ItemStack
	 *
	 * @return Catalyst ItemStack
	 */
	public ItemStack getCatalyst(){
		return catalyst == null ? new ItemStack(ModItems.botanicalResource, 1, 0) : catalyst.copy();
	}

	// Helper Methods
	private static boolean simpleAreStacksEqual(ItemStack stack, ItemStack stack2){
		return stack.getItem() == stack2.getItem() && (stack.getItemDamage() == stack2.getItemDamage() || (stack.getItemDamage() == 32767 || stack2.getItemDamage() == 32767));
	}

	public static List<ItemStack> reduceStacks(List<ItemStack> toReduce){
		final List<ItemStack> reduced = new ArrayList<ItemStack>();
		final List<ItemStack> localToReduce = new ArrayList<ItemStack>(toReduce);
		localToReduce.removeAll(Collections.singleton(null));// Remove nulls
		for(final ItemStack curr : localToReduce)
			if(curr.stackSize == 1)
				reduced.add(curr.copy());
			else
				for(int i = 0; i < curr.stackSize; i++){
					final ItemStack newCurr = curr.copy();
					newCurr.stackSize = 1;
					reduced.add(newCurr);
				}
		return reduced;
	}
}