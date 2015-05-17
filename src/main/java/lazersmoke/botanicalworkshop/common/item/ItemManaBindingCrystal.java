package lazersmoke.botanicalworkshop.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import lazersmoke.botanicalworkshop.client.core.helper.IconHelper;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import vazkii.botania.common.item.ItemMod;

public class ItemManaBindingCrystal extends ItemMod{
	IIcon[] icons;
	
	ItemManaBindingCrystal(){
		super();
		setHasSubtypes(true);
		setUnlocalizedName(LibItemNames.MANA_BINDING_CRYSTAL);
	}
	
	@Override
	public void registerIcons(IIconRegister register){
		icons = new IIcon[2];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forItem(register, this, i);
	}
	
	@Override
	public IIcon getIconFromDamage(int par1) {
		return icons[Math.min(icons.length - 1, par1)];
	}

	@Override
	public void getSubItems(Item item, CreativeTabs creativeTab, List subItemList) {
		for(int i = 0; i < 16; i++)
			subItemList.add(new ItemStack(item, 1, i));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return getUnlocalizedNameLazy(itemStack) + itemStack.getItemDamage();
	}

	String getUnlocalizedNameLazy(ItemStack itemStack) {
		return super.getUnlocalizedName(itemStack);
	}
}