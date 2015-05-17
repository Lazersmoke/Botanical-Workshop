package lazersmoke.botanicalworkshop.common.item;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import lazersmoke.botanicalworkshop.api.mana.ISuperGatewayCatalyst;
import vazkii.botania.common.item.ItemMod;

public class ModCatalyst extends ItemMod implements ISuperGatewayCatalyst{
	@Override
	public int getEntityLifespan(ItemStack itemStack, World world) {
		return Integer.MAX_VALUE;
	}
}