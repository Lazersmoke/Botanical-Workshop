package lazersmoke.botanicalworkshop.common.item.equipment.armor.shifted;

import thaumcraft.api.IGoggles;
import thaumcraft.api.nodes.IRevealer;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import cpw.mods.fml.common.Optional;

@Optional.InterfaceList({
		@Optional.Interface(modid = "Thaumcraft", iface = "thaumcraft.api.IGoggles", striprefs = true),
		@Optional.Interface(modid = "Thaumcraft", iface = "thaumcraft.api.nodes.IRevealer", striprefs = true)
})
public class ItemShiftedHelmet extends ItemShiftedArmor implements IGoggles,
		IRevealer{

	public ItemShiftedHelmet(){
		this(LibItemNames.SHIFTED_HELMET);
	}

	public ItemShiftedHelmet(String name){
		super(0, name);
	}

	@Override
	public boolean showNodes(ItemStack itemstack, EntityLivingBase player) {
		if(super.getCore(itemstack, player.worldObj) != null && super.getCore(itemstack, player.worldObj).getCurrentMana() >= 100){
			super.getCore(itemstack, player.worldObj).recieveMana(-100);
			return ItemNBTHelper.getBoolean(itemstack, TAG_UPGRADE_BASE + "RevealingUpgrade", false) && super.getCore(itemstack, player.worldObj) != null;
		}
		return false;
	}

	@Override
	public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player) {
		if(super.getCore(itemstack, player.worldObj) != null && super.getCore(itemstack, player.worldObj).getCurrentMana() >= 100){
			super.getCore(itemstack, player.worldObj).recieveMana(-100);
			return ItemNBTHelper.getBoolean(itemstack, TAG_UPGRADE_BASE + "RevealingUpgrade", false) && super.getCore(itemstack, player.worldObj) != null;
		}
		return false;
	}

}