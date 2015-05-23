package lazersmoke.botanicalworkshop.common.item.catalyst;

import lazersmoke.botanicalworkshop.api.mana.IGatewayCatalyst;
import lazersmoke.botanicalworkshop.common.item.ItemMod;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCatalyst extends ItemMod implements IGatewayCatalyst{
	
	private static final int maxStackSize = 1;
	
	public ItemCatalyst(String name) {
		super(name);
	}

	@Override
	public int getItemStackLimit(ItemStack stack){
		return maxStackSize;
	}
	
	@Override
	public int getEntityLifespan(ItemStack itemStack, World world) {
		return Integer.MAX_VALUE;
	}
}