/**
 * This class was created by <Vazkii>. It's distributed as part of the Botania Mod. Get the Source Code in github: https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 14, 2014, 5:20:53 PM (GMT)]
 */
package lazersmoke.botanicalworkshop.common.core;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vazkii.botania.client.lib.LibResources;

public final class BotanicalWorkshopCreativeTab extends CreativeTabs{

	public static BotanicalWorkshopCreativeTab INSTANCE = new BotanicalWorkshopCreativeTab();
	List list;

	public BotanicalWorkshopCreativeTab(){
		super("BotanicalWorkshop");
		setNoTitle();
		setBackgroundImageName(LibResources.GUI_CREATIVE);
	}

	@Override
	public ItemStack getIconItemStack(){
		return new ItemStack(vazkii.botania.common.item.ModItems.lexicon);
	}

	@Override
	public Item getTabIconItem(){
		return getIconItemStack().getItem();
	}

	@Override
	public boolean hasSearchBar(){
		return true;
	}

	@Override
	public void displayAllReleventItems(List list){
		this.list = list;

	}
}