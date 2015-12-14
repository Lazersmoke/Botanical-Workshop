package lazersmoke.botanicalworkshop.common.item.shifted;

import lazersmoke.botanicalworkshop.api.shifted.IShiftedArmorUpgrade;
import lazersmoke.botanicalworkshop.common.item.ItemMod;
import lazersmoke.botanicalworkshop.common.item.equipment.armor.shifted.ItemShiftedArmor;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemShiftedHopsUpgrade extends ItemMod implements IShiftedArmorUpgrade{

	public ItemShiftedHopsUpgrade(){
		super(LibItemNames.HOPS_UPGRADE);
	}

	@Override
	public String getKey(){
		return "HopsUpgrade";
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack){
		if(ItemShiftedArmor.getCore(stack, world) != null && ItemShiftedArmor.getCore(stack, world).getCurrentMana() >= 1000){
			player.addPotionEffect(new PotionEffect(Potion.jump.id, 10, 4, true));// true sets ambient; no or reduced particles
			ItemShiftedArmor.getCore(stack, world).recieveMana(-1000);
		}
	}

	@Override
	public String getDisplayName(){
		return "Shifted Hops Upgrade";
	}
}