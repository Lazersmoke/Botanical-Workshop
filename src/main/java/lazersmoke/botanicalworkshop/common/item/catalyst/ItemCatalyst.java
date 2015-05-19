package lazersmoke.botanicalworkshop.common.item.catalyst;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import lazersmoke.botanicalworkshop.api.mana.IGatewayCatalyst;
import lazersmoke.botanicalworkshop.common.item.ItemMod;

public class ItemCatalyst extends ItemMod implements IGatewayCatalyst{
	@Override
	public int getEntityLifespan(ItemStack itemStack, World world) {
		return Integer.MAX_VALUE;
	}
}