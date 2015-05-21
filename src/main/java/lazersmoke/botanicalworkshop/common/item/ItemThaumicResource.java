package lazersmoke.botanicalworkshop.common.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.MinecraftForge;
import lazersmoke.botanicalworkshop.client.core.helper.IconHelper;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;

public class ItemThaumicResource extends ItemMod{
	final int types = 1;
	IIcon[] icons;

	public ItemThaumicResource() {
		super();
		setUnlocalizedName(LibItemNames.THAUMIC_RESOURCE);
		setHasSubtypes(true);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tabs, List subItemList) {
		for(int i = 0; i < types; i++)
			subItemList.add(new ItemStack(item, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		icons = new IIcon[types];
		for(int i = 0; i < icons.length; i++)
			icons[i] = IconHelper.forName(register, LibItemNames.THAUMIC_RESOURCE_NAMES[i]);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + LibItemNames.THAUMIC_RESOURCE_NAMES[Math.min(types - 1, stack.getItemDamage())];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		return icons[Math.min(icons.length - 1, par1)];
	}
}