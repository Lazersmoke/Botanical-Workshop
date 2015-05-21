/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [May 16, 2014, 7:00:36 PM (GMT)]
 */
package lazersmoke.botanicalworkshop.common.item.block;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import lazersmoke.botanicalworkshop.client.lib.LibResources;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.IPickupAchievement;

public class ItemBlockMod extends ItemBlock implements IPickupAchievement, ICraftAchievement {

	public ItemBlockMod(Block block) {
		super(block);
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack par1ItemStack) {
		return getUnlocalizedNameInefficiently_(par1ItemStack).replaceAll("tile.", "tile." + LibResources.PREFIX_MOD);
	}

	public String getUnlocalizedNameInefficiently_(ItemStack stack) {
		return super.getUnlocalizedNameInefficiently(stack);
	}

	@Override
	public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
		return field_150939_a instanceof ICraftAchievement ? ((ICraftAchievement) field_150939_a).getAchievementOnCraft(stack, player, matrix) : null;
	}

	@Override
	public Achievement getAchievementOnPickup(ItemStack stack, EntityPlayer player, EntityItem item) {
		return field_150939_a instanceof IPickupAchievement ? ((IPickupAchievement) field_150939_a).getAchievementOnPickup(stack, player, item) : null;
	}

}