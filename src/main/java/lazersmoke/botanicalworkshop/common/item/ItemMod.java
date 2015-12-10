package lazersmoke.botanicalworkshop.common.item;

import java.util.Arrays;
import java.util.List;

import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMod extends Item{

	private boolean namedMetadata = false;
	private static String[] creativelyHiddenNames = new String[] {
		LibItemNames.BOTANICAL_RESOURCE_NAMES[1]
	// Recipe Missing
	};

	public ItemMod(String unLocalizedName){
		super();
		if(! ( Arrays.asList(creativelyHiddenNames).contains(unLocalizedName) ))
			setCreativeTab(BotanicalWorkshop.creativeTab);
		this.setUnlocalizedName(unLocalizedName);
	}

	public Item setNamedMetadata(boolean namedMetadata){
		this.namedMetadata = namedMetadata;
		return this;
	}

	@Override
	public Item setUnlocalizedName(String par1Str){
		GameRegistry.registerItem(this, par1Str);
		return super.setUnlocalizedName(par1Str);
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack stack){
		if(namedMetadata){
			return super.getUnlocalizedNameInefficiently(stack).replaceAll("item\\.", "item." + LibResources.PREFIX_MOD) + "." + stack.getItemDamage();
		}
		return super.getUnlocalizedNameInefficiently(stack).replaceAll("item\\.", "item." + LibResources.PREFIX_MOD);
	}

	public void addStringToTooltip(String s, List<String> tooltip){
		tooltip.add(s.replaceAll("&", "\u00a7"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register){
		String resourceName = LibResources.PREFIX_MOD
				+ this.getUnlocalizedName().replaceAll("item\\.", "");
		itemIcon = register.registerIcon(resourceName);
	}
}