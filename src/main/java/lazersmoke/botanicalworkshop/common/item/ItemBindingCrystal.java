package lazersmoke.botanicalworkshop.common.item;

import java.util.List;

import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.crafting.recipe.LexiconBindingRecipe;
import lazersmoke.botanicalworkshop.common.item.catalyst.ItemCatalyst;
import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import vazkii.botania.api.lexicon.KnowledgeType;
import vazkii.botania.api.lexicon.LexiconEntry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemBindingCrystal extends ItemCatalyst{

	IIcon[] icons;

	ItemBindingCrystal(){
		super(LibItemNames.BINDING_CRYSTAL);
		setHasSubtypes(true);
		GameRegistry.addRecipe(new LexiconBindingRecipe());
	}

	@Override
	public void registerIcons(IIconRegister register){
		icons = new IIcon[4];
		for(int i = 0; i < icons.length; i++)
			icons[i] = register.registerIcon(LibResources.PREFIX_MOD
			        + getUnlocalizedName().replaceAll("item\\.", "") + i);
	}

	@Override
	public IIcon getIconFromDamage(int par1){
		return icons[Math.min(icons.length - 1, par1)];
	}

	@Override
	public void getSubItems(Item item, CreativeTabs creativeTab,
	        List subItemList){
		for(int i = 0; i < icons.length; i++)
			subItemList.add(new ItemStack(item, 1, i));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack){
		return getUnlocalizedNameLazy(itemStack) + itemStack.getItemDamage();
	}

	String getUnlocalizedNameLazy(ItemStack itemStack){
		return super.getUnlocalizedName(itemStack);
	}

	public static KnowledgeType getKnowledgeType(ItemStack bindingCrystal){
		switch(bindingCrystal.getItemDamage()){
			case 0:
				return LexiconData.workshopKnowledge;
			case 1:
				return LexiconData.bloodKnowledge;
			case 2:
				return LexiconData.thaumicKnowledge;
			case 3:
				return LexiconData.mechanicalKnowledge;
		}
		return LexiconData.workshopKnowledge;
	}

	public static LexiconEntry getOpeningEntry(ItemStack bindingCrystal){
		switch(bindingCrystal.getItemDamage()){
			case 0:
				return LexiconData.workshopIntro;
			case 1:
				return LexiconData.bloodIntro;
			case 2:
				return LexiconData.thaumicIntro;
			case 3:
				return LexiconData.mechanicalIntro;
		}
		return LexiconData.workshopIntro;
	}

	@Override
	public boolean hasContainerItem(){
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack){
		return itemStack;
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack){
		return false;
	}
}