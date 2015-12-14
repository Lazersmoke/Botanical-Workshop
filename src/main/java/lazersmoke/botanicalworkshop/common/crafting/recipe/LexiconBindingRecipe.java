package lazersmoke.botanicalworkshop.common.crafting.recipe;

import lazersmoke.botanicalworkshop.common.item.ItemBindingCrystal;
import lazersmoke.botanicalworkshop.common.item.ModItems;
import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.api.lexicon.KnowledgeType;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.item.ItemLexicon;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class LexiconBindingRecipe implements IRecipe{

	@Override
	public boolean matches(InventoryCrafting var1, World var2){
		boolean foundCrystal = false;
		boolean foundLexicon = false;

		for(int i = 0; i < var1.getSizeInventory(); i++){
			ItemStack stack = var1.getStackInSlot(i);
			if(stack != null){
				if(foundCrystal && foundLexicon)
					return false;
				if(stack.getItem() == ModItems.bindingCrystal && !foundCrystal)
					foundCrystal = true;
				else if(!foundLexicon){
					if(stack.getItem() instanceof ILexicon)
						foundLexicon = true;
					else
						return false;
				}
			}
		}

		return foundCrystal && foundLexicon;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1){
		ItemStack lexiconStack = null;
		ItemStack bindingCrystal = null;

		for(int i = 0; i < var1.getSizeInventory(); i++){
			ItemStack stack = var1.getStackInSlot(i);
			if(stack != null){
				if(stack.getItem() instanceof ILexicon && lexiconStack == null)
					lexiconStack = stack;
				else
					bindingCrystal = stack;
			}
		}

		KnowledgeType knowledgeType = ItemBindingCrystal.getKnowledgeType(bindingCrystal);
		LexiconEntry openingEntry = ItemBindingCrystal.getOpeningEntry(bindingCrystal);

		ILexicon lexicon = (ILexicon) lexiconStack.getItem();
		if(lexicon.isKnowledgeUnlocked(lexiconStack, knowledgeType))
			return null;

		ItemStack copy = lexiconStack.copy();
		lexicon.unlockKnowledge(copy, knowledgeType);
		ItemLexicon.setForcedPage(copy, openingEntry.unlocalizedName);
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