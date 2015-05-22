package lazersmoke.botanicalworkshop.common.item;

import java.util.List;

import lazersmoke.botanicalworkshop.api.mana.IGatewayCatalyst;
import lazersmoke.botanicalworkshop.common.item.catalyst.ItemCatalyst;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import vazkii.botania.client.core.helper.IconHelper;

public class ItemBindingCrystal extends ItemCatalyst implements IGatewayCatalyst{
	IIcon[] icons;
	
	ItemBindingCrystal(){
		super(LibItemNames.BINDING_CRYSTAL);
		setHasSubtypes(true);
		setUnlocalizedName(LibItemNames.BINDING_CRYSTAL);
	}
	
	@Override
	public void registerIcons(IIconRegister register){
		icons = new IIcon[4];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forItem(register, this, i);
	}
	
	@Override
	public IIcon getIconFromDamage(int par1) {
		return icons[Math.min(icons.length - 1, par1)];
	}

	@Override
	public void getSubItems(Item item, CreativeTabs creativeTab, List subItemList) {
		for(int i = 0; i < icons.length; i++)
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