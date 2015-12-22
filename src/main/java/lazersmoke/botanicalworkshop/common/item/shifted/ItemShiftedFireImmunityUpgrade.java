package lazersmoke.botanicalworkshop.common.item.shifted;

import lazersmoke.botanicalworkshop.api.shifted.IShiftedArmorUpgrade;
import lazersmoke.botanicalworkshop.common.item.ItemMod;
import lazersmoke.botanicalworkshop.common.item.equipment.armor.shifted.ItemShiftedArmor;
import lazersmoke.botanicalworkshop.common.lib.LibConfigs;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemShiftedFireImmunityUpgrade extends ItemMod implements IShiftedArmorUpgrade{

	public ItemShiftedFireImmunityUpgrade(){
		super(LibItemNames.FIREPROOF_UPGRADE);
	}

	@Override
	public String getKey(){
		return "FireProofUpgrade";
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack){
		if(ItemShiftedArmor.getCore(stack, world) != null && ItemShiftedArmor.getCore(stack, world).getCurrentMana() >= LibConfigs.SHIFTED_FIRE_IMMUNITY_COST && player.isBurning()){
			player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 10, 1, true));// true sets ambient; no or reduced particles
			ItemShiftedArmor.getCore(stack, world).recieveMana(-LibConfigs.SHIFTED_FIRE_IMMUNITY_COST);
		}
	}

	@Override
	public String getDisplayName(){
		return "Shifted Fire Immunity Upgrade";
	}
}