package lazersmoke.botanicalworkshop.common.item.shifted;

import lazersmoke.botanicalworkshop.api.shifted.IShiftedArmorUpgrade;
import lazersmoke.botanicalworkshop.common.item.ItemMod;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemShiftedRevealingUpgrade extends ItemMod implements
        IShiftedArmorUpgrade{

	public ItemShiftedRevealingUpgrade(){
		super(LibItemNames.REVEALING_UPGRADE);
	}

	@Override
	public String getKey(){
		return "RevealingUpgrade";
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack){
		// Mana handled by native Shifted Helmet
	}

	@Override
	public String getDisplayName(){
		return "Thaumic Revealing Upgrade";
	}
}