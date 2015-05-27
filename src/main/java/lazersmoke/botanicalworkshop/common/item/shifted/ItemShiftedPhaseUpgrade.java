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

public class ItemShiftedPhaseUpgrade extends ItemMod implements IShiftedArmorUpgrade{
	public ItemShiftedPhaseUpgrade() {
		super(LibItemNames.PHASE_UPGRADE);
	}

	@Override
	public String getKey() {
		return "PhaseUpgrade";
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack){
		if(ItemShiftedArmor.getCore(stack, world).addMana(-1000))
			player.addPotionEffect(new PotionEffect(Potion.invisibility.id, 10, 1));
	}
}
