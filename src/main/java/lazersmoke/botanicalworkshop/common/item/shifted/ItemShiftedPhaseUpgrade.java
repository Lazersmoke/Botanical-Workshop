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

public class ItemShiftedPhaseUpgrade extends ItemMod implements IShiftedArmorUpgrade{

	public ItemShiftedPhaseUpgrade(){
		super(LibItemNames.PHASE_UPGRADE);
	}

	@Override
	public String getKey(){
		return "PhaseUpgrade";
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack){
		if(ItemShiftedArmor.getCore(stack, world) != null && ItemShiftedArmor.getCore(stack, world).getCurrentMana() >= LibConfigs.SHIFTED_PHASE_COST && player.isSneaking()){
			player.addPotionEffect(new PotionEffect(Potion.invisibility.id, 10, 1, true));// true sets ambient; no or reduced particles
			ItemShiftedArmor.getCore(stack, world).recieveMana(-LibConfigs.SHIFTED_PHASE_COST);
		}
		((ItemShiftedArmor) stack.getItem()).setPhantomInk(stack, true);
	}

	@Override
	public String getDisplayName(){
		return "Shifted Phase Upgrade";
	}
}
